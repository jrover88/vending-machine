package com.eugen.utils;

import com.eugen.entity.MinorUnit;
import com.eugen.entity.Product;

public class ConsumerService {

    public boolean enoughGoods(Product product) {
        return product.isExist();
    }

    public boolean enoughMoney(Product product, MinorUnit balance) {
        return product.getPrice().getMoney() <= balance.getMoney();
    }

    public void buyProduct(Product product, MinorUnit balance) {
        if(enoughGoods(product) && enoughMoney(product, balance)) {
            product.buyProduct();
            balance.setMoney(balance.getMoney() - product.getPrice().getMoney());
            System.out.println("You bought " + product.getName()); //logger
        }
        else if (!enoughMoney(product, balance)) {
            System.out.println("Not enough money. Insert money "); //logger
        }
        else if (!enoughGoods(product)){
            System.out.println("Not enough goods. Select another product "); //logger
        }
    }

}
