package kcn.misc.MeReferencePatternExample;


public class ClassWithMethodToPassAround
{
    /* MeReferences are generics with two generic types:
     * First type is always used as parameter input type.
     * Second type is generally used as return type.
     *
     */


    /* this is the method that will be passed around */
    public String addTwoNumbers(int a, int b){
        return "" + a + " plus " + b + "is " + (a + b) + ".";
    }
}


