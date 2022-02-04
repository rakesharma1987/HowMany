package com.howmanykids.aitylgames;

import java.util.Random;

public class Util {

    public int generateRandomNumber(){
        Random rand = new Random();
        return rand.nextInt((8 - 1) + 1) + 1;
    }
}
