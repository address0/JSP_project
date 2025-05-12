package com.example.command.category;

import com.example.command.Command;
import com.example.dao.CategoryDAO;
import com.example.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.ServletContext;

public class CategoryCreateCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = request.getServletContext();
        CategoryDAO categoryDAO = new CategoryDAO(context);

        // 1. 파라미터 수집
        String nmCategory = request.getParameter("nmCategory");
        String nmExplain = request.getParameter("nmExplain");
        String ynUse = request.getParameter("ynUse");
        String ynDelete = "N"; // 항상 N
        String noRegister = "admin"; // 임시 하드코딩 (로그인 연동 시 수정)

        Integer cnLevel = null;
        Integer nbParentCategory = 0;
        if (request.getParameter("cnLevel") != null && !request.getParameter("cnLevel").isEmpty()) {
            cnLevel = Integer.parseInt(request.getParameter("cnLevel"));
        }
        if (cnLevel != null && cnLevel > 1) {
            String parentStr = request.getParameter("nbParentCategory");
            if (parentStr != null && !parentStr.isEmpty()) {
                nbParentCategory = Integer.parseInt(parentStr);
            }
        }

        // 2. 상위 카테고리 기반 nmFullCategory 계산
        String nmFullCategory = nmCategory;
        Integer nbGroup = 0;
        if (nbParentCategory != 0) {
            Category parent = categoryDAO.getParentCategoryById(nbParentCategory);
            if (parent != null && parent.getNmFullCategory() != null) {
                nmFullCategory = parent.getNmFullCategory() + " > " + nmCategory;
                nbGroup = parent.getNbGroup();
                System.out.println("nbGroup: " + nbGroup);
            }
        }

        // 3. cnOrder 자동 계산 (현재 레벨에서 가장 큰 값 + 1)
        int cnOrder = categoryDAO.getNextOrderInLevel(cnLevel);

        // 4. Category 객체 생성
        Category category = new Category();
        category.setNbParentCategory(nbParentCategory);
        category.setNmCategory(nmCategory);
        category.setNmFullCategory(nmFullCategory);
        category.setNmExplain(nmExplain);
        category.setCnLevel(cnLevel);
        category.setCnOrder(cnOrder);
        category.setYnUse(ynUse);
        category.setYnDelete(ynDelete);
        category.setNoRegister(noRegister);
        category.setNbGroup(nbGroup);

        // 5. 등록
        int result = categoryDAO.addCategory(category);
        request.setAttribute("result", result > 0 ? "success" : "fail");

        response.sendRedirect("/category/topList.do");
    }
}
