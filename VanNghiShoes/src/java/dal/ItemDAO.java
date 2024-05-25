/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import dto.ProductSizeDTO;
import java.util.ArrayList;
import java.util.List;
import model.Item;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Product;

/**
 *
 * @author nghin
 */
public class ItemDAO extends DBContext {

    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        Item item = null;
        Product product = null;
        Category category = null;
        ProductSizeDAO productSizeDAO = new ProductSizeDAO();
        List<ProductSizeDTO> productSizeDTOs = new ArrayList<>();
        String query = "SELECT	P.ID,\n"
                + "		P.[Name],\n"
                + "		P.Price,\n"
                + "		P.listedPrice,\n"
                + "		P.DiscountID,\n"
                + "		C.CateID,\n"
                + "		C.CateName,\n"
                + "		P.[Description],\n"
                + "		P.Img1,\n"
                + "		P.Img2,\n"
                + "		P.Img3,\n"
                + "		P.Img4,\n"
                + "		P.Img5,\n"
                + "		P.Img6,\n"
                + "		P.CreatedDate\n"
                + "FROM [dbo].[Products] AS P\n"
                + "INNER JOIN [dbo].[Categories] AS C ON P.CateID = C.CateID\n"
                + "WHERE [ID] IN (\n"
                + "	SELECT [ProductID]\n"
                + "	FROM [ShoesShopDB].[dbo].[ProductSize]\n"
                + "	GROUP BY [ProductID]\n"
                + ");";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getString(8),
                        rs.getInt(6),
                        rs.getInt(5),
                        rs.getDate(15),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14));
                category = new Category(rs.getInt(6),
                        rs.getString(7));
                productSizeDTOs = productSizeDAO.getProductSizeDTOByProductId(rs.getInt(1));
                item = new Item(product, category, productSizeDTOs);
                items.add(item);
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public Item getItemByProductId(int id) {
        Item item = null;
        Product product = null;
        Category category = null;
        ProductSizeDAO productSizeDAO = new ProductSizeDAO();
        List<ProductSizeDTO> productSizeDTOs = new ArrayList<>();
        String query = "SELECT	P.ID,\n"
                + "		P.[Name],\n"
                + "		P.Price,\n"
                + "		P.listedPrice,\n"
                + "		P.DiscountID,\n"
                + "		C.CateID,\n"
                + "		C.CateName,\n"
                + "		P.[Description],\n"
                + "		P.Img1,\n"
                + "		P.Img2,\n"
                + "		P.Img3,\n"
                + "		P.Img4,\n"
                + "		P.Img5,\n"
                + "		P.Img6,\n"
                + "		P.CreatedDate\n"
                + "FROM [dbo].[Products] AS P\n"
                + "INNER JOIN [dbo].[Categories] AS C ON P.CateID = C.CateID\n"
                + "WHERE [ID] IN (\n"
                + "	SELECT [ProductID]\n"
                + "	FROM [ShoesShopDB].[dbo].[ProductSize]\n"
                + "	GROUP BY [ProductID]\n"
                + ") AND [ID] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getString(8),
                        rs.getInt(6),
                        rs.getInt(5),
                        rs.getDate(15),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14));
                category = new Category(rs.getInt(6),
                        rs.getString(7));
                productSizeDTOs = productSizeDAO.getProductSizeDTOByProductId(rs.getInt(1));
                item = new Item(product, category, productSizeDTOs);
                return item;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    public List<Item> getItemByFilter(String cateCondition, String sizeCondition, long lower, long upper, String sortCondition, String pageCondition) {
        List<Item> items = new ArrayList<>();
        Item item = null;
        Product product = null;
        Category category = null;
        ProductSizeDAO productSizeDAO = new ProductSizeDAO();
        List<ProductSizeDTO> productSizeDTOs = new ArrayList<>();
        String temp = "";
        if (!sizeCondition.isEmpty()) {
            temp = "WHERE SizeID IN " + sizeCondition;
        }
        String query = "SELECT\n"
                + "    P.ID,\n"
                + "    P.[Name],\n"
                + "    P.Price,\n"
                + "    P.listedPrice,\n"
                + "    P.DiscountID,\n"
                + "    C.CateID,\n"
                + "    C.CateName,\n"
                + "    P.[Description],\n"
                + "    P.Img1,\n"
                + "    P.Img2,\n"
                + "    P.Img3,\n"
                + "    P.Img4,\n"
                + "    P.Img5,\n"
                + "    P.Img6,\n"
                + "    P.CreatedDate\n"
                + "FROM \n"
                + "    [dbo].[Products] AS P\n"
                + "INNER JOIN \n"
                + "    [dbo].[Categories] AS C ON P.CateID = C.CateID\n"
                + "WHERE \n"
                + "    P.[ID] IN (SELECT [ProductID]\n"
                + "               FROM [ShoesShopDB].[dbo].[ProductSize]\n"
                + temp
                + "               GROUP BY [ProductID]\n"
                + "              )";
        if (lower != upper) {
            String priceCondition = "AND (listedPrice >=" + lower + " AND  listedPrice <=" + upper + ")";
            query += priceCondition;
        }
        if (!cateCondition.isEmpty()) {
            query += "AND C.CateID IN " + cateCondition;
        }
        if (!sortCondition.isEmpty()) {
            query += sortCondition;
        }
        if(!pageCondition.isEmpty()) {
            query += pageCondition;
        }
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getString(8),
                        rs.getInt(6),
                        rs.getInt(5),
                        rs.getDate(15),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14));
                category = new Category(rs.getInt(6),
                        rs.getString(7));
                productSizeDTOs = productSizeDAO.getProductSizeDTOByProductId(rs.getInt(1));
                item = new Item(product, category, productSizeDTOs);
                items.add(item);
            }
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        ItemDAO d = new ItemDAO();
//        List<Item> items = d.getAll();
//        for (Item item : items) {
//            System.out.println(item.getProduct().getName());
//            for (ProductSizeDTO p : item.getProductSizeDTOs()) {
//                System.out.println(p.getSize());
//            }
//            System.out.println("--------");
//        }
//        System.out.println(d.getItemByProductId(1).getProduct().getName());
//        for (ProductSizeDTO o : d.getItemByProductId(1).getProductSizeDTOs()) {
//            System.out.println(o.getSize());
//        }
//        System.out.println("-----------");
    }

}
