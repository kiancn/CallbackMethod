package kcn.misc.MeRefPatternExample;
import kcn.methodreferencing.MeRef;

class AnotherClass
{
    MeRef<Integer, String> processingMethod;

    public String makesStringsWithNumbers(int number, int numb)
    {
        return processingMethod.run_VV(number, numb);
    }
}
