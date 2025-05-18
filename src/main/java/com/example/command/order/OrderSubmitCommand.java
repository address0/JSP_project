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
import java.util.ArrayList;
import com.example.dao.ProductDAO;
import com.example.model.Product;
import com.example.model.User;
import com.example.model.Order;
import com.example.model.OrderItem;

import java.net.URLEncoder;

public class OrderSubmitCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        ServletContext context = request.getServletContext();
        String userId = (String) request.getSession().getAttribute("userId");

        // 주문자 정보 입력값
        String orderPerson = request.getParameter("nmOrderPerson");
        String receiver = request.getParameter("nmReceiver");
        String zipno = request.getParameter("noDeliveryZipno");
        String address = request.getParameter("nmDeliveryAddress");
        String receiverTel = request.getParameter("nmReceiverTelno");
        String deliverySpace = request.getParameter("nmDeliverySpace");

        // 상품 정보 배열
        String[] productIds = request.getParameterValues("noProduct");
        String[] prices = request.getParameterValues("qtUnitPrice");
        String[] qtys = request.getParameterValues("qtOrderItem");

        int totalAmount = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (int i = 0; i < productIds.length; i++) {
            int unitPrice = Integer.parseInt(prices[i]);
            int quantity = Integer.parseInt(qtys[i]);
            int amount = unitPrice * quantity;

            OrderItem item = new OrderItem();
            item.setNoProduct(productIds[i]);
            item.setQtUnitPrice(unitPrice);
            item.setQtOrderItem(quantity);
            item.setQtOrderItemAmount(amount);
            item.setIdUser(userId);

            orderItems.add(item);
            totalAmount += amount;
        }

        // 주문 정보 DTO
        Order order = new Order();
        order.setIdUser(userId);
        order.setQtOrderAmount(totalAmount);
        order.setNmOrderPerson(orderPerson);
        order.setNmReceiver(receiver);
        order.setNoDeliveryZipno(zipno);
        order.setNmDeliveryAddress(address);
        order.setNmReceiverTelno(receiverTel);
        order.setNmDeliverySpace(deliverySpace);
        order.setCdOrderType("10"); // 일반 주문
        order.setStOrder("10");     // 주문완료
        order.setStPayment("20");   // 결제완료

        // DAO로 insert 처리
        CartDAO dao = new CartDAO(context);
        boolean success = dao.insertOrderWithItems(order, orderItems);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/order/complete.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp?msg=" + URLEncoder.encode("주문 처리 실패", "UTF-8"));
        }
    }
}

