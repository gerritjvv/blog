package gerrit.graalvm.java2py;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyArray;
import org.graalvm.polyglot.proxy.ProxyObject;

import java.io.IOException;
import java.util.*;

/**
 * Access Java Maps from Python
 */
public class JavaListsInPy {

    public static void main(String args[]) throws Throwable {

        Engine pyEngine = Engine.newBuilder().useSystemProperties(true).build();

        Source pySrc = compile(
                        "l[:10]"
                // returns a new instance of Person
        );

        // exec
        Context ctx = Context.newBuilder("python").allowAllAccess(true)
                .allowHostAccess(true)
                .engine(pyEngine)
                .build();


        List list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);


        Value bindings = ctx.getBindings("python");
        bindings.putMember("l", ProxyArray.fromList(list));

        Value retList = ctx.eval(pySrc);

        System.out.println("Returned from python: " + retList);
    }


    public static Source compile(String python) throws IOException {
        return Source.newBuilder("python", python, "main.py").build();
    }
}
