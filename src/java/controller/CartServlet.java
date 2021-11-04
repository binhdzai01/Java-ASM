/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Order;

/**
 *
 * @author hoang
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

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
        if(order==null)
        {
            request.setAttribute("listProduct", new ArrayList<>());
        }
        else {
            request.setAttribute("listProduct", order.getProIds());
        }
        request.setAttribute("account", account);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
