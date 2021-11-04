/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Order;
import model.Account;
import model.Product;
public class OrderDetailDAO extends DBContext{
    PreparedStatement ps;
    ResultSet rs;
    public void createProductCart(int orderId,Product product)
    {
        String sql = "insert into OrderDetails values(?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, product.getProId());
            ps.setInt(3, product.getProQuantity());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void updateProductCart(int orderId,Product product)
    {
        String sql = "update OrderDetails "
                + "set quantity ="+product.getProQuantity()
                + " where orderId = "+orderId
                + " and proId = "+product.getProId();
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void deleteProductCart(int orderId,Product product)
    {
        String sql = "delete from  OrderDetails "
                + " where orderId = "+orderId
                + " and proId = "+product.getProId();
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}
