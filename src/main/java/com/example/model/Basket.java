package com.example.model;

import java.util.Date;

public class Basket {
    private Integer nbBasket;
    private String noUser;
    private Integer qtBasketAmount;
    private String noRegister;
    private Date daFirstDate;

    public Basket() {
        // Default constructor
    }
    public Integer getNbBasket() {
        return nbBasket;
    }
    public void setNbBasket(Integer nbBasket) {
        this.nbBasket = nbBasket;
    }
    public String getNoUser() {
        return noUser;
    }
    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }
    public Integer getQtBasketAmount() {
        return qtBasketAmount;
    }
    public void setQtBasketAmount(Integer qtBasketAmount) {
        this.qtBasketAmount = qtBasketAmount;
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
