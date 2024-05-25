/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.util.List;
import model.Discount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nghin
 */
public class DiscountDAO extends DBContext {

    public List<Discount> getAll() {
        List<Discount> discounts = new ArrayList<>();
        String query = "SELECT [ID]\n"
                + "      ,[Name]\n"
                + "      ,[Description]\n"
                + "      ,[DiscountPercent]\n"
                + "  FROM [ShoesShopDB].[dbo].[Discounts]";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            Discount discount = null;
            while (rs.next()) {                
                discount = new Discount(rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getFloat(4));
                discounts.add(discount);
            }
            return discounts;
        } catch (SQLException ex) {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
}
