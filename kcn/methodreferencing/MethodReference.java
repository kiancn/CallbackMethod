package kcn.methodreferencing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;


/**
 * Class instances hold a reference to *a method and an object* and is able to execute that method
 * on that object from anywhere
 * The Reference has a generic twin in MeReference, which is safer to use and has much greater
 * flexibility.
 * <p>
 * NB. Naming of run methods is mechanical and I don't really like it, but didn't come up with something
 * better yet.
 */
public class MethodReference
        implements IMethodReference
{

    /*exception-counters ... unending */
    private Object objectToExecuteMethodOn;
    private Method methodToExecute;

    private boolean referenceIsBroke;
    private int[] exceptionsCaught;

    private int IllegalAccessExceptionThrown;
    private int InvocationTargetExceptionThrown;
    private int NullPointerExceptionCaughtPrematurely;
    private int NoSuchMethodExceptionThrown;
    private int ExecutingObjectMissingCaught;
    private int MethodObjectMissingCaught;

    public MethodReference(Object executingThing, Method method)
    {
        methodToExecute = method;
        objectToExecuteMethodOn = executingThing;
        methodToExecute.setAccessible(true); /* making method available even if it is private : if you
        reference it, I trust you want to do that, private or not. */
        initializeExceptionsArray();
    }


    public MethodReference(Object executingThing, String methodName)
    {
        try
        {
            methodToExecute = executingThing.getClass().getMethod(methodName);
            methodToExecute.setAccessible(true);
        } catch(NoSuchMethodException e)
        {
            NoSuchMethodExceptionThrown++;
        } catch(NullPointerException e)
        {
            NullPointerExceptionCaughtPrematurely++;
        }
        objectToExecuteMethodOn = executingThing;

        initializeExceptionsArray();
    }



    /*
      RUN METHODS
     */

    /**
     * Method executes the method referenced on object supplied.
     * - no parameters
     * - returns void
     **/
    public void run()
    {
        if(!isNullFound())
        {
            try
            {
                methodToExecute.invoke(objectToExecuteMethodOn);
            } catch(IllegalAccessException e)
            {
                IllegalAccessExceptionThrown++; /*just updating the counter and moving on */
            } catch(InvocationTargetException e)
            {
                InvocationTargetExceptionThrown++;
            }
        }
    }


    /**
     * Method executes the method referenced, returns void
     *
     * @param inputParameter object type T whose value will be used as parameter when executing referenced method.
     **/
    public <T> void run_paramT(T inputParameter)
    {
        if(!isNullFound())
        {
            try
            {
                methodToExecute.invoke(objectToExecuteMethodOn, inputParameter);
            } catch(IllegalAccessException e)
            {
                IllegalAccessExceptionThrown++;
            } catch(InvocationTargetException e)
            {
                InvocationTargetExceptionThrown++;
            }
        }
    }


    /**
     * Method returns the result of the referenced method,
     * - called/executed method is ONLY able to and MUST
     * return an object of the same type as it's parameter type T.
     *
     * @param inputParameter object type T whose value will be used as parameter when executing referenced method.
     * @return an object of type T, same as inputParameter-type.
     **/
    public <T> T run_paramT_reT(T inputParameter)
    {
        if(!isNullFound())
        {
            try
            {
                return (T)methodToExecute.invoke(objectToExecuteMethodOn, inputParameter);
            } catch(IllegalAccessException e)
            {
                IllegalAccessExceptionThrown++;
            } catch(InvocationTargetException e)
            {
                InvocationTargetExceptionThrown++;
            }
        }
        return null;
    }

    /**
     * Method executes the method referenced, returns a type Object object;
     * so if you choose this method, you must cast the return.
     *
     * @param inputParameter object type T whose value will be used as parameter when executing referenced method.
     * @return object type Object
     **/
    public <T> Object run_paramT_reObj(T inputParameter)
    {
        if(!isNullFound())
        {
            try
            {
                methodToExecute.invoke(objectToExecuteMethodOn, inputParameter);
            } catch(IllegalAccessException e)
            {
                IllegalAccessExceptionThrown++;
            } catch(InvocationTargetException e)
            {
                InvocationTargetExceptionThrown++;
            }
        }

        return null;
    }

    /**
     * Method 'runs/invokes' methodToExecute with parameter of type T and returns an object of type U.
     * <p>
     * This is possible because the return type is not a part of the signature necessary to identify the
     * method at construction, and so different return types can slip in: Errors are avoided because
     * returns are simply not checked at construction time.
     * <p>
     * This is not safe, or maybe even smart - but it might be used in clever ways!
     * <p>
     * NB: this seems like a bad idea to me, but also something some people might do. But I recommend
     * the generic version for most tasks.
     *
     * @param inputT input parameter type T; this is the handled/active parameter
     * @param inputU 'output' parameter type U: U is the type
     *               argued object used *only* to get return *type*.
     * @return object type Object
     **/
    public <T, U> Object run_paramTU_reObj(T inputT, U inputU)
    {
        if(!isNullFound())
        {
            try
            {
                return methodToExecute.invoke(objectToExecuteMethodOn, inputT, inputU);
            } catch(IllegalAccessException e)
            {
                IllegalAccessExceptionThrown++;
            } catch(InvocationTargetException e)
            {
                InvocationTargetExceptionThrown++;
            }
        }
        return null;
    }


    /*
     * GETTERS, NO SETTERS
     */

    /**
     * Method returns a java.lang.reflect.Method type object. <p></p><b>
     * This is the object that invoke is called on
     * (with objectToExecute as first parameter). </b>
     * <p> </p>
     */
    @Override
    public Method getMethodToExecute()
    {
        if(!isMethodObjectNull())
        {
            return methodToExecute;
        } else
        {
            NullPointerExceptionCaughtPrematurely++;
            return null;
        }
    }

    /**
     * Method return
     */
    @Override
    public Object getExecutingObject()
    {
        if(!isExecutingObjectNull())
        {
            return objectToExecuteMethodOn;
        } else
        {
            NullPointerExceptionCaughtPrematurely++;
            return null;
        }
    }

    /* Exception handling: exceptions and nulls are tracked as int,
     * and it one if reported, this object will stop being able
     *  to execute it's run methods */

    /**
     * Method returns true if MethodReference is somehow broke:
     * either null is detected or exceptions have been caught.
     * <p>
     */

    public boolean isReferenceBroke()
    {
        /* if any null is found, simply return true */
        referenceIsBroke = isNullFound();
        /* else go through exceptions already caught */
        referenceIsBroke = didExceptionsHappen();

        return referenceIsBroke;
    }
    /* NB: So, someone might wonder if an option is not
     * missing throughout the code; namely the option to repair a MethodReference.
     * But no, I think that is a bad idea: finding out what actually went wrong consistently
     * and having ways to fix that just has too many arbitrary variables.
     */

    /**
     * Method returns true if any object checked is null
     */
    @Override
    public boolean isNullFound()
    {
        if(isExecutingObjectNull() || isMethodObjectNull()){ return true; } else{ return false; }
    }

    private boolean isExecutingObjectNull()
    {
        if(Objects.isNull(objectToExecuteMethodOn))
        {
            ExecutingObjectMissingCaught++;
            referenceIsBroke = true; /** this MethodReference is always broken if it's object is missing */
            return true;
        } else{return false;}
    }

    private boolean isMethodObjectNull()
    {
        if(Objects.isNull(methodToExecute))
        {
            MethodObjectMissingCaught++;
            referenceIsBroke = true; /* if it's method is missing MethodReference is always broken */
            return true;
        } else{return false;}
    }

    /**
     * Method will return true if any of the counter ints in exceptionsCaught
     * are above 0.
     */
    public boolean didExceptionsHappen()
    {
        for(int value : exceptionsCaught)
        {
            if(value > 0)
            {
                return true;
            }
        }
        /* if code reaches here, no exceptions were found to be caught */
        return false;
    }

    /**
     * Method return the array of counted errors;
     * intent is for user to interpret the array to detect what type of error occurred
     * by interpreting the value at each index and act accordingly.
     * <p>If you  j u s t  want to know if something went wrong, pull isReferenceBroke()
     */
    public int[] getExceptionsCaught()
    {
        initializeExceptionsArray(); /* re-initializing exceptionsCaught */
        return exceptionsCaught;
    }

    private void initializeExceptionsArray()
    {
        exceptionsCaught = new int[]{IllegalAccessExceptionThrown,
                InvocationTargetExceptionThrown,
                NullPointerExceptionCaughtPrematurely, /* I believe the */
                NoSuchMethodExceptionThrown,
                ExecutingObjectMissingCaught,
                MethodObjectMissingCaught
        };
    }
}