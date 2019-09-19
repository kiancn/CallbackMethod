# Method References
    package kcn.methodreferencing

In short:
The Methodreferencing package allows you to pass methods along as references & to pack methods together to pass along.

Actually there are <b> MethodReferences, MethodPacks, MeRefs and MePacks </b>

* The MethodReferences and MethodPack are non-generic types and are ideal for simpler signalling tasks.
* The MeRefs and MePacks can do what the non-generics do - and much much more, and have a degree of type-safety and allow 
  many complex task types.

    -- This will grow into a proper introduction. 2019/09/17 --
    
      <i>Project version 0.01d (only end to end tests done; but no irregular behaviour noticed)</i>
      <I>Project v0.015 (one-tenth-of-one-tenth of the way, and then half that) 2019/09/18</i>
        - Major changes: a) name changes and b) complete rewrite of exception handling.


There are:

    * MethodReference   
    * MethodPack        
These are non-generic versions that are very easy to pass around;
but that have low type-safety and limited capabilities when it comes to parameters

And then there are Generic versions called: 

    * MeRef<V,O>        type instances are objects that contains a method you can execute whereever
    * MePack<V,O>       type is a Glorified list of MeRefs



Personally I like the generics much better, but both versions work fine.

# Notice
* All files are heavily commented ( except when they aren't yet ).
* A small set of test-scenarios is included in the package (and prepared for you in Main).

* A new, less confusing example of a MeReference in action has been added (it's light!):

      kcn.misc.MeReferencePatternExample           

* I'm sure there are still some places where I haven't replaced MeReference with MeRef in comments, please do this in your mind. Also, sorry.

# Short Explanation
# A basic basic pattern to start using a MeRef<V,O>

* You need a reference to an Object that will execute the method 
* You need a Method (or the string name of a method - as here)
* You need Classes of the types of parameters the method takes

Let's say there is a class called: 

    ImportantClass{ ... content ...}

and in that class there is a method with the following signature

    public String methodName (int number) { ... ... }

What you do to make a MeReference is to write:

    ImportantClass executingObject = new ImportantClass();

    MeRef<Integer,String> reference = new MeRef<>(executingObject,
                                                             "methodName",
                                                             new Class[]{int.class});
                             
You can then pass it around like the reference it is,

    someMethod(reference);

and access the method by typing:

    reference.run( 42 );

<i>( ... and yes, of course you can use the return value ... but I kept it simplified )</i>


<B>It very much works!</B>
- it also very much has issues; 
  * The constructors and methods have been tested against some case but but all ny a long shot: but it is difficult to figure out exactly how to use the different features together - without having see examples; so I'll make something up and make the existing better.  

Finishing up: 
* I hope the tests referenced in main Main are readable and comprehensible. <b> Please look at the new example first </b>
  
* I apologize for using custom wrapper classes in the examples (two 2d coordinate types and a Matrix class... they'll be standardized at some date.).



This is a proof of concept version and many possible features are missing. <i> Please inspire me </i>

* try it out, it's pretty neat!
* please comment: all experiences are welcome, even if you just need to shout
* send any suggestions for improvements to me

* Please pass this around, I believe it is pretty useful.



# Next feature goals:
* Test features more rigidly
* For MeReference: Automatic inference and checking of return type is possible at construction time because Method object comes with a .getReturnType method.
* Optimizing invoke calls: The two Me(thod)Reference types use Reflection at construction time and at run time. The heavy lifting is done at construction time, then the references are there. However, at execution time (in all run() variants) the invoke-method is called on the referenced Method object, and that is about twice as heavy as a regular method call (http://www.jguru.com/faq/view.jsp?EID=246569): though it might be worth it because you can pass methods around like a freak, it is worth a closer look at the internals of the invoke-method.

* Actual return type inference; but I think that will get a seperate set of types - because the existing works pretty well without the added rigidity/security. 

* Make an introduction to each feature of the classes (and possibly define a set of best practices...).
