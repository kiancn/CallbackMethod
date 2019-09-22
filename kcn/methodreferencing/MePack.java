package kcn.methodreferencing;

import java.util.ArrayList;
import java.util.List;

/**
 * Class instances are able to hold and pass around method-references.
 *
 * <p> There are supplied only two run-methods in this class, both return void.
 * <p></p><b> To use/run/execute the individual MeRefs and use their returns in meaningful ways,
 * call getMeRefs() and access each by index, and use the specifically needed run..(..) variant . </b>
 *
 * <p><p></p> This is a design decision, and I made it like this because
 * it makes it very transparent what you/user ends up doing with the return value.
 * </p></p>
 */
public class MePack<V, O>
{
    private List<MeRef<V, O>> methods; /* contained MeRefs*/
    private boolean automaticNullChecks; /* value of boolean effects all run method */
    private ArrayList<String> removedMethods;

    /**
     * Default Constructor; V and O are given, but no methods.
     * <p> NB. Not supplying anything inside the diamonds, ie. defaulting V,O
     * gives V, O the values of Object, Object </p>
     */
    public MePack()
    {
        this.methods = new ArrayList<MeRef<V, O>>();
        removedMethods = new ArrayList<String>();
    }

    /**
     * Constructor; V and O are given, and list of MeRefs.
     * <p> Take note that the supplied list is not itself 'saved', but used
     * to copy the reference to each member MeRef; so the supplied list is
     * not further tied to the function of the MePack.
     * <p>
     */
    public MePack(List<MeRef<V, O>> methodReferences)
    {
        this.methods = new ArrayList<MeRef<V, O>>();
        methods.addAll(methodReferences);
        removedMethods = new ArrayList<String>();

    }

    /** Method returns the internal list of MeRefs for your running pleasure. */
    public List<MeRef<V, O>> getMeRefs()
    {
        return methods;
    }

    /**
     * Method executes run() on each MeRef in methods List
     * <p></p>Method necessitates that zero parameter, void return type methods are referenced supplied.
     * <p></p>* using a MeRef with the signature:   MeRef<>   is ideal for this purpose..
     **/
    public void run()
    {
        if(automaticNullChecks){ handleBadReferences(); }

        for(MeRef<V, O> method : methods)
        {
            method.run();
        }
    }

    /**
     * Method executes all method on it's list in supplied order:
     * - takes a single type V parameter
     * - returns void
     **/
    public void run(V value)
    {
        if(automaticNullChecks){ handleBadReferences(); }

        for(MeRef<V, O> method : methods)
        {
            method.run(value);
        }
    }

    /**
     * Add a method with appropriate signature to internal method list
     *
     * <p></p><p> And several references to the same MeRef is not a problem;
     * that really depends on your task as such.</p>
     */
    public void add(MeRef<V, O> method)
    {
        methods.add(method);

    }

    /** Method adds supplied MeRef only if it is not already on methods-list */
    public boolean addNoDuplicates(MeRef<V,O> method){
        if(!methods.contains(method)){
            methods.add(method);
            return true;
        }
        else return false;
    }


    /**
     * Remove a method from internal methods-list
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
     * Method removes 'dead' MethodReferences from list (I know this fits one line)
     **/
    public void handleBadReferences()
    {
        for(MeRef<V, O> mr : methods)
        {
            if(mr.isReferenceBroke())
            {   /* Adding name of method to be removed, to a log/list removedMethods   */
                removedMethods.add(mr.getMethodObject().getName());
                methods.remove(mr);
            }
        }
    }

    /** Gets you the number of items on */
    public int size()
    {
        return methods.size();
    }
}


