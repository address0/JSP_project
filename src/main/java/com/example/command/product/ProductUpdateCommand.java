package com.example.command.product;

import com.example.command.Command;
import com.example.dao.ProductDAO;
import com.example.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductUpdateCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/product/list.do");
            return;
        }
        int noProduct = Integer.parseInt(idParam);
        ProductDAO productDAO = new ProductDAO(request.getServletContext());
        String updateStatus = request.getParameter("updateStatus");

        if (updateStatus == null || updateStatus.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/product/list.do");
        } else if ("end".equals(updateStatus)) {
            boolean dateResult = productDAO.updateProductEndDate(noProduct);
            if (!dateResult) {
                response.sendRedirect(request.getContextPath() + "/product/list.do");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/product/detail.do?id=" + noProduct);
        } else if ("stock".equals(updateStatus)) {
            boolean stockResult = productDAO.updateProductStock(noProduct);
            if (!stockResult) {
                response.sendRedirect(request.getContextPath() + "/product/list.do");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/product/detail.do?id=" + noProduct);
        } else {
            String nmProduct = request.getParameter("nmProduct");
            String nmDetailExplain = request.getParameter("nmDetailExplain");
            String idFile = request.getParameter("idFile");
            String dtStartDate = request.getParameter("dtStartDate");
            String dtEndDate = request.getParameter("dtEndDate");
            Integer qtCustomerPrice = Integer.parseInt(request.getParameter("qtCustomerPrice"));
            Integer qtSalePrice = Integer.parseInt(request.getParameter("qtSalePrice"));
            Integer qtStock = Integer.parseInt(request.getParameter("qtStock"));
            Integer qtDeliveryFee = Integer.parseInt(request.getParameter("qtDeliveryFee"));
            Product product = new Product();
            product.setNoProduct(noProduct);
            product.setNmProduct(nmProduct);
            product.setNmDetailExplain(nmDetailExplain);
            product.setIdFile(idFile);
            product.setDtStartDate(java.sql.Date.valueOf(dtStartDate));
            product.setDtEndDate(java.sql.Date.valueOf(dtEndDate));
            product.setQtCustomerPrice(qtCustomerPrice);
            product.setQtSalePrice(qtSalePrice);
            product.setQtStock(qtStock);
            product.setQtDeliveryFee(qtDeliveryFee);

            boolean updateResult = productDAO.updateProduct(product);
            if (!updateResult) {
                response.sendRedirect(request.getContextPath() + "/product/list.do");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/product/detail.do?id=" + noProduct);
        }
    }
}
