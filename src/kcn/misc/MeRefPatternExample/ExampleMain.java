package kcn.misc.MeRefPatternExample;

/* I am aware that coding standards have not been kept premium
 through-out example; it is written this way for clarity */

import kcn.callbackmethods.CallMe;
import kcn.callbackmethods.CallbackMethod;
import kcn.misc.Int3;

public class ExampleMain
{
    public static void main(String[] args)
    {
        System.out.println("\t\nInside >> ExampleMain main method << \t\n");

        ActiveClass example = new ActiveClass();

        /* Actual example */
        example.simpleExample();

        ExampleMain exampleMain = new ExampleMain();

        CallbackMethod sumM = new CallbackMethod(exampleMain, "returnInt3");

        Int3 int3 = (Int3)sumM.run(2, 8, 4);


        var combinos = new CallMe<Object, String>(exampleMain, "combineThese");
        System.out.println(combinos.didExceptionsHappen());
        System.out.println(combinos.run(4, false, int3));
        System.out.println(combinos.isReferenceBroke());

        var gSum = new CallMe<Integer, Int3>(exampleMain, "returnInt3");
        int3 = gSum.run(4, 12, 32);

        System.out.println(int3);
    }

    public String combineThese(int number, boolean truth, Int3 v3)
    {
        System.out.println("combineThese ran");
        return "Number supplied: " + number + "\t Thruth: " + truth + "\t Int3 supplied: " + v3;
    }

    public Int3 returnInt3(int a, int b, int c){return new Int3(a, b, c);}
}
