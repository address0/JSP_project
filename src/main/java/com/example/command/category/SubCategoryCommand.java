package com.example.command.category;
import com.example.command.Command;
import com.example.dao.CategoryDAO;
import com.example.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SubCategoryCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int parentId = Integer.parseInt(request.getParameter("parentId"));

        CategoryDAO dao = new CategoryDAO(request.getServletContext());
        List<Category> subCategories = dao.getSubCategories(parentId);

        request.setAttribute("subCategories", subCategories);
        request.getRequestDispatcher("/admin/subCategory.jsp").forward(request, response);
    }
}

