package com.Task2;

// Task-2: Guess The Number

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalRounds = 3;
        int maxAttempts = 10;
        int score = 0;

        System.out.println("Welcome to the Guess the Number Game!");
        System.out.println("You have " + totalRounds + " rounds to play.");
        System.out.println("You can make up to " + maxAttempts + " attempts per round.");

        for (int round = 1; round <= totalRounds; round++) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nRound " + round + " starts now!");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess (1-100): ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You guessed the correct number.");
                    int pointsEarned = (maxAttempts - attempts + 1) * 10;
                    score += pointsEarned;
                    System.out.println("You earned " + pointsEarned + " points.");
                } else if (userGuess < numberToGuess) {
                    System.out.println("The number is higher than your guess. Try again.");
                } else {
                    System.out.println("The number is lower than your guess. Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + numberToGuess + ".");
            }

            System.out.println("Your score after round " + round + " is: " + score);
        }

        System.out.println("\nGame over! Your final score is: " + score);
        scanner.close();
    }
}