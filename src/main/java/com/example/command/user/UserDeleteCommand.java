package com.example.command.user;

import com.example.command.Command;
import com.example.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserDeleteCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        UserDAO dao = new UserDAO(request.getServletContext());
        boolean deleted = dao.markUserAsDeleted(id);
        String result = deleted ? "delete" : "fail";

        response.sendRedirect(request.getContextPath() + "/main/modifyResult.jsp?result=" + result);
    }
}

