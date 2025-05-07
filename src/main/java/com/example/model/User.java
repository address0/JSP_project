package com.example.model;

public class User {
    public String idUser;
    public String nmUser;
    public String nmPaswd;
    public String nmEncPaswd;
    public String noMobile;
    public String nmEmail;
    public String stStatus;
    public String cdUserType;

    public User(String idUser, String nmUser, String nmPaswd, String noMobile, String nmEmail,
                String stStatus, String cdUserType) {
        this.idUser = idUser;
        this.nmUser = nmUser;
        this.nmPaswd = nmPaswd;
        this.noMobile = noMobile;
        this.nmEmail = nmEmail;
        this.stStatus = stStatus;
        this.cdUserType = cdUserType;
    }
    public User() {
        // Default constructor
    }
    public String getIdUser() {
        return idUser;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public String getNmUser() {
        return nmUser;
    }
    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }
    public String getNmPaswd() {
        return nmPaswd;
    }
    public void setNmPaswd(String nmPaswd) {
        this.nmPaswd = nmPaswd;
    }
    public String getNmEncPaswd() {
        return nmEncPaswd;
    }
    public void setNmEncPaswd(String nmEncPaswd) {
        this.nmEncPaswd = nmEncPaswd;
    }
    public String getNoMobile() {
        return noMobile;
    }
    public void setNoMobile(String noMobile) {
        this.noMobile = noMobile;
    }
    public String getNmEmail() {
        return nmEmail;
    }
    public void setNmEmail(String nmEmail) {
        this.nmEmail = nmEmail;
    }
    public String getStStatus() {
        return stStatus;
    }
    public void setStStatus(String stStatus) {
        this.stStatus = stStatus;
    }
    public String getCdUserType() {
        return cdUserType;
    }
    public void setCdUserType(String cdUserType) {
        this.cdUserType = cdUserType;
    }
}
