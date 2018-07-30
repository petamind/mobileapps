package com.petaminds.tung.numberguessinggame;

import java.util.Random;

public class NumberGuessingGame {
    //syntax: access_modif data_type name
    private int secretNumber;
    private String message;
    private int count;

    //constructor 1
    public NumberGuessingGame() {
        //this is a default constructor because it has no input param
        //1. Generate a random secret number
        //a. create a random object for generating a random number
        //Syntax: ClassName varName = new Constructor()
        Random random = new Random();
        this.secretNumber = random.nextInt(101);
        //2. clear the message
        message = ""; //<-- empty string
        //3. clear the count
        this.count = 0;
     }

    //constructor 2
    public NumberGuessingGame(int secretNumber, String message) {
        //custom constructor with 2 input params
        this.secretNumber = secretNumber;
        this.message = message;
    }

    //Method:
    //Syntax: acc_modif return_type method_name ( input params) { body of the method}
    public String check(int guessedNumber){
        //1. increase count by 1
        this.count++; // = count + 1;
        //2. check if guessedNumber = secretNumber
        if(guessedNumber == secretNumber){
            message = "Congrats!!!";
        } else if(guessedNumber < secretNumber) {
            message += count + ". too small!\n";
        } else {//other cases
            message += count + ". too big!\n";
        }
        //3. Generate a suitable message

        return message;
    }
}
