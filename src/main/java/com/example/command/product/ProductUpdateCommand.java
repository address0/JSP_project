package com.example.command.product;

import com.example.command.Command;
import com.example.dao.ContentDAO;
import com.example.dao.ProductDAO;
import com.example.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import jakarta.servlet.http.Part;

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
        ContentDAO contentDAO = new ContentDAO(request.getServletContext());
        String updateStatus = request.getParameter("updateStatus");

        if (updateStatus == null || updateStatus.isEmpty()) {
            String nmProduct = request.getParameter("nmProduct");
            String nmDetailExplain = request.getParameter("nmDetailExplain");
            String dtStartDate = request.getParameter("dtStartDate");
            String dtEndDate = request.getParameter("dtEndDate");
            Integer qtCustomerPrice = Integer.parseInt(request.getParameter("qtCustomer"));
            Integer qtSalePrice = Integer.parseInt(request.getParameter("qtSalePrice"));
            Integer qtStock = Integer.parseInt(request.getParameter("qtStock"));
            Integer qtDeliveryFee = Integer.parseInt(request.getParameter("qtDeliveryFee"));

            // 파일 관련
            String idFile = UUID.randomUUID().toString(); // 기존 이미지 ID
            Part filePart = request.getPart("idFile");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            if (fileName != null && !fileName.isBlank()) {
                // 새 이미지 업로드 시 갱신
                String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
                String saveFileName = UUID.randomUUID().toString() + "." + extension;
                String relativePath = "/upload/images/";
                String realPath = request.getServletContext().getRealPath(relativePath);

                File uploadDir = new File(realPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                System.out.println("fileName: " + fileName);

                String fullPath = realPath + File.separator + saveFileName;
                System.out.println("fullPath: " + fullPath);
                byte[] fileBytes = filePart.getInputStream().readAllBytes();
                filePart.write(fullPath);

                com.example.model.Content content = new com.example.model.Content();
                content.setIdFile(idFile); // 그대로 사용
                content.setNmOrgFile(fileName);
                content.setNmSaveFile(saveFileName);
                content.setNmFilePath(relativePath + saveFileName);
                content.setBoSaveFile(fileBytes);
                content.setNmFileExt(extension);
                content.setCdFileType("IMG");
                content.setDaFirstDate(new Date());

                contentDAO.insertContent(content); // 이미지 갱신
            }

            Product product = new Product();
            product.setNoProduct(noProduct);
            product.setNmProduct(nmProduct);
            product.setNmDetailExplain(nmDetailExplain);
            product.setIdFile(idFile);
            product.setDtStartDate(java.sql.Date.valueOf(dtStartDate));
            product.setDtEndDate(java.sql.Date.valueOf(dtEndDate));
            product.setQtCustomer(qtCustomerPrice);
            product.setQtSalePrice(qtSalePrice);
            product.setQtStock(qtStock);
            product.setQtDeliveryFee(qtDeliveryFee);

            boolean updateResult = productDAO.updateProduct(product);
            if (!updateResult) {
                response.sendRedirect(request.getContextPath() + "/product/list.do");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/product/detail.do?id=" + noProduct);
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
            response.sendRedirect(request.getContextPath() + "/product/list.do");
        }
    }
}
