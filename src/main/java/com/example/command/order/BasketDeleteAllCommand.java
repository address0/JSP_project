package com.example.command.order;

import com.example.command.Command;
import com.example.dao.CartDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class BasketDeleteAllCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String basketIdParam = request.getParameter("basketId");
        String userId = (String) request.getSession().getAttribute("userId");

        if (basketIdParam == null || userId == null) {
            response.sendRedirect(request.getContextPath() + "/error.jsp?msg=" + URLEncoder.encode("요청이 잘못되었습니다", "UTF-8"));
            return;
        }

        int basketId = Integer.parseInt(basketIdParam);
        ServletContext context = request.getServletContext();
        CartDAO dao = new CartDAO(context);

        boolean success = dao.deleteAllItemsByBasketId(basketId);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/basket/list.do?id=" + userId);
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp?msg=" + URLEncoder.encode("장바구니 비우기 실패", "UTF-8"));
        }
    }
}
