package com.example.command.product;

import com.example.command.Command;
import com.example.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.example.model.Product;
import jakarta.servlet.http.HttpSession;


public class ProductListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO(request.getServletContext());
        List<Product> productList = productDAO.getAllProducts();

        request.setAttribute("productList", productList);
        HttpSession session = request.getSession();
        String userStatus = (String) session.getAttribute("status");

        if ("admin".equals(userStatus)) {
            request.getRequestDispatcher("/admin/manage.jsp?managePage=productManage").forward(request, response);
        } else {
            request.getRequestDispatcher("/admin/manage.jsp").forward(request, response);
        }
    }
}
