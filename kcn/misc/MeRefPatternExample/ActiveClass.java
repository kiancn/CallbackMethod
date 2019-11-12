package kcn.misc.MeRefPatternExample;

import kcn.callbackmethods.CallMe;


public class ActiveClass
{
    public void activity_setUpMeRef_SendOff_UseReturn()
    {
        /* this class contains two methods we want to execute somewhere else */
        ClassWithMethodToPassAround executingObject = new ClassWithMethodToPassAround();


        /* Declaring/constructing the MeReference */ /* PLEASE NOTE: a MeRef will become inert if not
        declared right; it logs the failure internally, and that information is easy to come by, check out
        the exposed methods of both MeRef and MePack */
        CallMe<Integer, String> reference = new CallMe<>(executingObject,
                                                         "addTwoNumbers",
                                                         new Class[]{int.class, int.class});
        /* Turning off automatic pre-emptive null checks null */
        reference.setPersistentNullChecks(false);

        /* Declaring another MeRef, for another method */
        CallMe<Integer, String> reference1 = new CallMe<Integer, String>(executingObject,
                                                                         "numberToString",
                                                                         new Class[]{int.class});


        /* this class has a field for a method-reference (and execute it through another method) */
        AnotherClass anotherClassObj = new AnotherClass();

        /* method passed as reference another class */
        anotherClassObj.processingMethod = reference;

        /* makesStringsWithNumbers method 'pulls' the supplied MeRef internally
         *  */
        String recievedString = anotherClassObj.makesStringsWithNumbers(1, 2);

        System.out.println(recievedString);

        /* And pulling the objectified method here, just to show off the action directly */
        recievedString = reference1.run(1);

        System.out.println(recievedString);
    }
}
