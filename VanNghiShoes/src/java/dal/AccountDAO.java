package dal;

import context.DBContext;
import java.util.List;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.detail.AccountRole;

/**
 *
 * The AccountDAO class to get account from database.
 *
 * @version 1.0 14 Feb 2024
 * @author nghin
 */
public class AccountDAO extends DBContext {

    public List<Account> getAll() {

        // query to select all account.
        String query = "SELECT [ID]\n"
                + "      ,[Email]\n"
                + "      ,[Password]\n"
                + "      ,[FullName]\n"
                + "      ,[Phone]\n"
                + "      ,[Status]\n"
                + "      ,[Role]\n"
                + "      ,[token]\n"
                + "  FROM [ShoesShopDB].[dbo].[Accounts]";
        try {

            // prepare statement
            PreparedStatement ps = connection.prepareStatement(query);

            // get set all account
            ResultSet rs = ps.executeQuery();
            List<Account> accounts = new ArrayList<>();
            Account account = null;
            while (rs.next()) {
                account = new Account(rs.getInt(1), // get id.
                        rs.getString(2), // get email.
                        rs.getString(3), // get password.
                        rs.getString(4), // get full name.
                        rs.getString(5), // get phone number.
                        rs.getInt(6), // get status.
                        AccountRole.create(rs.getInt(7)), // get role.
                        rs.getString(8)); // get token.

                // add account to account list
                accounts.add(account);
            }
            return accounts;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getAccountByEmailAndPassword(String email, String password) {

        // query to select account by email and password.
        String query = "SELECT [ID]\n"
                + "      ,[Email]\n"
                + "      ,[Password]\n"
                + "      ,[FullName]\n"
                + "      ,[Phone]\n"
                + "      ,[Status]\n"
                + "      ,[Role]\n"
                + "      ,[token]\n"
                + "  FROM [ShoesShopDB].[dbo].[Accounts]\n"
                + "  WHERE [Email] = ? AND [Password] = ?;";

        try {

            // prepare statement.
            PreparedStatement ps = connection.prepareStatement(query);

            // set email into query.
            ps.setString(1, email);

            //set password into query.
            ps.setString(2, password);

            // get account
            ResultSet rs = ps.executeQuery();
            Account account = null;
            if (rs.next()) {
                System.out.println(rs.getString(7));
                account = new Account(rs.getInt(1), // get id.
                        rs.getString(2), // get email.
                        rs.getString(3), // get password.
                        rs.getString(4), // get full name.
                        rs.getString(5), // get phone number.
                        rs.getInt(6), // get status.
                        AccountRole.create(rs.getInt(7)), // get role.
                        rs.getString(8)); // get token.
            }
            return account;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int updateStatusById(int id) {
        int rowAffected = 0;
        final int VERIFY_STT = 1;
        String query = "UPDATE [dbo].[Accounts]\n"
                + "   SET [Status] = ?\n"
                + " WHERE [ID] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, VERIFY_STT);
            ps.setInt(2, id);
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowAffected;
    }

    public Account getAccountByToken(String token) {
        Account account = null;
        String query = "SELECT [ID]\n"
                + "      ,[Email]\n"
                + "      ,[Password]\n"
                + "      ,[FullName]\n"
                + "      ,[Phone]\n"
                + "      ,[Status]\n"
                + "      ,[Role]\n"
                + "      ,[token]\n"
                + "  FROM [dbo].[Accounts]\n"
                + "  WHERE [token] = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account(rs.getInt(1), // get id.
                        rs.getString(2), // get email.
                        rs.getString(3), // get password.
                        rs.getString(4), // get full name.
                        rs.getString(5), // get phone number.
                        rs.getInt(6), // get status.
                        AccountRole.create(rs.getInt(7)), // get role.
                        rs.getString(8)); // get token.
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public Account getAccountById(int id) {
        Account account = null;
        String query = "SELECT [ID]\n"
                + "      ,[Email]\n"
                + "      ,[Password]\n"
                + "      ,[FullName]\n"
                + "      ,[Phone]\n"
                + "      ,[Status]\n"
                + "      ,[Role]\n"
                + "      ,[token]\n"
                + "  FROM [dbo].[Accounts]\n"
                + "  WHERE [ID] = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account(rs.getInt(1), // get id.
                        rs.getString(2), // get email.
                        rs.getString(3), // get password.
                        rs.getString(4), // get full name.
                        rs.getString(5), // get phone number.
                        rs.getInt(6), // get status.
                        AccountRole.create(rs.getInt(7)), // get role.
                        rs.getString(8)); // get token.
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int insert(Account account) {
        int rowAffected = 0;
        String query = "INSERT INTO [dbo].[Accounts]\n"
                + "           ([Email]\n"
                + "           ,[Password]\n"
                + "           ,[FullName]\n"
                + "           ,[Phone]\n"
                + "           ,[Status]\n"
                + "           ,[Role]\n"
                + "           ,[token])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, account.getEmail());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getFullName());
            ps.setString(4, account.getPhone());
            ps.setInt(5, account.getStatus());
            ps.setInt(6, convertSRoleToDRole(account.getRole()));
            ps.setString(7, account.getToken());
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowAffected;
    }

    public int updatePassword(String email, String password) {
        int rowAffected = 0;
        String query = "UPDATE [dbo].[Accounts]\n"
                + "   SET [Password] = ?\n"
                + " WHERE [Email] = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, email);
            rowAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowAffected;
    }

    public boolean checkAccountByEmail(String email) {
        String emailAc = null;
        String query = "SELECT [Email]\n"
                + "FROM [dbo].[Accounts]\n"
                + "WHERE [Email] = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                // get email from database.
                emailAc = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emailAc == null;
    }

    private int convertSRoleToDRole(AccountRole role) {
        if(role.toString().equals("admin")) {
            return 1;
        } else if(role.toString().equals("custommer")) {
            return 2;
        } else {
            return 3;
        }
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        
        Account acc = dao.getAccountByEmailAndPassword("NghiNVHE176303@fpt.edu.vn", "8615322ff3f49a922a799b0dbc87f055f652511217c58a4d15c7b09f78788ce1");
//        acc.setRole(AccountRole.SUPPER_ADMIN);
        String name = acc.getEmail() + acc.getFullName() + acc.getPhone();
        System.out.println(sercurity.EncryptSHA256.toHexString(name));
    }
}
