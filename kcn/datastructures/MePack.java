package kcn.datastructures;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/** Class instances are able to hold and pass around method-references */
public class MePack<V, O>
{
    private List<MeRef<V, O>> methods;
    private boolean automaticNullChecks; /* value of boolean effects all run method*/

    /* Unprimed Constructor; V and O are given, but no methods*/
    public MePack()
    {
        this.methods = new ArrayList<MeRef<V, O>> ();
    }

    /* Unprimed Constructor; V and O are given, and so is an no methods.
     *
     * *-* using List because any kind of List should work */
    public MePack(List<MeRef<V, O>> methodReferences)
    {
        this.methods = new ArrayList<MeRef<V, O>> ();
        methods.addAll ( methodReferences );
    }

    public List<MeRef<V, O>> getMeRefs()
    {
        return methods;
    }

    /**
     * Method executes run() on each MeRef in methods List
     * <p></p>Method nescessitates that zero parameter, void return type methods are referenced supplied.
     *  <p></p>* using a MeRef with the signature:   MeRef<>   is ideal for this purpose..
     **/
    public void run()
    {
        if (automaticNullChecks) { handleNullReferences (); }

        for(MeRef<V, O> method : methods){
            method.run ();
        }
    }

        /**
         * Method executes a collection of single <T> parameter and <T> retun type.
         */
        public <T> void run_void(T value)
        {
            if (automaticNullChecks) { handleNullReferences (); }

            for(MeRef<V,O> method : methods)
            {
                method.run((V)value); /* it is possible that null will be passed here */
            }
        }


        /**
         * Method executes all method on it's list in supplied order:
         * - takes two parameters
         * - a parameter of type <T> that is supplied as a parameter to all methods-list classes.
         * ** The value of parameter is used.
         * - a parameter of type <U> that is used solely for defining the *return type*
         * ** The value of parameter is discarded.
         * - NB. if methods ends up not returning any method, the supplied object will be returned! (returnClassObject)
         **/
        public O run(V value) throws InvocationTargetException, IllegalAccessException
        {
            if (automaticNullChecks) { handleNullReferences (); }

            for(MeRef<V,O> method : methods)
            {
                return method.run( value);
            }

            return null;
        }


    public boolean add(MeRef<V, O> method)
    {
        methods.add ( method );
        return true;
    }


    public boolean remove(MeRef<V, O> method)
    {
        if (methods.contains ( method ))
        {
            methods.remove ( method );
            return true;
        } else return false;
    }



    public void enableAutoHandleNullReferences(boolean turnOnAutomaticHandlingOfNullObjects)
    {
        automaticNullChecks = turnOnAutomaticHandlingOfNullObjects;
    }

    /**
     * method removes 'dead' MethodReferences from list (I know this fits one line)
     **/

    public void handleNullReferences()
    {
        for(MeRef<V, O> mr : methods)
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


