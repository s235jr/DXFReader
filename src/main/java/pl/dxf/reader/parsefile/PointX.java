package pl.dxf.reader.parsefile;

public class PointX implements CheckPoint {

    private double pointX;

    public PointX(double pointX) {
        this.pointX = pointX;
    }

    @Override
    public void checkPoint(Dimension dimension) {
        {
            if (dimension.getMinX() < 0 || dimension.getMinX() > this.pointX) {
                dimension.setMinX(this.pointX);
            }
            if (dimension.getMaxX() < 0 || dimension.getMaxX() < this.pointX) {
                dimension.setMaxX(this.pointX);
            }
        }
    }
}
