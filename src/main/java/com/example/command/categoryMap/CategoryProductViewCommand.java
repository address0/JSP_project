package com.example.command.categoryMap;

import com.example.command.Command;
import com.example.dao.CategoryDAO;
import com.example.dao.CategoryMapDAO;
import com.example.dao.ProductDAO;
import com.example.model.Category;
import com.example.model.CategoryProductMap;
import com.example.model.Product;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class CategoryProductViewCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = request.getServletContext();
        ProductDAO productDAO = new ProductDAO(context);
        CategoryDAO categoryDAO = new CategoryDAO(context);
        CategoryMapDAO mapDAO = new CategoryMapDAO(context);

        List<Product> products = productDAO.getAllProducts();
        List<Category> categories = categoryDAO.getAllCategories();
        List<CategoryProductMap> mappings = mapDAO.findAllCategoryMaps();

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("mappings", mappings);

        request.getRequestDispatcher("/product/productList.jsp").forward(request, response);
    }
}
