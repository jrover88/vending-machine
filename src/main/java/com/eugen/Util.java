package com.eugen;

import java.util.Map;
import java.util.Scanner;

public class Util {
    public static final double MAX_VALUE = 10.0;

    public static boolean isDouble(String number) {
        try {
            Double.parseDouble(number);
        } catch (NumberFormatException e) {
            System.out.println("ERROR - Input must be number");
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        if (Double.parseDouble(number) < 0 || Double.parseDouble(number) > 10.0) {
            System.out.println("\nERROR - Quantity on hand must be between $0.01 and $10.00");
            return false;
        } else {
            return true;
        }
    }

}
