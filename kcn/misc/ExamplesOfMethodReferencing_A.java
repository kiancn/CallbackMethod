package kcn.misc;

import kcn.callbackmethods.CallMePack;
import kcn.callbackmethods.CallMe;
import kcn.callbackmethods.CallbackPack;
import kcn.callbackmethods.CallbackMethod;
//import kcn.utility.TO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* I accept the throws for readability; and the stack-trace is nice for direct debugging anyway
 * All possible exceptions in the MethodReferences and MeRefs are handled internally, also in MethodPack,
 * not yet in MePack; Example_DeclaringWithThisKeyword_ShowLogging class this for the MeRef.
 *
 * All the Method References and packs have 'safety by default'; you can declare one unsuccessfully
 *  and not get an error; or a method reference might stop working because of exceptions, and no exception
 * will be thrown. Bad? Maybe.
 * The current functionality follows the idea that MeRefs are best used as callback methods or signalling
 * tasks with many clients (that is what the packs are for).
 * As such I'd rather safely/prepared return a null (when executing a reffed method)
 *                  or do a quick .isReferenceBroke(), which returns true if reference has thrown exceptions
 * (Recommended checking when instancing and when unsure of the current state of affairs when executing
 * contained methods).
 *
 * The other part is that the MePacks CAN check if each reference is broke before attempting to execute it
 * and remove from itself.
 *  */

/**
 * I apologize for confusing tests and rather random example material. Later examples are better IMO.
 * I'll build template examples for all configurations ASP (at some point )
 * I believe test2 ended up being the most .
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

        test1_MeRef_2parametersVV_returnsO();

        test2_MeRef_ParametersVV_returnsO();

        test3_MeRef_2ParametersVV_OReturn();

        test4_MeRef_2ParametersVArray_OReturn();

        test5_runObjV();

        test6_MePackRunningSomeMeRefs();

        test7_PassingMePacksAround();
    }

    /**
     * Testing the non-generic version of MethodReferences and MethodPacks
     */
    public void test0_NonGenericMethodReference() throws NoSuchMethodException
    {
        System.out.println("\t\nInside >> test0_NonGenericMethodReference() << \t\n");

        /* Second test of same feature */
        SimpleCalculations scObject = new SimpleCalculations();

        CallbackMethod square = new CallbackMethod(scObject, scObject.getClass().getMethod("NumberSquared", int.class));

        CallbackMethod abs = new CallbackMethod(scObject, scObject.getClass().getMethod("NumberAbsolute", int.class));

        /* running a single MethodReference with parameter and no return type */
        square.run_paramT(5000);

        /* Testing MethodPack with MethodReferences w no return ( .returnResult )*/
        CallbackPack mp = new CallbackPack();
        mp.add(square);
        mp.add(abs);
        mp.run(-4);
        mp.run(10);
        mp.run(25);

        /* Example of method requiring use of run_paramT_reT; which  but return type of wrapped method must
        match parameter type */
        CallbackPack stringMethods = new CallbackPack();
        CallbackMethod stringMethod = new CallbackMethod(scObject, scObject.getClass().getMethod("returnString", String.class));

        System.out.println(stringMethod.run_paramT_reT("\nAbracadrabra"));

    }

    /* Testing MethodReferenceGeneric where 'run method' requires two parameters and has a return */
    public void test1_MeRef_2parametersVV_returnsO() throws NoSuchMethodException
    {
        System.out.println("\t\nInside >> test1_MeRef_2parametersVV_returnsO() << \t\n");

        /* An rather random object with an appropriate method available */
        SimpleCalculations calc = new SimpleCalculations();

        /* declaring array or classes for use as argument in new MeRef object*/
        Class[] mPClasses = {int.class, int.class};


        /* */
        CallMe<Integer, Int2D> returnMethod;


        /* Technique A for giving Method argument to MeRef constructor */
        returnMethod = new CallMe<>(calc, calc.getClass().getMethod("makeIntCoordinate",
                                                                    mPClasses));

        /* Technique B for giving Method argument to constructor:
         A or B is a matter of circumstance and taste */
        returnMethod = new CallMe<>(calc, "makeIntCoordinate", mPClasses);


        /* ACTION! */
        Int2D coord = returnMethod.run(1, 2);

        System.out.println("Coordinate created; X: "
                           + coord.x() + " | Y: "
                           + coord.y());

        /* and this, just to flaunt, that you can actuallly dot the returned type of the wrapped method:  */
        System.out.println("Coordinate created;" +
                           " X: " + returnMethod.run(4, 5).x()
                           + " | Y: " + returnMethod.run(4, 5).y());
    }

    public void test2_MeRef_ParametersVV_returnsO() throws NoSuchMethodException
    {
        System.out.println("\t\nInside >> test2_MeRef_ParametersVV_returnsO() << \t\n");

        SimpleCalculations calc = new SimpleCalculations();

        CallMe<Integer, Int2D> returnMethod;

        /* notice the way (type class) parameters after 'name' are listed vs. in test1 */
        returnMethod = new CallMe<>(calc, calc.getClass().getMethod("makeIntCoordinate",
                                                                    int.class, int.class));

        /* MethodReference passed as an argument in a method */
        ExampleMethodsClass_A arbi = new ExampleMethodsClass_A();

        arbi.printSomething(returnMethod, 100, 150);
        arbi.printSomething(returnMethod, 15, 60);
    }

    public void test3_MeRef_2ParametersVV_OReturn() throws NoSuchMethodException
    {
        System.out.println("Inside >> test3_MeRef_2ParametersVV_OReturn() << ");

        SimpleCalculations calc = new SimpleCalculations();

        CallMe<Integer, Int2D> returnMethod;

        returnMethod = new CallMe<>(calc, calc.getClass().getMethod("makeIntCoordinate",
                                                                    new Class[]{int.class, int.class}));
        System.out.println("returnMethod in test3_MeRef_2ParametersVV_OReturn tested " + (returnMethod.isReferenceBroke() ? "postive" : "negative") +
                           " for internal errors");

        /* MethodReference passed as an argument in a method */
        ExampleMethodsClass_A arbi = new ExampleMethodsClass_A();

        arbi.printSomething(returnMethod, 100, 150);
        arbi.printSomething(returnMethod, 15, 60);

    }

    public void test4_MeRef_2ParametersVArray_OReturn() throws NoSuchMethodException
    {
        System.out.println("\t\nInside >> test4_MeRef_2ParametersVArray_OReturn() << \t\n");

        /* Testing MethodReferenceGeneric with array return type og <>   */
        SimpleCalculations calc = new SimpleCalculations();

        Int2D[] iCoordsArray = new Int2D[]{
                new Int2D(1, 2),
                new Int2D(2, 3),
                new Int2D(3, 4),
                new Int2D(4, 5)
        };
        /* Getting the Class type object of   */


        Method iCMethod = calc.getClass().getMethod("coordinateMean", iCoordsArray.getClass());


        CallMe<Int2D[], Integer> iCArrayMethodReference = new CallMe<>(calc, iCMethod);
        // this is like magic to me, notice the empty <> - gives <Object,Object>


        /* NB. coordinateMean method does nothing but return the mean of the values in the array */
        System.out.println("iCArrayMethodReference " + iCArrayMethodReference.run(iCoordsArray));

    }

    public void test5_runObjV() throws NoSuchMethodException
    {
        System.out.println("\t\nInside >> test5_runObjV() << \t\n");

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
        ExampleMethodsClass_A testObject = new ExampleMethodsClass_A();



        /* make an array of the classes fitting the parameter-list */
        Class[] argClasses = {Object.class, Int2D.class};



        /* fetch reference to the mehod, supplying the correct array of args */
        Method giveFloatCoordinateMethod = testObject.getClass().getMethod("giveFloatCoordinate", argClasses);

        /* Declaring the actual method reference; supplying the argClasses  */
        var giveFCoordMeRef = new CallMe<Int2D, Float2D>(testObject, "giveFloatCoordinate", argClasses);

        /* This is NOT nessecary; I'm simply showing the feature that the Method parameter can also be
         * fullly prepared; and that you therefore can used a different constructor */
        giveFCoordMeRef = new CallMe<>(testObject, giveFloatCoordinateMethod);


        /* Preparing an Object; parameter in referenced method. RANDOM EX */
        Object objectUsedAsInput = "This is a String cast as an Object!";

        /* Preparing an IntCoordinate; parameter in referenced method RANDOM EX */
        Int2D int2D = new Int2D(5, 34);

        /* object method referenced will return value to */
        Float2D float2D;

        /* THIS IS THE ACTION: */

        float2D = giveFCoordMeRef.execute(objectUsedAsInput, int2D);

        System.out.println("Main found a float coordinate: X: " + float2D.x() + " Y: " + float2D.y());

    }

    public void test6_MePackRunningSomeMeRefs() throws NoSuchMethodException
    {
        System.out.println("\t\nInside >> test6_MePackRunningSomeMeRefs() <<\t\n ");

        /*                                                                         */
        /* Testing generic method packs     */

        /* make an array of the classes fitting the parameter-list */
        Class[] argClasses = {Object.class, Int2D.class};
        /* Conjuring a testObject to perform tasks*/
        ExampleMethodsClass_A testObject = new ExampleMethodsClass_A();

        /* fetch reference to the method, supplying the correct array of args */
        Method giveFCoordMeRef;
        giveFCoordMeRef = testObject.getClass().getMethod("giveFloatCoordinate", argClasses);

        /* Declaring/Constructing a new MeRef */
        CallMe giveFloatCoordGMR = new CallMe<Int2D, Float2D>(testObject, giveFCoordMeRef);

        /* Newing up a methodpack of relevant types*/
        CallMePack<Int2D, Float2D> genPack = new CallMePack<>();

        /* Just an appropriate Object that we also know will cast well as a String */
        Object objectUsedAsInput = "This is a String cast as an Object!";

        /* Adding x methods to genPack */
        for(int i = 0; i < 5; i++)
        {
            /*the way to add a method reference to pack
             *  - this IS strongly typed, so delicious*/
            genPack.add(giveFloatCoordGMR);
        }


        /* Getting elements from same list  */
        for(int i = 0; i < genPack.getMeRefs().size(); i++)
        {  /* and executing each one with different input arguments. */
            genPack.getMeRefs().get(i).execute(objectUsedAsInput, new Int2D(i * i, i + i));
        }
    }

    public void test7_PassingMePacksAround()
    {
        System.out.println("\t\nInside >> test7_PassingMePacksAround() <<\t\n ");

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

        var aMePack = new CallMePack<String, String>();

        /* maybe easiest way to prepare parameter classes for MeRef construction: */
        Class[] parameterClasses = {String.class, String.class};

        /*but you need the methods to match ;) [Class/object is purely placeholder] */
        ExampleMethodsClass_A objectWithMethodsA = new ExampleMethodsClass_A();
        ExampleMethodsClass_A objectWithMethodsB = new ExampleMethodsClass_A();
        ExampleMethodsClass_A objectWithMethodsC = new ExampleMethodsClass_A(); /* using three different
                                        objects to illustrate different senders of methods to a MePack */


        /* 1 of 3 method-references (that will be passed) */
        CallMe<String, String> callMe_Q = new CallMe<>(objectWithMethodsA, "printMessage1", parameterClasses);

        /* 2 of 3 method-refs */
        CallMe<String, String> callMe_QQ;
        callMe_QQ = new CallMe<>(objectWithMethodsB, "printMessage2", parameterClasses);

        /* 3 of 3 method-references */
        CallMe<String, String> callMe_QQQ;
        callMe_QQQ = new CallMe<>(objectWithMethodsC, "printMessage3", parameterClasses);

        /* adding MeRefs: order makes a difference - first in first executed */
        aMePack.add(callMe_Q);
        aMePack.add(callMe_QQ);
        aMePack.add(callMe_QQQ);


        /* and an object to receive and process the MeRefs*/
        ExampleMethodsClass_B exB = new ExampleMethodsClass_B();

        /* sending method pack */
        exB.clientMethods = aMePack;

        /* supplying string-array (example specific of course) */
        exB.stringArray = stringsToMessWith.clone();

        /* processing string arrays supplied with methods supplied */
        exB.processStringArray();

    }
}
