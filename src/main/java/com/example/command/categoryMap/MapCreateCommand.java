package com.example.command.categoryMap;

import com.example.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.dao.CategoryMapDAO;
import com.example.model.CategoryProductMap;

public class MapCreateCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        Integer categoryId = 0;

        if (request.getParameter("categoryId") != null) {
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        } else {
            response.sendRedirect("error.jsp");
            return;
        }
        CategoryMapDAO categoryMapDAO = new CategoryMapDAO(request.getServletContext());

        CategoryProductMap categoryProductMap = new CategoryProductMap();

        int cnOrder = categoryMapDAO.getMaxOrderForCategory(categoryId);

        categoryProductMap.setNoProduct(productId);
        categoryProductMap.setNbCategory(categoryId);
        categoryProductMap.setCnOrder(cnOrder);

        boolean result = categoryMapDAO.addCategoryMap(categoryProductMap);

        if (!result) {
            response.sendRedirect("error.jsp");
            return;
        }

        response.sendRedirect("success.jsp");
    }
}
