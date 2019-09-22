# Method References
    package kcn.methodreferencing

In short:
The Methodreferencing package allows you to pass methods along as references & to pack methods together to pass along.

Actually there are <b> MethodReferences, MethodPacks, MeRefs and MePacks </b>

* The MethodReferences and MethodPack are non-generic types and are ideal for simpler signalling tasks.
* The MeRefs and MePacks can do what the non-generics do - and much much more, and have a degree of type-safety and allow 
  many complex task types.

    -- This will grow into a proper introduction. 2019/09/17 --
    
      <i>v. 0.01d (only end to end tests done; but no irregular behaviour noticed)</i>
      <I>v. 0.015 (one-tenth-of-one-tenth of the way, and then half that) 2019/09/18</i>
        - Major changes: a) name changes and b) complete rewrite of exception handling.
      <i>v. 0.01501 a) readability upgrades, examples less-confusing-made, b) minor changes in exception handling</i>
      <i>v. 0.016 (IHoldMethodReference; gone b) many run-methods in packs; gone c) descriptive text less bad</i>
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
* You need a MeRef<V,O> - (V is always paramater type, and O is return type. <i>Mostly, consult run..(..)-method comments</i>) 
* You need a Method or the string name of a method - as here; there are many constructors.
* You need a Class or Class[] of the type/types of parameters the method takes (and primary types act different and need an array to accept even a single parameter (at construction)).

Let's say there is a class called: 

    ImportantClass{ ... content ...}

and in that class there is a method with the following signature

    public String methodName (int number) { ... ... }


Let's just say you get a reference to instance of that class like this:

    ImportantClass executingObject = new ImportantClass();



<b>You then declare a MeRef with appropriate signature </b>

    MeRef<Integer,String> reference = new MeRef<>(executingObject,          // method-executing object
                                                   "methodName",            // identifier of method (aka name)
                                                   new Class[]{int.class}); 
                                                        // array of Class type objects that mirrors the
                                                        // parameter list of the sought after method.

Now, if a method was to take this MeRef as a parameter type, it might look exactly like this:

    void someMethod(MeRef<Integer,String> aMethodToProcess){ ... content ... } 
 
You can then pass a reference to a method;

    someMethod(reference);

and access the passed method : (like this in this case, there are a few run..(..)-methods)

    reference.run( 42 );

<i>( ... and yes, of course you can use the return value, as if executing the contained method directly )</i>


<B>It very much works!</B>
- it also very much has issues; 
  * The constructors and methods have been tested against some case but but all ny a long shot: but it is difficult to figure out exactly how to use the different features together - without having see examples; so I'll make something up and make the existing better.  

Finishing up: 
* I hope the tests referenced in main Main are readable and comprehensible. <b> Please look at the new example first </b>
  
* Sorry for using custom wrapper classes in the examples (two 2d coordinate types and a Matrix class... they'll be standardized at some date.).


This is a proof of concept version and many possible features are missing. <i> Please inspire me </i>

* try it out, it's pretty neat!
* please comment: all experiences are welcome, even if you just need to shout
* please fork and develop the idea! I'd love to participate!
* Please pass this around, I believe it is pretty useful.

# Pending goals:
* Test features more rigidly
* Optimizing invoke calls: The two Me(thod)Reference types use Reflection at construction time and at run time. The heavy lifting is done at construction time, then the references are there. However, at execution time (in all run() variants) the invoke-method is called on the referenced Method object, and that is about twice as heavy as a regular method call (http://www.jguru.com/faq/view.jsp?EID=246569): though it might be worth it because you can pass methods around like a freak, it is worth a closer look at the internals of the invoke-method.

* Make an introduction to each feature of the classes (and possibly define a set of best practices...).
