package com.example.dao;

import jakarta.servlet.ServletContext;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.example.model.Content;
import com.example.util.DBUtil;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContentDAO {
    private final ServletContext context;

    public ContentDAO(ServletContext context) {
        this.context = context;
    }

    public boolean insertContent(Content content) {
        String sql = "INSERT INTO TB_CONTENT (" +
                "id_file, nm_org_file, nm_save_file, nm_file_path, bo_save_file, " +
                "nm_file_ext, cd_file_type, da_save, cn_hit, id_service, id_org_file, " +
                "no_register, da_first_date" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE, 0, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, content.getIdFile());
            pstmt.setString(2, content.getNmOrgFile());
            pstmt.setString(3, content.getNmSaveFile());
            pstmt.setString(4, content.getNmFilePath());
            pstmt.setBytes(5, content.getBoSaveFile()); // BLOB
            pstmt.setString(6, content.getNmFileExt());
            pstmt.setString(7, content.getCdFileType());
            pstmt.setString(8, content.getIdService());
            pstmt.setString(9, content.getIdOrgFile());
            pstmt.setString(10, content.getNoRegister());
            pstmt.setDate(11, new java.sql.Date(content.getDaFirstDate().getTime()));

            int result = pstmt.executeUpdate();
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
             return false;
        }
    }

    public boolean deleteContent(String idFile) {
        String sql = "DELETE FROM TB_CONTENT WHERE id_file = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idFile);
            int result = pstmt.executeUpdate();
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
             return false;
        }
    }

    public Content getContentById(String idFile) {
        String sql = "SELECT * FROM TB_CONTENT WHERE id_file = ?";
        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idFile);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Content content = new Content();
                    content.setIdFile(rs.getString("id_file"));
                    content.setNmOrgFile(rs.getString("nm_org_file"));
                    content.setNmSaveFile(rs.getString("nm_save_file"));
                    content.setNmFilePath(rs.getString("nm_file_path"));
                    content.setBoSaveFile(rs.getBytes("bo_save_file")); // BLOB
                    content.setNmFileExt(rs.getString("nm_file_ext"));
                    content.setCdFileType(rs.getString("cd_file_type"));
                    content.setDaSave(rs.getDate("da_save"));
                    content.setCnHit(rs.getInt("cn_hit"));
                    content.setIdService(rs.getString("id_service"));
                    content.setIdOrgFile(rs.getString("id_org_file"));
                    content.setNoRegister(rs.getString("no_register"));
                    content.setDaFirstDate(rs.getDate("da_first_date"));
                    return content;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateContent(Content content) {
        String sql = "UPDATE TB_CONTENT SET " +
                "nm_org_file = ?, nm_save_file = ?, nm_file_path = ?, bo_save_file = ?, " +
                "nm_file_ext = ?, cd_file_type = ?, da_save = SYSDATE, cn_hit = cn_hit + 1, " +
                "id_service = ?, id_org_file = ?, no_register = ? " +
                "WHERE id_file = ?";

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, content.getNmOrgFile());
            pstmt.setString(2, content.getNmSaveFile());
            pstmt.setString(3, content.getNmFilePath());
            pstmt.setBytes(4, content.getBoSaveFile()); // BLOB
            pstmt.setString(5, content.getNmFileExt());
            pstmt.setString(6, content.getCdFileType());
            pstmt.setString(7, content.getIdService());
            pstmt.setString(8, content.getIdOrgFile());
            pstmt.setString(9, content.getNoRegister());
            pstmt.setString(10, content.getIdFile());

            int result = pstmt.executeUpdate();
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
             return false;
        }
    }

    public List<Content> getAllContents() {
        String sql = "SELECT * FROM TB_CONTENT";
        List<Content> contents = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection(context);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Content content = new Content();
                content.setIdFile(rs.getString("id_file"));
                content.setNmOrgFile(rs.getString("nm_org_file"));
                content.setBoSaveFile(rs.getBytes("bo_save_file")); // BLOB
                content.setNmFilePath(rs.getString("nm_file_path"));

                contents.add(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }
}
