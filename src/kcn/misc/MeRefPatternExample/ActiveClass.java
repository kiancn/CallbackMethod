package kcn.misc.MeRefPatternExample;

import kcn.callbackmethod.CallMe;


public class ActiveClass
{
    public void simpleExample()
    {
        /* this class contains two methods we want to execute somewhere else */
        ClassWithMethodToPassAround executingObject = new ClassWithMethodToPassAround();


        /* Declaring/constructing the MeReference */ /* PLEASE NOTE: a MeRef will become inert if not
        declared right; it logs the failure internally, and that information is easy to come by, check out
        the exposed methods of both MeRef and MePack */
        CallMe<Integer, String> reference = new CallMe<>(executingObject, "addTwoNumbers");

        /* this class has a field for a method-reference (and execute it through another method) */
        AnotherClass anotherClassObj = new AnotherClass();

        /* method passed as reference another class */
        anotherClassObj.processingMethod = reference;

        /* makesStringsWithNumbers method 'pulls' the supplied MeRef internally */
        String recievedString = anotherClassObj.makesStringsWithNumbers(1, 2);

        System.out.println(recievedString);

        /* Declaring another MeRef, for another method */
        var reference1 = new CallMe<Integer, String>(executingObject, "numberToString");

        /* And pulling the objectified method here, just to show off the action directly */
        recievedString = reference1.run(1);

        System.out.println(recievedString);
    }
}
