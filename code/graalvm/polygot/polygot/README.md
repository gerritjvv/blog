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

#### Maps and Lists

Maps, Arrays and Lists need to be wrapped in a ProxyObject or ProxyArray interface to be use-able 
in Python as native objects arrays and lists.

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

#### Maps and Lists

Python lists can be cast to the Collection, List, Iterable Java interfaces.

Python dictionaries can be cast to the Map Java interface. 

Gets work as expected, but keySet does not, and returns the truffle object proxy methods.

### Create a Python Object, return it to Java and functions on it

From Python:

```python
class Person:
  def __init__(self, name, age):
    self.name = name
    self.age = age
                  
p = Person('Jack', 30)
p
```
 
From Java:

```java
Value pyPerson = eval(...);

System.out.println("pyPerson getMember(name): " + pyPerson.getMember("name"));
//pyPerson getMember(name): 'Jack'

pyPerson.putMember("age", 22);
System.out.println("pyPerson getMember(age): " + pyPerson.getMember("age"));
//pyPerson getMember(age): 22
```
