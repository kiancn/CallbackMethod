package kcn.datastructures;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class MePack<V, O>

{
    private List<MeReference<V, O>> methods;
    private boolean automaticNullChecks; /* value of boolean effects all run method*/

    /* Unprimed Constructor; V and O are given, but no methods*/
    public MePack()
    {
        this.methods = new ArrayList<MeReference<V, O>> ();
    }

    /* Unprimed Constructor; V and O are given, and so is an no methods.
     *
     * *-* using List because any kind of List should work */
    public MePack(List<MeReference<V, O>> methodReferences)
    {
        this.methods = new ArrayList<MeReference<V, O>> ();
        methods.addAll ( methodReferences );
    }

    public List<MeReference<V, O>> getMethods()
    {
        return methods;
    }

    /**
     * Method executes a collection of no-parameter, O-type return type methods.
     * Actually, it is run() on each MethodReferenceGeneric-object in methods that
     * is
     * executed...
     **/
    public void run() throws
                      InvocationTargetException,
                      IllegalAccessException
    {
        if (automaticNullChecks) { handleNullReferences (); }

        for(MeReference<V, O> method : methods){
            method.run ();
        }
    }
//
//        /**
//         * Method executes a collection of single <T> parameter and <T> retun type.
//         */
//        public <T> void run(T value) throws InvocationTargetException, IllegalAccessException
//        {
//            if (automaticNullChecks) { handleNullReferences (); }
//
//            for(MethodReferenceGeneric<V,O> method : methods)
//            {
//                method.run ( value );
//            }
//        }


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

            for(MeReference<V,O> method : methods)
            {
                return method.run( value);
            }

            return null;
        }


    public boolean add(MeReference<V, O> method)
    {
        methods.add ( method );
        return true;
    }


    public boolean remove(MeReference<V, O> method)
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
        for(MeReference<V, O> mr : methods)
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


