package com.example.model;

import java.util.Date;

public class Order {
    private String idOrder;
    private String noUser;
    private Integer qtOrderAmount;
    private Integer qtDeliMoney;
    private Integer qiDeliPeriod;
    private String nmOrderPerson;
    private String nmReceiver;
    private String noDeliveryZipno;
    private String noDeliveryAddress;
    private String nmReceiverTelno;
    private String nmDeliverySpace;
    private String cdOrderType;
    private Date cdOrderDate;
    private String stOrder;
    private String stPayment;
    private String noRegister;
    private Date daFirstDate;

    public Order() {
        // Default constructor
    }
    public String getIdOrder() {
        return idOrder;
    }
    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }
    public String getNoUser() {
        return noUser;
    }
    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }
    public Integer getQtOrderAmount() {
        return qtOrderAmount;
    }
    public void setQtOrderAmount(Integer qtOrderAmount) {
        this.qtOrderAmount = qtOrderAmount;
    }
    public Integer getQtDeliMoney() {
        return qtDeliMoney;
    }
    public void setQtDeliMoney(Integer qtDeliMoney) {
        this.qtDeliMoney = qtDeliMoney;
    }
    public Integer getQiDeliPeriod() {
        return qiDeliPeriod;
    }
    public void setQiDeliPeriod(Integer qiDeliPeriod) {
        this.qiDeliPeriod = qiDeliPeriod;
    }
    public String getNmOrderPerson() {
        return nmOrderPerson;
    }
    public void setNmOrderPerson(String nmOrderPerson) {
        this.nmOrderPerson = nmOrderPerson;
    }
    public String getNmReceiver() {
        return nmReceiver;
    }
    public void setNmReceiver(String nmReceiver) {
        this.nmReceiver = nmReceiver;
    }
    public String getNoDeliveryZipno() {
        return noDeliveryZipno;
    }
    public void setNoDeliveryZipno(String noDeliveryZipno) {
        this.noDeliveryZipno = noDeliveryZipno;
    }
    public String getNoDeliveryAddress() {
        return noDeliveryAddress;
    }
    public void setNoDeliveryAddress(String noDeliveryAddress) {
        this.noDeliveryAddress = noDeliveryAddress;
    }
    public String getNmReceiverTelno() {
        return nmReceiverTelno;
    }
    public void setNmReceiverTelno(String nmReceiverTelno) {
        this.nmReceiverTelno = nmReceiverTelno;
    }
    public String getNmDeliverySpace() {
        return nmDeliverySpace;
    }
    public void setNmDeliverySpace(String nmDeliverySpace) {
        this.nmDeliverySpace = nmDeliverySpace;
    }
    public String getCdOrderType() {
        return cdOrderType;
    }
    public void setCdOrderType(String cdOrderType) {
        this.cdOrderType = cdOrderType;
    }
    public Date getCdOrderDate() {
        return cdOrderDate;
    }
    public void setCdOrderDate(Date cdOrderDate) {
        this.cdOrderDate = cdOrderDate;
    }
    public String getStOrder() {
        return stOrder;
    }
    public void setStOrder(String stOrder) {
        this.stOrder = stOrder;
    }
    public String getStPayment() {
        return stPayment;
    }
    public void setStPayment(String stPayment) {
        this.stPayment = stPayment;
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
