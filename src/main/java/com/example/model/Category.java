package com.example.model;

import java.util.Date;

public class Category {
    private int nbCategory;
    private Integer nbParentCategory;
    private String nmCategory;
    private String nmFullCategory;
    private String mnExplain;
    private Integer cnOrder;
    private String ynUse;
    private String ynDelete;
    private String noRegister;
    private Date daFirstDate;

    // Getter 및 Setter
    public int getNbCategory() {
        return nbCategory;
    }

    public void setNbCategory(int nbCategory) {
        this.nbCategory = nbCategory;
    }

    public Integer getNbParentCategory() {
        return nbParentCategory;
    }

    public void setNbParentCategory(Integer nbParentCategory) {
        this.nbParentCategory = nbParentCategory;
    }

    public String getNmCategory() {
        return nmCategory;
    }

    public void setNmCategory(String nmCategory) {
        this.nmCategory = nmCategory;
    }

    public String getNmFullCategory() {
        return nmFullCategory;
    }

    public void setNmFullCategory(String nmFullCategory) {
        this.nmFullCategory = nmFullCategory;
    }

    public String getMnExplain() {
        return mnExplain;
    }

    public void setMnExplain(String mnExplain) {
        this.mnExplain = mnExplain;
    }

    public Integer getCnOrder() {
        return cnOrder;
    }

    public void setCnOrder(Integer cnOrder) {
        this.cnOrder = cnOrder;
    }

    public String getYnUse() {
        return ynUse;
    }

    public void setYnUse(String ynUse) {
        this.ynUse = ynUse;
    }

    public String getYnDelete() {
        return ynDelete;
    }

    public void setYnDelete(String ynDelete) {
        this.ynDelete = ynDelete;
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
