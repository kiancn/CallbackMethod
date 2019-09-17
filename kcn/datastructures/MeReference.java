package kcn.datastructures;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* Should this class be called MethodReferenceGeneric or GMR or GeMeRef or GMeRef or  */

public class MeReference<V, O>
        implements IMethodReference
{
    /* ~<>~ Fields ~<>~ */

    private boolean autoHandleNullObject;
    private boolean shortCircuitRun;
    private Object objectReference;
    private Method method;
    private Class[] argClasses;

    /* ~<>~ Constructors ~<>~ */


    public MeReference(Object executingObject, Method methodThatWillBeExecuted)
    {
        objectReference = executingObject;
        method = methodThatWillBeExecuted;
        autoHandleNullObject = true; /* safety by default */
    }

    public MeReference(Object executingObject, String methodName) throws
                                                                             NoSuchMethodException
    {
        objectReference = executingObject;
        method = executingObject.getClass().getMethod(methodName);
        autoHandleNullObject = true; /* safety by default */
    }

    /* probably the preferable constructor */
    public MeReference(Object executingObject, String methodName, Class[] varargClasses) throws
                                                                                                    NoSuchMethodException
    {
        argClasses = varargClasses;
        objectReference = executingObject;
        method = executingObject.getClass().getMethod(methodName, varargClasses);
        autoHandleNullObject = true; /* safety by default */

    }

    public MeReference(Object executingObject, String methodName, boolean handleNullObject) throws
                                                                                                       NoSuchMethodException
    {
        objectReference = executingObject;
        method = executingObject.getClass().getMethod(methodName);
        autoHandleNullObject = handleNullObject;
    }

    /* ~<>~ Methods ~<>~ */

    public MeReference(Object executingObject, Method methodThatWillBeExecuted, boolean handleNullObject)
    {
        objectReference = executingObject;
        method = methodThatWillBeExecuted;
        autoHandleNullObject = handleNullObject;
    }

    /**
     * Method executes supplied method with no parameters and a type O return
     */
    @SuppressWarnings("unchecked") /* compiler is unsure of return type, but we
     know from construction time */
    public O run() throws
                   InvocationTargetException,
                   IllegalAccessException
    {

        if(autoHandleNullObject){ isObjectNull(); }
        if(!shortCircuitRun)
        {
            return (O)method.invoke(objectReference);
        }
        /* null returns are passed */
        return null;
    }

    /**
     * Method executes supplied method with a single type V parameter,
     * and returns a type O object.
     **/
    @SuppressWarnings("unchecked") /* same (see O run() )*/
    public O run(V vValue) throws
                           InvocationTargetException,
                           IllegalAccessException
    {

        if(autoHandleNullObject){ isObjectNull(); }
        if(!shortCircuitRun)
        {
            return (O)method.invoke(objectReference, vValue);
        }
        /* null returns are passed */
        return null;
    }

    /**
     * Method executes supplied method with two type V parameters,
     * and returns a type O object.
     **/
    @SuppressWarnings("unchecked") /* same (see run() )*/
    public O run_VV(V vValueA, V vValueB) throws
                                       InvocationTargetException,
                                       IllegalAccessException
    {

        if(autoHandleNullObject){ isObjectNull(); }
        if(!shortCircuitRun)
        {
            return (O)method.invoke(objectReference, vValueA, vValueB);
        }

        /* null returns are passed */
        return null;
    }

    /**
     * Method takes an array of objects of type V and returns an object of type O.
     * <p></p><b>LIMITS:</b><p>
     * <i> * cannot be used to return an array of fundamental types! </i><p>
     *
     * @param valuesArray an array of type V objects.
     * @return object of type O
     **/
    @SuppressWarnings("unchecked")
    public O run(V[] valuesArray) throws
                                  InvocationTargetException,
                                  IllegalAccessException
    {

        if(autoHandleNullObject){ isObjectNull(); }

        if(!shortCircuitRun)
        {
            return (O)method.invoke(objectReference, new Object[]{valuesArray});
        }
        /* null returns are passed */
        return null;
    }

    /**
     * Method takes a Object-object and a V-object as arguments, and returns an
     * O-type object. (M..R..G.. <V,O>)
     * <p></p><p> - method takes first an object of type Object
     * <p> - then method takes an object type V
     * <p> - method returns a type O object
     * <p></p>
     * <b> - NB. the contained method must take care to cast the Object to
     * something useful.* </b>
     * <p>  - Method is named different because the compiler cannot distinguish between
     * run(V,V) and run(Object,V), - and so run_ObjV was born.
     * Sorry for the confusion. </p>
     *
     * @param inputObject Object type object; any object (remember to type cast in
     *                    executing method)
     * @param value       an object of type V
     * @return an O-type object
     */
    @SuppressWarnings("unchecked") /* compiler is unsure of return type, but we
     know from construction time */
    public O run_ObjV(Object inputObject, V value) throws
                                                   InvocationTargetException,
                                                   IllegalAccessException
    {
        /* if autoHandle.. is true, check if object is null*/
        if(autoHandleNullObject){isObjectNull();}
        /* if object is not null, shortCir.. will be false*/
        if(!shortCircuitRun)
        {
            /* A. type cast return type to O; for the compiler
             *  B. call invoke on referenced method with inputObject and value as
             * parameters
             * C. return type O-object from this method */
            return (O)method.invoke(objectReference, inputObject, value);
        }

        return null;
    }

    /**
     * Method takes an Object object and an array of objects of type V and returns
     * an object of type O.
     * <p></p>
     * <b>LIMITS:</b><p>
     * <i> * cannot be used to return an array of fundamental types! </i><p>
     *
     * @param inputObject an object of type Object.
     * @param valuesArray an array of type V objects.
     * @return object of type O
     **/
    @SuppressWarnings("unchecked")
    public O run(Object inputObject, V[] valuesArray) throws
                                                      InvocationTargetException,
                                                      IllegalAccessException
    {

        if(autoHandleNullObject){ isObjectNull(); }

        if(!shortCircuitRun)
        {
            return (O)method.invoke(objectReference,
                                    inputObject,
                                    new Object[]{valuesArray});
        }
        /* null returns are passed */
        return null;
    }

    @SuppressWarnings("unchecked")
    public Object run(O inputTypeO, V inputTypeV) throws
                                                  InvocationTargetException,
                                                  IllegalAccessException
    {   /* if autohandling null object, do that*/
        if(autoHandleNullObject){ isObjectNull(); }

        if(!shortCircuitRun)
        {
            return getMethodToExecute().invoke(objectReference, inputTypeO, inputTypeV);
        }
        return null;
    }

    /**
     * Method returns true if executing object is null,
     * & switches shortCircuitRun to avoid fatal errors if object IS null.
     **/
    public boolean isObjectNull()
    {
        if(objectReference == null)
        {
            shortCircuitRun = true;
            return true;
        } else
        {
            shortCircuitRun = false;
            return false;
        }
    }

    @Override
    public Method getMethodToExecute(){ return method; }

    @Override
    public Object getExecutingObject()
    {
        if(!isObjectNull())
        {
            return objectReference;
        } else
        {
            return null;
        }
    }

    public Object getExecutingObjectUnsafe(){ return objectReference; }

    public void setParameterClasses(Class[] classes){ argClasses = classes.clone(); }

    public void destoyMethodReference(){

    }
}
/*
 * This saved my life as to how to supply the array of V to invoke in run(V[]) :
 * https://yourmitra.wordpress.com/2008/09/26/using-java-reflection-to-invoke-a-method-with-array-parameters/
 *
 * On the perils of Generics and suppressing warnings
 * http://www.angelikalanger.com/GenericsFAQ/FAQSections/ProgrammingIdioms.html#FAQ300
 * https://www.codejava.net/java-core/the-java-language/suppresswarnings-annotation-examples
 * */