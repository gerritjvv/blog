package gerrit.graalvm.java2py;

public class Car {
    enum COLOR {
        RED,
        BLUE
    }

    COLOR color;

    public Car() {
        this.color = COLOR.RED;
    }

    public Car(COLOR color) {
        this.color = color;
    }

    public COLOR getColor() {
        return color;
    }

    public void setColor(COLOR color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color=" + color +
                '}';
    }
}
