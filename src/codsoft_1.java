import java.util.Random;
import java.util.Scanner;
public class NUMBER_GAME {
    public static void main(String[] args) {
        final int START_NO = 1;
        final int END_NO = 100;
        final int MAX_ATTEMPTS = 7;
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int rounds = 0;

        boolean playAgain = true;
        while (playAgain) {
            Random random = new Random();
            int numberToGuess = random.nextInt(END_NO - START_NO + 1) + START_NO;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nI have selected a number between " +START_NO +
                    " and " + END_NO + ". You have " + MAX_ATTEMPTS + " attempts,No attempts more than this are allowed");

            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess: ");
                int guess;
                try {
                    guess = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Please enter a valid integer");
                    scanner.nextLine();
                    continue;
                }
                attempts++;

                if (guess == numberToGuess) {
                    System.out.println("Correct! You have guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    score++;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low.");
                } else {
                    System.out.println("Too high.");
                }
            }
            if (!guessedCorrectly) {
                System.out.println("Sorry, you are out of attempts. The number was: " + numberToGuess+"Give it an another try");
            }

            rounds++;
            System.out.println("Current Score: " + score + " win(s) out of " + rounds + " round(s).");

            System.out.print("Would you like to try your luck again? (y/n): ");
            String again = scanner.next();
            playAgain = again.equalsIgnoreCase("y");
        }

        System.out.println("Final Score: " + score + " win(s) out of " + rounds + " round(s). Thanks for playing the guess the number game");
        scanner.close();
    }
}
