package com.example.dao;

import com.example.model.CategoryProductMap;
import jakarta.servlet.ServletContext;
import com.example.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class CategoryMapDAO {
    private final ServletContext context;

    public CategoryMapDAO(ServletContext context) {
        this.context = context;
    }

    public List<CategoryProductMap> findAllCategoryMaps() {
        List<CategoryProductMap> categoryProductMaps = new ArrayList<>();
        String sql = "SELECT * FROM TB_CATEGORY_PRODUCT_MAPPING";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                CategoryProductMap categoryProductMap = new CategoryProductMap();
                categoryProductMap.setNoProduct(rs.getString("no_product"));
                categoryProductMap.setNbCategory(rs.getInt("nb_category"));
                categoryProductMap.setNoRegister(rs.getString("no_register"));
                categoryProductMap.setDaFirstDate(rs.getDate("da_first_date"));
                categoryProductMaps.add(categoryProductMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryProductMaps;
    }

    public List<CategoryProductMap> findCategoryMapByProductId(int productId) {
        List<CategoryProductMap> categoryProductMaps = new ArrayList<>();
        String sql = "SELECT * FROM TB_CATEGORY_PRODUCT_MAPPING WHERE no_product = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    CategoryProductMap categoryProductMap = new CategoryProductMap();
                    categoryProductMap.setNoProduct(rs.getString("no_product"));
                    categoryProductMap.setNbCategory(rs.getInt("nb_category"));
                    categoryProductMap.setNoRegister(rs.getString("no_register"));
                    categoryProductMap.setDaFirstDate(rs.getDate("da_first_date"));
                    categoryProductMaps.add(categoryProductMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryProductMaps;
    }

    public List<CategoryProductMap> findCategoryMapsByCategoryId(int categoryId) {
        List<CategoryProductMap> categoryProductMaps = new ArrayList<>();
        String sql = "SELECT * FROM TB_CATEGORY_PRODUCT_MAPPING WHERE nb_category = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CategoryProductMap categoryProductMap = new CategoryProductMap();
                    categoryProductMap.setNoProduct(rs.getString("no_product"));
                    categoryProductMap.setNbCategory(rs.getInt("nb_category"));
                    categoryProductMap.setNoRegister(rs.getString("no_register"));
                    categoryProductMap.setDaFirstDate(rs.getDate("da_first_date"));
                    categoryProductMaps.add(categoryProductMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryProductMaps;
    }

    public boolean addCategoryMap(CategoryProductMap categoryProductMap) {
        String sql = "INSERT INTO TB_CATEGORY_PRODUCT_MAPPING (no_product, nb_category, cn_order, no_register) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoryProductMap.getNoProduct());
            pstmt.setInt(2, categoryProductMap.getNbCategory());
            pstmt.setInt(3, categoryProductMap.getCnOrder());
            pstmt.setString(4, categoryProductMap.getNoRegister());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getMaxOrderForCategory(int categoryId) {
        int maxOrder = 0;
        String sql = "SELECT NVL(MAX(cn_order), 0) FROM TB_CATEGORY_PRODUCT_MAPPING WHERE nb_category = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    maxOrder = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxOrder;
    }

    public boolean deleteCategoryMap(String productId, int categoryId) {
        String sql = "DELETE FROM TB_CATEGORY_PRODUCT_MAPPING WHERE no_product = ? AND nb_category = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productId);
            pstmt.setInt(2, categoryId);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
