package kcn.datastructures;

public interface IHoldMethodReference
{
    boolean add(IMethodReference method);

    boolean remove(IMethodReference method);

    void autoHandleNullReferences(boolean turnOnAutomaticHandlingOfNullObjects);

    void handleNullReferences();

    int length();
}
