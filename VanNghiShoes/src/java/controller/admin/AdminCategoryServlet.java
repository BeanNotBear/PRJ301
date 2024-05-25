/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Category;
import org.json.*;

/**
 *
 * @author nghin
 */
public class AdminCategoryServlet extends HttpServlet {

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
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAll();
        if (account != null) {
            String role = account.getRole().toString();
            if (role.equals("admin")
                    || role.equals("super-admin")) {
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("category_admin.jsp").forward(request, response);
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
        response.setContentType("text/html; charset=UTF-8");
        String name = request.getParameter("name");
        if (action.equals("edit")) {
            int rowAffected = 0;

            try {
                CategoryDAO categoryDAO = new CategoryDAO();
                int id = Integer.parseInt(request.getParameter("id"));
                Category category = new Category(id, name);
                rowAffected = categoryDAO.update(category);
            } catch (NumberFormatException ex) {
            }

            JSONObject jsonResponse = new JSONObject();
            try {
                if (rowAffected == 0) {
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Update category fail");
                } else {
                    jsonResponse.put("status", "success");
                    jsonResponse.put("message", "Update category successfully");
                }
            } catch (JSONException ex) {
                Logger.getLogger(AdminCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } else if(action.equals("delete")) {
            int rowAffected = 0;

            try {
                CategoryDAO categoryDAO = new CategoryDAO();
                int id = Integer.parseInt(request.getParameter("id"));
                Category category = new Category(id, name);
                rowAffected = categoryDAO.delete(category);
            } catch (NumberFormatException ex) {
            }

            JSONObject jsonResponse = new JSONObject();
            try {
                if (rowAffected == 0) {
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Delete category fail");
                } else {
                    jsonResponse.put("status", "success");
                    jsonResponse.put("message", "Delete category successfully");
                }
            } catch (JSONException ex) {
                Logger.getLogger(AdminCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } else if(action.equals("add")) {
            int rowAffected = 0;

            try {
                CategoryDAO categoryDAO = new CategoryDAO();
                Category category = new Category(0, request.getParameter("categoryName"));
                rowAffected = categoryDAO.insert(category);
            } catch (NumberFormatException ex) {
            }

            JSONObject jsonResponse = new JSONObject();
            try {
                if (rowAffected == 0) {
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Add fail");
                } else {
                    jsonResponse.put("status", "success");
                    jsonResponse.put("message", "Add successfully");
                }
            } catch (JSONException ex) {
                Logger.getLogger(AdminCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(404);
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
