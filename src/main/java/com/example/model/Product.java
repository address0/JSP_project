package com.example.model;

import java.sql.Date;

public class Product {
    public Integer noProduct;
    public String nmProduct;
    public String nmDetailExplain;
    public String idFile;
    public String dtStartDate;
    public String dtEndDate;
    public Integer qtCustomerPrice;
    public Integer qtSalePrice;
    public Integer qtStock;
    public Integer qtDeliveryFee;
    public String noRegister;
    private Date daFirstDate;

    public Product(String nmProduct, String nmDetailExplain, String dtStartDate, String dtEndDate,
                   Integer qtSalePrice, Integer qtStock) {
        this.nmProduct = nmProduct;
        this.nmDetailExplain = nmDetailExplain;
        this.dtStartDate = dtStartDate;
        this.dtEndDate = dtEndDate;
        this.qtSalePrice = qtSalePrice;
        this.qtStock = qtStock;
    }

    public Product(Integer noProduct, String nmProduct, String nmDetailExplain, String idFile,
                   String dtStartDate, String dtEndDate, Integer qtCustomer, Integer qtSalePrice,
                   Integer qtStock, Integer qtDeliveryFee, String noRegister, Date daFirstDate) {
        this.noProduct = noProduct;
        this.nmProduct = nmProduct;
        this.nmDetailExplain = nmDetailExplain;
        this.idFile = idFile;
        this.dtStartDate = dtStartDate;
        this.dtEndDate = dtEndDate;
        this.qtCustomerPrice = qtCustomer;
        this.qtSalePrice = qtSalePrice;
        this.qtStock = qtStock;
        this.qtDeliveryFee = qtDeliveryFee;
        this.noRegister = noRegister;
        this.daFirstDate = daFirstDate;
    }
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
    public String getDtStartDate() {
        return dtStartDate;
    }
    public void setDtStartDate(String dtStartDate) {
        this.dtStartDate = dtStartDate;
    }
    public String getDtEndDate() {
        return dtEndDate;
    }
    public void setDtEndDate(String dtEndDate) {
        this.dtEndDate = dtEndDate;
    }
    public Integer getQtCustomerPrice() {
        return qtCustomerPrice;
    }
    public void setQtCustomerPrice(Integer qtCustomerPrice) {
        this.qtCustomerPrice = qtCustomerPrice;
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
