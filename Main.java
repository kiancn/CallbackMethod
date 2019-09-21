import kcn.misc.Example_ThisKeywordProblem;
import kcn.misc.ExamplesOfMethodReferencing_A;
import kcn.misc.MeRefPatternExample.ExampleMain;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLOutput;

public class Main
{
    /* Each method exemplify the use of either:
     *      - MethodReferences and MethodPacks (non-generic)
     *      - MeReferences and MePacks (generic versions)
     *      - just MeReferences (generic)
     */
    public static void main(String[] args) throws
                                           InterruptedException,
                                           NoSuchMethodException,
                                           InvocationTargetException,
                                           IllegalAccessException
    {
        exampleMethods();

    }

    public static void exampleMethods() throws
                                        InterruptedException,
                                        NoSuchMethodException,
                                        IllegalAccessException,
                                        InvocationTargetException
    {
        /* I think: and recommend starting with the MeRefPatternExample;
         * It has it's own main. Here it is: */

//        ExampleMain.main(new String[]{});

        System.out.println("Uncomment the example you'd like to check out.");

//        var examples = new ExamplesOfMethodReferencing_A();

//        examples.test2_MeRef_ParametersVV_returnsO();
//        examples.test7PassingMePacksAround();
//        examples.test0_NonGenericMethodReference();   /* it works fine but takes time to execute, since
//                                                          it 'animates' a walk through a matrix */
//        examples.test1_MeRef_2parametersVV_returnsO();
//        examples.test3_MeRef_2ParametersVV_OReturn();
//        examples.test4_MeRef_2ParametersVV_ArrayReturn(); /* I blame so many of these examples being
//                                                             run_VV on the fact that I am lazy.     */
//        examples.test5_runObjV();
//        examples.test6_MePackRunningSomeMeRefs();


        /* This example  shows  how to declare a MeRef using the this-keyword;
         *  and some of the logging features of MeRefs (MePacks also does exception logging) */

//        Example_ThisKeywordProblem pob = new Example_ThisKeywordProblem();
//        pob.demonstrationThisKeywordProblematic();

    }
}
