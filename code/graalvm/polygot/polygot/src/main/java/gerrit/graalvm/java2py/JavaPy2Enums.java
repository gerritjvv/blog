package gerrit.graalvm.java2py;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;

public class JavaPy2Enums {

    public static void main(String args[]) throws Throwable {

        Engine pyEngine = Engine.newBuilder().useSystemProperties(true).build();

        Source pySrc = compile(
                "from java.gerrit.graalvm.java2py import Car\n" +
                "c = Car()\n" +
                "print(str(c.getColor().name()))\n"

                //"from java.gerrit.graalvm.java2py import Car$COLOR\n"
                //Exception in thread "main" SyntaxError: invalid syntax
                //"from java.gerrit.graalvm.java2py.Car.COLOR import BLUE\n"
                //Exception in thread "main" ModuleNotFoundError: No module named 'java.gerrit.graalvm.java2py.Car.COLOR'; 'java.gerrit.graalvm.java2py.Car' is not a package

                //"from java.gerrit.graalvm.java2py.Car import COLOR\n"
                //Exception in thread "main" ImportError: cannot import name 'COLOR'

                //"from java.gerrit.graalvm.java2py import Car.COLOR\n"
                //Exception in thread "main" SyntaxError: invalid syntax

                //"Car('BLUE')\n"
                //Exception in thread "main" TypeError: invalid invocation of foreign callable

        );

        //new Car(Car.COLOR.BLUE);

        // exec
        Context ctx = Context.newBuilder("python").allowAllAccess(true)
                .engine(pyEngine)
                .build();


        ctx.eval(pySrc);

    }


    public static Source compile(String python) throws IOException {
        return Source.newBuilder("python", python, "main.py").build();
    }
}
