package kcn.misc.MeReferencePatternExample;

import kcn.datastructures.MeRef;

import java.lang.reflect.InvocationTargetException;

/* Main constructs a method-reference, passes it to another object (of type AnotherObject),
 * which executes it internally.
 **/
public class ExampleMain
{
    public static void main(String[] args) throws
                                           NoSuchMethodException,
                                           InvocationTargetException,
                                           IllegalAccessException
    {
        /* this class contains a method we want to execute */
        ClassWithMethodToPassAround executingObject = new ClassWithMethodToPassAround();

        /* this class can take a method-reference (and execute it through another method) */
        AnotherClass anotherClassObj = new AnotherClass();

        /* Declaring/constructing the MeReference */
        MeRef<Integer, String> reference = new MeRef<>(executingObject,
                                                       "addTwoNumbers",
                                                       new Class[]{int.class, int.class});
        /* Declaring another, for another method */
        MeRef<Integer, String> reference1 = new MeRef<Integer, String>(executingObject,
                                                                       "numberToString",
                                                                       new Class[]{int.class});
        /* method passed as reference */
        anotherClassObj.processingMethod = reference;

        /* method 'pulls' the       method-reference       internally  on the other object */
        String recievedString = anotherClassObj.makesStringsWithNumbers(1, 2);

        System.out.println(recievedString);


        recievedString = reference1.run(1);

        System.out.println(recievedString);
    }
}
