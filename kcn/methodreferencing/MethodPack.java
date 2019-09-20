package kcn.methodreferencing;

import java.util.ArrayList;
import java.util.List;
/* 2019/09/19 added simple logging feature when a methodreference has failed and is removed, and other
adjustments */

/**
 * A MethodPack contains a list of MeRefs;
 * <p>  - it is able to execute a collection of no-parameter, no/void return type methods.
 * <p>  - it is able to execute a collection of single <V> parameter and <V> retun type methods.
 * <p> - it is able to execute a collection of single <V> parameter and <O> retun type.
 * <p>  * <b>Implementing this, take care to consistently 'supply methods' that have the right return types,
 * because compiler cannot tell a method with a wrong signature from a good signature.</b></p>
 * <p>  * This can be a good thing, if used carefully - but catastrophic in all other cases.
 * <p>  *
 * <p> - you can do a variety of loosely coupled fun / dependency injection tasks
 * <p>    with this class, * <p><b>  - but it is not strong on compiler-time checking:</b>
 * <p>
 * - the generic sibling MethodPathGeneric is much more versatile and much more type-safe
 */
public class MethodPack
{

    private int removedMethodsCount;
    private ArrayList<String> removedMethodsNamesList;
    private List<MethodReference> methods;
    private boolean automaticNullChecks; /* value of boolean effects all run method*/

    public MethodPack()
    {
        methods = new ArrayList<>();
        removedMethodsNamesList = new ArrayList<>();
        removedMethodsCount = 0;
    }

    public List<MethodReference> getMethods()
    {
        return methods;
    }

    /**
     * Method executes a collection of no-parameter, no/void return type methods.
     * Actually, it is run() on each MethodReference in methods that is executed...
     **/
    public void run()
    {
        if(automaticNullChecks){ handleBadReferences(); }

        for(MethodReference method : methods) { method.run(); }
    }

    /**
     * Method executes a collection of single <V> parameter and <V> return type.
     */
    public <V> void run(V value)
    {
        if(automaticNullChecks){ handleBadReferences(); }

        for(MethodReference method : methods) { method.run_paramT_reObj(value); }
    }


    /**
     * Method executes all MethodReferences on it's list in supplied orders
     * - Method supplied must:
     * - take two parameters, can be of different types types;
     * - return an Object.
     */
    public <V, O> Object run(V value, O returnClassObject)
    {
        if(automaticNullChecks){ handleBadReferences(); }

        for(MethodReference method : methods) { return method.run_paramTU_reObj(value, returnClassObject); }

        return returnClassObject;
    }

    /**
     * Method adds a MethodReference object to internal list
     **/
    public boolean add(MethodReference method)
    {
        methods.add(method);
        return true;
    }

    /**
     * Method adds a MethodReference object to internal list
     **/
    public boolean remove(MethodReference method)
    {
        if(methods.contains(method))
        {
            methods.remove(method);
            return true;
        } else return false;
    }

    /**
     * Method enables or disables automatic null-checks.
     * <p> Notice that the MethodPack </p>
     *
     * <p> Method does not perform any checks itself; going
     * forward, all methods (MeRefs) will be checked. </p>
     * <p><b>NB: Not enabled by default</b>, which maybe it
     * should be - but I prefer responsibility to safety.
     * <p></p>
     * <p> Note that MethodReferences check+log themselves internally.
     * They return null if they are somehow broken.</p>
     * </p>
     */
    public void autoHandleNullReferences(boolean turnOnAutomaticHandlingOfNullObjects)
    {
        automaticNullChecks = turnOnAutomaticHandlingOfNullObjects;
    }

    /**
     * method removes 'dead' MethodReferences from list (I know it all fits one line)
     * <p> this is public to allow manual cleaning; </p>
     **/
    public void handleBadReferences()
    {
        for(MethodReference mr : methods)
        {
            if(mr.isReferenceBroke())
            {
                /* getting the name down before ejecting the bad apple */
                removedMethodsNamesList.add(mr.getMethodObject().getName());
                methods.remove(mr);
                removedMethodsCount++;
            }
        }
    }

    /**
     * Get length of methods list
     */
    public int length(){ return methods.size(); }

    public int getRemovedMethodsCount(){ return removedMethodsCount; }

    public ArrayList<String> getRemovedMethodsNamesList(){ return removedMethodsNamesList; }
}
