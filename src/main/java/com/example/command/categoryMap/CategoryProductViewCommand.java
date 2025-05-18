package com.example.command.categoryMap;

import com.example.command.Command;
import com.example.dao.CategoryDAO;
import com.example.dao.CategoryMapDAO;
import com.example.dao.ProductDAO;
import com.example.model.Category;
import com.example.model.Product;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class CategoryProductViewCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = request.getServletContext();
        CategoryDAO categoryDAO = new CategoryDAO(context);
        CategoryMapDAO mapDAO = new CategoryMapDAO(context);
        ProductDAO productDAO = new ProductDAO(context);

        String nbCategoryParam = request.getParameter("nb_category");

        List<Product> productList;
        List<Category> allTopCategories = categoryDAO.getCategoriesByParent(0); // level 1
        List<Category> middleCategories = new ArrayList<>();
        List<Category> subCategories = new ArrayList<>();
        Category selectedCategory = null;

        if (nbCategoryParam == null || nbCategoryParam.isEmpty()) {
            productList = productDAO.getAllProducts();
        } else {
            int selectedId = Integer.parseInt(nbCategoryParam);
            selectedCategory = categoryDAO.getCategoryById(selectedId);
            productList = mapDAO.getProductsByCategory(selectedId);

            if (selectedCategory != null && selectedCategory.getCnLevel() == 2) {
                subCategories = categoryDAO.getCategoriesByParent(selectedId);
            }
            if (selectedCategory != null && selectedCategory.getCnLevel() == 1) {
                middleCategories = categoryDAO.getCategoriesByParent(selectedId);
            }
        }

        request.setAttribute("topCategories", allTopCategories);
        request.setAttribute("middleCategories", middleCategories);
        request.setAttribute("subCategories", subCategories);
        request.setAttribute("selectedCategory", selectedCategory);
        request.setAttribute("productList", productList);

        request.getRequestDispatcher("/product/productList.jsp").forward(request, response);
    }
}

