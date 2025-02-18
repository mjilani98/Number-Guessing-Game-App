package com.example.hw2q5;

import java.util.Random;

public class GuessGame {

    private int mysteryNumber;
    private int userGuess;
    private int chances;
    private boolean guessed;

    //default constructor
    public GuessGame()
    {
        //creating a random number between 1 to 100
        Random random = new Random();
        mysteryNumber = random.nextInt(100) + 1;
        //for each new game , the player has 8 chances
        chances = 8;
    }

    //method to get the mystery number
    public int getMysteryNumber()
    {
        return mysteryNumber;
    }

    //method takes the user guess
    public void setUserGuess(int guess)
    {
        this.userGuess = guess;
    }

    //method decrement the number of chances
    public void decrementChances()
    {
        chances -= 1;
    }

    //if the user guessed the number , set the value true
     public boolean getGuessed()
     {
         if(userGuess == mysteryNumber)
         {
             return true;
         }
         else
         {
             return false;
         }
     }

     //method to get the number of chances left
     public int getChances()
     {
         return chances;
     }

     //method returns a message based on the player guess

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
