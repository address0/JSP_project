package com.example.command.category;
import com.example.command.Command;
import com.example.dao.CategoryDAO;
import com.example.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CategoryUpdateFormCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/category/topList.do");
            return;
        }

        int categoryId = Integer.parseInt(idParam);
        CategoryDAO dao = new CategoryDAO(request.getServletContext());
        Category category = dao.getCategoryById(categoryId);

        if (category == null) {
            response.sendRedirect(request.getContextPath() + "/category/topList.do");
            return;
        }

        request.setAttribute("category", category);
        System.out.println("CategoryUpdateFormCommand: " + category.getNmCategory());
        request.getRequestDispatcher("/category/categoryWrite.jsp").forward(request, response);
    }
}

