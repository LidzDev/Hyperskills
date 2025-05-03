package machine;

import java.util.Scanner;

enum Coffee {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        final int waterNeeded;
        final int milkNeeded;
        final int coffeeBeansNeeded;
        final int cost;

        Coffee(int waterNeeded, int milkNeeded, int coffeeBeansNeeded, int cost) {
            this.waterNeeded = waterNeeded;
            this.milkNeeded = milkNeeded;
            this.coffeeBeansNeeded = coffeeBeansNeeded;
            this.cost = cost;
        }
    }

public class CoffeeMachine {
    private int waterSupply;
    private int milkSupply;
    private int coffeeBeansSupply;
    private int cupsSupply;
    private int cashSupply;

    // messages
    private static final String INVENTORY = "The coffee machine has:";
    private static final String WATER_ML = "%d ml of water%n";
    private static final String MILK_ML = "%d ml of milk%n";
    private static final String BEANS_GR = "%d g of coffee beans%n";
    private static final String CUPS_NO = "%d disposable cups%n";
    private static final String CASH_USD = "$%d of money%n";
    private static final String WITHDRAWAL = "I gave you $%d";
    private static final String ACTION = "Write action (buy, fill, take):";
    private static final String CHOICE = "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:";
    private static final String WRONG_NUMBER = "1, 2 or 3";
    private static final String ADD_WATER = "Write how many ml of water you want to add:";
    private static final String ADD_MILK = "Write how many ml of milk you want to add:";
    private static final String ADD_BEANS = "Write how many grams of coffee beans you want to add:";
    private static final String ADD_CUPS = "Write how many disposable cups you want to add:";


    private static Scanner scanner = new Scanner(System.in);


    public CoffeeMachine(int water, int milk, int coffeeBeans, int cups, int cash) {
        this.waterSupply = water;
        this.milkSupply = milk;
        this.coffeeBeansSupply = coffeeBeans;
        this.cupsSupply = cups;
        this.cashSupply = cash;
    }

    public int getWaterSupply() {
        return waterSupply;
    }

    public int getMilkSupply() {
        return milkSupply;
    }

    public int getCoffeeBeansSupply() {
        return coffeeBeansSupply;
    }

    public int getCupsSupply() {
        return cupsSupply;
    }

    public int getCashSupply() {
        return cashSupply;
    }

    public void setWaterSupply(int waterUsed, int waterAdded) {
        this.waterSupply -= waterUsed;
        this.waterSupply += waterAdded;
    }

    public void setMilkSupply(int milkUsed, int milkAdded) {
        this.milkSupply -= milkUsed;
        this.milkSupply += milkAdded;
    }

    public void setCoffeeBeansSupply(int coffeeBeansUsed, int coffeeBeansAdded) {
        this.coffeeBeansSupply -= coffeeBeansUsed;
        this.coffeeBeansSupply += coffeeBeansAdded;
    }

    public void setCupsSupply(int cupsUsed, int cupsAdded) {
        this.cupsSupply -= cupsUsed;
        this.cupsSupply += cupsAdded;
    }

    public void setCashSupply(int cashRemoved, int cashAdded) {
        this.cashSupply -= cashRemoved;
        this.cashSupply += cashAdded;
    }

    public void printState() {
        System.out.println(INVENTORY);
        System.out.printf(WATER_ML, getWaterSupply());
        System.out.printf(MILK_ML, getMilkSupply());
        System.out.printf(BEANS_GR, getCoffeeBeansSupply());
        System.out.printf(CUPS_NO, getCupsSupply());
        System.out.printf(CASH_USD, getCashSupply());
        System.out.println();
    }

    private void machineTalk(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        machine.printState();
        machine.machineTalk(ACTION);
        String input = scanner.nextLine();
        switch (input) {
            case "buy" -> {
                machine.machineTalk(CHOICE);
                try {
                    machine.buyCoffee(scanner.nextInt());
                }
                catch (Exception e) {
                    machine.machineTalk(WRONG_NUMBER);
                }
            }
            case "fill" -> {
                machine.fillUp();
            }
            case "take" -> {
                machine.takeCash();
            }
            default -> {
                machine.machineTalk("unrecognised action");
            }
        }
        System.out.println("\n");
        machine.printState();
    }

    private void takeCash() {
        int money = getCashSupply();
        System.out.printf(WITHDRAWAL, money);
        setCashSupply(money, 0);
    }

    private void fillUp() {
        machineTalk(ADD_WATER);
        fillIngredient("water");
        machineTalk(ADD_MILK);
        fillIngredient("milk");
        machineTalk(ADD_BEANS);
        fillIngredient("beans");
        machineTalk(ADD_CUPS);
        fillIngredient("cups");
    }

    private void fillIngredient(String type) {
        int quantity = scanner.nextInt();
        switch (type) {
            case "water" -> setWaterSupply(0, quantity);
            case "milk" -> setMilkSupply(0, quantity);
            case "beans" -> setCoffeeBeansSupply(0, quantity);
            case "cups" -> setCupsSupply(0, quantity);
        }
    }

    private void buyCoffee(int number) {
        Coffee coffee;
        switch (number) {
            case 1 -> coffee = Coffee.ESPRESSO;
            case 2 -> coffee = Coffee.LATTE;
            case 3 -> coffee = Coffee.CAPPUCCINO;
            default -> {
                machineTalk(WRONG_NUMBER);
                return;
            }
        }

        if (checkInventory(coffee)){
            setWaterSupply(coffee.waterNeeded, 0);
            setMilkSupply(coffee.milkNeeded, 0);
            setCoffeeBeansSupply(coffee.coffeeBeansNeeded, 0);
            setCupsSupply(1, 0);
            setCashSupply(0, coffee.cost);
        }
    }

    private boolean checkInventory(Coffee coffee) {
        if (getWaterSupply() < coffee.waterNeeded){
            return false;
        }
        if (getMilkSupply() < coffee.milkNeeded){
            return false;
        }
        if (getCoffeeBeansSupply() < coffee.coffeeBeansNeeded){
            return false;
        }
        if (getCupsSupply() < 1){
            return false;
        }
        return true;
    }


    private static long getQuantity(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            try {
                return scanner.nextLong();
            } catch (Exception e) {
                System.out.println("numbers");
            }
        }
    }
}



