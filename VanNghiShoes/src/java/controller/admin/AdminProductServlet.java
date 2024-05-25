/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CategoryDAO;
import dal.DiscountDAO;
import dal.ProductDAO;
import dal.ProductSizeDAO;
import dal.SizeDAO;
import dto.ProductDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import model.Account;
import model.Category;
import model.Discount;
import model.Product;
import model.ProductSize;
import model.Size;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nghin
 */
public class AdminProductServlet extends HttpServlet {

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
        CategoryDAO categoryDAO = new CategoryDAO();
        SizeDAO sizeDAO = new SizeDAO();
        DiscountDAO discountDAO = new DiscountDAO();
        ProductDAO productDAO = new ProductDAO();

        List<Category> categories = categoryDAO.getAll();
        List<Size> sizes = sizeDAO.getAll();
        List<Discount> discounts = discountDAO.getAll();
        List<Product> products = productDAO.getAll();
        List<ProductDTO> productDTOs = productDAO.getAllProductDTO();

        if (account != null) {
            String role = account.getRole().toString();
            if (role.equals("admin") || role.equals("super-admin")) {
                request.setAttribute("categories", categories);
                request.setAttribute("sizes", sizes);
                request.setAttribute("discounts", discounts);
                request.setAttribute("products", products);
                request.setAttribute("productDTOs", productDTOs);
                request.getRequestDispatcher("product_admin.jsp").forward(request, response);
            } else {
                response.sendRedirect("login");
            }
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
        String action = request.getParameter("action");
        int rowAffected = 0;
        String name = null;
        int id = 0;
        int categoryId = 0;
        long price = 0;
        long listedPrice = 0;
        int productDiscountId = 0;
        String description = null;
        String[] images = null;
        int sizeId = 0;
        int quantity = 0;
        Product product = null;
        ProductDAO productDAO = new ProductDAO();
        ProductSize productSize = null;
        ProductSizeDAO productSizeDAO = new ProductSizeDAO();
        JSONObject jsonResponse = new JSONObject();

        if (action.equals("add")) {
            try {
                name = request.getParameter("productName");
                categoryId = Integer.parseInt(request.getParameter("productCategoryID"));
                price = Long.parseLong(request.getParameter("productPrice"));
                listedPrice = (long)(price *  1.35);
                productDiscountId = Integer.parseInt(request.getParameter("productDiscount"));
                description = request.getParameter("productDescription");
                images = new String[6];
                for (int i = 0; i < 6; i++) {
                    images[i] = request.getParameter("image" + (i + 1));
                }
                Date date = new Date(290803);
                product = new Product(0,
                        name,
                        price,
                        listedPrice,
                        description,
                        categoryId,
                        productDiscountId,
                        date,
                        images[0],
                        images[1],
                        images[2],
                        images[3],
                        images[4],
                        images[5]);

                rowAffected = productDAO.insert(product);

            } catch (Exception e) {
//                request.setAttribute("addProductError", "Somethings wrong!");
            }

            try {
                if (rowAffected == 0) {
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Add product fail");
                } else {
                    jsonResponse.put("status", "success");
                    jsonResponse.put("message", "Add product successfully");
                }
            } catch (JSONException ex) {
                Logger.getLogger(AdminCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (action.equals("add-product-size")) {
            try {
                id = Integer.parseInt(request.getParameter("productIdSize"));
                sizeId = Integer.parseInt(request.getParameter("sizeIdSize"));
                quantity = Integer.parseInt(request.getParameter("productQuantitySize"));
                productSize = new ProductSize(id, sizeId, quantity);
                rowAffected = productSizeDAO.insert(productSize);
            } catch (Exception e) {
                rowAffected = 0;
            }
            try {
                if (rowAffected == 0) {
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Add product size fail");
                } else {
                    jsonResponse.put("status", "success");
                    jsonResponse.put("message", "Add product size successfully");
                }
            } catch (JSONException ex) {
                Logger.getLogger(AdminCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (action.equals("edit")) {
            try {
                id = Integer.parseInt(request.getParameter("id"));
                name = request.getParameter("name");
                categoryId = Integer.parseInt(request.getParameter("cateId"));
                price = Long.parseLong(request.getParameter("price"));
                listedPrice = (long)(price*1.35);
                quantity = Integer.parseInt(request.getParameter("quantity"));
                productDiscountId = Integer.parseInt(request.getParameter("discountId"));
                description = request.getParameter("description");
                sizeId = Integer.parseInt(request.getParameter("sizeId"));
                images = new String[6];
                
                for (int i = 0; i < 6; i++) {
                    images[i] = request.getParameter("img" + (i + 1));
                }
                Date temp = new Date(29082003);

                product = new Product(id,
                        name,
                        price,
                        listedPrice,
                        description,
                        categoryId,
                        productDiscountId,
                        temp,
                        images[0],
                        images[1],
                        images[2],
                        images[3],
                        images[4],
                        images[5]);
                productSize = new ProductSize(id, sizeId, quantity);
                rowAffected = productDAO.update(product);
                if (rowAffected == 0) {
                    throw new Exception();
                }
                rowAffected = productSizeDAO.updateQuantity(productSize);
                if (rowAffected == 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                rowAffected = 0;
            }
            try {
                if (rowAffected == 0) {
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Add product size fail");
                } else {
                    jsonResponse.put("status", "success");
                    jsonResponse.put("message", "Add product size successfully");
                }
            } catch (JSONException ex) {
                Logger.getLogger(AdminCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } else if(action.equals("delete")) {
            try {
                id = Integer.parseInt(request.getParameter("id"));
                sizeId = Integer.parseInt(request.getParameter("sizeId"));
                rowAffected = productSizeDAO.delete(id, sizeId);
            } catch (Exception e) {
            }
            try {
                if (rowAffected == 0) {
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Delete product size fail");
                } else {
                    jsonResponse.put("status", "success");
                    jsonResponse.put("message", "Delete product size successfully");
                }
            } catch (JSONException ex) {
                Logger.getLogger(AdminCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
            response.setStatus(HttpServletResponse.SC_OK);
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
