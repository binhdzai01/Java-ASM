/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerAdmin;

import dal.CategoryDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Category;

/**
 *
 * @author hoang
 */
@MultipartConfig
@WebServlet(name = "ManageCategoryServlet", urlPatterns = {"/manage-category"})
public class ManageCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Manage-Category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String catName = request.getParameter("catname");
        System.out.println("catName :" +catName);
        String catDes = request.getParameter("catdes");
        for (Part part : request.getParts()) {
            InputStream is = request.getPart(part.getName()).getInputStream();
            int i = is.available();
            byte[] b = new byte[i];
            is.read(b);
            String fileName = getFileName(part);
            if (fileName == null) {
                continue;
            }

            String rootPath = "F:/LEARN-JAVAWEB-PRJ301/BinhStore/web/images/categories/" + catName ;
            File theDir = new File(rootPath);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            String fileWay = rootPath + "/" + fileName;
            FileOutputStream os = new FileOutputStream(fileWay);
            os.write(b);
            is.close();
            Category category = new Category(0, catName, "./images/categories/" + catName+"/" +fileName, catDes);
//            (new CategoryDAO()).createCategory(category);
            break;
        }
        response.sendRedirect("manage-category");
    }

    private String getFileName(Part part) {
        String partHeader = part.getHeader("content-disposition");
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;

    }

}
