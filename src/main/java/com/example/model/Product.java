package com.example.model;

import java.sql.Date;

public class Product {
    public Integer noProduct;
    public String nmProduct;
    public String nmDetailExplain;
    public String idFile;
    public Date dtStartDate;
    public Date dtEndDate;
    public Integer qtCustomer;
    public Integer qtSalePrice;
    public Integer qtStock;
    public Integer qtDeliveryFee;
    public String noRegister;
    private Date daFirstDate;

    public Product() {
        // Default constructor
    }
    public Integer getNoProduct() {
        return noProduct;
    }
    public void setNoProduct(Integer noProduct) {
        this.noProduct = noProduct;
    }
    public String getNmProduct() {
        return nmProduct;
    }
    public void setNmProduct(String nmProduct) {
        this.nmProduct = nmProduct;
    }
    public String getNmDetailExplain() {
        return nmDetailExplain;
    }
    public void setNmDetailExplain(String nmDetailExplain) {
        this.nmDetailExplain = nmDetailExplain;
    }
    public String getIdFile() {
        return idFile;
    }
    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }
    public Date getDtStartDate() {
        return dtStartDate;
    }
    public void setDtStartDate(Date dtStartDate) {
        this.dtStartDate = dtStartDate;
    }
    public Date getDtEndDate() {
        return dtEndDate;
    }
    public void setDtEndDate(Date dtEndDate) {
        this.dtEndDate = dtEndDate;
    }
    public Integer getQtCustomer() {
        return qtCustomer;
    }
    public void setQtCustomer(Integer qtCustomer) {
        this.qtCustomer = qtCustomer;
    }
    public Integer getQtSalePrice() {
        return qtSalePrice;
    }
    public void setQtSalePrice(Integer qtSalePrice) {
        this.qtSalePrice = qtSalePrice;
    }
    public Integer getQtStock() {
        return qtStock;
    }
    public void setQtStock(Integer qtStock) {
        this.qtStock = qtStock;
    }
    public Integer getQtDeliveryFee() {
        return qtDeliveryFee;
    }
    public void setQtDeliveryFee(Integer qtDeliveryFee) {
        this.qtDeliveryFee = qtDeliveryFee;
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
