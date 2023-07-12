package PolymorphShape;

public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void calculatePerimeter() {
        double perimeter = 2 * Math.PI * this.radius;
        this.setPerimeter(perimeter);
    }

    @Override
    public void calculateArea() {

    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
