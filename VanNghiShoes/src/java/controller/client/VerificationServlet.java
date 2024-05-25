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
import untils.Captcha;
import untils.Email;

/**
 *
 * @author nghin
 */
public class VerificationServlet extends HttpServlet {

    private String code = null;
    private String email = null;

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
        code = Captcha.generateCaptcha();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        email = (String)session.getAttribute("email");
        boolean isSuccess = false;
        AccountDAO accountDAO = new AccountDAO();
        if (account == null) {
            isSuccess = Email.sendEmail(email, "Verification", "Your code: " + code);
        } else {
            isSuccess = Email.sendEmail(account.getEmail(), "Verification", "Your code: " + code);
        }
        System.out.println("Mail: " + isSuccess);
        request.getRequestDispatcher("client/verification.jsp").forward(request, response);
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
        String code = request.getParameter("code");
        if (code.equals(this.code)) {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            if (account == null) {
                response.sendRedirect("changePassword");
            } else {
                AccountDAO accountDAO = new AccountDAO();
                accountDAO.updateStatusById(account.getID());
                response.sendRedirect("home");
            }
        } else {
            request.setAttribute("codeIncorrect", "The code incorrect!");
            request.getRequestDispatcher("client/verification.jsp").forward(request, response);
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
