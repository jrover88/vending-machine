import java.util.Scanner;

public class func {
    public static final double MAX_VALUE = 10.0;
    private static double insertedMoney;


    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            System.out.println("ERROR - Input must be number");
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        if (Double.parseDouble(s) < 0 || Double.parseDouble(s) > 10.0) {
            System.out.println("\nERROR - Quantity on hand must be between $0.01 and $10.00");
            return false;
        } else {
            return true;
        }
    }

}
