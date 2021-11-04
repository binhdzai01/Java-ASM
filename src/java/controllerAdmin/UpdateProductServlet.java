package controllerAdmin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

/**
 *
 * @author hoang
 */
@WebServlet(urlPatterns = {"/update-product"})
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("proId"));
        ProductDAO proDao = new ProductDAO();
        Product product = proDao.getProductById(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("Update-Product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int proId = Integer.parseInt(request.getParameter("proId"));
        String proName = request.getParameter("proname");
        double proPrice = Double.parseDouble(request.getParameter("proprice"));
        String proDes = request.getParameter("prodes");
        int catId = Integer.parseInt(request.getParameter("catId"));
        Product product = new Product(proId, proName, proPrice, proDes, catId, null, proDes, new Category(catId, "", "", ""), catId, catId);
        ProductDAO proDao = new ProductDAO();
        proDao.updateProduct(product);
        response.sendRedirect("manage-product");
    }

}
