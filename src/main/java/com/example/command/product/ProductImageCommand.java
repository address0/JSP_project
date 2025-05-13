package com.example.command.product;

import com.example.command.Command;
import com.example.dao.ContentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.model.Content;
import java.io.OutputStream;

public class ProductImageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idFile = request.getParameter("idFile");

        if (idFile == null || idFile.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing idFile");
            return;
        }

        ContentDAO contentDAO = new ContentDAO(request.getServletContext());
        Content content = contentDAO.getContentById(idFile);

        if (content == null || content.getBoSaveFile() == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("image/" + content.getNmFileExt());

        byte[] imageBytes = content.getBoSaveFile();
        OutputStream out = response.getOutputStream();
        out.write(imageBytes);
    }
}
