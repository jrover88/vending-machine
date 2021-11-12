public class Service {

    public boolean enoughGoods(Product p) {
        return p.isExist();
    }

    public boolean enoughMoney(Product product, MinorUnit balance) {
        return product.getPrice().getMoney() <= balance.getMoney();
    }

    public void buyProduct(Product product, MinorUnit balance) {
        if(enoughGoods(product) && enoughMoney(product, balance)) {
            product.buyProduct();
            balance.setMoney(balance.getMoney() - product.getPrice().getMoney()); // ??????????????
            System.out.println("You bought " + product.getName());
        }
        else if (!enoughMoney(product, balance)) {
            System.out.println("Not enough money. Insert money ");
        }
        else if (!enoughGoods(product)){
            System.out.println("Not enough goods. Select another product ");
        }
    }


}
