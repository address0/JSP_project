package com.example.command.user;

import com.example.command.Command;
import com.example.dao.UserDAO;
import com.example.model.User;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserSignupCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        User user = new User();
        user.setIdUser(request.getParameter("email"));
        user.setNmUser(request.getParameter("username"));
        user.setNmPaswd(request.getParameter("password"));
        user.setNoMobile(request.getParameter("tel"));
        user.setNmEmail(request.getParameter("email"));

        ServletContext context = request.getServletContext();
        UserDAO dao = new UserDAO(context);

        if (dao.isUserExists(user.getIdUser())) {
            response.sendRedirect(request.getContextPath() + "/user/joinResult.jsp?result=duplicate");
            return;
        }

        String userType = dao.getAllUsers().isEmpty() ? "20" : "10";
        boolean success = dao.insertUser(user, userType);

        response.sendRedirect(request.getContextPath() + "/user/joinResult.jsp?result=" + (success ? "success" : "fail"));
    }
}

