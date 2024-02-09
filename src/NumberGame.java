import java.util.Random;

public class NumberGame {
    private int randomNumber;
    private int attemptsLeft;
    private int score;
    private final int MAX_ATTEMPTS = 5; // Maximum number of attempts allowed

    public NumberGame() {
        generateRandomNumber();
        attemptsLeft = MAX_ATTEMPTS;
        score = 0;
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1; // Generates a random number between 1 and 100
    }

    public String checkGuess(int guess) {
        attemptsLeft--;
        if (guess == randomNumber) {
            score += (MAX_ATTEMPTS - attemptsLeft); // Increment score based on the number of attempts taken
            return "Congratulations! You guessed the correct number.";
        } else if (guess < randomNumber) {
            return "Too low. Try again.";
        } else {
            return "Too high. Try again.";
        }
    }

    public boolean isGameOver() {
        return attemptsLeft <= 0;
    }

    public int getScore() {
        return score;
    }
}
