package kcn.misc;

import kcn.datastructures.MePack;

import java.lang.reflect.InvocationTargetException;


/* [Class/object is purely placeholder]
Class mocks the function of an object that fires packs of MeRefs on command */
public class ExampleMethodsClass_B
{

    MePack<String, String> clientMethods;
    String[] stringArray;

    public ExampleMethodsClass_B(){ this.clientMethods = new MePack<>(); }

    public void processStringArray() throws
                                     InvocationTargetException,
                                     IllegalAccessException
    {
        for(int i = 0; i < stringArray.length - 1; i++)
        {
            processStringPair(clientMethods, stringArray[i], stringArray[i + 1]);
        }
    }


    public void processStringPair(MePack<String, String> mePack,
                                  String string1, String string2) throws
                                                                 InvocationTargetException,
                                                                 IllegalAccessException
    {
        for(int i = 0; i < mePack.getMethods().size(); i++)
        {
            System.out.print("MeRef[" + (int)(i + 1) + "] ");
            /* running the method in spot i .... btw arguments are type-safe*/
            mePack.getMethods().get(i).run(string1, string2);
        }
    }

    /*Pseudo-random; */
    int getRandomAccess(String[] stringsToMessWith)
    {
        int r = (int)(Math.random() * (double)stringsToMessWith.length - 1);
        return r;
    }
}