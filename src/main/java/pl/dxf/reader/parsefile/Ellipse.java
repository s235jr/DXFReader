package pl.dxf.reader.parsefile;

import java.util.Scanner;

public class Ellipse implements ParseObject {

    private double middleX;
    private double middleY;


    @Override
    public Scanner parseObject(Scanner scanner, Dimension dimension) {

        while (scanner.hasNext()) {
            String ellipseLine = scanner.nextLine();
            if (ellipseLine.equals(" 10")) {
                middleX = Double.valueOf(scanner.nextLine());

            }

            if (ellipseLine.equals(" 20")) {
                middleY = Double.valueOf(scanner.nextLine());
            }




            if (ellipseLine.equals("ENDSEC")) {
                break;
            }
        }
        return scanner;

    }
}
