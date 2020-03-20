package gerrit.graalvm.java2py;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;

public class Java2Py2 {

    public static void main(String args[]) throws Throwable {

        Engine pyEngine = Engine.newBuilder().useSystemProperties(true).build();

        Source pySrc = compile(
                "p = Person('name', 12)\n" +
                        "p.setAge(21)\n" +
                        "p"
                // instantiates a java Person instance, sets  the age and returns it
        );

        // exec
        Context ctx = Context.newBuilder("python").allowAllAccess(true)
                .engine(pyEngine)
                .build();

        Value bindings = ctx.getBindings("python");
        bindings.putMember("Person", Person.class);

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
