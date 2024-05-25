/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.client;

import dal.ItemDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Cart;
import model.Item;
import model.Product;
import model.Size;
import model.Yield;

/**
 *
 * @author nghin
 */
public class CartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        if (account == null || !account.getRole().toString().equals("customer")) {
            response.sendRedirect("login");
            return;
        }
        if (account.getStatus() != 1) {
            response.sendRedirect("verification");
            return;
        }
        Cookie[] cookies = request.getCookies();
        String text = "";
        Cookie cookie = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("cart")) {
                    text += c.getValue();
                    System.out.println(text);
                }
            }
            cookie = new Cookie("cart", text);
        }

        ItemDAO itemDAO = new ItemDAO();
        List<Item> items = itemDAO.getAll();
        Cart cart = new Cart(text, account.getID(), items);
        List<Yield> yields = cart.getCart();
        for (Yield yield : yields) {
            System.out.println(yield.getProduct().getName());
        }

        int quantityYieldInCart = 0;
        if (yields != null) {
            quantityYieldInCart = yields.size();
        }

        long totalPrice = cart.totalPrice();
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("sizeCart", quantityYieldInCart);
        request.setAttribute("cart", yields);

        String action = request.getParameter("action");
        if (action != null && action.equals("addYield")) {
            String productId = request.getParameter("id");
            String sizeId = request.getParameter("sizeId");
            String qtt = request.getParameter("quantity");
            int id = Integer.parseInt(productId);
            int sId = Integer.parseInt(sizeId);
            int quantity = Integer.parseInt(qtt);
            Item i = itemDAO.getItemByProductId(id);
            Product product = i.getProduct();
            Size size = new Size(sId, id);
            Yield yield = new Yield(product, quantity, size);
            cart.addYield(yield);
            String txt = "";
            for (Yield y : cart.getCart()) {
                txt += account.getID() + ":" + y.getProduct().getId() + ":" + y.getSize().getId() + ":" + y.getQuantity() + "-";
            }
            if (cookie != null) {
                cookie.setValue("");
                cookie.setValue(text + txt);
            } else {
                cookie.setValue(txt);
            }
            response.addCookie(cookie);
        }
        if (action != null && action.equals("deleteYield")) {
            String sid = request.getParameter("sizeId");
            int productId = Integer.parseInt(request.getParameter("productId"));
            int sizeId = Integer.parseInt(request.getParameter("sizeId"));
            cart.removeYield(productId, sizeId);
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
        if(action != null && action.equals("editQuantity")) {
            String pid = request.getParameter("productId");
            String sid = request.getParameter("sizeId");
            String qtt = request.getParameter("quantity");
            int productId = Integer.parseInt(pid);
            int sizeId = Integer.parseInt(sid);
            int quantity = Integer.parseInt(qtt);
            cart.getYieldByProductIdAndSizeId(productId, sizeId).setQuantity(quantity);
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
        request.getRequestDispatcher("client/cart.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (action.equals("addYield")) {

        } else if (action.equals("deleteYield")) {

        }
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
