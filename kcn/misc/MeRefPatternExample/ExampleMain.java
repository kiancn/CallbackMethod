package kcn.misc.MeRefPatternExample;

/* I am aware that coding standards have not been kept premium
 through-out example; it is written this way for clarity */

public class ExampleMain
{
    public static void main(String[] args)
    {
        System.out.println("\t\nInside >> ExampleMain main method << \t\n");

        ActiveClass example = new ActiveClass();

        /* Actual example */
        example.activity_setUpMeRef_SendOff_UseReturn();
    }
}
