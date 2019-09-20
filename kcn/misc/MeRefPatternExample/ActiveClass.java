package kcn.misc.MeRefPatternExample;

import kcn.methodreferencing.MeRef;


public class ActiveClass
{
    public void activity_setUpMeRef_SendOff_UseReturn()
    {
        /* this class contains a method we want to execute */
        ClassWithMethodToPassAround executingObject = new ClassWithMethodToPassAround();


        /* this class has a field for a method-reference (and execute it through another method) */
        AnotherClass anotherClassObj = new AnotherClass();

        /* Declaring/constructing the MeReference */
        MeRef<Integer, String> reference = new MeRef<>(executingObject,
                                                       "addTwoNumbers",
                                                       new Class[]{int.class, int.class});
        reference.setAutoCheckForExceptions(false);
        /* Declaring another MeRef, for another method */
        MeRef<Integer, String> reference1 = new MeRef<Integer, String>(executingObject,
                                                                       "numberToString",
                                                                       new Class[]{int.class});
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
