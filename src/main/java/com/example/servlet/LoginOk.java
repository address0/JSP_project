package com.example.servlet;

import com.example.util.DBUtil;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;

import com.example.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String sql = "SELECT * FROM TB_USER WHERE id_user = ? AND nm_paswd = ?";
        try (Connection conn = DBUtil.getConnection(getServletContext());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("st_status");

                if ("ST02".equals(status)) {
                    System.out.println("⚠️ 탈퇴 처리된 계정입니다.");
                    return;
                }
                User user = new User();
                user.setIdUser(rs.getString("id_user"));
                user.setNmUser(rs.getString("nm_user"));
                user.setNmPaswd(rs.getString("nm_paswd"));
                user.setNoMobile(rs.getString("no_mobile"));
                user.setNmEmail(rs.getString("nm_email"));
                user.setCdUserType(rs.getString("cd_user_type"));

                HttpSession session = request.getSession();
                session.setAttribute("loginUser", user);

                response.sendRedirect("index.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}