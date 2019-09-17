# MethodReferences, MethodPacks
 & MeReferences and MePacks
MethodReferences allows you to pass methods along as references and to pack methods together to pass along.

-- This description will grow into an introduction. 2019/09/17 --

<I>Project version 0.01d (only end to end tests done; but no irregular behaviour noticed)</i>

There are two versions and two do work together.

There are non-generic versions that are very easy to pass around;
but that have low type-safety and limited capabilities when it comes to parameters

* MethodReference
* MethodPack

Generic versions called:
* MeReference<V,O>
* MePack<V,O>

# Notice
* The files themselves are heavily commented 
* A small set of test-scenarios is included in the package (and prepared for you in Main).
* A new, less confusing example of a MeReference in action has been added:

      kcn.misc.MeReferencesPatternExample           
* It is heavily commented.

# Short Explanation
# A basic basic pattern of MeReferences 

* You need a reference to an Object that will execute the method 
* You need a Method 
* You need Classes of the types of parameters the method takes

Let's say there is a class called: 

    ImportantClass{ ... content ...}

and in that class there is a method with the following signature 
public String methodName (int number)

What you do to make a MeReference is to write:

    ImportantClass executingObject = new ImportantClass();

    MeReference<Integer,String> reference = new MeReference<>(instanceClass,
                                                             "methodName",
                                                             new Class[]{int.class});
                             
You can then pass it around like the field it is,
and access the method by typing:

    reference.run('input int');
    
or even

    someMethod(reference);
    
( ... and yes, of course you can use the return value ...)


<B>It very much works!</B>
- it also very much has issues; 
  * in MeReference the constructors have been tested seperately, and so have every other part: but it is difficult to figure out exactly how to use the different features together; so I'll make something up and make the existing better.  

Finishing up: 
* I hope the tests referenced in main Main are readable and comprehensible. <b> Please look at the new example first <b>
  
* I apologize for using custom wrapper classes in the examples (two 2d coordinate types and a Matrix class... they'll be standardized at some date.).



This is a proof of concept version and many possible features are missing.

* try it out, it's pretty neat!
* please comment: all experiences are welcome, even if you just need to shout
* send any suggestions for improvements to me

* Please pass this around, I believe it is pretty useful.



# Next feature goals:
* Test features more rigidly
* For MeReference: Automatic inference and checking of return type is possible at construction time because Method object comes with a .getReturnType method.
* Optimizing invoke calls: The two Me(thod)Reference types use Reflection at construction time and at run time. The heavy lifting is done at construction time, then the references are there. However, at execution time (in all run() variants) the invoke-method is called on the referenced Method object, and that is about twice as heavy as a regular method call (http://www.jguru.com/faq/view.jsp?EID=246569): though it might be worth it because you can pass methods around like a freak, it is worth a closer look at the internals of the invoke-method.

* Make an introduction to each feature of the classes (and possibly define a set of best practices...).
