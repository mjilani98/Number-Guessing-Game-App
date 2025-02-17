package com.example.hw2q5;

import java.util.Random;

public class GuessGame {

    private int mysteryNumber;
    private int userGuess;

    public GuessGame()
    {
        //creating a random number between 1 to 100
        Random random = new Random();
        int mysteryNumber = random.nextInt(100) + 1;
    }

    public void setUserGuess(int guess)
    {
        this.userGuess = guess;
    }

    public String getMessage()
    {
        String message = "";

        //if the user guess is same as the random number
        if(userGuess == mysteryNumber)
        {
            message = "Correct , you have guessed the number";
        }
        // if the user guess is in higher than the random number
        else if (userGuess > mysteryNumber)
        {
            message = "Your guess is higher than the number";
        }
        // if the user guess is lower than the number
        else if (userGuess < mysteryNumber)
        {
            message = "Your guess is lower than the number";
        }

        return message;
    }
}
