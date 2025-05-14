package com.example.command.user;

import com.example.command.Command;
import com.example.dao.UserDAO;
import com.example.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserProfileCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = (String) request.getSession().getAttribute("userId");

        UserDAO dao = new UserDAO(request.getServletContext());
        User user = dao.getUserById(id);

        if (user != null) {
            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("/main/modify.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/user/login.jsp");
        }
    }
}

