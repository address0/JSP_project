package com.example.command.product;

import com.example.command.Command;
import com.example.dao.ContentDAO;
import com.example.dao.ProductDAO;
import com.example.model.Product;
import com.example.model.Content;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.nio.file.Paths;
import java.util.UUID;
import jakarta.servlet.http.Part;
import java.util.Date;
import java.io.File;

public class ProductCreateCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Part filePart = request.getPart("idFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        String saveFileName = UUID.randomUUID().toString() + "." + extension;
        String relativePath = "/upload/images/";

        String realPath = request.getServletContext().getRealPath(relativePath);
        File uploadDir = new File(realPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        byte[] fileBytes = filePart.getInputStream().readAllBytes();

        String idFile = UUID.randomUUID().toString();
        String fullPath = realPath + File.separator + saveFileName;
        filePart.write(fullPath);

        Content content = new Content();
        content.setIdFile(idFile);
        content.setNmOrgFile(fileName);
        content.setNmSaveFile(saveFileName);
        content.setNmFilePath(relativePath + saveFileName);

        System.out.println(realPath);
        content.setBoSaveFile(fileBytes);
        content.setNmFileExt(extension);
        content.setCdFileType("IMG");
        content.setDaFirstDate(new Date());

        ContentDAO contentDAO = new ContentDAO(request.getServletContext());
        contentDAO.insertContent(content);


        String nmProduct = request.getParameter("nmProduct");
        String nmDetailExplain = request.getParameter("nmDetailExplain");
        String dtStartDate = request.getParameter("dtStartDate");
        String dtEndDate = request.getParameter("dtEndDate");
        Integer qtCustomerPrice = Integer.parseInt(request.getParameter("qtCustomer"));
        Integer qtSalePrice = Integer.parseInt(request.getParameter("qtSalePrice"));
        Integer qtStock = Integer.parseInt(request.getParameter("qtStock"));
        Integer qtDeliveryFee = Integer.parseInt(request.getParameter("qtDeliveryFee"));

        Product product = new Product();
        product.setNmProduct(nmProduct);
        product.setNmDetailExplain(nmDetailExplain);
        product.setIdFile(idFile);
        product.setDtStartDate(java.sql.Date.valueOf(dtStartDate));
        product.setDtEndDate(java.sql.Date.valueOf(dtEndDate));
        product.setQtCustomer(qtCustomerPrice);
        product.setQtSalePrice(qtSalePrice);
        product.setQtStock(qtStock);
        product.setQtDeliveryFee(qtDeliveryFee);

        ProductDAO productDAO = new ProductDAO(request.getServletContext());
        productDAO.addProduct(product);

        response.sendRedirect(request.getContextPath() + "/product/list.do");
    }
}
