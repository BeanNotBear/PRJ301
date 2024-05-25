/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.client;

import controller.admin.AdminCategoryServlet;
import dal.CategoryDAO;
import dal.ItemDAO;
import dal.SizeDAO;
import dto.CategoryDTO;
import dto.SizeDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;
import model.Size;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nghin
 */
public class CategoryServlet extends HttpServlet {

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
        // ( 1, 2, )
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuilder cateIdCondition = new StringBuilder();
        StringBuilder sizeIdCondition = new StringBuilder();
        StringBuilder priceCondition = new StringBuilder();
        String sortCondition = "";
        String display = "TOP 6";
        SizeDAO sizeDAO = new SizeDAO();
        ItemDAO itemDAO = new ItemDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        List<SizeDTO> sizes = sizeDAO.getAllSizeDTO();
        List<Item> items = new ArrayList<>();
        List<CategoryDTO> categoryDTOs = categoryDAO.getAllCategoryDTO();
        String action = request.getParameter("action");
        String[] categoryIds = request.getParameterValues("categoryIds[]");
        String[] sizeIds = request.getParameterValues("sizeIds[]");
        String lowerPrice = request.getParameter("lowerPrice");
        String upperPrice = request.getParameter("upperPrice");
        String sortType = request.getParameter("sortType");
        String lower_range = request.getParameter("lower_range");
        String upper_range = request.getParameter("upper_range");
        String rangeCondition = "";
        long lower = 0;
        long upper = 9000000;

        request.setAttribute("sizes", sizes);
        request.setAttribute("categoryDTOs", categoryDTOs);
        if (action == null || action.equals("")) {
            items = itemDAO.getAll();
            request.setAttribute("items", items);
            request.getRequestDispatcher("client/category.jsp").forward(request, response);
        } else if (action.equals("filter")) {
            items.clear();
            if (categoryIds != null) {
                cateIdCondition.append("(");
                int length = categoryIds.length;
                for (int i = 0; i < length; i++) {
                    if (i == length - 1) {
                        cateIdCondition.append(categoryIds[i]);
                        cateIdCondition.append(")");
                        break;
                    }
                    if (i < length - 1) {
                        cateIdCondition.append(categoryIds[i]);
                        cateIdCondition.append(",");
                    }
                }
                if (cateIdCondition.length() < 3) {
                    cateIdCondition = new StringBuilder("");
                }
            }
            if (sizeIds != null) {
                sizeIdCondition.append("(");
                int length = sizeIds.length;
                for (int i = 0; i < sizeIds.length; i++) {
                    if (i == length - 1) {
                        sizeIdCondition.append(sizeIds[i]);
                        sizeIdCondition.append(")");
                        break;
                    }
                    if (i < length - 1) {
                        sizeIdCondition.append(sizeIds[i]);
                        sizeIdCondition.append(",");
                    }
                }
                if (sizeIdCondition.length() < 3) {
                    sizeIdCondition = new StringBuilder("");
                }
            }
            if (lowerPrice != null && upperPrice != null) {
                try {
                    lower = Long.parseLong(lowerPrice);
                    upper = Long.parseLong(upperPrice);
                } catch (Exception e) {
                }
            }
            if (sortType != null) {
                if (sortType.equals("price asc")) {
                    sortCondition = "ORDER BY p.listedPrice ASC";
                } else if (sortType.equals("price desc")) {
                    sortCondition = "ORDER BY p.listedPrice DESC";
                } else if (sortType.equals("cancel sort")) {
                    sortCondition = "";
                }
            }
            if (lower_range != null && upper_range != null) {
                if (sortCondition.equals("")) {
                    rangeCondition = "ORDER BY ID\n"
                            + "	OFFSET " + lower_range + " ROWS FETCH NEXT " + upper_range + " ROWS ONLY";
                } else {
                    rangeCondition = "	OFFSET " + lower_range + " ROWS FETCH NEXT " + upper_range + " ROWS ONLY";
                }

            } else {
                if (sortCondition.equals("")) {
                    rangeCondition = "ORDER BY ID\n"
                            + "	OFFSET 0 ROWS FETCH NEXT 6 ROWS ONLY";
                } else {
                    rangeCondition = "	OFFSET 0 ROWS FETCH NEXT 6 ROWS ONLY";
                }
            }
            items = itemDAO.getItemByFilter(cateIdCondition.toString(),
                    sizeIdCondition.toString(), lower, upper, sortCondition, rangeCondition);
            for (Item item : items) {
                out.println("<div class=\"col-lg-4 col-md-6\">\n"
                        + "                                    <div class=\"single-product\">\n"
                        + "                                        <img class=\"img-fluid\" src=\"" + item.getProduct().getImg1() + "\" alt=\"" + item.getProduct().getName() + "\">\n"
                        + "                                        <div class=\"product-details\">\n"
                        + "                                            <h6>" + item.getProduct().getName() + "</h6>\n"
                        + "                                            <div class=\"price\">\n"
                        + "                                                <h6>" + item.getProduct().getListedPrice() + " VND</h6>\n"
                        + "                                            </div>\n"
                        + "                                            <div class=\"prd-bottom\">\n"
                        + "                                                <a onclick=\"\" class=\"social-info\">\n"
                        + "                                                    <span class=\"lnr lnr-heart\"></span>\n"
                        + "                                                    <p class=\"hover-text\">Wishlist</p>\n"
                        + "                                                </a>\n"
                        + "                                                <a onclick=\"\" class=\"social-info\">\n"
                        + "                                                    <span class=\"lnr lnr-sync\"></span>\n"
                        + "                                                    <p class=\"hover-text\">compare</p>\n"
                        + "                                                </a>\n"
                        + "                                                <a href=\"" + request.getContextPath() + "/product-details?id=" + item.getProduct().getId() + "\" class=\"social-info\">\n"
                        + "                                                    <span class=\"lnr lnr-move\"></span>\n"
                        + "                                                    <p class=\"hover-text\">view more</p>\n"
                        + "                                                </a>\n"
                        + "                                            </div>\n"
                        + "                                        </div>\n"
                        + "                                    </div>\n"
                        + "                                </div>");
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
