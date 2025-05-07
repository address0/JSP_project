package com.example.dao;

import com.example.model.Category;
import com.example.util.DBUtil;
import jakarta.servlet.ServletContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private final ServletContext context;

    public CategoryDAO(ServletContext context) {
        this.context = context;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM TB_CATEGORY";

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setNbCategory(rs.getInt("nb_category"));
                category.setNbParentCategory(rs.getObject("nb_parent_category") != null ? rs.getInt("nb_parent_category") : null);
                category.setNmCategory(rs.getString("nm_category"));
                category.setNmFullCategory(rs.getString("nm_full_category"));
                category.setMnExplain(rs.getString("nm_explain"));
                category.setCnOrder(rs.getObject("cn_order") != null ? rs.getInt("cn_order") : null);
                category.setYnUse(rs.getString("yn_use"));
                category.setYnDelete(rs.getString("yn_delete"));
                category.setNoRegister(rs.getString("no_register"));
                category.setDaFirstDate(rs.getDate("da_first_date"));

                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Category> getTopCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM TB_CATEGORY WHERE nb_parent_category IS NULL ORDER BY cn_order";

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setNbCategory(rs.getInt("nb_category"));
                category.setNbParentCategory(null); // 상위 없음
                category.setNmCategory(rs.getString("nm_category"));
                category.setNmFullCategory(rs.getString("nm_full_category"));
                category.setMnExplain(rs.getString("nm_explain"));
                category.setCnOrder(rs.getObject("cn_order") != null ? rs.getInt("cn_order") : null);
                category.setYnUse(rs.getString("yn_use"));
                category.setYnDelete(rs.getString("yn_delete"));
                category.setNoRegister(rs.getString("no_register"));
                category.setDaFirstDate(rs.getDate("da_first_date"));

                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }


    public List<Category> getSubCategories(int parentId) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM TB_CATEGORY WHERE nb_parent_category = ? ORDER BY cn_order";

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, parentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setNbCategory(rs.getInt("nb_category"));
                    category.setNbParentCategory(rs.getObject("nb_parent_category") != null ? rs.getInt("nb_parent_category") : null);
                    category.setNmCategory(rs.getString("nm_category"));
                    category.setNmFullCategory(rs.getString("nm_full_category"));
                    category.setMnExplain(rs.getString("nm_explain"));
                    category.setCnOrder(rs.getObject("cn_order") != null ? rs.getInt("cn_order") : null);
                    category.setYnUse(rs.getString("yn_use"));
                    category.setYnDelete(rs.getString("yn_delete"));
                    category.setNoRegister(rs.getString("no_register"));
                    category.setDaFirstDate(rs.getDate("da_first_date"));

                    categories.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

}
