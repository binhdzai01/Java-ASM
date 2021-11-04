/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("accountsession");
        request.setAttribute("user", user);
        request.getRequestDispatcher("Profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("accountsession");
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String renewpass = request.getParameter("renewpass");
        request.setAttribute("user", user);
        String hashpass ="";
        try {
            hashpass = extension.Extension.generateHash(oldpass);
        } catch (Exception e) {
        }
        if(!hashpass.equals(user.getAccountPass()) || !newpass.equals(renewpass))
        {
            request.setAttribute("error", "Error Change Password");
            request.getRequestDispatcher("Profile.jsp").forward(request, response);
            return;
        }
        AccountDAO dao = new AccountDAO();
        try {
            hashpass = extension.Extension.generateHash(newpass);
        } catch (Exception e) {
        }
        dao.updatePassword(user.getAccountId(), hashpass);
        user.setAccountPass(hashpass);
        session.setAttribute("user", user);
        request.setAttribute("success", "Change Password Successful");
        request.getRequestDispatcher("Profile.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
