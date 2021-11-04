/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import extension.Extension;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author hoang
 */
@WebServlet(name = "SignUp", urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("SignUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String Repassword = request.getParameter("Repassword");
        AccountDAO dao = new AccountDAO();
        Account user = dao.getAccountByName(username);
        if (password.equals(Repassword) && user == null) {
            String hashPass = "";
            try {
                hashPass = Extension.generateHash(password);

            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Account account = new Account(1,username, hashPass, false,0);
            dao.createAccount(account);
            HttpSession session = request.getSession();
            account = dao.getAccountByName(account.getAccountName());
            session.setAttribute("accountsession", account);
            response.sendRedirect("home");
            return;
        }

        if (password.equals(Repassword) == false) {
            request.setAttribute("errorAccount", "Password and Rewrite Password don't match!");
        }
        if(user!=null)
        {
            request.setAttribute("errorAccount", "Account already existed!");
        }
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("Repassword", Repassword);
        request.getRequestDispatcher("SignUp.jsp").forward(request, response);
    }

}
