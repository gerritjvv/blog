package gerrit.graalvm.java2py;

public interface IPerson {

    String getName();

    int getAge();

    void setName(String name);

    void setAge(int age);

    IPerson clone();

}
