package gerrit.graalvm.java2py;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyArray;
import org.graalvm.polyglot.proxy.ProxyObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Access Java Maps from Python
 */
public class JavaMapsInPy {

    public static void main(String args[]) throws Throwable {

        Engine pyEngine = Engine.newBuilder().useSystemProperties(true).build();

        Source pySrc = compile(
                "print(str(person.name)) \n" +
                        "person"
                // returns a new instance of Person
        );

        // exec
        Context ctx = Context.newBuilder("python").allowAllAccess(true)
                .allowHostAccess(true)
                .engine(pyEngine)
                .build();

        Map<String, Object> person = new HashMap<>();
        person.put("name", "Jack");
        person.put("age", 21);

        Value bindings = ctx.getBindings("python");
        bindings.putMember("person", new ProxyMap(person));

        Value retPerson = ctx.eval(pySrc);

        System.out.println("Returned from python: " + retPerson);
        // Returned from python: Person{name='Jack', age=22}
    }


    public static Source compile(String python) throws IOException {
        return Source.newBuilder("python", python, "main.py").build();
    }

    static class ProxyMap implements ProxyObject {

        final Map<String, Object> map;

        public ProxyMap(Map<String, Object> map) {
            this.map = map;
        }

        @Override
        public boolean removeMember(String key) {
            return map.remove(key) != null;
        }

        @Override
        public Object getMember(String key) {
            return map.get(key);
        }

        @Override
        public boolean hasMember(String key) {
            return map.containsKey(key);
        }

        @Override
        public void putMember(String key, Value value) {
            map.put(key, value.isHostObject() ? value.asHostObject() : value);
        }

        @Override
        public Object getMemberKeys() {
            return new ProxyArray() {
                private final Object[] keys = map.keySet().toArray();

                public void set(long index, Value value) {
                    throw new UnsupportedOperationException();
                }

                public long getSize() {
                    return this.keys.length;
                }

                public Object get(long index) {
                    if (index >= 0L && index <= 2147483647L) {
                        return this.keys[(int) index];
                    } else {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }
            };
        }
    }

}
