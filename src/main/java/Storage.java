import java.util.HashMap;
import java.util.Map;

public class Storage {
    private HashMap<String, Product> store;
    private int rows;
    private int columns;
    private char X = 'A';
    private int Y = 1;
    private MinorUnit balance;
    private MinorUnit allMoney;
    private Service service;

    public Storage(int rows, int columns, Service service) {
        this.store = new HashMap<>(rows * columns);
        this.rows = rows;
        this.columns = columns;
        this.balance = new MinorUnit(0);
        this.allMoney = new MinorUnit(0);
        this.service = service;
    }

    public MinorUnit getBalance() {
        return balance;
    }

    public void setBalance(MinorUnit balance) {
        this.balance = balance;
    }

    public MinorUnit getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(MinorUnit allMoney) {
        this.allMoney = allMoney;
    }


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void put(Product product) {
        this.store.put(generatePlaceCoordinate(), product);
    }

    private String generatePlaceCoordinate() {
        if (this.Y > this.columns) {
            this.Y = 1;
            this.X++;
        }

        String string = this.X + ("" + this.Y++);
        return string;
    }

    public void showProduct(String place) {
        if (containProduct(place)) {
            this.store.get(place).show();
        }
    }

    public boolean containProduct(String s) {
        return this.store.containsKey(s);
    }


    public Product getProduct(String s) {
        if (containProduct(s)) {
            return this.store.get(s);
        }
        return null;
    }

    public void addBalance(double money) {
        if (money > func.MAX_VALUE) {
            money = func.MAX_VALUE;
        }
        this.balance.add((int) (money * 100));
        this.allMoney.add((int) (money * 100));
    }

    public void showAllProduct() {
        for (Map.Entry<String, Product> pair : this.store.entrySet()) {
            System.out.println(pair.getKey());
            pair.getValue().show();
            System.out.println();
        }
    }

}
