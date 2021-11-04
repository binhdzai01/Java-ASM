/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import dal.OrderDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "PayServlet", urlPatterns = {"/pay"})
public class PayServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("accountsession") == null) {
            response.sendRedirect("login");
            return;
        }
        Account account = (Account) session.getAttribute("accountsession");
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getCurrentCart(account.getAccountId());  
        Double total = Double.parseDouble(request.getParameter("total"));
        (new AccountDAO()).updateMoney(account.getAccountName(), -total);
        ProductDAO productDAO = new ProductDAO();
        for(Product product:order.getProIds())
        {
            productDAO.addSelled(product);
        }
        orderDAO.changeCartToOrder(order.getOrderId());
        response.sendRedirect("home");
    }

}
