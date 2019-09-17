package kcn.datastructures;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Class instances hold a reference to *a method and an object* and is able to execute that method
 * on that object from anywhere
 * The Reference has a generic twin in MethodReferenceGeneric
 */
public class MethodReference
        implements IMethodReference
{

    Object objectToExecuteMethodOn;
    private Method methodToExecute;

    /*
     * CONSTRUCTORS
     */

    public MethodReference(Object executingThing, Method method)
    {
        methodToExecute = method;
        objectToExecuteMethodOn = executingThing;
    }

    public MethodReference(Object executingThing, String methodName) throws
                                                                     NoSuchMethodException
    {
        methodToExecute = executingThing.getClass ().getMethod ( methodName );
        objectToExecuteMethodOn = executingThing;
    }

    /*
      RUN METHODS
     */

    /**
     * Method executes the method referenced on object supplied.
     * - no parameters
     * - returns void
     **/
    public void run() throws
                      InvocationTargetException,
                      IllegalAccessException
    {
        methodToExecute.invoke ( objectToExecuteMethodOn );
    }

    /**
     * Method executes the method referenced, returns void
     *
     * @param inputParameter object type T whose value will be used as parameter when executing referenced method.
     **/
    public <T> void run(T inputParameter) throws
                                          InvocationTargetException,
                                          IllegalAccessException
    {
        methodToExecute.invoke ( objectToExecuteMethodOn, inputParameter );
    }

    /**
     * Method returns the result of the called method to caller,
     * - called/executed method is ONLY able to and MUST return an object of the same type
     * as it's parameter.
     *
     * @param inputParameter object type T whose value will be used as parameter when executing referenced method.
     **/
    public <T> T runWithReturn(T inputParameter) throws
                                                 InvocationTargetException,
                                                 IllegalAccessException

    {
        return (T) methodToExecute.invoke ( objectToExecuteMethodOn, inputParameter );
    }

    /**
     * Method runs methodToExecute with parameter of type T and returns an object of type U:
     * Method takes a parameter of type, returns a result of another type (TO CALLLER method).
     *
     * @param inputParameter    input parameter type T; this is the handled/active parameter
     * @param returnClassObject 'output' parameter type U: argued object used *only* to get return *type*.
     **/
    public <T, U> U runWithReturn(T inputParameter, U returnClassObject) throws
                                                                         InvocationTargetException,
                                                                         IllegalAccessException
    {
        return (U) methodToExecute.invoke ( objectToExecuteMethodOn, inputParameter );
    }

    @Override
    public boolean isObjectNull()
    {
        return objectToExecuteMethodOn == null;
    }

    /*
     * GETTERS, NO SETTERS
     */
    @Override
    public Method getMethodToExecute()
    {
        return methodToExecute;
    }

    @Override
    public Object getExecutingObject()
    {
        return objectToExecuteMethodOn;
    }

}
