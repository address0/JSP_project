package com.example.command.categoryMap;

import com.example.command.Command;
import com.example.dao.CategoryDAO;
import com.example.dao.ContentDAO;
import com.example.dao.ProductDAO;
import com.example.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.example.model.Product;
import com.example.model.Content;

public class MapFormCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDao = new ProductDAO(request.getServletContext());
        CategoryDAO categoryDao = new CategoryDAO(request.getServletContext());
        ContentDAO contentDao = new ContentDAO(request.getServletContext());

        List<Product> products = productDao.getAllProducts();
        List<Category> categories = categoryDao.getAllCategories();
        List<Content> contents = contentDao.getAllContents();

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("images", contents);
        request.getRequestDispatcher("/categoryMap/mapForm.jsp").forward(request, response);
    }
}
