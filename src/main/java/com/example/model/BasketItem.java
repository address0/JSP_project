package com.example.model;

import java.util.Date;

public class BasketItem {
    private Integer nbBasketItem;              // 장바구니 품목 식별번호 (PK)
    private Integer nbBasket;                  // 장바구니 식별번호 (FK)
    private Integer cnBasketItemOrder;         // 품목 순번
    private String noProduct;               // 상품 코드
    private String idUser;                  // 사용자 식별번호 (FK)
    private Integer qtBasketItemPrice;      // 품목 단가
    private Integer qtBasketItemQty;        // 품목 수량
    private Integer qtBasketItemAmount;     // 품목 금액 (단가 * 수량)
    private String noRegister;              // 최초 등록자 ID
    private Date daFirstDate;               // 최초 등록 일시
    private Product Product;

    // --- Getters and Setters ---

    public Integer getNbBasketItem() {
        return nbBasketItem;
    }

    public void setNbBasketItem(Integer nbBasketItem) {
        this.nbBasketItem = nbBasketItem;
    }

    public Integer getNbBasket() {
        return nbBasket;
    }

    public void setNbBasket(Integer nbBasket) {
        this.nbBasket = nbBasket;
    }

    public Integer getCnBasketItemOrder() {
        return cnBasketItemOrder;
    }

    public void setCnBasketItemOrder(Integer cnBasketItemOrder) {
        this.cnBasketItemOrder = cnBasketItemOrder;
    }

    public String getNoProduct() {
        return noProduct;
    }

    public void setNoProduct(String noProduct) {
        this.noProduct = noProduct;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Integer getQtBasketItemPrice() {
        return qtBasketItemPrice;
    }

    public void setQtBasketItemPrice(Integer qtBasketItemPrice) {
        this.qtBasketItemPrice = qtBasketItemPrice;
    }

    public Integer getQtBasketItemQty() {
        return qtBasketItemQty;
    }

    public void setQtBasketItemQty(Integer qtBasketItemQty) {
        this.qtBasketItemQty = qtBasketItemQty;
    }

    public Integer getQtBasketItemAmount() {
        return qtBasketItemAmount;
    }

    public void setQtBasketItemAmount(Integer qtBasketItemAmount) {
        this.qtBasketItemAmount = qtBasketItemAmount;
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

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product product) {
        Product = product;
    }
}
