/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

/**
 *
 * @author hoang
 */
@WebServlet(name = "ShopAllServlet", urlPatterns = {"/all-product"})
public class ShopAllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get sort by
        int sortby = 1;
        if (null != request.getParameter("sort")) {
            sortby = Integer.parseInt(request.getParameter("sort"));
        }
        switch (sortby) {
            case 1: {
                request.setAttribute("sortname", "selected");
                break;
            }
            case 2: {
                request.setAttribute("sortasc", "selected");
                break;
            }
            case 3: {
                request.setAttribute("sortdesc", "selected");
                break;
            }
            default: {
                request.setAttribute("sortnew", "selected");
                break;
            }
        }
        request.setAttribute("sortabc", sortby);

        //get search by name
        String name = "";
        if (request.getParameter("search") != null) {
            name = request.getParameter("search").trim();

        }
        request.setAttribute("searchname", name);

        //get selected cartegory
        int catCheck = 0;
        if (null != request.getParameter("category")) {
            catCheck = Integer.parseInt(request.getParameter("category"));
        }
        request.setAttribute("category", catCheck);
        
        //get price range
        int beginprice = 0;
        int endprice = 0;
        if (request.getParameter("beginp") != null && !"".equals(request.getParameter("beginp"))) {
            beginprice = Integer.parseInt(request.getParameter("beginp"));
            request.setAttribute("beginprice", beginprice);
        }
        if (request.getParameter("endp") != null && !"".equals(request.getParameter("endp"))) {
            endprice = Integer.parseInt(request.getParameter("endp"));
            request.setAttribute("endprice", endprice);
        }

        //DAO
        ProductDAO pdao = new ProductDAO();
        CategoryDAO cdao = new CategoryDAO();
        
        //load cartegory
        List<Category> categories = new ArrayList<>();
        Category all = new Category();
        all.setCatId(0);
        all.setCatName("All");
        categories.add(all);
        for (Category cat : cdao.getAllCategory()) {
            categories.add(cat);
        }
        request.setAttribute("categories", categories);

        //get and pagecount
        int pagecount = pdao.getPageCount(catCheck, beginprice, endprice, name);
        request.setAttribute("numberPage", pagecount);
        //get and set curent page
        int page = 1;
        if (null != request.getParameter("page")) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("pageCurrent", page);
        //paging calculation
        int beginPage = page - 1;
        int endPage = page + 1;
        if (page < 3) {
            beginPage = 1;
            endPage = 3;
            if (endPage > pagecount) {
                endPage = pagecount;
            }
        } else {
            if (page > pagecount - 2) {
                endPage = pagecount;
                beginPage = pagecount - 2;
            }
        }
        List<Product> products = pdao.getAllProductByConstrain(page - 1, sortby, catCheck, beginprice, endprice, name);
        request.setAttribute("beginPage", beginPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("lstzsize", products.size() - 1);
        request.setAttribute("listP", products);
        request.getRequestDispatcher("All-Product.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
