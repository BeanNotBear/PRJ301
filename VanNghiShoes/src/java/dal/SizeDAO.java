/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import dto.SizeDTO;
import java.util.List;
import model.Size;
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
public class SizeDAO extends DBContext {

    public List<Size> getAll() {
        List<Size> sizes = new ArrayList<>();
        String query = "SELECT [ID]\n"
                + "      ,[Size]\n"
                + "  FROM [ShoesShopDB].[dbo].[Size]";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            Size size = null;
            while (rs.next()) {
                size = new Size(rs.getInt(1),
                        rs.getFloat(2));
                sizes.add(size);
            }
            return sizes;
        } catch (SQLException ex) {
            Logger.getLogger(SizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<SizeDTO> getAllSizeDTO() {
        List<SizeDTO> sizeDTOs = new ArrayList<>();
        SizeDTO sizeDTO = new SizeDTO();
        String query = "SELECT	SizeID, \n"
                + "		S.Size,\n"
                + "		COUNT(SizeID) AS NumberOfProduct\n"
                + "FROM [ProductSize] as PS\n"
                + "INNER JOIN Size AS S ON PS.SizeID = S.ID\n"
                + "GROUP BY SizeID, S.Size";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sizeDTO = new SizeDTO(rs.getInt(1),
                        rs.getFloat(2),
                        rs.getInt(3));
                sizeDTOs.add(sizeDTO);
            }
            return sizeDTOs;
        } catch (SQLException ex) {
            Logger.getLogger(SizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void main(String[] args) {
        //        SizeDAO d = new SizeDAO();
        //        System.out.println(d.getAll().get(0).getSize());
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.toString());
    }

}
