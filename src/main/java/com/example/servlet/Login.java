package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;

import java.io.PrintWriter;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}