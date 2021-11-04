/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.OrderDAO;
import dal.OrderDetailDAO;
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
@WebServlet(name = "ChangeCartServlet", urlPatterns = {"/changeCart"})
public class ChangeCartServlet extends HttpServlet {

    Account account;
    OrderDAO orderDAO = new OrderDAO();
    Order order;
    Product product;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("accountsession") == null) {
                response.sendRedirect("login");
                return;
            }
            
            account = (Account) session.getAttribute("accountsession");
           
            order = orderDAO.getCurrentCart(account.getAccountId());
            int proId = Integer.parseInt(request.getParameter("proId"));
            product = checkExist(order.getProIds(), proId);
        } catch (IOException e) {

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        product.setProQuantity(product.getProQuantity()+1);
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        orderDetailDAO.updateProductCart(order.getOrderId(), product);
        response.sendRedirect("cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        product.setProQuantity(product.getProQuantity()-1);
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        orderDetailDAO.updateProductCart(order.getOrderId(), product);
        if(product.getProQuantity()==0)
        {
            orderDetailDAO.deleteProductCart(order.getOrderId(), product);
            order.getProIds().remove(product);
            if(order.getProIds().size()==0)
            {
                orderDAO.deleteCart(order.getOrderId());
            }
        }
        response.sendRedirect("cart");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Product checkExist(List<Product> list, int id) {
        for (Product pd : list) {
            if (pd.getProId() == id) {
                return pd;
            }
        }
        return null;
    }

}
