/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author nghin
 */
public class AdminLoginServlet extends HttpServlet {

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
        if (account != null) {
            String role = account.getRole().toString();
            if (role.equals("admin")
                    || role.equals("supper-admin")) {
                response.sendRedirect("dashboard");
            } else {
                request.getRequestDispatcher("login_admin.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("login_admin.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = sercurity.EncryptSHA256.toHexString(password);
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByEmailAndPassword(username, password);
        if (account != null) {
            String role = account.getRole().toString();
            if (role.equals("admin")
                    || role.equals("super-admin")) {
                session.setAttribute("account", account);
                response.sendRedirect("dashboard");
            } else {
                request.setAttribute("login_fail", "Access denial");
                request.getRequestDispatcher("login_admin.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("login_fail", "The user name or password are incorrect.");
            request.getRequestDispatcher("login_admin.jsp").forward(request, response);
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
