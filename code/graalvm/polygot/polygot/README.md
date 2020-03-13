# Overview


Java code that shows how to call python with graalvm, and the different
ways of passing and receiving objects, from Java to Python and from Python to Java.

# Requirements

  * [SDK Man](https://sdkman.io/install)
  * Install the graalvm `sdk install java 20.0.0.r11-grl`
  * Set graalvm as the current java `sdk use java 20.0.0.r11-grl`
  * Install the graalvm python libraries `gu install python`
  * Test by running `graalpython`

# Use cases

## Java to Python and back

#### Create a Java object and call methods on it from Python 
then returning a Java class from Python to Java.


The object must be available in the bindings.
And if `allowAllAccess` or `allowHostAccess` is set with true on the context
Python can call methods and send arguments to the Java objects.


#### Create a Java object from Python

##### Via Bindings
The class name must be set to the bindings and as a class.  
For example `binginds("Person", Person.class)`, now you can run `Person('name', 21)`
and python will create the Java Person class.

##### Via import type

````python
import java
Person = java.type('gerrit.graalvm.java2py.Person')
Person('Jack', 21)
````

## Python to Java and back

  1. You have a Python class you and want to return it to Java and have Java call methods on it and 
then send it back to Python
 
 
  2. You have a Python class  and you want to create instances of it in Java and send it to Python.
