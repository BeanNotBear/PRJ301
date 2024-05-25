/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.client;

import com.vnpay.common.Config;
import dal.ItemDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductSizeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import model.Order;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import model.Account;
import model.Cart;
import model.OrderDetail;
import model.OrderStatus;
import model.ProductSize;
import model.Yield;

/**
 *
 * @author nghin
 */
public class CheckOutServlet extends HttpServlet {

    private List<Yield> yields;
    private long total;
    Cart cart;
    Cookie[] cookies;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null
                && account.getRole().toString().equals("customer")
                && account.getStatus() == 1) {
            cookies = request.getCookies();
            String text = "";
            ItemDAO itemDAO = new ItemDAO();
            for (Cookie c : cookies) {
                if (c.getName().equals("cart")) {
                    text = c.getValue();
                }
            }
            cart = new Cart(text, account.getID(), itemDAO.getAll());
            yields = cart.getCart();
            total = cart.totalPrice();
            session.setAttribute("items", yields);
            session.setAttribute("total", total);
            request.getRequestDispatcher("client/checkout.jsp").forward(request, response);
        } else if (account != null && account.getStatus() != 1) {
            response.sendRedirect("verification");
        } else {
            response.sendRedirect("login");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int rowAffected = 0;
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String address = request.getParameter("address");
        String postCode = request.getParameter("zip");
        String note = request.getParameter("note");
        String methodPay = request.getParameter("payment");

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        java.util.Date currentDate = calendar.getTime();

        Date date = new Date(currentDate.getTime());
        System.out.println(date);
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        java.util.Date shipDate = calendar.getTime();
        Date shippedDate = new Date(shipDate.getTime());
        System.out.println(shippedDate);
        String fullAddress = postCode + ", " + address + ", "
                + district + ", " + city + ", " + country;
        System.out.println(fullAddress);
        Order order = new Order();
        order.setAccount(account);
        order.setAddress(address);
        order.setNote(note);
        order.setOrderDate(date);
        order.setShipDate(shippedDate);
        order.setTotalPrice(total);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(1);
        order.setStatus(orderStatus);
        session.setAttribute("order", order);
        OrderDAO orderDAO = new OrderDAO();
        session.setAttribute("payment", methodPay);
        if (methodPay.equals("COD")) {
            if (rowAffected != 0) {
                session.setAttribute("msg", "Order successfully!");
                ProductSizeDAO productSizeDAO = new ProductSizeDAO();
                for (Yield yield : yields) {
                    ProductSize productSize = productSizeDAO.getProductSize(yield.getProduct().getId(),
                            yield.getSize().getId());
                    productSize.setQuantity(productSize.getQuantity() - yield.getQuantity());
                    int row = productSizeDAO.updateQuantity(productSize);
                    System.out.println(row);
                }
                int length = yields.size();
                for (int i = 0; i < length; i++) {
                    cart.removeYield(yields.get(i).getProduct().getId(),
                            yields.get(i).getSize().getId());
                }
                Cookie[] cookies = request.getCookies();
                Cookie cookie = null;
                for (Cookie c : cookies) {
                    if (c.getName().equals("cart")) {
                        cookie = c;
                    }
                }
                String text = "";
                List<Yield> y = cart.getCart();
                String txt = "";
                for (Yield yield : y) {
                    txt += account.getID() + ":" + yield.getProduct().getId() + ":" + yield.getSize().getId() + ":" + yield.getQuantity() + "-";
                }
                System.out.println("cookie: " + txt);
                if (cookie != null) {
                    cookie.setValue("");
                    cookie.setValue(txt);
                }
                response.addCookie(cookie);
            }
            response.sendRedirect("confirmation");
            System.out.println("COD");
        } else if (methodPay.equals("VN PAY")) {
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String vnp_OrderInfo = "Van Nghi Shoes - Order Payment";
            String orderType = "other";
            String vnp_TxnRef = Config.getRandomNumber(8);
            String vnp_IpAddr = Config.getIpAddress(request);
            String vnp_TmnCode = Config.vnp_TmnCode;

            Map vnp_Params = new HashMap<>();
            int totalPrice = Integer.parseInt(request.getParameter("amount"));
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(totalPrice * 100));
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_BankCode", "");
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
            vnp_Params.put("vnp_OrderType", orderType);
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", getDomainWithPortAndContextPath(request) + Config.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());

            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();

            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);

                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }

            String queryUrl = query.toString();
            String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

            if (rowAffected != 0) {
                session.setAttribute("msg", "Order successfully!");
            }
            ProductSizeDAO productSizeDAO = new ProductSizeDAO();
            for (Yield yield : yields) {
                ProductSize productSize = productSizeDAO.getProductSize(yield.getProduct().getId(),
                        yield.getSize().getId());
                productSize.setQuantity(productSize.getQuantity() - yield.getQuantity());
                productSizeDAO.updateQuantity(productSize);
            }
            int length = yields.size();
            for (int i = 0; i < length; i++) {
                cart.removeYield(yields.get(i).getProduct().getId(),
                        yields.get(i).getSize().getId());
            }
            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;
            for (Cookie c : cookies) {
                if (c.getName().equals("cart")) {
                    cookie = c;
                }
            }
            String text = "";
            List<Yield> y = cart.getCart();
            String txt = "";
            for (Yield yield : y) {
                txt += account.getID() + ":" + yield.getProduct().getId() + ":" + yield.getSize().getId() + ":" + yield.getQuantity() + "-";
            }
            System.out.println("cookie: " + txt);
            if (cookie != null) {
                cookie.setValue("");
                cookie.setValue(txt);
            }
            response.addCookie(cookie);
            response.sendRedirect(paymentUrl);
            System.out.println("VN PAY");
        }
    }

    private static String getDomainWithPortAndContextPath(HttpServletRequest request)
            throws MalformedURLException {
        return "http://" + new URL(request.getRequestURL().toString()).getHost() + ":" + request.getServerPort() + "/VanNghiShoes";
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
