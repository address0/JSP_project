package com.example.model;

import java.util.Date;

public class Content {
    private String idFile;            // 콘텐츠 식별 ID (PK)
    private String nmOrgFile;         // 원본 파일명
    private String nmSaveFile;        // 저장 파일명
    private String nmFilePath;        // 저장 경로명
    private byte[] boSaveFile;        // 저장 파일 (BLOB)
    private String nmFileExt;         // 파일 확장자명
    private String cdFileType;        // 파일 유형 코드
    private Date daSave;              // 저장 일시 (기본값: SYSDATE)
    private int cnHit;                // 조회수 (기본값: 0)
    private String idService;         // 서비스 ID
    private String idOrgFile;         // 원 콘텐츠 식별 ID
    private String noRegister;        // 최초 등록자 ID
    private Date daFirstDate;         // 최초 등록 일시

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public String getNmOrgFile() {
        return nmOrgFile;
    }

    public void setNmOrgFile(String nmOrgFile) {
        this.nmOrgFile = nmOrgFile;
    }

    public String getNmSaveFile() {
        return nmSaveFile;
    }

    public void setNmSaveFile(String nmSaveFile) {
        this.nmSaveFile = nmSaveFile;
    }

    public String getNmFilePath() {
        return nmFilePath;
    }

    public void setNmFilePath(String nmFilePath) {
        this.nmFilePath = nmFilePath;
    }

    public byte[] getBoSaveFile() {
        return boSaveFile;
    }

    public void setBoSaveFile(byte[] boSaveFile) {
        this.boSaveFile = boSaveFile;
    }

    public String getNmFileExt() {
        return nmFileExt;
    }

    public void setNmFileExt(String nmFileExt) {
        this.nmFileExt = nmFileExt;
    }

    public String getCdFileType() {
        return cdFileType;
    }

    public void setCdFileType(String cdFileType) {
        this.cdFileType = cdFileType;
    }

    public Date getDaSave() {
        return daSave;
    }

    public void setDaSave(Date daSave) {
        this.daSave = daSave;
    }

    public int getCnHit() {
        return cnHit;
    }

    public void setCnHit(int cnHit) {
        this.cnHit = cnHit;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getIdOrgFile() {
        return idOrgFile;
    }

    public void setIdOrgFile(String idOrgFile) {
        this.idOrgFile = idOrgFile;
    }

    public String getNoRegister() {
        return noRegister;
    }

    public void setNoRegister(String noRegister) {
        this.noRegister = noRegister;
    }

    public Date getDaFirstDate() {
        return daFirstDate;
    }

    public void setDaFirstDate(Date daFirstDate) {
        this.daFirstDate = daFirstDate;
    }
}
