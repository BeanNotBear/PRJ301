/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import dto.CategoryDTO;
import model.Category;
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
public class CategoryDAO extends DBContext {

    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        Category category = null;
        String query = "SELECT [CateID]\n"
                + "      ,[CateName]\n"
                + "  FROM [ShoesShopDB].[dbo].[Categories]";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getInt(1), rs.getString(2));
                categories.add(category);
            }
            return categories;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<CategoryDTO> getAllCategoryDTO() {
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        CategoryDTO categoryDTO = null;
        String query = "SELECT	C.CateID,\n"
                + "		C.CateName,\n"
                + "		COUNT(C.CateID) AS [NumberOfProducts]\n"
                + "FROM [dbo].[Categories] AS C\n"
                + "INNER JOIN [dbo].[Products] AS P ON C.CateID = P.CateID\n"
                + "GROUP BY C.CateID, C.CateName";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                categoryDTO = new CategoryDTO(rs.getInt(1),
                        rs.getString(2), 
                        rs.getInt(3));
                categoryDTOs.add(categoryDTO);
            }
            return categoryDTOs;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int insert(Category category) {
        int rowAffected = 0;
        String query = "INSERT INTO [dbo].[Categories]\n"
                + "           ([CateName])\n"
                + "     VALUES (?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, category.getName());
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowAffected;
    }

    public int update(Category category) {
        int rowAffected = 0;
        String query = "UPDATE [dbo].[Categories]\n"
                + "SET [CateName] = ?\n"
                + "WHERE CateID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowAffected;
    }

    public int delete(Category category) {
        int rowAffected = 0;
        String query = "DELETE FROM [dbo].[Categories]\n"
                + "      WHERE [CateID] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, category.getId());
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowAffected;
    }

    public static void main(String[] args) {
        CategoryDAO d = new CategoryDAO();
        System.out.println(d.update(new Category(4, "Nike")));
    }
}
