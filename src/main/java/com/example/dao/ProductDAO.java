package com.example.dao;

import com.example.model.Product;
import com.example.util.DBUtil;
import jakarta.servlet.ServletContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                product.setDtStartDate(rs.getDate("dt_start_date"));
                product.setDtEndDate(rs.getDate("dt_end_date"));
                product.setQtCustomer(rs.getInt("qt_customer_price"));
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

    public List<Product> getProductsSortedBy(String sortType, boolean onlySale) {
        List<Product> list = new ArrayList<>();

        String sortSql;
        switch (sortType) {
            case "priceAsc":
                sortSql = "ORDER BY QT_SALE_PRICE ASC";
                break;
            case "priceDesc":
                sortSql = "ORDER BY QT_SALE_PRICE DESC";
                break;
            case "name":
                sortSql = "ORDER BY NM_PRODUCT ASC";
                break;
            case "discount":
                sortSql = "ORDER BY (1 - QT_SALE_PRICE / NULLIF(QT_CUSTOMER_PRICE, 0)) DESC";
                break;
            case "latest":
                sortSql = "ORDER BY DA_FIRST_DATE DESC";
                break;
            case "default":
            default:
                sortSql = "ORDER BY NO_PRODUCT ASC";
                break;
        }

        String whereClause = "";
        if (onlySale) {
            whereClause = "WHERE QT_STOCK > 0 AND DT_START_DATE <= SYSDATE AND DT_END_DATE >= SYSDATE";
        }

        String sql = "SELECT * FROM TB_PRODUCT " + whereClause + " " + sortSql;

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setNoProduct(rs.getInt("NO_PRODUCT"));
                product.setNmProduct(rs.getString("NM_PRODUCT"));
                product.setNmDetailExplain(rs.getString("NM_DETAIL_EXPLAIN"));
                product.setQtSalePrice(rs.getInt("QT_SALE_PRICE"));
                product.setQtCustomer(rs.getInt("QT_CUSTOMER_PRICE"));
                product.setQtStock(rs.getInt("QT_STOCK"));
                product.setIdFile(rs.getString("ID_FILE"));
                product.setDaFirstDate(rs.getDate("DA_FIRST_DATE"));
                product.setDtStartDate(rs.getDate("DT_START_DATE"));
                product.setDtEndDate(rs.getDate("DT_END_DATE"));
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Product getProductById(int productId) {
        Product product = null;
        String sql = "SELECT * FROM TB_PRODUCT WHERE no_product = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setNoProduct(rs.getInt("no_product"));
                    product.setNmProduct(rs.getString("nm_product"));
                    product.setNmDetailExplain(rs.getString("nm_detail_explain"));
                    product.setIdFile(rs.getString("id_file"));
                    product.setDtStartDate(rs.getDate("dt_start_date"));
                    product.setDtEndDate(rs.getDate("dt_end_date"));
                    product.setQtCustomer(rs.getInt("qt_customer_price"));
                    product.setQtSalePrice(rs.getInt("qt_sale_price"));
                    product.setQtStock(rs.getInt("qt_stock"));
                    product.setQtDeliveryFee(rs.getInt("qt_delivery_fee"));
                    product.setNoRegister(rs.getString("no_register"));
                    product.setDaFirstDate(rs.getDate("da_first_date"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public int addProduct(Product product) {
        String sql = "INSERT INTO TB_PRODUCT (no_product, nm_product, nm_detail_explain, id_file, dt_start_date, dt_end_date, " +
                "qt_customer_price, qt_sale_price, qt_stock, qt_delivery_fee, no_register, da_first_date) " +
                "VALUES (SEQ_TB_PRODUCT.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, SYSDATE)";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getNmProduct());
            pstmt.setString(2, product.getNmDetailExplain());
            pstmt.setString(3, product.getIdFile());
            pstmt.setDate(4, product.getDtStartDate());
            pstmt.setDate(5, product.getDtEndDate());
            pstmt.setInt(6, product.getQtCustomer());
            pstmt.setInt(7, product.getQtSalePrice());
            pstmt.setInt(8, product.getQtStock());
            pstmt.setInt(9, product.getQtDeliveryFee());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE TB_PRODUCT SET nm_product = ?, nm_detail_explain = ?, id_file = ?, dt_start_date = ?, dt_end_date = ?, qt_customer_price = ?, qt_sale_price = ?, qt_stock = ?, qt_delivery_fee = ? WHERE no_product = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getNmProduct());
            pstmt.setString(2, product.getNmDetailExplain());
            pstmt.setString(3, product.getIdFile());
            pstmt.setDate(4, product.getDtStartDate());
            pstmt.setDate(5, product.getDtEndDate());
            pstmt.setInt(6, product.getQtCustomer());
            pstmt.setInt(7, product.getQtSalePrice());
            pstmt.setInt(8, product.getQtStock());
            pstmt.setInt(9, product.getQtDeliveryFee());
            pstmt.setInt(10, product.getNoProduct());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int deleteProduct(int productId) {
        String sql = "DELETE FROM TB_PRODUCT WHERE no_product = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean updateProductEndDate(Integer productCode) {
        String sql = "UPDATE TB_PRODUCT SET dt_end_date = ? WHERE no_product = ?";
        String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, yesterday);
            pstmt.setInt(2, productCode);

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProductStock(Integer productCode) {
        String sql = "UPDATE TB_PRODUCT SET qt_stock = 0 WHERE no_product = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productCode);

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
