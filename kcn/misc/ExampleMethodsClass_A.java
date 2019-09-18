package kcn.misc;

import kcn.methodreferencing.MeRef;
import kcn.utility.TO;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

/* Class holds a bunch to rather random methods that manipulate differente types of data
 * [Class/object is purely placeholder] */
public class ExampleMethodsClass_A
{
    public SimpleCalculations simpCalc;
    int bib;

    public ExampleMethodsClass_A(){}

    public ExampleMethodsClass_A(int i)
    {
        bib = i;
        simpCalc = new SimpleCalculations();
    }

    /**
     * Method printSomething is an example of a method that takes a
     * MethodReferenceGeneric as a parameter and executes it;
     * and because the reference is strongly typed, the return type
     * IS inferred and is utilized at call time :)
     * Also, the method is almost exactly useless.
     */
    public void printSomething(@NotNull MeRef<Integer, Int2D> methodReference, int a, int b) throws
                                                                                          InvocationTargetException,
                                                                                          IllegalAccessException
    {
        System.out.println("This is printSomething() from " +
                           this.getClass().getName() +
                           " instance \n\tX: " +
                           bib * methodReference.run_VV(a, b).x() +
                           "\t Y: " +
                           bib * methodReference.run_VV(a, b).y());
    }

    public Float2D giveFloatCoordinate(Object obj, Int2D iCoo)
    {

        String string = (String)obj;

        System.out.println("Supplied object was hopefully a String.\n Content:" + string);

        Float2D fC = new Float2D(iCoo.x(), iCoo.y());

        System.out.println("Your new float numbers are  X" + fC.x() + "  and  Y " + fC.y());

        return fC;
    }

    public void printMessage1(String sA, String sB)
    {
        System.out.println(TO.purple(sA.toLowerCase() + " " + sB.toLowerCase()));
    }

    public void printMessage2(String sA, String sB)
    {
        System.out.println(TO.green(sA.toUpperCase() + " " + sB.toUpperCase()));
    }

    public void printMessage3(String sA, String sB)
    {
        System.out.println("String A is " + sA.length() +
                           " characters long and string B is " + sB.length());
    }

}
