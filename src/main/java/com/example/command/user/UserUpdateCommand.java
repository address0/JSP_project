package com.example.command.user;

import com.example.command.Command;
import com.example.dao.UserDAO;
import com.example.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserUpdateCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setIdUser(request.getParameter("id"));
        user.setNmUser(request.getParameter("username"));
        user.setNoMobile(request.getParameter("phone"));
        user.setNmEmail(request.getParameter("email"));

        UserDAO dao = new UserDAO(request.getServletContext());
        boolean updated = dao.updateUserInfo(user);

        String result = updated ? "success" : "fail";
        response.sendRedirect(request.getContextPath() + "/main/modifyResult.jsp?result=" + result);
    }
}

