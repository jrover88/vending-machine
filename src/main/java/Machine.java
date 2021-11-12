import com.google.gson.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Machine {
    public static void main(String[] args) {

        // тупо считываем файл. доделать с буфером а не побайтово
        StringBuilder str = new StringBuilder();

        try (FileReader reader = new FileReader("src/main/java/input2.json")) {
            int c;
            while ((c = reader.read()) != -1) {
                str.append((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject jsonObject = gson.fromJson(String.valueOf(str), JsonObject.class);
        Storage storage = new Storage(jsonObject.get("config").getAsJsonObject().get("rows").getAsInt(),
                jsonObject.get("config").getAsJsonObject().get("columns").getAsInt(), new Service());

        JsonArray jsonArray = jsonObject.get("items").getAsJsonArray();
        for (JsonElement result : jsonArray) {
//            System.out.println(result);
            Product product = new Product(result.getAsJsonObject().get("name").getAsString(),
                    result.getAsJsonObject().get("amount").getAsInt(),
                    MinorUnit.convert(result.getAsJsonObject().get("price").getAsString()));
            storage.put(product);
        }
        // ===============================================================================
        Scanner scanner = new Scanner(System.in);
        String mainChoose = "";
        storage.showAllProduct();
        outerloop:
        while (true) {


            System.out.println("Current Balance: " + storage.getBalance().toUsd());
            String[] actionSelectProduct = {"1", ". Select product"};
            String[] actionInsertMoney = {"2", ". Insert money(max value: $10.00): "};
            String[] actionOff = {"3", ". Turn off machine"};

            System.out.println(actionSelectProduct[0] + actionSelectProduct[1]);
            System.out.println(actionInsertMoney[0] + actionInsertMoney[1]);
            System.out.println(actionOff[0] + actionOff[1]);
            mainChoose = scanner.next();
            while (!mainChoose.equals(actionSelectProduct[0]) && !mainChoose.equals(actionInsertMoney[0]) && !mainChoose.equals(actionOff[0])) {
                System.out.println("ERROR - Wrong input. Select right action number");
                mainChoose = scanner.next();
            }

            switch (mainChoose) {
                case "1":
                    System.out.println("Enter place number(A1, B2 ...): ");
                    String productChoice = scanner.next();
                    while (!storage.containProduct(productChoice)) {
                        System.out.println("Wrong product. Try again: ");
                        productChoice = scanner.next();
                    }
                    System.out.println("Your choice is: ");
                    storage.showProduct(productChoice);
                    storage.getService().buyProduct(storage.getProduct(productChoice), storage.getBalance());
                    storage.showProduct(productChoice);
                    break;
                case "2":
                    String insert = scanner.next();
                    while (!func.isDouble(insert)) {
                        System.out.println("error... Trying again");
                        insert = scanner.next();
                    }

                    double insertedMoneyAmount = Math.round(Double.valueOf(insert) * 100.0) / 100.0;
                    System.out.println(insertedMoneyAmount);
                    storage.addBalance(insertedMoneyAmount);

                    System.out.println("You inserted: $" + insertedMoneyAmount);
                    System.out.println("Balance is " + storage.getBalance().toUsd());
                    break;
                case "3":
                    break outerloop;
                default:
                    break;
            }


        }
        scanner.close();
        System.out.println("Finish!!!");

    }

}
