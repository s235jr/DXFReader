package pl.dxf.reader.parsefile;

import java.util.Scanner;

public class Spline implements ParseObject {
    @Override
    public Scanner parseObject(Scanner scanner, Dimension dimension) {

        while (scanner.hasNext()) {
            String splineLine = scanner.nextLine();
            if (splineLine.equals(" 10")) {
                PointX pointX = new PointX(Integer.valueOf(scanner.nextLine()));
                pointX.checkPoint(dimension);
            }

            if (splineLine.equals(" 20")) {
                PointY pointY = new PointY(Integer.valueOf(scanner.nextLine()));
                pointY.checkPoint(dimension);
            }
            if (splineLine.equals("ENDSEC")) {
                break;
            }
        }
        return scanner;
    }
}
