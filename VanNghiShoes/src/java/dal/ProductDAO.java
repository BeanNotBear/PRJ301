/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import dto.ProductDTO;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Discount;
import model.ProductSize;

/**
 *
 * @author nghin
 */
public class ProductDAO extends DBContext {

    public int insert(Product product) {
        int rowAffected = 0;
        String query = "INSERT INTO [dbo].[Products]\n"
                + "           ([Name]\n"
                + "           ,[Price]\n"
                + "           ,[listedPrice]\n"
                + "           ,[Description]\n"
                + "           ,[CateID]\n"
                + "           ,[DiscountID]\n"
                + "           ,[Img1]\n"
                + "           ,[Img2]\n"
                + "           ,[Img3]\n"
                + "           ,[Img4]\n"
                + "           ,[Img5]\n"
                + "           ,[Img6])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, product.getName());
            ps.setLong(2, product.getPrice());
            ps.setLong(3, product.getListedPrice());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getCateId());
            ps.setInt(6, product.getDiscountId());
            ps.setString(7, product.getImg1());
            ps.setString(8, product.getImg2());
            ps.setString(9, product.getImg3());
            ps.setString(10, product.getImg4());
            ps.setString(11, product.getImg5());
            ps.setString(12, product.getImg6());
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowAffected;
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        Product product = null;
        String query = "SELECT [ID]\n"
                + "      ,[Name]\n"
                + "      ,[Price]\n"
                + "      ,[listedPrice]\n"
                + "      ,[Description]\n"
                + "      ,[CateID]\n"
                + "      ,[DiscountID]\n"
                + "      ,[CreatedDate]\n"
                + "      ,[Img1]\n"
                + "      ,[Img2]\n"
                + "      ,[Img3]\n"
                + "      ,[Img4]\n"
                + "      ,[Img5]\n"
                + "      ,[Img6]\n"
                + "  FROM [ShoesShopDB].[dbo].[Products]";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14));
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> getProductByCateId(int id) {
        List<Product> products = new ArrayList<>();
        Product product = null;
        String query = "SELECT [ID]\n"
                + "      ,[Name]\n"
                + "      ,[Price]\n"
                + "      ,[Description]\n"
                + "      ,[CateID]\n"
                + "      ,[DiscountID]\n"
                + "      ,[CreatedDate]\n"
                + "      ,[Img1]\n"
                + "      ,[Img2]\n"
                + "      ,[Img3]\n"
                + "      ,[Img4]\n"
                + "      ,[Img5]\n"
                + "      ,[Img6]\n"
                + "      ,[listedPrice]\n"
                + "  FROM [ShoesShopDB].[dbo].[Products]\n"
                + "  WHERE CateID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ProductDTO> getAllProductDTO() {
        List<ProductDTO> productDTOs = new ArrayList<>();
        ProductDTO productDTO = null;
        Category category = null;
        Discount discount = null;
        ProductSize productSize = null;
        String query = "SELECT	P.ID, \n"
                + "		P.[Name], \n"
                + "		P.[Description], \n"
                + "		P.Price,\n"
                + "             P.listedPrice, \n"
                + "		C.CateID, \n"
                + "		C.CateName, \n"
                + "		D.ID AS [DiscountId], \n"
                + "		D.[Name], \n"
                + "		D.[Description], \n"
                + "		D.DiscountPercent, \n"
                + "		P.CreatedDate,\n"
                + "		PS.SizeID,\n"
                + "		PS.Quantity,\n"
                + "		P.Img1,\n"
                + "		P.Img2,\n"
                + "		P.Img3,\n"
                + "		P.Img4,\n"
                + "		P.Img5,\n"
                + "		P.Img6,\n"
                + "		S.Size\n"
                + "FROM [dbo].[Products] AS P\n"
                + "INNER JOIN [dbo].[Categories] AS C ON P.CateID = C.CateID\n"
                + "INNER JOIN [dbo].[Discounts] AS D ON P.DiscountID = D.ID\n"
                + "INNER JOIN [dbo].[ProductSize] AS PS ON P.ID = PS.ProductID\n"
                + "INNER JOIN [dbo].[Size]  AS S ON PS.SizeID = S.ID";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getInt(6),
                        rs.getString(7));
                discount = new Discount(rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getFloat(11));
                productSize = new ProductSize(rs.getInt(1),
                        rs.getInt(13),
                        rs.getInt(14));
                productDTO = new ProductDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getLong(4),
                        rs.getLong(5),
                        rs.getString(3),
                        category,
                        discount,
                        rs.getDate(12),
                        productSize,
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getString(20),
                        rs.getString(21));
                productDTOs.add(productDTO);
            }
            return productDTOs;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int update(Product product) {
        int rowAffected = 0;
        String query = "UPDATE [dbo].[Products]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[listedPrice] = ?\n"
                + "      ,[Description] = ?\n"
                + "      ,[CateID] = ?\n"
                + "      ,[DiscountID] = ?\n"
                + "      ,[Img1] = ?\n"
                + "      ,[Img2] = ?\n"
                + "      ,[Img3] = ?\n"
                + "      ,[Img4] = ?\n"
                + "      ,[Img5] = ?\n"
                + "      ,[Img6] = ?\n"
                + " WHERE ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, product.getName());
            ps.setLong(2, product.getPrice());
            ps.setLong(3, product.getListedPrice());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getCateId());
            ps.setInt(6, product.getDiscountId());
            ps.setString(7, product.getImg1());
            ps.setString(8, product.getImg2());
            ps.setString(9, product.getImg3());
            ps.setString(10, product.getImg4());
            ps.setString(11, product.getImg5());
            ps.setString(12, product.getImg6());
            ps.setInt(13, product.getId());
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowAffected;
    }

    public static void main(String[] args) {
        ProductDAO d = new ProductDAO();
        System.out.println(d.getAll().get(0).getListedPrice());
    }

}
