package kcn.methodreferencing;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
/* next up, add simple logging feature when a methodreference has failed */

/**
 * Class instances are able to hold and pass around method-references
 */
public class MePack<V, O>
{
    private List<MeRef<V, O>> methods;
    private boolean automaticNullChecks; /* value of boolean effects all run method*/

    /* Unprimed Constructor; V and O are given, but no methods*/
    public MePack()
    {
        this.methods = new ArrayList<MeRef<V, O>>();
    }

    /* Unprimed Constructor; V and O are given, and so is an no methods.
     *
     * *-* using List because any kind of List should work */
    public MePack(List<MeRef<V, O>> methodReferences)
    {
        this.methods = new ArrayList<MeRef<V, O>>();
        methods.addAll(methodReferences);
    }

    public List<MeRef<V, O>> getMeRefs()
    {
        return methods;
    }


    /**
     * Method executes run() on each MeRef in methods List
     * <p></p>Method nescessitates that zero parameter, void return type methods are referenced supplied.
     * <p></p>* using a MeRef with the signature:   MeRef<>   is ideal for this purpose..
     **/
    public void run()
    {
        if(automaticNullChecks){ handleNullReferences(); }

        for(MeRef<V, O> method : methods)
        {
            method.run();
        }
    }
    /* These methods were the result of not thinking ... apologize if agony is caused upon reading */
    /* They are here for reflection, will go soon */
    /**
     * Method executes all method on it's list in supplied order:
     * - takes a single type V parameter
     * - returns void
     **/
    public void run(V value)
    {
        if(automaticNullChecks){ handleNullReferences(); }

        for(MeRef<V, O> method : methods)
        {
            method.run(value);
        }
    }

    /**
     * Add a method with appropriate signature to internal method list
     */
    public boolean add(MeRef<V, O> method)
    {
        methods.add(method);
        return true;
    }

    /**
     * Remove a method from internal method list
     */
    public boolean remove(MeRef<V, O> method)
    {
        if(methods.contains(method))
        {
            methods.remove(method);
            return true;
        } else return false;
    }

    /**
     * Method enables or disables checking of methods before execution.
     *
     * @param turnItOn true enables auto-checking.
     */
    public void enableAutoHandleNullReferences(boolean turnItOn)
    {
        automaticNullChecks = turnItOn;
    }

    /**
     * method removes 'dead' MethodReferences from list (I know this fits one line)
     **/

    public void handleNullReferences()
    {
        for(MeRef<V, O> mr : methods)
        {
            if(mr.getExecutingObject() == null)
            {
                removedMethods.add(mr.getMethodObject().getName());
                methods.remove(mr);
            }
        }
    }

    private ArrayList<String> removedMethods;

    public int length()
    {
        return methods.size();
    }

}


