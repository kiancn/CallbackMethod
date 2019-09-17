package kcn.misc.MeReferencePatternExample;

import kcn.datastructures.MeReference;

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

        /* constructing the MeReference */
        MeReference<Integer, String> reference = new MeReference<>(executingObject,
                                                                   "addTwoNumbers",
                                                                   new Class[]{int.class, int.class});
        /* method passed as reference */
        anotherClassObj.processingMethod = reference;

        /* method 'pulls' the       method-reference       internally  on the other object */
        String recievedString = anotherClassObj.makesStringsWithNumbers(1, 2);

        System.out.println(recievedString);
    }
}
