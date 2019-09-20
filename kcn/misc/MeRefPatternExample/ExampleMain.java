package kcn.misc.MeRefPatternExample;

import kcn.methodreferencing.MeRef;

import java.lang.reflect.InvocationTargetException;

/* I am aware that coding standards have not been kept premium
 through-out example; it is written this way for clarity */

public class ExampleMain
{
    public static void main(String[] args)
    {
        ActiveClass example = new ActiveClass();
        /* Actual example */
        example.activity_setUpMeRef_SendOff_UseReturn();
    }
}
