package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import com.example.bean.User;
import com.example.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userList")
public class UserList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "SELECT * FROM TB_USER";
        try (Connection conn = DBUtil.getConnection(getServletContext());
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                if (rs.getString("cd_user_type").equals("20")) {
                    continue;
                }
                user.setIdUser(rs.getString("id_user"));
                user.setNmEmail(rs.getString("id_user"));
                user.setNmUser(rs.getString("nm_user"));
                user.setNmPaswd(rs.getString("nm_paswd"));
                user.setNoMobile(rs.getString("no_mobile"));
                user.setNmEncPaswd(rs.getString("nm_enc_paswd"));
                user.setStStatus(rs.getString("st_status"));
                user.setCdUserType(rs.getString("cd_user_type"));
                userList.add(user);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/manage.jsp?managePage=userManage");
            request.setAttribute("userList", userList);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/manage.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}