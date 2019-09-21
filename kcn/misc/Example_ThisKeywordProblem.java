package kcn.misc;

import kcn.methodreferencing.MeRef;

import java.lang.reflect.Modifier;

public class Example_ThisKeywordProblem
{
    MeRef<Integer, Double> intToDoubleMethod;

    public Double intToDouble(int number)
    {
        return Double.valueOf(number);
    }

    public void demonstrationThisKeywordProblematic() throws NoSuchMethodException
    {
        int c = 1;

        /* attempting to create a MeRef using this keyword;*/
        System.out.println("Try\t" + c++ + "");
        intToDoubleMethod = new MeRef<Integer, Double>(this, this.getClass().getMethod("intToDouble",
                                                                                     new Class[]{int.class}));
        /* the malfunction is caught internally  */
        printMeRefProblemLog(intToDoubleMethod);

        System.out.println("Try\t" + c++ + "");
        intToDoubleMethod = new MeRef<Integer, Double>(this, "intToDouble", new Class[]{int.class});
        printMeRefProblemLog(intToDoubleMethod);


        Example_ThisKeywordProblem ex = this;
        System.out.println("Try\t" + c++ + "");
        intToDoubleMethod = new MeRef<Integer, Double>(ex, ex.getClass().getMethod("intToDouble",
                                                                                   new Class[]{int.class}));
        printMeRefProblemLog(intToDoubleMethod);

        System.out.println("Try\t" + c++ + "");
        TinyNestedClass tinyInstance = new TinyNestedClass();
        intToDoubleMethod = new MeRef<Integer, Double>(tinyInstance,
                                                       tinyInstance.getClass().getMethod("intToDouble",
                                                                                         int.class));
        printMeRefProblemLog(intToDoubleMethod);

        Double d = intToDoubleMethod.run(42);

        System.out.println("Try\t" + c++ + "");
        intToDoubleMethod = new MeRef<Integer, Double>(tinyInstance, "intToDouble", new Class[]{int.class});
        printMeRefProblemLog(intToDoubleMethod);

    }

    void printMeRefProblemLog(MeRef<Integer, Double> meref)
    {
        System.out.println("MeRef is broke: " + meref.isReferenceBroke());
        System.out.println("MeRef executing Object null: " + meref.isExecutingObjectNull());
        System.out.println("MeRef Method object null: " + meref.isMethodObjectNull());
        System.out.println("Exceptions occurred: " + meref.didExceptionsHappen());
        System.out.println("Printing exceptions count array: ");

        int[] exCa = meref.getExceptionsCaught();
        int exceptionsCount = 0;
        for(int i = 0; i < exCa.length; i++)
        {
            System.out.print("[# " + i + "]> " + exCa[i] + " ");
            exceptionsCount += exCa[i];
        }

        if(exceptionsCount == 0)
        {
            System.out.println("\nNo exceptions.");
        } else
        {
            String[] exceptions = meref.getExceptionDescriptionStrings();
            for(int i = 0; i < exceptions.length; i++)
            {
                System.out.print("\n\t[# " + i + "] " + exceptions[i]);
            }
        }

        System.out.println("\n");
    }

    class TinyNestedClass
    {
        public Double intToDouble(int number){ return Double.valueOf(number); }
    }
}




