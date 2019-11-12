package kcn.misc;

import kcn.callbackmethods.CallMe;
/* The most interesting part of this is probably the showcasing of the internal logging: so break the
declarations and watch the error logs grow :)
Other than the class simple show some variations of declaring MeRefs */
public class Example_DeclaringWithThisKeyword_ShowLogging
{
    CallMe<Integer, Double> intToDoubleMethod;

    public Double intToDouble(int number)
    {
        return Double.valueOf(number);
    }

    public void demonstration_ExposeLogging_DeclaringMeRef() throws NoSuchMethodException
    {
        System.out.println("\t\nInside >> demonstration_ExposeLogging_DeclaringMeRef() << \t\n");

        int c = 1;

        /* attempting to create a MeRef using this keyword;*/
        System.out.println("Try\t" + c++ + "");
        intToDoubleMethod = new CallMe<Integer, Double>(this,
                                                        this.getClass().getMethod("intToDouble", new Class[]{int.class}));
        /* any malfunction is caught internally; local helper method exposes caught errors */
        printProblems(intToDoubleMethod);

        System.out.println("Try\t" + c++ + "");
        intToDoubleMethod = new CallMe<Integer, Double>(this,
                                                        "intToDouble",
                                                        new Class[]{int.class});
        printProblems(intToDoubleMethod);



        Example_DeclaringWithThisKeyword_ShowLogging ex = this;

        System.out.println("Try\t" + c++ + "");
        intToDoubleMethod = new CallMe<Integer, Double>(ex,
                                                        ex.getClass().getMethod("intToDouble", new Class[]{int.class}));
        printProblems(intToDoubleMethod);

        System.out.println("Try\t" + c++ + "");
        TinyNestedClass tinyInstance = new TinyNestedClass();
        intToDoubleMethod = new CallMe<Integer, Double>(tinyInstance,
                                                        tinyInstance.getClass().getMethod("intToDouble", int.class));
        printProblems(intToDoubleMethod);

        Double d = intToDoubleMethod.run(42);

        System.out.println("Try\t" + c++ + "");
        intToDoubleMethod = new CallMe<Integer, Double>(tinyInstance,
                                                        "intToDouble",
                                                        new Class[]{int.class});
        printProblems(intToDoubleMethod);

        /* This last declaration will fail, because the Method object cannot be identified by constructor
        * without being supplied with fitting Class objects (a Class object is need for each parameter
        * variable (and in identical order).
        *  */
        System.out.println("Try\t" + c++ + "");
        intToDoubleMethod = new CallMe<Integer, Double>(tinyInstance,
                                                        "intToDouble");
        printProblems(intToDoubleMethod);

    }

    /* Here, I am only trying to expose the internal exception-handling and the
     * the methods useful for accessing the state of a MeRef (parallel methods are found on MethodReference)*/
    void printProblems(CallMe<Integer, Double> callMe)
    {
        System.out.println("MeRef is broke: " + callMe.isReferenceBroke());
        System.out.println("MeRef executing Object null: " + callMe.isExecutingObjectNull());
        System.out.println("MeRef Method object null: " + callMe.isMethodObjectNull());
        System.out.println("Exceptions occurred: " + callMe.didExceptionsHappen());
        System.out.println("Printing exceptions count array: ");

        int exCa[] = callMe.getExceptionsCaught();
        int exceptionsCount = 0;
        for(int i = 0; i < exCa.length; i++)
        {
            System.out.print("#" + i + ": " + exCa[i] + "\t");
            exceptionsCount += exCa[i];
        }

        if(exceptionsCount == 0)
        {
            System.out.println("\nNo exceptions.");
        } else
        {
            /* This called method returns a set of strings with names of possible
             errors that could have occurred; the order of this array mirrors the
             order of the array of actually occurred arrays. It's for debugging. */
            String[] exceptionDescriptions = callMe.getExceptionDescriptionStrings();
            int[] exceptions = callMe.getExceptionsCaught();

            for(int i = 0; i < exceptionDescriptions.length; i++)
            {
                System.out.print("\n\t[#"+i+"] "+ exceptionDescriptions[i] + " [" +exceptions[i] + "] ");
            }
        }

        System.out.println("\n");
    }

    class TinyNestedClass
    {
        public Double intToDouble(int number){ return Double.valueOf(number); }
    }
}




