package com.example.model;

import java.util.Date;

public class OrderItem {
    private String idOrderItem;               // 주문 품목 ID (PK)
    private String idOrder;                   // 주문 ID (FK)
    private Integer cnOrderItem;              // 주문 품목 순번
    private String noProduct;                 // 상품 코드
    private String noUser;                    // 사용자 식별번호
    private Integer qtUnitPrice;              // 주문 품목 단가
    private Integer qtOrderItem;              // 주문 품목 수량
    private Integer qtOrderItemAmount;        // 주문 품목 금액
    private Integer qtOrderItemDeliveryFee;   // 배송 금액
    private String stPayment;                 // 결제 상태
    private String noRegister;                // 등록자 ID
    private Date daFirstDate;                 // 최초 등록 일시

    public String getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(String idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getCnOrderItem() {
        return cnOrderItem;
    }

    public void setCnOrderItem(Integer cnOrderItem) {
        this.cnOrderItem = cnOrderItem;
    }

    public String getNoProduct() {
        return noProduct;
    }

    public void setNoProduct(String noProduct) {
        this.noProduct = noProduct;
    }

    public String getNoUser() {
        return noUser;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public Integer getQtUnitPrice() {
        return qtUnitPrice;
    }

    public void setQtUnitPrice(Integer qtUnitPrice) {
        this.qtUnitPrice = qtUnitPrice;
    }

    public Integer getQtOrderItem() {
        return qtOrderItem;
    }

    public void setQtOrderItem(Integer qtOrderItem) {
        this.qtOrderItem = qtOrderItem;
    }

    public Integer getQtOrderItemAmount() {
        return qtOrderItemAmount;
    }

    public void setQtOrderItemAmount(Integer qtOrderItemAmount) {
        this.qtOrderItemAmount = qtOrderItemAmount;
    }

    public Integer getQtOrderItemDeliveryFee() {
        return qtOrderItemDeliveryFee;
    }

    public void setQtOrderItemDeliveryFee(Integer qtOrderItemDeliveryFee) {
        this.qtOrderItemDeliveryFee = qtOrderItemDeliveryFee;
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
