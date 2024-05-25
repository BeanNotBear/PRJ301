/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import dto.ProductSizeDTO;
import model.ProductSize;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nghin
 */
public class ProductSizeDAO extends DBContext {

    public int insert(ProductSize productSize) {
        int rowAffected = 0;
        String query = "INSERT INTO [dbo].[ProductSize]\n"
                + "           ([ProductID]\n"
                + "           ,[SizeID]\n"
                + "           ,[Quantity])\n"
                + "     VALUES\n"
                + "           (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productSize.getProductID());
            ps.setInt(2, productSize.getSizeID());
            ps.setInt(3, productSize.getQuantity());
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductSizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowAffected;
    }

    public List<ProductSizeDTO> getProductSizeDTOByProductId(int id) {
        List<ProductSizeDTO> productSizeDTOs = new ArrayList<>();
        ProductSizeDTO productSizeDTO = null;
        String query = "SELECT  PS.ProductID,\n"
                + "		PS.SizeID,\n"
                + "		S.Size,\n"
                + "		PS.Quantity\n"
                + "FROM ProductSize AS PS\n"
                + "INNER JOIN [dbo].[Size] AS S ON PS.SizeID = S.ID\n"
                + "WHERE ProductID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                productSizeDTO = new ProductSizeDTO(rs.getInt(1),
                        rs.getInt(2),
                        rs.getFloat(3),
                        rs.getInt(4));
                productSizeDTOs.add(productSizeDTO);
            }
            return productSizeDTOs;
        } catch (SQLException ex) {
            Logger.getLogger(ProductSizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int updateQuantity(ProductSize productSize) {
        int rowAffected = 0;
        String query = "UPDATE [dbo].[ProductSize]\n"
                + "		SET [Quantity] = ?\n"
                + " WHERE ProductID = ? AND [SizeID] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productSize.getQuantity());
            ps.setInt(2, productSize.getProductID());
            ps.setInt(3, productSize.getSizeID());
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductSizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowAffected;
    }

    public ProductSize getProductSize(int productId, int sizeId) {
        ProductSize productSize = new ProductSize();
        String query = "SELECT [ProductID]\n"
                + "      ,[SizeID]\n"
                + "      ,[Quantity]\n"
                + "  FROM [dbo].[ProductSize]\n"
                + "  WHERE [ProductID] = ? AND [SizeID] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, sizeId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                productSize.setProductID(rs.getInt(1));
                productSize.setSizeID(rs.getInt(2));
                productSize.setQuantity(rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductSizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productSize;
    }

    public int delete(int productId, int sizeId) {
        int rowAffected = 0;
        String query = "DELETE FROM [dbo].[ProductSize]\n"
                + "     WHERE [ProductID] = ?  AND [SizeID] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, sizeId);
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductSizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowAffected;
    }

    public static void main(String[] args) {
        ProductSizeDAO d = new ProductSizeDAO();
        System.out.println(d.getProductSizeDTOByProductId(1).get(1).getSize());
    }

}
