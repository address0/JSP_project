package com.example.command.category;
import com.example.command.Command;
import com.example.dao.CategoryDAO;
import com.example.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.ServletContext;

public class CategoryUpdateCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int nbCategory = Integer.parseInt(request.getParameter("nbCategory"));

        String nmCategory = request.getParameter("nmCategory");
        String nmExplain = request.getParameter("nmExplain");
        String ynUse = request.getParameter("ynUse");

        Integer cnLevel = null;
        Integer nbParentCategory = null;
        if (request.getParameter("cnLevel") != null && !request.getParameter("cnLevel").isEmpty()) {
            cnLevel = Integer.parseInt(request.getParameter("cnLevel"));
        }
        if (cnLevel != null && cnLevel > 1) {
            String parentStr = request.getParameter("nbParentCategory");
            if (parentStr != null && !parentStr.isEmpty()) {
                nbParentCategory = Integer.parseInt(parentStr);
            }
        }

        ServletContext context = request.getServletContext();
        CategoryDAO dao = new CategoryDAO(context);

        String nmFullCategory = nmCategory;
        if (nbParentCategory != null) {
            Category parent = dao.getCategoryById(nbParentCategory);
            if (parent != null && parent.getNmFullCategory() != null) {
                nmFullCategory = parent.getNmFullCategory() + " > " + nmCategory;
            }
        }

        Category category = new Category();
        category.setNbCategory(nbCategory);
        category.setNbParentCategory(nbParentCategory);
        category.setNmCategory(nmCategory);
        category.setNmFullCategory(nmFullCategory);
        category.setNmExplain(nmExplain);
        category.setYnUse(ynUse);

        int result = dao.updateCategory(category);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/category/topList.do?result=updateSuccess");
        } else {
            response.sendRedirect(request.getContextPath() + "/category/form?id=" + nbCategory + "&result=fail");
        }
    }
}

