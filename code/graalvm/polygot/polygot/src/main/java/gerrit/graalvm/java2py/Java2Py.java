package gerrit.graalvm.java2py;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;

/**
 * You have a Java class and you want to make it available to Python, calling methods from Python and
 * then returning a Java class from Python to Java.
 */
public class Java2Py {

    public static void main(String args[]) throws Throwable {

        Engine pyEngine = Engine.newBuilder().useSystemProperties(true).build();

        Source pySrc = compile(
                "print(str(person)) \n" +
                        // prints out <foreign object at 0x7cbede2b>
                        "print('Name: ' + person.getName())\n" +
                        // prints out Name: Jack
                        "person.setAge(22)\n" +
                        "print('Age: ' + str(person.getAge()))\n" +
                        // prints out Age: 22
                        "person.clone()"
                // returns a new instance of Person
        );

        // exec
        Context ctx = Context.newBuilder("python").allowAllAccess(true)
                .allowHostAccess(true)
                .engine(pyEngine)
                .build();

        Value bindings = ctx.getBindings("python");
        bindings.putMember("person", new Person("Jack", 21));

        ctx.getBindings("python");

        Value clonedPerson = ctx.eval(pySrc);
        IPerson person = clonedPerson.as(IPerson.class);

        System.out.println("Returned from python: " + person);
        // Returned from python: Person{name='Jack', age=22}
    }


    public static Source compile(String python) throws IOException {
        return Source.newBuilder("python", python, "main.py").build();
    }
}
