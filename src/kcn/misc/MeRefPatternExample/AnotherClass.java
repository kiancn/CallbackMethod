package kcn.misc.MeRefPatternExample;
import kcn.callbackmethod.CallMe;

class AnotherClass
{
    CallMe<Integer, String> processingMethod;

    public String makesStringsWithNumbers(int number, int numb)
    {
        return processingMethod.run(number, numb);
    }
}
