package com.example.command.order;
import com.example.command.Command;
import com.example.dao.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import com.example.dao.ProductDAO;
import com.example.model.Product;

public class OrderFormCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] itemIds = request.getParameterValues("basketItems"); // 장바구니 → 다중
        String productId = request.getParameter("productId");         // 단일 상품

        ProductDAO productDAO = new ProductDAO(request.getServletContext());
        List<Product> productList = new ArrayList<>();

        if (productId != null) {
            Product p = productDAO.getProductById(Integer.parseInt(productId));
            if (p != null) productList.add(p);
        } else if (itemIds != null) {
            CartDAO cartDAO = new CartDAO(request.getServletContext());
            for (String itemId : itemIds) {
                Product p = cartDAO.getProductFromBasketItemId(Integer.parseInt(itemId));
                if (p != null) productList.add(p);
            }
        }

        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/order/orderForm.jsp").forward(request, response);
    }
}

