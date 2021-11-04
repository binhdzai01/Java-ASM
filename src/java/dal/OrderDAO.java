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

public class OrderDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;

    public List<Product> getListOrder(int orderId) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from products pd \n"
                + "inner join OrderDetails od on pd.proId = od.proId  where orderId = " + orderId;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), "", 0, null, "", null, 0, rs.getInt(13));
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Order getCurrentCart(int id) {
        String sql = "select * from Orders where orderState = 0 and accountId =" + id;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt(1);
                Order order = new Order(orderId, rs.getDate(2),
                        (new AccountDAO()).getAccountById(rs.getInt(3)), rs.getBoolean(4), getListOrder(orderId));
                return order;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void createCart(Account account) {
        String sql = "insert into Orders values(?,?,0)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setDate(1, new Date(System.currentTimeMillis()));
            ps.setInt(2, account.getAccountId());
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void deleteCart(int orderId) {
        String sql = "delete from orders where orderId=" + orderId;
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void changeCartToOrder(int orderId) {
        String sql = "update orders set orderState = 1 where orderId = " + orderId;
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        String sql = "select * from Orders order by orderDate desc";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getDate(2),
                        (new AccountDAO()).getAccountById(rs.getInt(3)), true, new ArrayList<>()));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public double getTotalMoney(int orderId) {
        String sql = "select sum(proPrice) from OrderDetails odD\n"
                + "inner join Products pd on odD.proId = pd.proId\n"
                + "group by orderId having orderId ="+orderId;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("SQL :" + sql);
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }
}
