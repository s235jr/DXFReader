package pl.dxf.reader.parsefile;

public class PointY implements CheckPoint {

    private double pointY;

    public PointY(double pointY) {
        this.pointY = pointY;
    }

    @Override
    public void checkPoint(Dimension dimension) {
        {
            if (dimension.getMinY() < 0 || dimension.getMinY() > this.pointY) {
                dimension.setMinY(this.pointY);
            }
            if (dimension.getMaxY() < 0 || dimension.getMaxY() < this.pointY) {
                dimension.setMaxY(this.pointY);
            }
        }
    }
}
