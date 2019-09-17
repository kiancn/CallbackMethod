package kcn.datastructures;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* A kcn.datastructures.MethodPack contains a list for MethodReferences;
 *  - it is able to execute a collection of no-parameter, no/void return type methods.
 *  - it is able to execute a collection of single <T> parameter and <T> retun type.
 *  - it is able to execute a collection of single <T> parameter and <U> retun type.
 *
 *  - you can do a variety of loosely coupled fun /
 *                       dependency injection tasks
 *    with this class,
 *  - but it is not strong on compiler-time checking:
 *
 *  - the generic sibling MethodPathGeneric is much more versatile
 *
 *
 * */
public class MethodPack

{
    private List<MethodReference> methods;
    private boolean automaticNullChecks; /* value of boolean effects all run method*/

    public MethodPack()
    {
        this.methods = new ArrayList<MethodReference> ();
    }

    public List<MethodReference> getMethods()
    {
        return methods;
    }

    /**
     * Method executes a collection of no-parameter, no/void return type methods.
     * Actually, it is run() on each MethodReference in methods that is executed...
     **/
    public void run() throws
                      InvocationTargetException,
                      IllegalAccessException
    {
        if (automaticNullChecks) { handleNullReferences (); }

        for(MethodReference method : methods){ method.run (); }
    }

    /**
     * Method executes a collection of single <V> parameter and <V> retun type.
     */
    public <V> void run(V value) throws
                                 InvocationTargetException,
                                 IllegalAccessException
    {
        if (automaticNullChecks) { handleNullReferences (); }

        for(MethodReference method : methods)
        {
            method.run ( value );
        }
    }


    /**
     * Method executes all method on it's list in supplied order:
     * - takes two parameters
     * - a parameter of type <V> that is supplied as a parameter to all methods-list classes.
     * ** The value of parameter is used.
     * - a parameter of type <O> that is used solely for defining the *return type*
     * ** The value of parameter is discarded.
     * - NB. if methods ends up not returning any method, the supplied object will be returned! (returnClassObject)
     **/
    public <V, O> O run(V value, O returnClassObject) throws
                                                      InvocationTargetException,
                                                      IllegalAccessException
    {
        if (automaticNullChecks) { handleNullReferences (); }

        for(MethodReference method : methods)
        {
            return method.runWithReturn ( value, returnClassObject );
        }

        return returnClassObject;
    }
    /** Method adds a MethodReference object to internal list **/
    public boolean add(MethodReference method)
    {
        methods.add ( method );
        return true;
    }
    /** Method adds a MethodReference object to internal list **/
    public boolean remove(MethodReference method)
    {
        if (methods.contains ( method ))
        {
            methods.remove ( method );
            return true;
        } else return false;
    }
    /**Method */
    public void autoHandleNullReferences(boolean turnOnAutomaticHandlingOfNullObjects)
    {
        automaticNullChecks = turnOnAutomaticHandlingOfNullObjects;
    }

    /**
     * method removes 'dead' MethodReferences from list (I know this fits one line)
     **/
    public void handleNullReferences()
    {
        for(MethodReference mr : methods)
        {
            if (mr.getExecutingObject () == null)
            {
                methods.remove ( mr );
            }
        }
    }

    public int length()
    {
        return methods.size ();
    }

}
