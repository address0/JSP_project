package com.example.command.user;

import com.example.command.Command;
import com.example.dao.UserDAO;
import com.example.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class UserListCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDAO dao = new UserDAO(request.getServletContext());
        List<User> userList = dao.getAllUsers();

        request.setAttribute("userList", userList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/manage.jsp?managePage=userManage");
        dispatcher.forward(request, response);
    }
}

