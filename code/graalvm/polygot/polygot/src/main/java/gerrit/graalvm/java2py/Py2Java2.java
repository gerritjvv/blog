package gerrit.graalvm.java2py;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;

public class Py2Java2 {

    public static void main(String args[]) throws Throwable {

        Engine pyEngine = Engine.newBuilder().useSystemProperties(true).build();

        Source pySrc = compile(
                "import java\n" +
                        "Person = java.type('gerrit.graalvm.java2py.Person')\n" +
                        "Person('abc', 30)"
                // instantiates a java Person instance, sets  the age and returns it
        );

        // exec
        Context ctx = Context.newBuilder("python").allowAllAccess(true)
                .engine(pyEngine)
                .build();

        Value pyPerson = ctx.eval(pySrc);

        System.out.println("Returned from python: " + pyPerson);
    }


    public static Source compile(String python) throws IOException {
        return Source.newBuilder("python", python, "main.py").build();
    }
}
