/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerAdmin;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Category;
import model.Product;

/**
 *
 * @author hoang
 */
@MultipartConfig
@WebServlet(name = "ManageProductServlet", urlPatterns = {"/manage-product"})
public class ManageProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Manage-Product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String proName = request.getParameter("proname");
            double proPrice = Double.parseDouble(request.getParameter("proprice"));

            String proDes = request.getParameter("prodes");
            Date proCreate = Date.valueOf(request.getParameter("prodate"));
            int catId = Integer.parseInt(request.getParameter("catId"));
            for (Part part : request.getParts()) {
                InputStream is = request.getPart(part.getName()).getInputStream();
                int i = is.available();
                byte[] b = new byte[i];
                is.read(b);
                String fileName = getFileName(part);
                if (fileName == null) {
                    continue;
                }

                String rootPath = "F:/LEARN-JAVAWEB-PRJ301/BinhStore/web/images/products/" + proName;
                File theDir = new File(rootPath);
                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
                String fileWay = rootPath + "/" + fileName;
                FileOutputStream os = new FileOutputStream(fileWay);
                os.write(b);
                is.close();
                Category cat = new Category(catId, "", "", "");
                Product product = new Product(1, proName, proPrice, proDes, 0, proCreate, "./images/products/" + proName+"/" +fileName, cat,0,0);
                (new ProductDAO()).createProduct(product);
                break;
            }
        } catch (Exception e) {
        }
        finally{
            response.sendRedirect("manage-product");
        }
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
