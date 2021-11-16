package com.eugen;

public class MinorUnit {

    private int money;

    public MinorUnit(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public static int convert(String price) {
        return Integer.parseInt(price.replace("$", "").replace(".","").replace(",",""));
    }

    public String toUsd() {
        String s = "$" + this.money / 100 + ".";
        if(this.money % 100 < 9) {
            s += "0" + this.money % 100;
        } else {
            s += this.money % 100;
        }
        return s;
    }

    public MinorUnit add(int money) {
        this.money += money;
        return this;
    }
}
