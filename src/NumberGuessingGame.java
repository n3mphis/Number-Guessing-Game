import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);
    private static int lowerLimit = 1;
    private static int upperLimit = 100;
    private static int chances;
    private static int option;
    private static int numberGuess;
    private static String keepPlaying;
    private static int randomNumber = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
    private static GameTimer timer = new GameTimer();
    private static String outOfGuesses = "\nYou ran out of guesses. 😔 \nThe secret number was " + randomNumber;

    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        System.out.println("----- Welcome to the Number Guessing Game! -----");
        System.out.println("I'm thinking of a number between " + lowerLimit + " and " + upperLimit + " 🤔");

        System.out.println();

        System.out.println("""
                Please select the difficulty level:
                1. Easy (10 chances)
                2. Medium (5 chances)
                3. Hard (3 chances)
                
                6. Quit
                """);

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter your choice: ");
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                if (option >= 1 && option <= 3 || option == 6) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Invalid option ❗. Please enter a number");
                scanner.nextLine();
            }
        }
        switch (option) {
            case 1:
                easyLevel();
                break;
            case 2:
                mediumLevel();
                break;
            case 3:
                hardLevel();
                break;
            case 6:
                System.out.println("Exiting the program. Goodbye!");
                break;
            default:
                System.out.println("Not a valid option.");
                break;
        }
    }

    public static void playGame(String difficulty, int chancesGuessing) {
        System.out.println("Great! You have selected the " + difficulty + " level. \nLet's start the game! ");
        timer.start();
        chances = chancesGuessing;
        int attempts = 0;

        while (chances > 0) {
            System.out.print("Enter your guess: ");
            try {
                numberGuess = scanner.nextInt();
                scanner.nextLine();
                attempts++;
            } catch (NumberFormatException e) {
                System.out.println("❗ Invalid option. Please enter a number. ❗");
                continue;
            }

            if (numberGuess > randomNumber) {
                System.out.println("Incorrect! ❌ The number is less than " + numberGuess + ".");
                chances--;

                if (chances > 1) {
                    System.out.println("You have " + chances + " guesses left");
                } else if (chances == 1){
                    System.out.println("You have " + chances + " guess left");
                } else {
                    System.out.println(outOfGuesses);
                }
            } else if (numberGuess < randomNumber) {
                System.out.println("Incorrect! ❌ The number is greater than " + numberGuess + ".");
                chances--;

                if (chances > 1) {
                    System.out.println("You have " + chances + " guesses left");
                } else if (chances == 1){
                    System.out.println("You have " + chances + " guess left");
                } else {
                    System.out.println(outOfGuesses);
                }
            } else {
                if (attempts == 1) {
                    System.out.println("🎉 Congratulations! You guessed the number in 1 attempt. 🎉");
                    System.out.println("Time taken: " + timer.getTimeTaken());
                } else {
                    System.out.println("🎉 Congratulations! You guessed the number in " + attempts + " attempts. 🎉");
                    System.out.println("Time taken: " + timer.getTimeTaken());
                }
                break;
            }
        }

        System.out.println();

        keepPlaying();
    }

    public static void keepPlaying() {
        System.out.print("Do you wish to play again? (Y/N): ");
        keepPlaying = scanner.nextLine();

        if (keepPlaying.equalsIgnoreCase("y")) {
            randomNumber = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
            System.out.println();
            showMenu();
        } else {
            System.out.println("Thanks for playing, see you later 😄");
        }
    }

    private static void easyLevel() {
        playGame("easy", 10);
    }

    private static void mediumLevel() {
        playGame("medium", 5);
    }

    private static void hardLevel() {
        playGame("hard", 3);
    }
}
