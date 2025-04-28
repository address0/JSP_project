package com.example.servlet;
import com.example.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/signup")
public class Signup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");

        try (Connection conn = DBUtil.getConnection()) {
            String countSql = "SELECT COUNT(*) FROM TB_USER";
            PreparedStatement countStmt = conn.prepareStatement(countSql);
            ResultSet rs = countStmt.executeQuery();

            int userCount = 0;
            if (rs.next()) {
                userCount = rs.getInt(1);
            }
            String userType = (userCount == 0) ? "20" : "10";

            String insertSql = "INSERT INTO TB_USER (ID_USER, NM_USER, NM_PASWD, NO_MOBILE, NM_EMAIL, CD_USER_TYPE, ST_STATUS, DA_FIRST_DATE) " +
                    "VALUES (?, ?, ?, ?, ?, ?, 'ST01', SYSDATE)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, id);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, tel);
            pstmt.setString(5, email);
            pstmt.setString(6, userType);

            pstmt.executeUpdate();

            response.sendRedirect(request.getContextPath() + "/register.jsp?result=success");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/register.jsp?result=fail"); // 에러 발생
        }
    }
}