package com.example.command.category;

import com.example.command.Command;
import com.example.dao.CategoryDAO;
import com.example.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class TopCategoryCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoryDAO dao = new CategoryDAO(request.getServletContext());
        List<Category> topCategories = dao.getTopCategories(); // nb_parent_category IS NULL

        request.setAttribute("mainCategories", topCategories);
        request.getRequestDispatcher("/admin/categoryManage.jsp").forward(request, response);
    }
}

