/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.client;

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
public class ChangePasswordServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");
        if(account != null) {
            
            // forward to change password.
            request.getRequestDispatcher("client/changePassword.jsp").forward(request, response);
        } else {
            
            // forward to change password after forget password.
            request.getRequestDispatcher("client/changePasswordForget.jsp").forward(request, response);
        }
        
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
        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        String oldPassword = null;
        password = sercurity.EncryptSHA256.toHexString(password);
        AccountDAO accountDAO = new AccountDAO();
        int rowAffected = 0;
        String email = null;
        
        Account account = (Account)session.getAttribute("account");
        if(account != null) {
            email = account.getEmail();
            oldPassword = request.getParameter("oldPassword");
            oldPassword = sercurity.EncryptSHA256.toHexString(oldPassword);
            
            // old password is correct.
            if(oldPassword.equals(account.getPassword())) {
                rowAffected = accountDAO.updatePassword(email, password);
                response.sendRedirect("logout");
            } else {
                // old password is wrong.
                request.setAttribute("OLD_PASS_WR","Old password is incorrect!" );
                request.getRequestDispatcher("client/changePassword.jsp").forward(request, response);
            }
        } else {
            email = (String)session.getAttribute("email");
            rowAffected = accountDAO.updatePassword(email, password);
            System.out.println(rowAffected);
            response.sendRedirect("login");
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
