package com.example.controller;

import com.example.command.Command;
import com.example.command.categoryMap.*;
import com.example.command.user.*;
import com.example.command.product.*;
import com.example.command.category.*;
import com.example.command.main.*;
import com.example.command.order.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
@MultipartConfig(maxFileSize = 10 * 1024 * 1024)
public class FrontController extends HttpServlet {

    private final Map<String, Command> commandMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commandMap.put("/user/loginForm.do", new UserLoginFormCommand());
        commandMap.put("/user/login.do", new UserLoginCommand());
        commandMap.put("/user/signup.do", new UserSignupCommand());
        commandMap.put("/user/delete.do", new UserDeleteCommand());
        commandMap.put("/user/profile.do", new UserProfileCommand());
        commandMap.put("/user/update.do", new UserUpdateCommand());
        commandMap.put("/user/list.do", new UserListCommand());
        commandMap.put("/user/action.do", new UserActionCommand());
        commandMap.put("/user/logout.do", new UserLogoutCommand());
        commandMap.put("/user/join.do", new UserJoinFormCommand());

        commandMap.put("/product/list.do", new ProductListCommand());
        commandMap.put("/product/detail.do", new ProductDetailCommand());
        commandMap.put("/product/createForm.do", new ProductCreateFormCommand());
        commandMap.put("/product/create.do", new ProductCreateCommand());
        commandMap.put("/product/updateForm.do", new ProductUpdateFormCommand());
        commandMap.put("/product/update.do", new ProductUpdateCommand());
        commandMap.put("/product/delete.do", new ProductDeleteCommand());
        commandMap.put("/product/image.do", new ProductImageCommand());
        commandMap.put("/product/categoryList.do", new CategoryProductViewCommand());

        commandMap.put("/category/list.do", new CategoryListCommand());
        commandMap.put("/category/topList.do", new TopCategoryCommand());
        commandMap.put("/category/subList.do", new SubCategoryCommand());
        commandMap.put("/category/form.do", new CategoryFormCommand());
        commandMap.put("/category/create.do", new CategoryCreateCommand());
        commandMap.put("/category/level.do", new LevelCategoryCommand());
        commandMap.put("/category/updateForm.do", new CategoryUpdateFormCommand());
        commandMap.put("/category/update.do", new CategoryUpdateCommand());

        commandMap.put("/categoryMap/mapForm.do", new MapFormCommand());
        commandMap.put("/categoryMap/update.do", new MapUpdateCommand());
        commandMap.put("/category/subListJson.do", new CategorySubListCommand());

        commandMap.put("/main.do", new MainPageCommend());

        commandMap.put("/cart/add.do", new CartAddCommand());
        commandMap.put("/basket/list.do", new BasketListCommand());
        commandMap.put("/basket/delete.do", new BasketDeleteCommand());
        commandMap.put("/basket/updateQty.do", new BasketUpdateQtyCommand());
        commandMap.put("/basket/deleteAll.do", new BasketDeleteAllCommand());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();
        Command command = commandMap.get(path);

        if (command != null) {
            command.execute(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청을 처리할 수 없습니다: " + path);
        }
    }
}
