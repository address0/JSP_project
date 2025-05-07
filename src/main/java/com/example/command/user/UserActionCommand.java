package com.example.command.user;

import com.example.command.Command;
import com.example.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserActionCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String idUser = request.getParameter("idUser");

        UserDAO dao = new UserDAO(request.getServletContext());

        switch (action) {
            case "approve", "activate" -> dao.updateStatus(idUser, "ST01");
            case "suspend" -> dao.updateStatus(idUser, "ST03");
            case "delete" -> dao.deleteUser(idUser);
        }

        response.sendRedirect("/user/list.do");
    }
}

