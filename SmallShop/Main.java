package calculator;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static final String BUBBLEGUM = "Bubblegum";
    public static final String TOFFEE = "Toffee";
    public static final String ICE_CREAM = "Ice cream";
    public static final String MILK_CHOCOLATE = "Milk chocolate";
    public static final String DOUGHNUT = "Doughnut";
    public static final String PANCAKE = "Pancake";

    public static void main(String[] args) {
        LinkedHashMap<String, BigDecimal> productPrices = getProductPrices();
        LinkedHashMap<String, BigDecimal> productEarnings = getProductEarnings();
     //  printPriceList(productPrices);
        int totalEarnings = printEarningsList(productEarnings);
        processExpenses(totalEarnings);
    }

    private static LinkedHashMap<String, BigDecimal> getProductPrices() {
        LinkedHashMap<String, BigDecimal> productPrices = new LinkedHashMap<>();
        productPrices.put(BUBBLEGUM, BigDecimal.TWO);
        productPrices.put(TOFFEE, BigDecimal.valueOf(0.2));
        productPrices.put(ICE_CREAM, BigDecimal.valueOf(5));
        productPrices.put(MILK_CHOCOLATE, BigDecimal.valueOf(4));
        productPrices.put(DOUGHNUT, BigDecimal.valueOf(2.5));
        productPrices.put(PANCAKE, BigDecimal.valueOf(3.2));
        return productPrices;
    }

    private static LinkedHashMap<String, BigDecimal> getProductEarnings() {
        LinkedHashMap<String, BigDecimal> productEarnings = new LinkedHashMap<>();
        productEarnings.put(BUBBLEGUM, BigDecimal.valueOf(202));
        productEarnings.put(TOFFEE, BigDecimal.valueOf(118));
        productEarnings.put(ICE_CREAM, BigDecimal.valueOf(2250));
        productEarnings.put(MILK_CHOCOLATE, BigDecimal.valueOf(1680));
        productEarnings.put(DOUGHNUT, BigDecimal.valueOf(1075));
        productEarnings.put(PANCAKE, BigDecimal.valueOf(80));
        return productEarnings;
    }

    private static void printPriceList(LinkedHashMap<String, BigDecimal> productPrices) {
        System.out.println("Prices:");
        productPrices.forEach((k, v) -> System.out.println(k + ": $" + v));
    }
    private static int printEarningsList(LinkedHashMap<String, BigDecimal> productEarnings) {
        System.out.println("Earned amount:");
        BigDecimal earnings = BigDecimal.ZERO;
        for (Map.Entry<String, BigDecimal> entry : productEarnings.entrySet()) {
            String k = entry.getKey();
            BigDecimal v = entry.getValue();
            System.out.println(k + ": $" + v);
            earnings = earnings.add(v);
        }
        //earnings.setScale(1);
        System.out.println("\nIncome: $" + earnings.intValue());
        return earnings.intValue();
    }
    private static void processExpenses(int earnings){
        System.out.println("Staff expenses:");
        Scanner scanner = new Scanner(System.in);
        int staffExpenses = scanner.nextInt();
        System.out.println("Other expenses:");
        int otherExpenses = scanner.nextInt();
        int revenue = earnings - (staffExpenses + otherExpenses);
        System.out.println("Net income: $" + revenue);
    }
}