package kcn.misc.MeRefPatternExample;

/* just a class with two methods that have parameters and return types that fit the example */
public class ClassWithMethodToPassAround
{

    public String addTwoNumbers(int a, int b){
        return "" + a + " plus " + b + " is " + (a + b) + ".";
    }

    public String numberToString(int a){
        return "" + a + " is " + a + ".";
    }
}


