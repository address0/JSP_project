package com.example.command.categoryMap;

import com.example.command.Command;
import com.example.dao.CategoryMapDAO;
import com.example.model.CategoryProductMap;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class MapUpdateCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        String[] selectedCategoryIds = request.getParameterValues("categoryList");

        System.out.println(selectedCategoryIds[0]);

        CategoryMapDAO categoryMapDAO = new CategoryMapDAO(request.getServletContext());

        List<CategoryProductMap> existingMaps = categoryMapDAO.findCategoryMapByProductId(Integer.parseInt(productId));
        Set<Integer> existingCategoryIds = existingMaps.stream()
                .map(CategoryProductMap::getNbCategory)
                .collect(Collectors.toSet());
        Set<Integer> selectedCategoryIdSet = new HashSet<>();

        if (selectedCategoryIds != null) {
            for (String categoryId : selectedCategoryIds) {
                selectedCategoryIdSet.add(Integer.parseInt(categoryId));
            }
        }

        Set<Integer> categoriesToAdd = new HashSet<>(selectedCategoryIdSet);
        categoriesToAdd.removeAll(existingCategoryIds);

        Set<Integer> categoriesToRemove = new HashSet<>(existingCategoryIds);
        categoriesToRemove.removeAll(selectedCategoryIdSet);

        boolean isSuccess = true;

        for (Integer categoryId : categoriesToRemove) {
            isSuccess &= categoryMapDAO.deleteCategoryMap(productId, categoryId);
        }
        for (Integer categoryId : categoriesToAdd) {
            int cnOrder = categoryMapDAO.getMaxOrderForCategory(categoryId) + 1;
            CategoryProductMap newMap = new CategoryProductMap();
            newMap.setNoProduct(productId);
            newMap.setNbCategory(categoryId);
            newMap.setCnOrder(cnOrder);
            isSuccess &= categoryMapDAO.addCategoryMap(newMap);
        }

        if (isSuccess) {
            response.sendRedirect("/categoryMap/mapForm.do?result=success");
        } else {
            response.sendRedirect("/categoryMap/mapForm.do?result=error");
        }
    }
}
