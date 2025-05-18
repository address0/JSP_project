package com.example.command.category;
import com.example.command.Command;
import com.example.dao.CategoryDAO;
import com.example.model.Category;
import com.google.gson.Gson;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CategorySubListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        CategoryDAO dao = new CategoryDAO(context);

        String parentIdParam = request.getParameter("parentId");
        int parentId = Integer.parseInt(parentIdParam);

        List<Category> subList = dao.getCategoriesByParent(parentId);  // 이미 구현된 메서드

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.print(gson.toJson(subList));
        out.flush();
    }
}