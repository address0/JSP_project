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
public class SignupOk extends HttpServlet {
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

        try (Connection conn = DBUtil.getConnection(getServletContext())) {
            String checkSql = "SELECT COUNT(*) FROM TB_USER WHERE ID_USER = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, id);
            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next() && checkRs.getInt(1) > 0) {
                response.sendRedirect(request.getContextPath() + "/user/joinResult.jsp?result=duplicate");
                return;
            }

            String countSql = "SELECT COUNT(*) FROM TB_USER";
            PreparedStatement countStmt = conn.prepareStatement(countSql);
            ResultSet rs = countStmt.executeQuery();

            int userCount = 0;
            if (rs.next()) {
                userCount = rs.getInt(1);
            }
            String userType = (userCount == 0) ? "20" : "10";

            String insertSql = "INSERT INTO TB_USER (ID_USER, NM_USER, NM_PASWD, NO_MOBILE, NM_EMAIL, CD_USER_TYPE, ST_STATUS, DA_FIRST_DATE) " +
                    "VALUES (?, ?, ?, ?, ?, ?, 'ST00', SYSDATE)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, id);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, tel);
            pstmt.setString(5, email);
            pstmt.setString(6, userType);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                response.sendRedirect(request.getContextPath() + "/user/joinResult.jsp?result=success");
            } else {
                response.sendRedirect(request.getContextPath() + "/user/joinResult.jsp?result=fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/user/joinResult.jsp?result=error");
        }
    }
}