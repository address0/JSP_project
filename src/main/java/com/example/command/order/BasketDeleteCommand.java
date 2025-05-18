package com.example.command.order;
import com.example.command.Command;
import com.example.dao.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BasketDeleteCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int nbBasketItem = Integer.parseInt(request.getParameter("nbBasketItem"));
        String userId = (String) request.getSession().getAttribute("userId");

        CartDAO dao = new CartDAO(request.getServletContext());
        boolean success = dao.deleteBasketItem(nbBasketItem);

        // 삭제 후 장바구니 다시 조회
        if (success) {
            response.sendRedirect(request.getContextPath() + "/basket/list.do?id=" + userId);
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
