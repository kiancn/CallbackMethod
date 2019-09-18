package kcn.misc.MeReferencePatternExample;

import kcn.methodreferencing.MeRef;

import java.lang.reflect.InvocationTargetException;

class AnotherClass
{
    MeRef<Integer, String> processingMethod;

    public String makesStringsWithNumbers(int number, int numb) throws
                                                                InvocationTargetException,
                                                                IllegalAccessException
    {
        return processingMethod.run_VV(number, numb);
    }
}
