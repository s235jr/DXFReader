package pl.dxf.reader.parsefile;

import org.springframework.stereotype.Component;

import java.util.Scanner;
public class Circle implements ParseObject {

    private double middleX;
    private double middleY;
    private double radius;
    private double radiusStart = 0;
    private double radiusEnd = 360;

    public Circle() {
    }

    @Override
    public Scanner parseObject(Scanner scanner, Dimension dimension) {


        while (scanner.hasNext()) {
            String circleLine = scanner.nextLine();
            if (circleLine.equals(" 10")) {
                middleX = Double.valueOf(scanner.nextLine());
            }

            if (circleLine.equals(" 20")) {
                middleY = Double.valueOf(scanner.nextLine());
            }

            if (circleLine.equals(" 40")) {
                radius = Double.valueOf(scanner.nextLine());
            }

            if (circleLine.equals("AcDbArc")) {
                while (!circleLine.equals("ENDSEC") || !circleLine.equals("AcDbEntity")) {
                    circleLine = scanner.nextLine();
                    if (circleLine.equals(" 50")) {
                        radiusStart = Double.valueOf(scanner.nextLine());
                    }

                    if (circleLine.equals(" 51")) {
                        radiusEnd = Double.valueOf(scanner.nextLine());
                        break;
                    }
                }
            }
            if (circleLine.equals("ENDSEC") || circleLine.equals("AcDbEntity")) {
                break;
            }
        }

        for (int i = (int)radiusStart; i <= radiusEnd; i++) {
            if (i % 90 == 0) {
                PointX pointX = new PointX(middleX + radius * Math.cos(Math.toRadians(i)));
                PointY pointY = new PointY(middleY + radius * Math.sin(Math.toRadians(i)));
                pointX.checkPoint(dimension);
                pointY.checkPoint(dimension);
            }
        }

        PointX startPointX = new PointX(middleX + radius * Math.cos(Math.toRadians(radiusStart)));
        PointY startPointY = new PointY(middleY + radius * Math.sin(Math.toRadians(radiusStart)));
        startPointX.checkPoint(dimension);
        startPointY.checkPoint(dimension);

        PointX endPointX = new PointX(middleX + radius * Math.cos(Math.toRadians(radiusEnd)));
        PointY endPointY = new PointY(middleY + radius * Math.sin(Math.toRadians(radiusEnd)));
        endPointX.checkPoint(dimension);
        endPointY.checkPoint(dimension);

        return scanner;
    }
}
