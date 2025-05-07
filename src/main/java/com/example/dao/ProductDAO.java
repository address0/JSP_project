package com.example.dao;

import com.example.model.Product;
import com.example.util.DBUtil;
import jakarta.servlet.ServletContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final ServletContext context;

    public ProductDAO(ServletContext context) {
        this.context = context;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM TB_PRODUCT";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setNoProduct(rs.getInt("no_product"));
                product.setNmProduct(rs.getString("nm_product"));
                product.setNmDetailExplain(rs.getString("nm_detail_explain"));
                product.setIdFile(rs.getString("id_file"));
                product.setDtStartDate(rs.getString("dt_start_date"));
                product.setDtEndDate(rs.getString("dt_end_date"));
                product.setQtCustomerPrice(rs.getInt("qt_customer_price"));
                product.setQtSalePrice(rs.getInt("qt_sale_price"));
                product.setQtStock(rs.getInt("qt_stock"));
                product.setQtDeliveryFee(rs.getInt("qt_delivery_fee"));
                product.setNoRegister(rs.getString("no_register"));
                product.setDaFirstDate(rs.getDate("da_first_date"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
