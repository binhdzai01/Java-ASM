/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;

/**
 *
 * @author hoang
 */
public class AccountDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;


    
    public Account getAccountByName(String name) {
        if (name != null && !name.equals("")) {
            String sql = "select * from Accounts where accountName = '" + name + "'";
            try {
                ps = connection.prepareStatement(sql);
//                ps.setString(1, name);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Account account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5));
                    return account;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    public Account getAccountById(int id) {
        try {
            String sql = "select * from Accounts where accountId = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5));
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<Account>();
        try {
            String sql = "select * from Accounts";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5));
                list.add(account);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public void createAccount(Account account) {
        String sql = "insert into Accounts values(?,?,?,0)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, account.getAccountName());
            ps.setString(2, account.getAccountPass());
            ps.setBoolean(3, account.isIsAdmin());
            System.out.println("aaaaaaaaaaa");
            ps.executeUpdate();
            System.out.println("sql: " + sql );
        } catch (SQLException e) {

        }
    }

    public void deleteAccountById(int id) {
        String sql = "delete from accounts where accountId = " + id;
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateMoney(String username, double money) {
        String sql = " update Accounts \n"
                + "set cash = cash +" + money + "\n"
                + "where accountName = '" + username + "'";
        
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateRole(int id) {
        String sql = "update Accounts \n"
                + "set isAdmin = 1 \n"
                + "where accountId = "+id;
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }
    public void updatePassword(int id,String password)
    {
        String sql = "update Accounts \n"
                + "set accountPass = '"+ password+"' "
                + "where accountId = "+id;
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }
}
