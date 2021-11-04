/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Order;
import model.Product;

/**
 *
 * @author hoang
 */
@WebServlet(name = "ProductDetailServlet", urlPatterns = {"/product-detail"})
public class ProductDetailServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("productid"));
        ProductDAO dao = new ProductDAO();
        Product product = dao.getProductById(id);
        List<Product> viewProduct = dao.getTopView();
        request.setAttribute("viewProduct", viewProduct);
        request.setAttribute("product", product);
        request.getRequestDispatcher("Product-Detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("accountsession") == null) {
            response.sendRedirect("login");
            return;
        }
        Account account = (Account) session.getAttribute("accountsession");
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getCurrentCart(account.getAccountId());
        if (order == null) {
            orderDAO.createCart(account);
            order = orderDAO.getCurrentCart(account.getAccountId());
        }
        int id = Integer.parseInt(request.getParameter("id"));
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        Product product = checkExist(order.getProIds(), id);
        if(product==null)
        {
            product= (new ProductDAO()).getProductById(id);
            product.setProQuantity(1);
            orderDetailDAO.createProductCart(order.getOrderId(), product);
        }
        else
        {
            product.setProQuantity(product.getProQuantity()+1);
            orderDetailDAO.updateProductCart(order.getOrderId(), product);
        }
        response.sendRedirect("cart");
    }
    
    private Product checkExist(List<Product> list, int id)
    {
        for(Product pd : list)
        {
            if(pd.getProId()== id) return pd;
        }
        return null;
    }

}
