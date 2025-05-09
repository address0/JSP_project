package com.example.command.category;

import com.example.command.Command;
import com.example.dao.CategoryDAO;
import com.example.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;
import com.google.gson.Gson;

public class LevelCategoryCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String levelStr = request.getParameter("level");
        if (levelStr == null || levelStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid level");
            return;
        }

        int level = Integer.parseInt(levelStr);
        CategoryDAO dao = new CategoryDAO(request.getServletContext());
        List<Category> list = dao.getLevelCategoryList(level);

        System.out.println("LevelCategoryCommand: " + list);

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        out.print(gson.toJson(list));
        out.flush();
    }
}
