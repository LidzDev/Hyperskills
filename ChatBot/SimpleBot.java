package bot;

import java.util.Scanner;

public class SimpleBot {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        greet("SimpleBot", "2025");
        remindName();
        guessAge();
        count();
        test();
        getAnswer();
        end();
    }

    static void greet(String assistantName, String birthYear) {
        System.out.println("Hello! My name is " + assistantName + ".");
        System.out.println("I was created in " + birthYear + ".");
        System.out.println("Please, remind me of your name.");
    }

    static void remindName() {
        String name = scanner.nextLine();
        System.out.printf("What a great name you have, %s!\n", name);
    }

    static void guessAge() {
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");
        int rem3 = scanner.nextInt(), rem5 = scanner.nextInt(), rem7 = scanner.nextInt();
        int age = (rem3 * 70 + rem5 * 21 + rem7 * 15) % 105;
        System.out.printf("Your age is %d, that's a good time to start programming!\n", age);
    }

    static void count() {
        System.out.println("Now I will prove to you that I can count to any number you want.");
        int num = scanner.nextInt();
        for (int i = 0; i <= num; i++) {
            System.out.printf("%d!\n", i);
        }
    }

    static void test() {
        System.out.println("Let's test your programming knowledge.");
        System.out.println("Why do we use methods?");
        System.out.println("1. To repeat a statement multiple times.");
        System.out.println("2. To decompose a program into several small subroutines.");
        System.out.println("3. To determine the execution time of a program.");
        System.out.println("4. To interrupt the execution of a program.");
    }

    static void getAnswer() {
        while (true) {
            int choice = scanner.nextInt();
            if (checkAnswer(choice)){
                break;
            }
        }
    }

    static boolean checkAnswer(int choice) {
        if (choice == 2) {
            return true;
        } else {
            System.out.println("Please, try again.");
            return false;
        }
    }

    static void end() {
        System.out.println("Congratulations, have a nice day!"); // Do not change this text
    }
}
