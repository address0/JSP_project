package com.example.dao;

import com.example.model.Category;
import com.example.util.DBUtil;
import jakarta.servlet.ServletContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

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
                category.setNmExplain(rs.getString("nm_explain"));
                category.setCnOrder(rs.getObject("cn_order") != null ? rs.getInt("cn_order") : null);
                category.setYnUse(rs.getString("yn_use"));
                category.setYnDelete(rs.getString("yn_delete"));
                category.setNoRegister(rs.getString("no_register"));
                category.setDaFirstDate(rs.getDate("da_first_date"));
                category.setNbGroup(rs.getInt("nb_group"));
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
                category.setNmExplain(rs.getString("nm_explain"));
                category.setCnOrder(rs.getObject("cn_order") != null ? rs.getInt("cn_order") : null);
                category.setYnUse(rs.getString("yn_use"));
                category.setYnDelete(rs.getString("yn_delete"));
                category.setNoRegister(rs.getString("no_register"));
                category.setDaFirstDate(rs.getDate("da_first_date"));
                category.setNbGroup(rs.getInt("nb_group"));

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
                    category.setNmExplain(rs.getString("nm_explain"));
                    category.setCnOrder(rs.getObject("cn_order") != null ? rs.getInt("cn_order") : null);
                    category.setYnUse(rs.getString("yn_use"));
                    category.setYnDelete(rs.getString("yn_delete"));
                    category.setNoRegister(rs.getString("no_register"));
                    category.setDaFirstDate(rs.getDate("da_first_date"));
                    category.setNbGroup(rs.getInt("nb_group"));

                    categories.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public int addCategory(Category category) {

        String sql;

        if (category.getNbParentCategory() == 0) {
            sql = "INSERT INTO TB_CATEGORY"+
                    "(nb_category, nb_parent_category, nm_category, nm_full_category, "+
                    "nm_explain, cn_level, cn_order, yn_use, yn_delete, no_register, nb_group)"+
                    "VALUES (SEQ_TB_CATEGORY.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SEQ_TB_CATEGORY.NEXTVAL)";
        } else {
            sql = "INSERT INTO TB_CATEGORY"+
                    "(nb_category, nb_parent_category, nm_category, nm_full_category, "+
                    "nm_explain, cn_level, cn_order, yn_use, yn_delete, no_register, nb_group)"+
                    "VALUES (SEQ_TB_CATEGORY.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, category.getNbParentCategory());
            pstmt.setString(2, category.getNmCategory());
            pstmt.setString(3, category.getNmFullCategory());
            pstmt.setString(4, category.getNmExplain());
            pstmt.setObject(5, category.getCnLevel());
            pstmt.setObject(6, category.getCnOrder());
            pstmt.setString(7, category.getYnUse());
            pstmt.setString(8, category.getYnDelete());
            pstmt.setString(9, category.getNoRegister());
            if (category.getNbGroup() != 0) {
                pstmt.setInt(10, category.getNbGroup());
            }

            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Category> getLevelCategoryList(int level) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM TB_CATEGORY WHERE cn_level = ?";

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, level);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setNbCategory(rs.getInt("nb_category"));
                    category.setNmCategory(rs.getString("nm_category"));
                    categories.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category getParentCategoryById(int id) {
        String sql = "SELECT * FROM TB_CATEGORY WHERE nb_category = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Category category = new Category();
                    category.setNbCategory(rs.getInt("nb_category"));
                    category.setNmFullCategory(rs.getString("nm_full_category"));
                    category.setNbGroup(rs.getInt("nb_group"));
                    return category;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM TB_CATEGORY WHERE nb_category = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Category category = new Category();
                    category.setNbCategory(rs.getInt("nb_category"));
                    category.setNmCategory(rs.getString("nm_category"));
                    category.setNmFullCategory(rs.getString("nm_full_category"));
                    category.setNmExplain(rs.getString("nm_explain"));
                    category.setCnLevel(rs.getObject("cn_level") != null ? rs.getInt("cn_level") : null);
                    category.setCnOrder(rs.getObject("cn_order") != null ? rs.getInt("cn_order") : null);
                    category.setNbGroup(rs.getInt("nb_group"));
                    return category;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getNextOrderInLevel(int level) {
        String sql = "SELECT NVL(MAX(cn_order), 0) + 1 FROM TB_CATEGORY WHERE cn_level = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, level);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int updateCategory(Category category) {
        String sql = "UPDATE TB_CATEGORY SET " +
                "nb_parent_category = ?, " +
                "nm_category = ?, " +
                "nm_full_category = ?, " +
                "nm_explain = ?, " +
                "yn_use = ? " +
                "WHERE nb_category = ?";

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, category.getNbParentCategory());
            pstmt.setString(2, category.getNmCategory());
            pstmt.setString(3, category.getNmFullCategory());
            pstmt.setString(4, category.getNmExplain());
            pstmt.setString(5, category.getYnUse());
            pstmt.setInt(6, category.getNbCategory());

            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateFullCategoryPathFromRoot(String newFullName, int nbGroup, int baseLevel) {
        String sql = "UPDATE TB_CATEGORY " +
                "SET nm_full_category = ? || ' > ' || nm_category " +
                "WHERE nb_group = ? AND cn_level > ?";

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newFullName);
            pstmt.setInt(2, nbGroup);
            pstmt.setInt(3, baseLevel);

            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
