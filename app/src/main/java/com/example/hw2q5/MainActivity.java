package com.example.hw2q5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private GuessGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //creating a button handler object
        ButtonHandler handler = new ButtonHandler();

        //getting hold of the submit button
        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(handler);

        //create a game
        game = new GuessGame();

        //setting up the screen
        TextView txtViewChances = findViewById(R.id.txtViewNumOfGuesses);
        txtViewChances.setText(game.getChances()+"");

    }

    private class ButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            //Number of chances left text view
            TextView txtViewChances = findViewById(R.id.txtViewNumOfGuesses);

            //response message text view
            TextView txtViewMessage = findViewById(R.id.txtViewResponse);

            //get the guess
            EditText input = findViewById(R.id.edtTxtInput);
            String strNumber = input.getText().toString();
            int number =0;
            try {
                number = Integer.parseInt(strNumber);
            }
            catch (NumberFormatException e)
            {
                Toast toast = Toast.makeText(getApplicationContext(),"Invalid input",Toast.LENGTH_LONG);
                toast.show();
            }

            //sending the user guess to the model
            game.setUserGuess(number);

            //if the user did not guess the number
            if(game.getGuessed() == false)
            {
                //decrement the number of chances
                game.decrementChances();


                //show number of chances left
                txtViewChances.setText(game.getChances()+"");

                //show message of the range of numbers
                txtViewMessage.setText(game.getMessage());
            }
            //if the user guessed the number
            else
            {
                //disable the button so they can not keep playing
                Button btnSubmit = findViewById(R.id.btnSubmit);
                btnSubmit.setEnabled(false);
                //show a message response , the user guessed the number
                txtViewMessage.setText(game.getMessage());
                //show a dialog box to ask if the they wish to play again
                showDialogBox();
            }

            //if the player out of chances without guessing the number
            if(game.getChances() == 0 )
            {
                //disable the button so they can not keep playing
                Button btnSubmit = findViewById(R.id.btnSubmit);
                btnSubmit.setEnabled(false);

                //show a message that they have lost the game
                txtViewMessage.setText("You ran out of chances\nThe number is : "+game.getMysteryNumber());
                showDialogBox();
            }
        }
    }

    private void showDialogBox()
    {
        //create a dialog box
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(this);

        //set message on dialog box
        dialogBox.setMessage("Game ended , do you want to play again");

        //create an event handler for the dialog box
        DialogBoxListener handler = new DialogBoxListener();

        //add event handler to the dialog box
        dialogBox.setPositiveButton("Yes",handler);
        dialogBox.setNegativeButton("No",handler);
        dialogBox.setNeutralButton("Cancel",handler);
        dialogBox.show();
    }

    private class DialogBoxListener implements DialogInterface.OnClickListener
    {

        //Method takes different actions depending on which button
        //is clicked on the dialog box
        @Override
        public void onClick(DialogInterface dialog, int id)
        {
            //if the positive button is clicked , replay the game
            if(id == -1)
            {
                //creating a new game
                game = new GuessGame();
                //resetting the number of chances
                TextView txtViewChances = findViewById(R.id.txtViewNumOfGuesses);
                txtViewChances.setText(game.getChances()+"");

                //resetting the response message
                TextView txtViewMessage = findViewById(R.id.txtViewResponse);
                txtViewMessage.setText("");

                //reset the input box
                EditText input = findViewById(R.id.edtTxtInput);
                input.setText("");

                //enable the button
                Button btnSubmit = findViewById(R.id.btnSubmit);
                btnSubmit.setEnabled(true);


            }
            //if negative button is clicked , end the game and end the app
            else if(id == -2)
            {
                //destroy the app
                MainActivity.this.finish();
            }
            //if neutral button is clicked, dialog box disappear
            else;
        }
    }
}