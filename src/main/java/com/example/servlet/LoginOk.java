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
                String userType = rs.getString("cd_user_type");
                if ("20".equals(userType)) {
                    response.sendRedirect(request.getContextPath() + "/user/loginResult.jsp?result=admin");
                    return;
                }
                String status = rs.getString("st_status");
                if ("ST00".equals(status)) {
                    response.sendRedirect(request.getContextPath() + "/user/loginResult.jsp?result=inactive");
                    return;
                }

                HttpSession session = request.getSession();
                session.setAttribute("id", rs.getString("id_user"));

                response.sendRedirect(request.getContextPath() + "/user/loginResult.jsp?result=success");
            } else {
                response.sendRedirect(request.getContextPath() + "/user/loginResult.jsp?result=fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/user/loginResult.jsp?result=error");
        }
    }
}