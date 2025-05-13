package com.example.command.main;

import com.example.command.Command;
import com.example.dao.ProductDAO;
import com.example.dao.UserDAO;
import com.example.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.model.User;
import java.util.List;

public class MainPageCommend implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userStatus = (String) request.getSession().getAttribute("status");

        if (userStatus != null) {
            String userId = (String) request.getSession().getAttribute("id");
            UserDAO userDAO = new UserDAO(request.getServletContext());
            User user = userDAO.getUserById(userId);
            if (user != null) {
                request.setAttribute("user", user);
            }
        }

        ProductDAO productDAO = new ProductDAO(request.getServletContext());
        List<Product> productList = productDAO.getAllProducts();

        request.setAttribute("productList", productList);

        request.getRequestDispatcher("/main/home.jsp").forward(request, response);
    }
}
