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
import model.Category;
import model.Product;

public class ProductDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;

    public Product getProductById(int id) {
        String sql = "select * from products where proId=" + id;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(8),
                        (new CategoryDAO()).getCategoryById(rs.getInt(7)),
                        rs.getInt(9), 0);
                return product;

            }
        } catch (Exception e) {
        }
        return null;
    }

    public void createProduct(Product product) {
        String sql = "insert into products values(?,?,?,?,?,?,?,0)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, product.getProName());
            ps.setDouble(2, product.getProPrice());
            ps.setString(3, product.getProDes());
            ps.setInt(4, product.getProView());
            ps.setDate(5, product.getProCreate());
            ps.setInt(6, product.getCategory().getCatId());
            ps.setString(7, product.getProImg());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteProduct(int id) {
        String sql = "delete from products where proId = " + id;
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products";
        try {

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(8),
                        (new CategoryDAO()).getCategoryById(rs.getInt(7)),
                        rs.getInt(9), 0);
                list.add(product);

            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProductByConstrain(int index, int order_by, int CategoryID, long begin, long end, String name) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE 1=1";
        sql = addCategoryID(sql, CategoryID);  //WHERE CategoryID=
        sql = addUnitPrice(sql, begin, end);    //WHERE UnitsPrice BETWEEN
        sql = addSearchByName(sql, name);
        StringBuilder sb = new StringBuilder(sql);
        switch (order_by) {
            case 1: {
                sb.append(" ORDER BY proName ASC OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY");
                break;
            }
            case 2: {
                sb.append(" ORDER BY proPrice ASC OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY");
                break;
            }
            case 3: {
                sb.append(" ORDER BY proPrice DESC OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY");
                break;
            }
            default: {
                sb.append(" ORDER BY proId DESC OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY");
                break;
            }
        }
        try {

            ps = connection.prepareStatement(sb.toString());
            ps.setInt(1, index * 6);
            System.out.println("SQL: " + sb.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(8),
                        (new CategoryDAO()).getCategoryById(rs.getInt(7)),
                        rs.getInt(9), 0);
                list.add(product);

            }
        } catch (Exception e) {
        }
        return list;
    }

    // Extension
    private String addCategoryID(String sql, int CategoryID) {
        StringBuilder sb = new StringBuilder(sql);
        if (CategoryID > 0) {
            sb.append(" AND catId=").append(CategoryID);
        }
        return sb.toString();
    }

    private String addUnitPrice(String sql, long begin, long end) {
        StringBuilder sb = new StringBuilder(sql);
        if (end > 0) {
            sb.append(" AND proPrice BETWEEN ").append(begin).append(" AND ").append(end);
        }
        return sb.toString();
    }

    private String addSearchByName(String sql, String name) {
        StringBuilder sb = new StringBuilder(sql);
        if (!name.isEmpty()) {
            sb.append(" AND proName like '%").append(name).append("%'");
        }
        return sb.toString();
    }

    public int getPageCount(int CategoryID, long begin, long end, String name) {
        int st = 0;
        String sql = "SELECT COUNT(*) FROM Products WHERE 1=1";
        sql = addCategoryID(sql, CategoryID);
        sql = addUnitPrice(sql, begin, end);
        sql = addSearchByName(sql, name);
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                st = (int) Math.ceil(rs.getInt(1) / 6.0);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return st;
    }

    public void increaseView(int proId) {
        String sql = "update Products\n"
                + "set proView = proView +1 \n"
                + "where proId = " + proId;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Product> getTopView() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT Top(3) * FROM Products ORDER BY proView DESC";
        try {

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(8),
                        (new CategoryDAO()).getCategoryById(rs.getInt(7)),
                        rs.getInt(9), 0);
                list.add(product);

            }
        } catch (Exception e) {
        }
        return list;
    }

    public void addSelled(Product product) {
        String sql = "update Products\n"
                + "set proSelled = proSelled +" + product.getProQuantity() + " where proId = " + product.getProId();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void updateProduct(Product product) {
        String sql = "update Products\n"
                + " set proName ='" + product.getProName()
                + "', proPrice =" + product.getProPrice()
                + ", proDes ='" + product.getProDes()
                + "', catId =" + product.getCategory().getCatId()
                + " where proId = " + product.getProId();
        try {
            System.out.println("sql : "+ sql);
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    public List<Product> getTopSelled() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT Top(3) * FROM Products ORDER BY proSelled DESC";
        try {

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(8),
                        (new CategoryDAO()).getCategoryById(rs.getInt(7)),
                        rs.getInt(9), 0);
                list.add(product);

            }
        } catch (Exception e) {
        }
        return list;
    }
}
