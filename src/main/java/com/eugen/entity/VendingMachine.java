package com.eugen.entity;

import com.eugen.utils.ConsumerService;
import com.eugen.utils.Operation;
import com.eugen.utils.Util;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private HashMap<String, Product> store;
    private int rows;
    private int columns;
    private char X = 'A';
    private int Y = 1;
    private MinorUnit balance;
    private MinorUnit allMoney;
    private ConsumerService consumerService;
    private Operation operation;


    private VendingMachine(int rows, int columns, ConsumerService consumerService) {
        this.store = new HashMap<>(rows * columns);
        this.rows = rows;
        this.columns = columns;
        this.balance = new MinorUnit(0);
        this.allMoney = new MinorUnit(0);
        this.consumerService = consumerService;
        this.operation = new Operation();
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


    public ConsumerService getService() {
        return consumerService;
    }

    public void setService(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    public void addProduct(Product product) {
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
        if (money > Util.MAX_VALUE) {
            money = Util.MAX_VALUE;
        }
        this.balance.add((int) (money * 100));
        this.allMoney.add((int) (money * 100));
    }

    public void showAllProducts() {
        for (Map.Entry<String, Product> pair : this.store.entrySet()) {
            System.out.println("place number: " + pair.getKey());
            pair.getValue().show();
            System.out.println();
        }
    }

    public static VendingMachine updateGoods(String pathToJson) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(pathToJson));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        VendingMachine storage = new VendingMachine(jsonObject.get("config").getAsJsonObject().get("rows").getAsInt(),
                jsonObject.get("config").getAsJsonObject().get("columns").getAsInt(), new ConsumerService());

        JsonArray jsonArray = jsonObject.get("items").getAsJsonArray();
        for (JsonElement result : jsonArray) {
            Product product = new Product(result.getAsJsonObject().get("name").getAsString(),
                    result.getAsJsonObject().get("amount").getAsInt(),
                    MinorUnit.convert(result.getAsJsonObject().get("price").getAsString()));
            storage.addProduct(product);
        }
        return storage;
    }

    public void showCurrentBalance() {
        System.out.println("Current Balance: " + this.balance.toUsd());
    }

    public void showActions() {
        this.operation.show();
    }

    public boolean containAction(String action) {
        return this.operation.checkActions(action);
    }

}
