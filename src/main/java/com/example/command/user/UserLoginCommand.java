package com.example.command.user;

import com.example.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;
import com.example.dao.UserDAO;
import com.example.model.User;
import java.io.IOException;

public class UserLoginCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ServletContext context = request.getServletContext();
        UserDAO dao = new UserDAO(context);
        User user = dao.login(email, password);

        String result;
        HttpSession session = request.getSession();

        if (user != null) {
            if ("20".equals(user.getCdUserType())) {
                result = "admin";
                session.setAttribute("status", "admin");
            } else if ("ST00".equals(user.getStStatus())) {
                result = "inactive";
                session.setAttribute("status", "inactive");
            } else {
                session.setAttribute("status", "user");
                result = "success";
            }
            session.setAttribute("username", user.getNmUser());
            session.setAttribute("userId", user.getIdUser());
        } else {
            result = "fail";
        }

        // JSP로 포워딩 (forward 방식)
        response.sendRedirect(request.getContextPath() + "/user/loginResult.jsp?result=" + result);

    }
}
