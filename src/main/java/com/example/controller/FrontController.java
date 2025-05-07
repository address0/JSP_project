package com.example.controller;

import com.example.command.Command;
import com.example.command.user.*;
import com.example.command.product.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

    private final Map<String, Command> commandMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commandMap.put("/user/loginForm.do", new UserLoginFormCommand());
        commandMap.put("/user/login.do", new UserLoginCommand());
        commandMap.put("/user/signup.do", new UserSignupCommand());
        commandMap.put("/user/delete.do", new UserDeleteCommand());
        commandMap.put("/user/profile.do", new UserProfileCommand());
        commandMap.put("/user/update.do", new UserUpdateCommand());
        commandMap.put("/user/list.do", new UserListCommand());
        commandMap.put("/user/action.do", new UserActionCommand());
        commandMap.put("/user/logout.do", new UserLogoutCommand());
        commandMap.put("/user/join.do", new UserJoinFormCommand());

        commandMap.put("/product/list.do", new ProductListCommand());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();
        Command command = commandMap.get(path);

        if (command != null) {
            command.execute(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청을 처리할 수 없습니다: " + path);
        }
    }
}
