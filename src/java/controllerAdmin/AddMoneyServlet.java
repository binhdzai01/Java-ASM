package controllerAdmin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author hoang
 */
@WebServlet(urlPatterns = {"/add-money"})
public class AddMoneyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Add-Money.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountName = request.getParameter("username");
        AccountDAO dao = new AccountDAO();
        Account user = dao.getAccountByName(accountName);
        if(user==null)
        {
            request.setAttribute("error", "Account doesn't exist!");
            request.getRequestDispatcher("Add-Money.jsp").forward(request, response);
            return;
        }
        else
        {
            double cash = Double.parseDouble(request.getParameter("money"));
            dao.updateMoney(accountName,cash);
            response.sendRedirect("home_admin");
        }
        
         
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
