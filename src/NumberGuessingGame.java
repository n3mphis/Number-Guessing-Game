import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);
    private static int lowerLimit = 1;
    private static int upperLimit = 100;
    private static int chances;
    private static int option;
    private static String keepPlaying;
    private static int randomNumber = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
    public static void main(String[] args) {
        System.out.println(randomNumber);
        gameStart();
    }

    public static void gameStart() {
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between " + lowerLimit + " and " + upperLimit + ".");
        System.out.println("You have 5 chances to guess the correct number.");

        System.out.println();

        System.out.println("""
                Please select the difficult level:
                1. Easy (10 chances)
                2. Medium (5 chances)
                3. Hard (3 chances)
                """);

        System.out.print("Enter your choice: ");
        option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1:
                easyLevel();
                break;
        }
    }

    private static void easyLevel() {
        System.out.println("Great! You have select the Easy difficulty level. \nLet's start the game!");
        chances = 10;
        while (chances != 0) {
            System.out.print("Enter your guess: ");
            int numberGuess = scanner.nextInt();
            scanner.nextLine();

            if (numberGuess > randomNumber) {
                System.out.println("Incorrect! The number is less than " + numberGuess + ".");
                chances--;
            } else if (numberGuess < randomNumber) {
                System.out.println("Incorrect! The number is greater than " + numberGuess + ".");
                chances--;
            } else {
                if (chances == 10) {
                    System.out.println("Congratulations! You guessed the correct number in 1 attempt.");
                } else {
                    System.out.println("Congratulations! You guessed the correct number in " + chances + " attempts.");
                }
                break;
            }
        }
        if (chances == 0) {
            System.out.println("You ran out of guesses!");
        }

        System.out.println();

        System.out.println("Do you wish to play again? (Y/N)");
        keepPlaying = scanner.nextLine();

        if (keepPlaying.equalsIgnoreCase("y")) {
            randomNumber = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
            easyLevel();
        } else {
            System.out.println("Exiting the program! Goodby :)");
        }
    }
}
