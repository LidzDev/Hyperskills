package tictactoe;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String OCCUPIED = "This cell is occupied! Choose another one!";
    public static String NUMBERS = "You should enter numbers!";
    public static String RANGE = "Coordinates should be from 1 to 3!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char[] chars = getChars(input);
        int[][] gridArray= setGrid(chars);
        printBoard(gridArray);
        int [] coordinates = getMoveCoordinates(scanner);

        //char [][] options = prepareForAnalysis(chars);
        //int [] winCounters = checkWinner(options);
        //int [] moveCounters = checkMoves(chars);
        //String result = doAnalysis(winCounters, moveCounters);
        //System.out.println(result);
        scanner.close();
    }

    private static int[][] setGrid(char[] chars) {
        int[][] grid = {
                {chars[0], chars[1], chars[2]},
                {chars[3], chars[4], chars[5]},
                {chars[6], chars[7], chars[8]}
        };
        return grid;
    }

    private static void printBoard(int[][] grid) {
        final String line = "---------\n";
        final String left = "| ";
        final String right = " |\n";
        StringBuilder board = new StringBuilder();
        board.append(line).append(left)
                .append((char)grid[0][0]).append(" ")
                .append((char)grid[0][1]).append(" ")
                .append((char)grid[0][2]).append(right)
                .append(left)
                .append((char)grid[1][0]).append(" ")
                .append((char)grid[1][1]).append(" ")
                .append((char)grid[1][2]).append(right)
                .append(left)
                .append((char)grid[2][0]).append(" ")
                .append((char)grid[2][1]).append(" ")
                .append((char)grid[2][2]).append(right)
                .append(line);
        System.out.printf("""
                %s
                """, board);
    }

//    private static void printGrid(char[] board) {
//        String outline = "---------";
//        StringBuilder builder = new StringBuilder();
//        builder.append(outline).append("\n");
//        for (int i = 0; i < board.length; i++) {
//            if (i %  3 == 0) {
//                builder.append("| ");
//            }
//            builder.append(board[i]).append(" ");
//            if ( (i + 1) % 3 == 0 ) {
//                builder.append("|\n");
//            }
//        }
//        builder.append(outline).append("\n");
//        System.out.printf("""
//                %s
//                """, builder);
//    }


    private static int[] getMoveCoordinates(Scanner scanner) {
        int[] coordinates;
        while (true) {
            String input = scanner.nextLine();
            if (validateMoveCoordinates(input)) {
                coordinates = getInts(input);
                break;
            }
        }
        return coordinates;
    }
    // need to consider flow here. If I have a number pair I want to check next if they are within range
    // if they are in a valid range then I want to validate if

    private static int[] getInts(String input) {
        int[] coordinates;
        coordinates = Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        return coordinates;
    }

    private static boolean validateMoveCoordinates(String input) {
        boolean isNumberPair = isItANumberPair(input);
    }

    private static boolean isItANumberPair(String input) {
        String regex = "\\d\\s+\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            printMessage(NUMBERS);
            return false;
        }
        else {
            return true;
        }
    }

    private static void printMessage(String x) {
        System.out.println(x);
    }

    private static String doAnalysis(int[] winCounters, int[] moveCounters) {
        if (winCounters[0] == 1 && winCounters[1] == 1) {
            return "Impossible";
        }
        if (moveCounters[0] - moveCounters[1] >= 2 || moveCounters[1] - moveCounters[0] >= 2) {
            return "Impossible";
        }
        if (winCounters[0] == 0 && winCounters[1] == 0) {
            if (moveCounters[2] > 0) {
                return "Game not finished";
            } else {
                return "Draw";
            }
        }
        if (winCounters[0] >= 1) {
            return "X wins";
        }
        if ( winCounters[1] >= 1) {
            return "O wins";
        }

        return "Draw";
    }

    private static int[] checkWinner(char[][] options) {
        char [] winX = {'X', 'X', 'X'};
        char [] winO = {'O', 'O', 'O'};
        int XCounter =0;
        int OCounter =0;
        for (int i = 0; i < options.length; i++) {
            if (Arrays.equals(options[i], winX)) {
                XCounter++;
            } else if (Arrays.equals(options[i], winO)) {
                OCounter++;
            }
        }
        int [] counters = {XCounter, OCounter};
        return counters;
    }

    private static int[] checkMoves(char[] chars) {
        int XCounter = 0;
        int OCounter = 0;
        int emptyCounter = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'X') {
                XCounter++;
            } else if (chars[i] == 'O') {
                OCounter++;
            } else {
                emptyCounter++;
            }
        }
        int[] counters = {XCounter, OCounter, emptyCounter};
        return counters;
    }

    private static char[][] prepareForAnalysis(char[] chars) {
        char [][] options = {
                {chars[0], chars[1], chars[2]},
                {chars[3], chars[4], chars[5]},
                {chars[6], chars[7], chars[8]},
                {chars[0], chars[3], chars[6]},
                {chars[1], chars[4], chars[7]},
                {chars[2], chars[5], chars[8]},
                {chars[0], chars[4], chars[8]},
                {chars[2], chars[4], chars[6]}
        };
//        for (int i = 0; i < options.length; i++) {
//            System.out.println(Arrays.toString(options[i]));
//        }
        return options;
    }



    private static char[] getChars(String input) {
        char[] chars = input.toCharArray();
        return chars;
    }
}
