/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderStatus;

/**
 *
 * @author nghin
 */
public class OrderDAO extends DBContext {

    public int insert(Order order) {
        int rowAffected = 0;
        String query = "INSERT INTO [dbo].[Order]\n"
                + "           ([ShipDate]\n"
                + "           ,[Note]\n"
                + "           ,[Status]\n"
                + "           ,[TotalPrice]\n"
                + "           ,[CustomerId]\n"
                + "           ,[Address])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDate(1, order.getShipDate());
            ps.setString(2, order.getNote());
            ps.setInt(3, order.getStatus().getId());
            ps.setLong(4, order.getTotalPrice());
            ps.setInt(5, order.getAccount().getID());
            ps.setString(6, order.getAddress());
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowAffected;
    }

    public int getLastId() {
        int id = -1;
        String query = "SELECT MAX([ID]) AS LastID\n"
                + "  FROM [ShoesShopDB].[dbo].[Order]";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT [ID]\n"
                + "      ,[OrderDate]\n"
                + "      ,[ShipDate]\n"
                + "      ,[Note]\n"
                + "      ,[Status]\n"
                + "      ,[TotalPrice]\n"
                + "      ,[CustomerId]\n"
                + "      ,[Address]\n"
                + "  FROM [ShoesShopDB].[dbo].[Order]";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            AccountDAO accountDAO = new AccountDAO();
            
            while (rs.next()) {                
                orders.add(new Order(
                        rs.getInt(1), 
                        rs.getDate(2), 
                        rs.getString(4), 
                        rs.getDate(3), 
                        new OrderStatus(rs.getInt(5), "Shipping"), 
                        rs.getLong(6), 
                        rs.getString(8), 
                        accountDAO.getAccountById(rs.getInt(7))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        System.out.println(dao.getAll().get(0).getAccount().getFullName());
    }
}
