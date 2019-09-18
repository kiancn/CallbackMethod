package kcn.methodreferencing;

import java.lang.reflect.Method;
/* though the generic and the non-generic types of references cannot go into each others containers,
* there are other task for which we would like to access them as one
*
* */
public interface IMethodReference
{
    /* method will return true if object (the one tied to method) is null */
    boolean isNullFound();
    /* method will return the Method object that is referenced */
    Method getMethodToExecute();
    /* method will return the Object object that is being referenced */
    Object getExecutingObject();
}
