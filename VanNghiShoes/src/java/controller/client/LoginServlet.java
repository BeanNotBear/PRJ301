/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.client;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import sercurity.EncryptSHA256;

/**
 *
 * @author nghin
 */
public class LoginServlet extends HttpServlet {

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
            if (account.getRole().toString().equals("customer")) {
                if (account.getStatus() == 0) {
                    response.sendRedirect("verification");
                    return;
                } else {
                    response.sendRedirect("home");
                    return;
                }
            }
        }
        request.getRequestDispatcher("client/login.jsp").forward(request, response);
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

        // get email from form.
        String email = request.getParameter("email");

        // get password from form.
        String password = request.getParameter("password");

        // check remember log in.
        boolean rememberMe = request.getParameter("remeberMe") != null;

        // encrypt password with SHA256 alorgithm.
        password = EncryptSHA256.toHexString(password);

        // declare account.
        Account account = null;

        // declare accountDAO.
        AccountDAO accountDAO = new AccountDAO();

        // get account by email and password.
        account = accountDAO.getAccountByEmailAndPassword(email, password);

        // check account has been existed.
        if (account != null) {
            if (account.getRole().toString().equals("customer")) {
                // create cookie of account token.
                Cookie cookie = new Cookie("token", account.getToken());

                // check remember log in.
                if (rememberMe) {

                    // set cookie live in 30 days.
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                }

                // add token into application.
                response.addCookie(cookie);

                // declare session.
                HttpSession session = request.getSession();

                // set account from session.
                session.setAttribute("account", account);

                // check account has been vefify.
                if (account.getStatus() == 0) {
                    response.sendRedirect("verification");
                    return;
                } else {
                    response.sendRedirect("home");
                    return;
                }
            }
        }
        request.setAttribute("loginFail", "Email or password are not correct!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
