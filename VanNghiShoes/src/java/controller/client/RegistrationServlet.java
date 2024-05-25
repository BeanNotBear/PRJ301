/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.client;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.detail.AccountRole;

/**
 *
 * @author nghin
 */
public class RegistrationServlet extends HttpServlet { 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("client/registration.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");
        String phoneNumber = request.getParameter("phone");
        String password = request.getParameter("password");
        password = sercurity.EncryptSHA256.toHexString(password);
        
        AccountDAO accountDAO = new AccountDAO();
        
        // if email does not exist.
        if(accountDAO.checkAccountByEmail(email)) {
            String token = sercurity.EncryptSHA256.toHexString(email + fullName + phoneNumber);
            Account accountTemp = new Account(0,
                    email, 
                    password, 
                    fullName, 
                    phoneNumber, 
                    0, 
                    AccountRole.CUSTOMER, 
                    token);
            accountDAO.insert(accountTemp);
            Account account = accountDAO.getAccountByToken(token);
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            response.sendRedirect("verification");
        } else {
            request.setAttribute("emailFail", "The email has been used!");
            request.setAttribute("fullName", fullName);
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("password", password);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
