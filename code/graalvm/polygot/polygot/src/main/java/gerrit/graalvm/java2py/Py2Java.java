package gerrit.graalvm.java2py;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;

public class Py2Java {

    public static void main(String args[]) throws Throwable {

        Engine pyEngine = Engine.newBuilder().useSystemProperties(true).build();

        Source pySrc = compile(
                "class Person:\n" +
                        "   def __init__(self, name, age):\n" +
                        "      self.name = name\n" +
                        "      self.age = age\n" +
                        "\n" +
                        "p = Person('Jack', 30)\n" +
                        "p"
                // instantiates a java Person instance, sets  the age and returns it
        );

        // exec
        Context ctx = Context.newBuilder("python").allowAllAccess(true)
                .engine(pyEngine)
                .build();

        Value pyPerson = ctx.eval(pySrc);

        System.out.println("Returned from python: " + pyPerson);
        // Returned from python: <__main__.Person object at 0x69ac5752>

        // Person p = pyPerson.as(Person.class);
        // you cannot cast here:
        // Exception in thread "main" java.lang.ClassCastException: Cannot convert '<__main__.Person object at 0x69ac5752>'(language: Python, type: <class '__main__.Person'>)
        // to Java type 'gerrit.graalvm.java2py.Person': Unsupported target type.

        System.out.println("pyPerson memberKeys: " + pyPerson.getMemberKeys());
        //pyPerson memberKeys: [name, age]

        System.out.println("pyPerson getMember(name): " + pyPerson.getMember("name"));
        //pyPerson getMember(name): 'Jack'

        pyPerson.putMember("age", 22);
        System.out.println("pyPerson getMember(age): " + pyPerson.getMember("age"));
        //pyPerson getMember(age): 22
    }


    public static Source compile(String python) throws IOException {
        return Source.newBuilder("python", python, "main.py").build();
    }
}
