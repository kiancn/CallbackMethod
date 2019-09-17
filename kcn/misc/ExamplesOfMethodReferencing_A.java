package kcn.misc;

import kcn.datastructures.MePack;
import kcn.datastructures.MeReference;
import kcn.datastructures.MethodPack;
import kcn.datastructures.MethodReference;
import kcn.utility.TO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * All these examples look/are bloated because the MeReferences and MePacks need to material to work on
 */

public class ExamplesOfMethodReferencing_A
{

    public void runAllExamplesReadCode()
            throws
            NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException
    {
        test0_NonGenericMethodReference();

        test1MeReferenceWith2parametersVV_returnsO();

        test2MeReferenceWith2ParametersVV_returnsO();

        test3MeReferenceWith2ParametersVV_OReturn();

        test4MeReferenceWith2ParametersVV_ArrayReturn();

        test5_runObjV();

        test6();

        test7PassingMePacksAround();

    }

    /**
     * Testing the non-generic version of MethodReferences and MethodPacks
     */
    public void test0_NonGenericMethodReference() throws
                                                  NoSuchMethodException,
                                                  InvocationTargetException,
                                                  IllegalAccessException
    {
        CharMatrix matrix3 = new CharMatrix(TO.blue("¤"), 5);

        /** Test of MethodReference type and MethodPack type **/
        /*The first ever instance of MethodReference,
         * using the constructor that takes a stringy name of
         * method
         */
        /** 'matrix3' is the object that executes the method, simpleMoveThough is the name of the method
         * involved **/
        MethodReference makeMatrixMethod = new MethodReference(matrix3, "simpleMoveThrough");

        MethodPack event = new MethodPack();

        event.add(makeMatrixMethod);

        event.add(new MethodReference(matrix3, "simpleMoveThrough"));

        event.run();


        /** Test of MethodReference type and MethodPack type **/
        /* Second test of same feature */
        SimpleCalculations sc = new SimpleCalculations();

        MethodReference square = new MethodReference(sc, sc.getClass().getMethod("NumberSquared", int.class));

        MethodReference abs = new MethodReference(sc, sc.getClass().getMethod("NumberAbsolute", int.class));

        /* running a single MethodReference with parameter and no return type */
        square.run(5000);

        /* Testing MethodPack with MethodReferences w no return ( .returnResult )*/
        MethodPack mp = new MethodPack();
        mp.add(square);
        mp.add(abs);
        mp.run(-4);
        mp.run(10);
        mp.run(25);

        /* Any kind of object can be passed to returnResult; but return type of wrapped method must match parameter type */
        MethodPack stringMethods = new MethodPack();
        MethodReference stringMethod = new MethodReference(sc, sc.getClass().getMethod("returnString", String.class));
        System.out.println(stringMethod.runWithReturn("Abracadrabra"));

    }

    /* Testing MethodReferenceGeneric where 'run method' requires two parameters and has a return */
    public void test1MeReferenceWith2parametersVV_returnsO() throws
                                                             NoSuchMethodException,
                                                             InvocationTargetException,
                                                             IllegalAccessException
    {
        /* An rather random object with an appropriate method available */
        SimpleCalculations calc = new SimpleCalculations();

        /*
         Three ways to achieve the same thing:
         * In order of difficulty for user:
         * The first (A) one is longer for the user AND equivalent internally to
         * The second (B)shorter way, probably more useful
         *  */

        MeReference<Integer, Int2D> returnMethod;
        Class[] mPClasses = {int.class, int.class};
        /* Technique A for giving method argument */
        returnMethod = new MeReference<>(calc, calc.getClass().getMethod("makeIntCoordinate",
                                                                         mPClasses));

        /* Technique B for giving method argument: A & B are equivalent */
        returnMethod = new MeReference<>(calc, "makeIntCoordinate", mPClasses);


        /* ACTION! */
        Int2D coord = returnMethod.run_VV(1, 2);

        System.out.println("Coordinate created; X: "
                           + coord.x() + " | Y: "
                           + coord.y());

        /* the same ACTION again */
        coord = returnMethod.run_VV(4, 5);

        System.out.println("Coordinate created; X: "
                           + returnMethod.run_VV(4, 5).x()
                           + " | Y: " + coord.y());
    }

    public void test2MeReferenceWith2ParametersVV_returnsO() throws
                                                             NoSuchMethodException,
                                                             InvocationTargetException,
                                                             IllegalAccessException
    {
        SimpleCalculations calc = new SimpleCalculations();

        MeReference<Integer, Int2D> returnMethod;

        /* notice the way (type class) parameters after 'name' are listed vs. in test1 */
        returnMethod = new MeReference<>(calc, calc.getClass().getMethod("makeIntCoordinate",
                                                                         int.class, int.class));

        /* MethodReference passed as an argument in a method */
        ExampleMethodsClass_A arbi = new ExampleMethodsClass_A();

        arbi.printSomething(returnMethod, 100, 150);
        arbi.printSomething(returnMethod, 15, 60);
    }

    public void test3MeReferenceWith2ParametersVV_OReturn() throws
                                                            NoSuchMethodException,
                                                            InvocationTargetException,
                                                            IllegalAccessException
    {


        SimpleCalculations calc = new SimpleCalculations();

        MeReference<Integer, Int2D> returnMethod;

        returnMethod = new MeReference<>(calc, calc.getClass().getMethod("makeIntCoordinate",
                                                                         new Class[]{int.class, int.class}));

        /* MethodReference passed as an argument in a method */
        ExampleMethodsClass_A arbi = new ExampleMethodsClass_A();

        arbi.printSomething(returnMethod, 100, 150);
        arbi.printSomething(returnMethod, 15, 60);

    }

    public void test4MeReferenceWith2ParametersVV_ArrayReturn() throws
                                                                NoSuchMethodException,
                                                                InvocationTargetException,
                                                                IllegalAccessException
    {
        /* Testing MethodReferenceGeneric with array return type og <>   */
        SimpleCalculations calc = new SimpleCalculations();

        Int2D[] iCoordsArray = new Int2D[]{
                new Int2D(1, 2),
                new Int2D(2, 3),
                new Int2D(3, 4),
                new Int2D(4, 5)
        };

        Class iCoordsArrayClass = iCoordsArray.getClass();


        Method iCMethod = calc.getClass().getMethod("coordinateMean", iCoordsArrayClass);

        var iCArrayMethodReference = new MeReference<>(calc, iCMethod);
        // this is like magic to me, notice the empty <> - gives <Object,Object>


        /* NB. coordinateMean method does nothing but return the mean of the values in the array */
        System.out.println("iCArrayMethodReference " + iCArrayMethodReference.run(iCoordsArray));

    }

    public void test5_runObjV() throws NoSuchMethodException, InvocationTargetException,
                                       IllegalAccessException
    {
        /*
         * Testing run_ObjV(Object.. V...);
         *      - it takes first an object of type Object
         *      - then it takes an object type V
         *      - it returns type O (M..R..G.. <V,O>)
         *
         *      - NB. the contained method must take care to cast the Object to
         *      the proper type!!
         */

        /* Preparing the test object, that will execute methods*/
        ExampleMethodsClass_A testObject = new ExampleMethodsClass_A(10);



        /* make an array of the classes fitting the parameter-list */
        Class[] argClasses = {Object.class, Int2D.class};



        /* fetch reference to the mehod, supplying the correct array of args */
        Method giveFloatCoordinateMethod = testObject.getClass().getMethod("giveFloatCoordinate", argClasses);

        /* Declaring the actual method reference; supplying the argClasses  */
        var giveFCoordMeRef = new MeReference<Int2D, Float2D>(testObject, "giveFloatCoordinate", argClasses);

        /* This is NOT nessecary; I'm simply showing the feature that the Method parameter can also be
         * fullly prepared; and that you therefore can used a different constructor */
        giveFCoordMeRef = new MeReference<>(testObject, giveFloatCoordinateMethod);


        /* Preparing an Object; parameter in referenced method. RANDOM EX */
        Object objectUsedAsInput = "This is a String cast as an Object!";

        /* Preparing an IntCoordinate; parameter in referenced method RANDOM EX */
        Int2D int2D = new Int2D(5, 34);

        /* object method referenced will return value to */
        Float2D float2D;

        /* THIS IS THE ACTION: */
        float2D = giveFCoordMeRef.run_ObjV(objectUsedAsInput, int2D);

        System.out.println("Main found a float coordinate: X: " + float2D.x() + " Y: " + float2D.y());

    }

    public void test6() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
    {
        /*                                                                         */
        /* Testing generic method packs     */

        /* make an array of the classes fitting the parameter-list */
        Class[] argClasses = {Object.class, Int2D.class};
        /* Conjuring a testObject to perform tasks*/
        ExampleMethodsClass_A testObject = new ExampleMethodsClass_A();

        /* fetch reference to the mehod, supplying the correct array of args */
        Method giveFCoordMeRef;
        giveFCoordMeRef = testObject.getClass().getMethod("giveFloatCoordinate", argClasses);

        MeReference giveFloatCoordGMR = new MeReference<Int2D, Float2D>(testObject, giveFCoordMeRef);

        /* Newing up a methodpack of supplied types*/
        MePack<Int2D, Float2D> genPack = new MePack<>();

        /* Just an appropriate and random Object */
        Object objectUsedAsInput = "This is a String cast as an Object!";

        /* Adding x methods to genPack */
        for(int i = 0; i < 5; i++)
        {
            /*the way to add a method reference to pack
             *  - this IS strongly typed, so delicious*/
            genPack.add(giveFloatCoordGMR);
        }


        /* Making a list and putting all method references into it.*/
        List<MeReference<Int2D, Float2D>> packOfMethods = genPack.getMethods();

        /* Getting elements from same list  */
        for(int i = 0; i < packOfMethods.size(); i++)
        {  /* and executing each one with different parameters. */
            packOfMethods.get(i).run_ObjV(
                    objectUsedAsInput,
                    new Int2D(i * i, i + i));
        }
    }

    public void test7PassingMePacksAround() throws
                                            InvocationTargetException,
                                            IllegalAccessException,
                                            NoSuchMethodException
    {

        /* an array of strings to send and manipulate ... [Class/object is purely placeholder] */
        String[] stringsToMessWith = new String[]{
                "Onki was a weird boy. Are you?",
                "Algernon was a sweet mouse.",
                "True men flee not darkness.",
                "All mortals manipulate matter.",
                "Gunki gunk. PLUNKNESS.",
                "Scully was the best detective.",
                "History was too short.",
                "Delighted by something the others went away."
        };

        var aMePack = new MePack<String, String>();

        /* maybe easiest way to prepare parameter classes for MeRef construction: */
        Class[] parameterClasses = {String.class, String.class};

        /*but you need the methods to match ;) [Class/object is purely placeholder] */
        ExampleMethodsClass_A objectWithMethodsA = new ExampleMethodsClass_A(5);
        ExampleMethodsClass_A objectWithMethodsB = new ExampleMethodsClass_A(5);
        ExampleMethodsClass_A objectWithMethodsC = new ExampleMethodsClass_A(5);


        /* 1 of 3 methodreferences (that will be passed) */
        MeReference<String, String> meRef_Q;
        meRef_Q = new MeReference<>(objectWithMethodsA, "printMessage1", parameterClasses);

        /* 2 of 3 methodreferences (that will be passed) */
        MeReference<String, String> meRef_QQ;
        meRef_QQ = new MeReference<>(objectWithMethodsB, "printMessage2", parameterClasses);

        /* 3 of 3 methodreferences (that will be passed) */
        MeReference<String, String> meRef_QQQ;
        meRef_QQQ = new MeReference<>(objectWithMethodsC, "printMessage3", parameterClasses);

        /* adding MeRefs: order makes a difference - first in first out*/
        aMePack.add(meRef_Q);
        aMePack.add(meRef_QQ);
        aMePack.add(meRef_QQQ);


        /* and an object to receive and process the MeRefs*/
        ExampleMethodsClass_B exB = new ExampleMethodsClass_B();

        /* sending method pack */
        exB.clientMethods = aMePack;

        /* supplying string-array (example specific of course)*/
        exB.stringArray = stringsToMessWith.clone();

        /* processing string arrays supplied with methods supplied */
        exB.processStringArray();

    }
}
