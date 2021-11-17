package com.eugen.utils;

import java.util.HashMap;
import java.util.Map;

public class Operation {
    private Map<String, String> actions;

    public Operation() {
        this.actions = new HashMap<String, String>();
        fill();
    }

    private void fill() {
        this.actions.put("1", "1. Select product");
        this.actions.put("2", "2. Insert money");
        this.actions.put("3", "3. Update goods");
        this.actions.put("4", "4. Turn off vending machine");
    }

    public void show() {
        for(Map.Entry<String, String> pair : this.actions.entrySet()) {
            System.out.println(pair.getValue());
        }
    }

    public boolean checkActions(String key){
        return this.actions.containsKey(key);
    }

}
