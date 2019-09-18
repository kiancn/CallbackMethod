import kcn.misc.ExamplesOfMethodReferencing_A;

import java.lang.reflect.InvocationTargetException;

public class Main
{

    public static void main(String[] args) throws
                                           InterruptedException,
                                           NoSuchMethodException,
                                           IllegalAccessException,
                                           InvocationTargetException
    {

        /* Each of the methods below exemplify the used of either:
         *      - MethodReferences and MethodPacks (non-generic)
         *      - MeReferences and MePacks (generic versions)
         *      - just MeReferences (generic)
         */

        /* These examples are not optimal for clarity, I think: and recommend starting with the
        MeReferencePatternExample;
        * It has it's own main. */
        var examples = new ExamplesOfMethodReferencing_A();

//        examples.test0_NonGenericMethodReference(); /* it works fine but takes time to execute */

        examples.test1MeReferenceWith2parametersVV_returnsO(); /**/
        examples.test2MeReferenceWith2ParametersVV_returnsO();
        examples.test3MeReferenceWith2ParametersVV_OReturn();
        examples.test4MeReferenceWith2ParametersVV_ArrayReturn();
        examples.test5_runObjV();
        examples.test6_MePackRunningSomeMeRefs();
//
        examples.test7PassingMePacksAround();

    }
}
