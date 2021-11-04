/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dal.AccountDAO;
import extension.Extension;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        AccountDAO dao = new AccountDAO();
        Account user = dao.getAccountByName(username);
        if (user != null) {
            String hashPass = "";
            try {
                hashPass = Extension.generateHash(password);
                System.out.println(hashPass);
                
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(hashPass);
            if (user.getAccountPass().equals(hashPass) == true) {
                HttpSession session = request.getSession();
                session.setAttribute("accountsession", user);
                response.sendRedirect("home");
                return;
            }
        }

        request.setAttribute("errorAccount", "Wrong account or password!");
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

}
