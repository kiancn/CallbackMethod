package kcn.misc.MeReferencePatternExample;

import kcn.datastructures.MeReference;

import java.lang.reflect.InvocationTargetException;

class AnotherClass
{
    MeReference<Integer, String> processingMethod;

    public String makesStringsWithNumbers(int number, int numb) throws
                                                                InvocationTargetException,
                                                                IllegalAccessException
    {
        return processingMethod.run_VV(number, numb);
    }
}
