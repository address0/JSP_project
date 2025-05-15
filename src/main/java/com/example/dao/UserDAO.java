package com.example.dao;

import com.example.model.User;
import com.example.util.DBUtil;
import jakarta.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final ServletContext context;

    public UserDAO(ServletContext context) {
        this.context = context;
    }

    public boolean isUserExists(String id) {
        String sql = "SELECT COUNT(*) FROM TB_USER WHERE ID_USER = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            return rs.next() && rs.getInt(1) > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return true; // 기본값으로 중복 방지
        }
    }

    public boolean insertUser(User user, String userType) {
        String sql = "INSERT INTO TB_USER (ID_USER, NM_USER, NM_PASWD, NO_MOBILE, NM_EMAIL, CD_USER_TYPE, ST_STATUS, DA_FIRST_DATE) " +
                "VALUES (?, ?, ?, ?, ?, ?, 'ST00', SYSDATE)";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getIdUser());
            pstmt.setString(2, user.getNmUser());
            pstmt.setString(3, user.getNmPaswd());
            pstmt.setString(4, user.getNoMobile());
            pstmt.setString(5, user.getNmEmail());
            pstmt.setString(6, userType);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User login(String id, String password) {
        String sql = "SELECT * FROM TB_USER WHERE ID_USER = ? AND NM_PASWD = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setIdUser(rs.getString("id_user"));
                    user.setNmUser(rs.getString("nm_user"));
                    user.setCdUserType(rs.getString("cd_user_type"));
                    user.setStStatus(rs.getString("st_status"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM TB_USER";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getString("id_user"));
                user.setNmUser(rs.getString("nm_user"));
                user.setNmPaswd(rs.getString("nm_paswd"));
                user.setNoMobile(rs.getString("no_mobile"));
                user.setNmEmail(rs.getString("nm_email"));
                user.setNmEncPaswd(rs.getString("nm_enc_paswd"));
                user.setStStatus(rs.getString("st_status"));
                user.setCdUserType(rs.getString("cd_user_type"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void updateStatus(String idUser, String status) {
        String sql = "UPDATE TB_USER SET st_status = ? WHERE id_user = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setString(2, idUser);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String idUser) {
        String sql = "DELETE FROM TB_USER WHERE id_user = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idUser);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserById(String id) {
        String sql = "SELECT * FROM TB_USER WHERE ID_USER = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setIdUser(rs.getString("ID_USER"));
                    user.setNmUser(rs.getString("nm_user"));
                    user.setNoMobile(rs.getString("no_mobile"));
                    user.setNmEmail(rs.getString("nm_email"));
                    user.setStStatus(rs.getString("st_status"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUserInfo(User user) {
        String sql = "UPDATE TB_USER SET nm_user = ?, no_mobile = ?, nm_email = ? WHERE ID_USER = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getNmUser());
            pstmt.setString(2, user.getNoMobile());
            pstmt.setString(3, user.getNmEmail());
            pstmt.setString(4, user.getIdUser());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean markUserAsDeleted(String idUser) {
        String sql = "UPDATE TB_USER SET st_status = 'ST02' WHERE ID_USER = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idUser);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
