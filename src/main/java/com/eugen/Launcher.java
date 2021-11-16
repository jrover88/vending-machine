package com.eugen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Launcher {

    private static final Logger LOGGER = Logger.getLogger(Launcher.class.getName());

    public static void main(String[] args) throws FileNotFoundException {
        FileHandler fh;
        try {
            fh = new FileHandler("src/main/java/com/eugen/log/log.txt", true);
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            LOGGER.info("Vending machine turned on");

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        String pathToJsonFile = "src/main/resources/input.json";
        VendingMachine machine = VendingMachine.updateGoods(pathToJsonFile);
        machine.showAllProducts();

        Scanner scanner = new Scanner(System.in);
        String mainChoice = "";

        outerloop:
        while (true) {
            machine.showActions();
            machine.showCurrentBalance();

            mainChoice = scanner.next();
            while (!machine.containAction(mainChoice)) {
                System.out.println("ERROR - Wrong input. Select right action number");
                mainChoice = scanner.next();
            }

            switch (mainChoice) {
                case "1":
                    System.out.println("Enter place number(A1, B2 ...): ");
                    String productChoice = scanner.next();
                    while (!machine.containProduct(productChoice)) {
                        System.out.println("Wrong product. Try again: ");
                        productChoice = scanner.next();
                    }
                    System.out.println("Your choice is: ");
                    machine.showProduct(productChoice);
                    if(machine.getService().enoughGoods(machine.getProduct(productChoice)) &&
                            machine.getService().enoughMoney(machine.getProduct(productChoice) ,machine.getBalance())) {
                        LOGGER.info("You bought: " + machine.getProduct(productChoice).getName());
                    }
                    machine.getService().buyProduct(machine.getProduct(productChoice), machine.getBalance());
                    break;
                case "2":
                    System.out.println("Insert money(vending machine take in from $0.01 to $10.00): ");
                    String insert = scanner.next();
                    while (!Util.isDouble(insert)) {
                        insert = scanner.next();
                    }
                    double insertedMoneyAmount = Math.round(Double.parseDouble(insert) * 100.0) / 100.0; // round to 2 digit
                    machine.addBalance(insertedMoneyAmount);
                    LOGGER.info("You inserted: $" + insertedMoneyAmount); // logger
                    break;
                case "3":
                    machine = VendingMachine.updateGoods(pathToJsonFile);
                    machine.showAllProducts();
                    LOGGER.info("Vending machine products list updated");
                    break;
                case "4":
                    LOGGER.info("Vending machine turned off");
                    break outerloop;
                default:
                    break;
            }

        }
        scanner.close();
        System.out.println("Vending machine turned off!!!");
    }

}
