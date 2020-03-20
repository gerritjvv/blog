package gerrit.graalvm.java2py;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Java2PyListComprehensions {
    public static void main(String args[]) throws Throwable {

        Engine pyEngine = Engine.newBuilder().useSystemProperties(true).build();

        Source pySrc = compile(
                "[v for v in numbers if v % 2 == 0]"
        );

        // exec
        Context ctx = Context.newBuilder("python").allowAllAccess(true)
                .engine(pyEngine)
                .build();

        Value bindings = ctx.getBindings("python");
        bindings.putMember("numbers", Arrays.asList(1,2,3,4,5));

        Value evens = ctx.eval(pySrc);

        System.out.println("Returned from python: " + evens.as(List.class));
    }


    public static Source compile(String python) throws IOException {
        return Source.newBuilder("python", python, "main.py").build();
    }
}
