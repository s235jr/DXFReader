package pl.dxf.reader.parsefile;

public class Dimension {

    private double minX = -1;
    private double maxX = -1;
    private double minY = -1;
    private double maxY = -1;

    public double getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public double getHeight() {
        return this.maxY - this.minY;
    }

    public double getWidth() {
        return this.maxX - this.minX;
    }
}