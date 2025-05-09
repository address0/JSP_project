package com.example.command.product;

import com.example.command.Command;
import com.example.dao.ProductDAO;
import com.example.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductUpdateFormCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/product/list.do");
            return;
        }

        int productId = Integer.parseInt(idParam);
        ProductDAO dao = new ProductDAO(request.getServletContext());
        Product product = dao.getProductById(productId);

        if (product == null) {
            response.sendRedirect(request.getContextPath() + "/product/list.do");
            return;
        }

        request.setAttribute("product", product);
        request.getRequestDispatcher("/product/productWrite.jsp").forward(request, response);
    }
}
