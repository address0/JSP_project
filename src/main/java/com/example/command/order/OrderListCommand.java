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
import com.example.model.Order;

public class OrderListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = request.getServletContext();
        CartDAO dao = new CartDAO(context);

        HttpSession session = request.getSession();
        String sessionStatus = (String) session.getAttribute("status");

        List<Order> orders;

        if ("admin".equals(sessionStatus)) {
            orders = dao.getAllOrders();
        } else {
            String userId = (String) session.getAttribute("userId");
            orders = dao.getOrdersByUser(userId);
        }

        request.setAttribute("orderList", orders);
        request.getRequestDispatcher("/order/orderList.jsp").forward(request, response);
    }
}
