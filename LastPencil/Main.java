package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {

    //messages go here:
    public static final String QUANTITY = "How many pencils would you like to use:";
    public static final String POSITIVE = "The number of pencils should be positive";
    public static final String NUMERIC = "The number of pencils should be numeric";
    public static final String VALID_VALUES = "Possible values: '1', '2' or '3'";
    public static final String FIRST = "Who will be the first(%s, %s):\n";
    public static final String CHOICE = "Choose between %s or %s\n";
    public static final String TURN = "%s's turn!\n";
    public static final String MANY_PENCILS = "Too many pencils were taken";

    public static final String PLAYER1 = "John";
    public static final String PLAYER2 = "Jack";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalPencils = setupGame(scanner);
        String currentPlayer = setupPlayers(scanner);
        printPencils(totalPencils);
        while (totalPencils > 0) {
            String nextPlayer = checkNextPlayer(currentPlayer);
            totalPencils = runTurn(scanner, currentPlayer, totalPencils, nextPlayer);
            currentPlayer = nextPlayer;
        }
        scanner.close();
    }

    private static int setupGame(Scanner scanner) {
        printMessage(QUANTITY);
        return getValidPencilCount((scanner));
    }

    private static int getValidPencilCount(Scanner scanner) {
        int pencils;
        while (true) {
            String input = scanner.nextLine();
            if (isValidPencilCount(input)) {
                pencils = Integer.parseInt(input);
                break;
            }
        }
        return pencils;
    }

    private static boolean isValidPencilCount(String input) {
        try {
            int n = Integer.parseInt(input);
            if (n > 0) {
                return true;
            } else if (n == 0) {
                printMessage(POSITIVE);
                return false;
            } else {
                printMessage(NUMERIC);
                return false;
            }
        } catch (NumberFormatException e) {
            printMessage(NUMERIC);
            return false;
        }
    }

    private static void printMessage(String x) {
        System.out.println(x);
    }


    private static String setupPlayers(Scanner scanner) {
        System.out.printf(FIRST, PLAYER1, PLAYER2);
        return getFirstPlayer(scanner);
    }

    private static String getFirstPlayer(Scanner scanner) {
        String nextPlayer;
        while (true) {
            String input = scanner.nextLine();
            if (isValidPlayer(input)) {
                nextPlayer = input;
                break;
            }
        }
        return nextPlayer;
    }

    private static boolean isValidPlayer(String player) {
        if (player.equals(PLAYER1) || player.equals(PLAYER2)) {
            return true;
        } else {
            System.out.printf(CHOICE, PLAYER1, PLAYER2);
            return false;
        }
    }

    private static void printPencils(int number) {
        System.out.println("|".repeat(number));
    }

    private static String checkNextPlayer(String currentPlayer) {
        return currentPlayer.equals(PLAYER1) ? PLAYER2 : PLAYER1;
    }

    private static int runTurn(Scanner scanner, String player, int pencils, String otherPlayer) {
        System.out.printf(TURN, player);
        int pencilsWanted = 1;
        if (player.equals(PLAYER2)) {
            pencilsWanted = getBotPencilsWanted(pencils);
            System.out.println(pencilsWanted);
        } else {
            pencilsWanted = getPencilsWanted(scanner, pencils);
        }
        if (pencilsWanted == pencils) {
            System.out.printf("%s won!\n", otherPlayer);
            return 0;
        }
        if (pencils > pencilsWanted) {
            printPencils(pencils - pencilsWanted);
            return pencils - pencilsWanted;
        }
        return 0;
    }

    private static int getBotPencilsWanted(int totalPencils) {
        final Random random = new Random();
        if (totalPencils == 1) {
            return 1;
        } else if (totalPencils % 4 == 1) {
            return random.nextInt(Math.min(3, totalPencils)) +1;
        } else {
            switch ((totalPencils + 4) % 4) {
                case 0: return 3;
                case 3: return 2;
                case 2: return 1;
                default: return 1;
            }
        }



    }

    private static int getPencilsWanted(Scanner scanner, int totalPencils) {
        int pencils;
        while (true) {
            String input = scanner.nextLine();
            if (validateTurn(input, totalPencils)) {
                pencils = Integer.parseInt(input);
                break;
            }
        }
        return pencils;
    }

    private static boolean validateTurn(String input, int totalPencils) {
        try {
            int n = Integer.parseInt(input);
            if (n > 3 || n <= 0 ) {
                printMessage(VALID_VALUES);
                return false;
            } else if (n > totalPencils) {
                printMessage(MANY_PENCILS);
                return false;
            }
        } catch (NumberFormatException e) {
            printMessage(VALID_VALUES);
            return false;
        }
        return true;
    }
}