package com.example.command.category;

import com.example.command.Command;
import com.example.dao.CategoryDAO;
import com.example.model.Category;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CategoryListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO(request.getServletContext());
        List<Category> categoryList = categoryDAO.getAllCategories();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/admin/manage.jsp?managePage=categoryManage").forward(request, response);
    }
}
