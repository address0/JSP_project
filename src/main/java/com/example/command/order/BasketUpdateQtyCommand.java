package com.example.command.order;
import com.example.command.Command;
import com.example.dao.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BasketUpdateQtyCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int nbBasketItem = Integer.parseInt(request.getParameter("nbBasketItem"));
        int newQty = Integer.parseInt(request.getParameter("quantity"));
        String userId = (String) request.getSession().getAttribute("userId");

        CartDAO dao = new CartDAO(request.getServletContext());
        boolean success = dao.updateBasketItemQty(nbBasketItem, newQty);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/basket/list.do?id=" + userId);
        } else {
            // 재고 초과 또는 실패 메시지 전달
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
