package com.debiansystems.junit4;

public class MyMath {

    public static int sum(int[] numbers){
        int sum = 0;
        for(int i : numbers){
            sum += i;
        }
        return sum;
    }
}
