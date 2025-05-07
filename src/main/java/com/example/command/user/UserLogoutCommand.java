package com.example.command.user;

import com.example.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher("/user/logout.jsp").forward(request, response);
    }
}
