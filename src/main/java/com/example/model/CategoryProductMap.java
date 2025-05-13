package com.example.model;

import java.util.Date;

public class CategoryProductMap {
    private String noProduct;
    private Integer nbCategory;
    private Integer cnOrder;
    private String noRegister;
    private Date daFirstDate;

    public CategoryProductMap() {
        // Default constructor
    }

    public String getNoProduct() {
        return noProduct;
    }

    public void setNoProduct(String noProduct) {
        this.noProduct = noProduct;
    }

    public Integer getNbCategory() {
        return nbCategory;
    }

    public void setNbCategory(Integer noCategory) {
        this.nbCategory = noCategory;
    }

    public Integer getCnOrder() {
        return cnOrder;
    }

    public void setCnOrder(Integer cnOrder) {
        this.cnOrder = cnOrder;
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
