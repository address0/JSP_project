package com.example.command.order;
import com.example.command.Command;
import com.example.dao.CartDAO;
import com.example.model.Basket;
import com.example.model.BasketItem;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BasketListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        ServletContext context = request.getServletContext();
        CartDAO dao = new CartDAO(context);

        Basket basket = dao.getBasketByUserId(userId);
        if (basket != null) {
            List<BasketItem> itemList = dao.getBasketItemsWithProduct(basket.getNbBasket());

            for (BasketItem item : itemList) {
                String productName = item.getProduct().getNmProduct();
                int quantity = item.getQtBasketItemQty();
                int totalAmount = item.getQtBasketItemAmount();
            }

            HttpSession session = request.getSession();
            session.setAttribute("basket", basket);
            session.setAttribute("basketItemList", itemList);

            response.sendRedirect(request.getContextPath() + "/order/basketList.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp?msg=장바구니가 없습니다");
        }
    }
}
