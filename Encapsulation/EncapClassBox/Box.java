package Encapsulation.EncapClassBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    public double getLength() {
        return length;
    }

    private void setLength(double length) {
        if (length > 0) {
            this.length = length;
        } else {
            throw new IllegalArgumentException("Length cannot be zero or negative.");
        }
    }

    public double getWidth() {
        return width;
    }

    private void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        } else {
            throw new IllegalArgumentException("Width cannot be zero or negative.");
        }

    }

    public double getHeight() {
        return height;
    }

    private void setHeight(double height) {
        if (height > 0) {
            this.height = height;
        } else {
            throw new IllegalArgumentException("Height cannot be zero or negative.");
        }

    }

    public double calculateSurfaceArea() {
        return 2 * this.length * this.width + 2 * this.width * this.height + 2 * this.height * this.length;
    }

    public double calculateLateralSurfaceArea() {
        return 2 * this.height * (this.width + this.length);
    }

    public double calculateVolume() {
        return this.height * this.length * this.width;
    }
}

