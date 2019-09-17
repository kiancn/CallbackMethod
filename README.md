# MethodReferences
MethodReferences allows you to pass methods along as references and to pack methods together to pass along.

-- This description will grow into an introduction. 2019/09/17 --

Project version 0.09f (only end to end tests done; but no irregular behaviour noticed)

There are two versions and two do work together.

There are non-generic versions that are very easy to pass around;
but that have low type-safety and limited capabilities when it comes to parameters

* MethodReference
* MethodPack

Generic versions called:
* MeReference<V,O>
* MePack<V,O>

The files themselves are heavily commented and a small set of test-scenarios 
is included in the package.
