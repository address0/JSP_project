package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.example.util.DBUtil;
import java.sql.ResultSet;
import com.example.bean.User;

@WebServlet("/modify")
public class ModifyOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        System.out.println("Method: " + request.getMethod());

        if (action.equals("delete")) {
            String sql = "UPDATE TB_USER SET st_status = 'ST02' WHERE ID_USER = ?";
            try (Connection conn = DBUtil.getConnection(getServletContext());
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, id);
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    response.sendRedirect(request.getContextPath() + "/main/modifyResult.jsp?result=delete");
                } else {
                    response.sendRedirect(request.getContextPath() + "/main/modifyResult.jsp?result=fail");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/main/modifyResult.jsp?result=error");
            }
        } else if (action.equals("profile")) {
            System.out.println("id : " + id);
            String sql = "SELECT * FROM TB_USER WHERE ID_USER = ?";
            try (Connection conn = DBUtil.getConnection(getServletContext());
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    User user = new User();
                    user.setIdUser(rs.getString("ID_USER"));
                    user.setNmUser(rs.getString("nm_user"));
                    user.setNoMobile(rs.getString("no_mobile"));
                    user.setNmEmail(rs.getString("nm_email"));
                    user.setStStatus(rs.getString("st_status"));
                    request.setAttribute("user", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/main/modify.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/user/login.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/user/login.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("username");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        System.out.println("Method: " + request.getMethod());

        String sql = "UPDATE TB_USER SET nm_user = ?, no_mobile = ?, nm_email = ? WHERE ID_USER = ?";
        try (Connection conn = DBUtil.getConnection(getServletContext());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, id);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/main/modifyResult.jsp?result=success");
            } else {
                response.sendRedirect(request.getContextPath() + "/main/modifyResult.jsp?result=fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/main/modifyResult.jsp?result=error");
        }
    }
}