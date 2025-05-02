package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.example.util.DBUtil;

@WebServlet("/userAction")
public class UserAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");  // approve, suspend, delete, activate
        String idUser = request.getParameter("idUser");

        String sql = switch (action) {
            case "approve" -> "UPDATE TB_USER SET st_status = 'ST01' WHERE id_user = ?";
            case "suspend" -> "UPDATE TB_USER SET st_status = 'ST03' WHERE id_user = ?";
            case "delete"  -> "DELETE FROM TB_USER WHERE id_user = ?";
            case "activate"-> "UPDATE TB_USER SET st_status = 'ST01' WHERE id_user = ?";
            default -> null;
        };

        if (sql == null || idUser == null) {
            response.sendRedirect("userList");
            return;
        }

        try (Connection conn = DBUtil.getConnection(getServletContext());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idUser);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("userList");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}