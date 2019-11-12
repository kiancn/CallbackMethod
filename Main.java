import kcn.misc.CallbackMethod_timeTest01;
import kcn.misc.ExamplesOfMethodReferencing_A;

import java.lang.reflect.InvocationTargetException;

/* Each method exemplify the use of either:
 *      - MethodReferences and MethodPacks (non-generic)
 *      - MeReferences and MePacks (generic versions)
 *      - just MeReferences (generic)
 */

public class Main
{
    public static void main(String[] args) throws
                                           InterruptedException,
                                           NoSuchMethodException,
                                           InvocationTargetException,
                                           IllegalAccessException
    {

        System.out.println("Please uncomment the example you'd like to try out \n");

        /* I recommend starting with the MeRefPatternExample
         * It has it's own main. Here it is:
         *  */

//        ExampleMain.main(new String[]{});


        /* Example shows declaration of a MeRef with this-keyword and
         *  and the logging features of MeRefs (MethodReferences, MethodPacks and
         *  MePacks also do exception logging)
         *  */

//        Example_DeclaringWithThisKeyword_ShowLogging demon = new Example_DeclaringWithThisKeyword_ShowLogging();
//        demon.demonstration_ExposeLogging_DeclaringMeRef();

        /*
        * A bunch of commented examples of declarations of MeRef<V,O>s and MePack<V,O>s
        * and simplistic (but hopefully clear'ish) examples of how to use them.
        * */

        var examples = new ExamplesOfMethodReferencing_A();
//
//        examples.test2_MeRef_ParametersVV_returnsO();
//        examples.test7_PassingMePacksAround();

        examples.runAllExamplesReadCode();
//
//        examples.test0_NonGenericMethodReference();
//        examples.test1_MeRef_2parametersVV_returnsO();        /* I blame so many of these examples being */
//        examples.test3_MeRef_2ParametersVV_OReturn();         /* run_VV on the fact that I am lazy.      */
//        examples.test4_MeRef_2ParametersVArray_OReturn();
//
//        examples.test5_runObjV();
//        examples.test6_MePackRunningSomeMeRefs();

        CallbackMethod_timeTest01 timeTest = new CallbackMethod_timeTest01();

        timeTest.testMenu();


//        timeTest.multipleTests_In_10_Fold_Increments(1, 4,false);
//        timeTest.multipleTests_In_10_Fold_Increments(2, 10,false);
//        timeTest.multipleTests_In_10_Fold_Increments(2, 10,false);
//        timeTest.multipleTests_In_Doubling_Increments(2, 16,false);


    }
}
