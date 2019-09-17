package kcn.misc.MeReferencePatternExample;


public class ClassWithMethodToPassAround
{


    /* this is the method that will be passed around */
    public String addTwoNumbers(int a, int b){
        return "" + a + " plus " + b + " is " + (a + b) + ".";
    }

    public String numberToString(int a){
        return "" + a + " is " + a + ".";
    }
}


