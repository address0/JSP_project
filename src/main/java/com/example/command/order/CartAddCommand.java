package com.example.command.order;

import com.example.command.Command;
import com.example.dao.CartDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class CartAddCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        ServletContext context = request.getServletContext();
        CartDAO dao = new CartDAO(context);

        String productId = request.getParameter("productId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        String message;

        if (userId == null || userId.isEmpty()) {
            message = "로그인이 필요합니다.";
        } else {
            boolean success = dao.addToCart(userId, productId, quantity);
            message = success ? "장바구니에 담겼습니다." : "오류가 발생했습니다.";
        }

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write(message);
    }
}
