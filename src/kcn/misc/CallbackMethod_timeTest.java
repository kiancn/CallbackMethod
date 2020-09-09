package kcn.misc;

import kcn.callbackmethods.CallMe;
import kcn.callbackmethods.CallbackMethod;

import java.util.Arrays;

public class CallbackMethod_timeTest
{
    CallbackMethod test_CalcSmth;

    CallMe<Integer, Integer> testG_CalcSmth;


    public CallbackMethod_timeTest()
    {
        test_CalcSmth = new CallbackMethod((Object)this, "calulateSomething", int.class);
        System.out.println("CallbackMethod tested " +
                           (test_CalcSmth.isReferenceBroke() ? "positive" : "negative") +
                           " for internal errors/exceptions.");

        testG_CalcSmth = new CallMe<Integer, Integer>((Object)this, "calulateSomething", int.class);
        System.out.println("CallMe tested " +
                           (testG_CalcSmth.isReferenceBroke() ? "positive" : "negative") +
                           " for internal errors/exceptions.");
        testG_CalcSmth.setPersistentNullChecks(false);
    }

    public int calulateSomething(int input)
    {
        return (int)Math.sqrt(input) / 2 + 2 * input;
    }

    public void multipleTests(long iterationsOffset, int numberOfTests,
                              boolean verboseReport)
    {
        for(int count = 0; count < numberOfTests; count++)
        {
            System.out.print(" " + count + "\n");
            test_briefReport((int)iterationsOffset);
        }
    }

    public void multipleTests_In_10_Fold_Increments(long iterationsOffset, int numberOfIncrements,
                                                    boolean verboseReport)
    {
        for(int count = 0; count < numberOfIncrements; count++)
        { test_briefReport((int)(iterationsOffset * Math.pow(10, count))); }
    }

    public void multipleTests_In_Doubling_Increments(long iterationsOffset, int numberOfIncrements,
                                                     boolean verboseReport)
    {
        for(int count = 0; count < numberOfIncrements; count++)
        { test_briefReport((int)(iterationsOffset * Math.pow(2, count))); }
    }


//    public boolean test(int iterations)
//    {
//        long result1 = 0;
//        long result2 = 0;
//
//
//        long ordinaryMethodTestDuration = 0;
//        long callbackMethodTestDuration = 0;
//        long testStart1 = 0;
//        long testStart2 = 0;
//
//        System.out.println("\n\t>> Running " + iterations + " tests <<\n");
//
//        /*
//         Instance method execution test, running as many times as the iteration value defines
//          */
//
//        System.out.println("\tOrdinary method test commencing.");
//
//        testStart1 = System.nanoTime();
//        for(int i = 0; i < iterations; i++) { result1 += calulateSomething(i); } // THE TEST
//
//        System.out.println("Test started at: " + String.format("%26d", testStart1));
//        ordinaryMethodTestDuration = System.nanoTime() - testStart1;
//        System.out.println("Ordinary Test nanos:\t" + String.format("%20d", ordinaryMethodTestDuration));
//
//        /*
//         Callback Method execution test, running as many times as the iteration value defines
//          */
////        if(!test_SquareRoot.isReferenceBroke())
////        {
//
//            System.out.println("\tCallback method test commencing.");
//
//            testStart2 = System.nanoTime();
//            for(long i = 0; i < iterations; i++) { result2 += test_CalcSmth.run_paramT_reT(i); } // THE TEST
//            callbackMethodTestDuration = System.nanoTime() - testStart2;
//
//            System.out.println("Test starting at: " + String.format("%26d", testStart2));
//
//            System.out.println("Callback test nanos:\t" + String.format("%20d", callbackMethodTestDuration));
//
////        } else
////        {
////            System.out.println("Your callback done broke.");
////            return false;
////        }
//
//        /* Report stuff */
//        System.out.println("\tRESULTS");
//        System.out.println("Ordinary method is "
//                           + ((ordinaryMethodTestDuration < callbackMethodTestDuration) ? "FASTER" : "SLOWER")
//                           + " than the callback." +
//                           "\nOrdinary Method test:\t " + String.format("%19d", ordinaryMethodTestDuration) +
//                           "\nCallback Method test:\t " + String.format("%19d", callbackMethodTestDuration) +
//
//                           "\nCallback %-time difference:\t " + percentDifference(ordinaryMethodTestDuration,
//                                                                                  callbackMethodTestDuration) + "%");
//        /* Checking if methods worked the same */
//        System.out.println("\nOrdinary Test checksum:\t" + result1);
//        System.out.println("Callback Test checksum:\t" + result2 + "\n");
//
//        return true;
//    }

    public boolean test_briefReport(int iterations)
    {

        int result1 = 0;
        int result2 = 0;
        int result3 = 0;

        long baselineMethodTestDuration = 0;
        long callbackMethodTestDuration = 0;
        long callMeTestDuration = 0;

        long baselineTestStart = 0;
        long callbackTestStart = 0;
        long callMeTestStart = 0;


        /* Callback Method execution test, running as many times as the iteration value defines*/
        if(!test_CalcSmth.isReferenceBroke())
        {
            callMeTestStart = System.nanoTime();
            for(int i = 0; i < iterations; i++) { result3 += testG_CalcSmth.run(i); } // THE
            // TEST
            callMeTestDuration = System.nanoTime() - callMeTestStart;
        } else
        {
            System.out.println("Your callback done broke.");
            System.out.println(Arrays.toString(testG_CalcSmth.getExceptionsCaught()));
            return false;
        }
        /*+++++++++++++++++++++++++++++++++*/

        /*  Instance method execution test, running as many times as the iteration value defines */
        baselineTestStart = System.nanoTime();
        for(int i = 0; i < iterations; i++) { result1 += calulateSomething(i); } // THE TEST
        baselineMethodTestDuration = System.nanoTime() - baselineTestStart;
        /*+++++++++++++++++++++++++++++++++*/

        /* Callback Method execution test, running as many times as the iteration value defines*/
        if(!test_CalcSmth.isReferenceBroke())
        {
            callbackTestStart = System.nanoTime();
            for(int i = 0; i < iterations; i++) { result2 += (int)test_CalcSmth.run(i); } // THE TEST
            callbackMethodTestDuration = System.nanoTime() - callbackTestStart;
        } else
        {
            System.out.println("Your callback done broke.");
            System.out.println(Arrays.toString(test_CalcSmth.getExceptionsCaught()));
            return false;
        }
        /*+++++++++++++++++++++++++++++++++*/

        /* Report stuff */
        System.out.print("[#-tests " + String.format("%10d", iterations) + "]");
        System.out.print(" < Base/Callback/CallMe-time : "
                         + String.format("[%11d] /", baselineMethodTestDuration)
                         + String.format(" [%11d] /", callbackMethodTestDuration)
                         + String.format(" [%11d] >", callMeTestDuration) +
                         " <Callback %-time diff:\t " +
                         percentDifference(baselineMethodTestDuration,
                                           callbackMethodTestDuration)
                         + "% >" + " <CallMe %-time diff: " +
                         percentDifference(baselineMethodTestDuration,
                                           callMeTestDuration)
                         + "% >");
        /* Checking if methods worked the same */
        System.out.print(" <" + ((result1 == result2 && result2 == result3) ? "Check OK" : "Check BAD") +
                         ">");
        return true;
    }

    private String percentDifference(long numberA, long numberB)
    {
        return String.format("%+9.1f", (((double)numberB / numberA) - 1) * 100);
    }

    public void testMenu()
    {
        boolean continueChecking = true;
        int numberOfIteration;
        int startingNumber;

        System.out.println("Welcome to the speed test of Callback Methods versus instance methods.\n" +
                           "\tNow, make your choices and prosper!\n");
        do
        {
            startingNumber = GET.getInteger("What number of tests would you like to run to begin with? ");
            numberOfIteration = GET.getInteger("How many test rounds would you like to run? ");

            if(GET.getVerification("\nAre you satisfied with your choices of" +
                                   "\n\t" + startingNumber + " as a starting number of tests." +
                                   "\nAnd\t" + numberOfIteration + " test rounds?" +
                                   "\nEnter: " + "(y)es or (n)o \t y / n ",
                                   "y", "n"))
            {
                switch(GET.getInteger("What kind of test would you like to run?" +
                                      "\nEnter\t0 \tto run a flat test (many rounds, same load each round)" +
                                      "\n\t\t2\tto run tests with load increasing by powers of 2 " +
                                      "\n\t\t10\tto run tests with load increasing by powers of 10. "))
                {
                    case 0:
                        multipleTests(startingNumber, numberOfIteration, false);
                        break;
                    case 2:
                        multipleTests_In_Doubling_Increments(startingNumber, numberOfIteration, false);
                        break;
                    case 10:
                        multipleTests_In_10_Fold_Increments(startingNumber, numberOfIteration, false);
                        break;
                    default:
                        System.out.println("That was not an option. Asshat.");
                }
            }

            continueChecking = GET.getVerification("Would you like to run another check?\t y / n ",
                                                   "",
                                                   "");
        } while(continueChecking);
    }

}
