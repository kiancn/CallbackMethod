# Callback Methods (formerly unknown as Method References)
    package kcn.methodreferencing


* Please substitute the terms (a) method reference and (b) MeRef for (a) callback method and (b) CallMe using the awesome power of imagination (just until I update the rest of this readme, promise; then you'll never have to imagine again)

* Try the speed test enabled in main. It's like if racing was boring, not fast and not dangerous. Also, informative and exciting: Do more than 400 test rounds and achieve stable results.

In short:
The kcn.methodreferencing package allows you to pass methods along as references & to pack methods together to store, pass along and execute at will.


Actually there are <b> CallbackMethods, CallbackPacks, CallMes<V,O> and CallPacks<V,O> </b>

* The CallbackMethods and CallbackPack are non-generic types and are ideal for simpler signalling tasks.

        * CallbackMethod   
        * CallbackPack        
    
These are non-generic versions that are very easy to pass around;
but that have low type-safety and limited capabilities when it comes to parameters

* The CallMe<V,O>s and CallPacks<V,O> can do what the non-generics do - and much much more, and have a degree of type-safety and allow 
  many complex task types.

        * CallMe<V,O>        type instances are objects that contains a method you can execute whereever
        * CallPack<V,O>        type is a Glorified list of CallMes (but with exception handling)


Personally I like the generics much better, but both versions work fine.


# Short Explanation
The CallbackMethods and CallMes are broadly speaking
wrapper classes for java.lang.reflect.Method objects;

The interesting part is that by  
1) storing a reference to an instance of a class that
has some desired method as a member, 
2) along with Class object(s) mirroring the 
method parameter list,
3) it is possible to create a java.lang.reflect.Method object 
and keep it, pass it around, pool it - and execute it 
from where-ever.
Even doing complex variations of parameters and return types. It's neat!
# A basic basic pattern to start using a CallMe<V,O>

* You need a reference to an Object that will execute the method 
* You need a Method or the string name of a method (there are multiple constructors).
* You need to list Class-objects of the type(s) of the parameters that the method takes.
* Then you can create a CallMe<V,O> - (V is always paramater type, and O is return type. <i>Mostly, consult run..(..)-method comments</i>) 

Let's say there is a class called: 

    ImportantClass{ ... content ...}

and in that class there is a method with the following signature

    public String methodName (int number) { ... ... }


Let's just say you get a reference to instance of that class like this:

    ImportantClass executingObject = new ImportantClass();


<b>You then declare a CallMe with appropriate signature </b>

    // Supplying Object instance executing method, name of method, and list each class in parameter list.
    
    CallMe<Integer,String> reference = new CallMe<>(executingObject,  
                                                   "methodName",           
                                                   int.class);                                                       

Now, if a method was to take this CallMe as a parameter type, it's parameter list might look exactly like this:

    void someMethod(CallMe<Integer,String> aMethodToProcess){ ... content ... } 
 
You can then pass a reference to a method;

    someMethod(reference);

and access the passed method : (like this in this case, there are a few run..(..)-methods)

    reference.run( 42 );

<i>( ... and yes, of course you can use the return value, as if executing the contained method directly )</i>


<B>It very much works!</B>
- it probably also has all sorts of issues I didn't discover; 
  * please write me about any issues you might be having!
  * it is a task to figure out exactly how  one can use the different features together
   without having see examples; so I'll make something up and make the existing better.  
   * In almost all the supplied examples I choose the construct the CallbackMethods/CallMes using the long-winded (<Object>,<Method>,<Var... Class> constructor, and it is close to unreadable, sorry. Better examples upcoming.

Finishing up: 
* I hope the tests referenced in main Main are easy enough to read. Update; they are not. Better examples, me.
  
* Sorry for using custom wrapper classes in the examples (two 2d coordinate types).


This is a proof of concept version and many possible features are missing. <i> Please inspire me! </i>

* try it out, it's pretty neat!
* please comment: all experiences are welcome, even if you just need to shout
* please fork and develop the idea! I'd love to participate!
* Please pass this around, I believe it is pretty useful.

# Pending goals:
* Test features more rigidly
* Optimizing invoke calls: The two Me(thod)Reference types use Reflection at construction time and at run time. The heavy lifting is done at construction time, then the references are there. However, at execution time (in all run() variants) the invoke-method is called on the referenced Method object, and that is about twice as heavy as a regular method call (http://www.jguru.com/faq/view.jsp?EID=246569): though it might be worth it because you can pass methods around like a freak, it is worth a closer look at the internals of the invoke-method.

* Make an introduction to each feature of the classes (and possibly define a set of best practices...).

* A few new constructors are coming up; when method-reference is created to reference a method on the same object as the method-reference is being declared on, the constructor can be as simple as a single string (for no parameter methods), or a string and a Class[] . These options should exist.



# Notice
* All files are heavily commented ( except when they aren't yet ).
* A small set of test-scenarios is included in the package (and prepared for you in Main).

* A new, less confusing example of a CallMe in action has been added (it's light!):

      kcn.misc.MeReferencePatternExample           


* I'm sure there are still some places where I haven't replaced CallMe with CallMe in comments, please do this in your mind. Also, sorry.

# Version Update Summaries
    -- This will grow into a proper introduction. 2019/09/17 --
    
      <i>v. 0.01d (only end to end tests done; but no irregular behaviour noticed)</i>
      <I>v. 0.015 (one-tenth-of-one-tenth of the way, and then half that) 2019/09/18</i>
        - Major changes: a) name changes and b) complete rewrite of exception handling.
      <i>v. 0.01501 a) readability upgrades, examples less-confusing-made, b) minor changes in exception handling</i>
      <i>v. 0.016 (IHoldCallbackMethod; gone b) many run-methods in packs; gone c) descriptive text less bad</i>     
      <i>v. 0.0165 Better explanatory comments, minor changes in exception handling + renamings</i> 
