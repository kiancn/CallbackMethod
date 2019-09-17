package kcn.datastructures;

import java.lang.reflect.Method;

public interface IMethodReference
{
    /* method will return true if object (the one tied to method) is null */
    boolean isObjectNull();
    /* method will return the Method object that is referenced */
    Method getMethodToExecute();
    /* method will return the Object object that is being referenced */
    Object getExecutingObject();
}
