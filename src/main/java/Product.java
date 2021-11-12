public class Product {
    private String name;
    private int amount;
    private MinorUnit price;

    public Product(String name, int amount, int price) {
        this.name = name;
        this.amount = amount;
        this.price = new MinorUnit(price);
    }

    public String getName() {
        return this.name;
    }

    public int getAmount() {
        return this.amount;
    }

    public MinorUnit getPrice() {
        return this.price;
    }

    public void show() {
        System.out.println("name: " + this.name);
        System.out.println("amount: " + this.amount);
        System.out.println("price: " + this.price.toUsd());
    }

    public boolean isExist() {
        return this.amount > 0;
    }

    public void buyProduct() {
        this.amount--;
    }
}
