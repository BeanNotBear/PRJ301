///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller.admin;
//
//import dal.CategoryDAO;
//import dal.DiscountDAO;
//import dal.ProductDAO;
//import dal.ProductImagesDAO;
//import dal.ProductSizeDAO;
//import dal.SizeDAO;
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.util.List;
//import model.Account;
//import model.Category;
//import model.Discount;
//import model.Product;
//import model.ProductImages;
//import model.ProductSize;
//import model.Size;
//
///**
// *
// * @author nghin
// */
//public class AdminAddNewProductServlet extends HttpServlet {
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Account account = (Account) session.getAttribute("account");
//        CategoryDAO categoryDAO = new CategoryDAO();
//        SizeDAO sizeDAO = new SizeDAO();
//        DiscountDAO discountDAO = new DiscountDAO();
//
//        List<Category> categories = categoryDAO.getAll();
//        List<Size> sizes = sizeDAO.getAll();
//        List<Discount> discounts = discountDAO.getAll();
//
//        if (account != null) {
//            String role = account.getRole().toString();
//            if (role.equals("admin") || role.equals("supper-admin")) {
//                request.setAttribute("categories", categories);
//                request.setAttribute("sizes", sizes);
//                request.setAttribute("discounts", discounts);
//                request.getRequestDispatcher("add_new_product.jsp").forward(request, response);
//            } else {
//                response.sendRedirect("login");
//            }
//        } else {
//            response.sendRedirect("login");
//        }
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String name = null;
//        int categoryId = 0;
//        long price = 0;
//        int sizeId = 0;
//        int quantity = 0;
//        int productDiscountId = 0;
//        String description = null;
//        String[] images = null;
//        Product product = null;
//        ProductSize productSize = null;
//        ProductImages productImages = null;
//        ProductDAO productDAO = new ProductDAO();
//        ProductSizeDAO productSizeDAO = new ProductSizeDAO();
//        ProductImagesDAO productImagesDAO = new ProductImagesDAO();
//
//        try {
//            name = request.getParameter("productName");
//            categoryId = Integer.parseInt(request.getParameter("productCategoryID"));
//            price = Long.parseLong(request.getParameter("productPrice"));
//            sizeId = Integer.parseInt(request.getParameter("productSize"));
//            quantity = Integer.parseInt(request.getParameter("quantity"));
//            productDiscountId = Integer.parseInt(request.getParameter("productDiscount"));
//            description = request.getParameter("productDescription");
//            images = new String[6];
//            for (int i = 0; i < 6; i++) {
//                images[i] = request.getParameter("image" + (i + 1));
//            }
//
//            product = new Product(0,
//                    name,
//                    price,
//                    description,
//                    categoryId,
//                    productDiscountId);
//
//            // product id
//            productSize = new ProductSize(2,
//                    sizeId,
//                    quantity);
//
//            productImages = new ProductImages(0,
//                    images[0],
//                    images[1],
//                    images[2],
//                    images[3],
//                    images[4],
//                    images[5]);
//
//            productDAO.insert(product);
//            productSizeDAO.insert(productSize);
//            productImagesDAO.insert(productImages);
//
//        } catch (Exception e) {
//            request.setAttribute("addProductError", "Somethings wrong!");
//        }
//
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
