package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Category;

public class CategoryDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();

        try {
            String sql = "select * from Categories";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(category);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void createCategory(Category category) {
        String sql = "insert into categories values(?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, category.getCatName());
            ps.setString(2, category.getCatImage());
            ps.setString(3, category.getCatDes());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteCategory(int id) {
        String sql = "delete from categories where catId = " + id;
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public Category getCategoryById(int id)
    {
        String sql = "select * from categories where catId = "+id;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                Category category = new Category(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                return category;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
