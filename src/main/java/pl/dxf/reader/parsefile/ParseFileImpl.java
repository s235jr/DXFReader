package pl.dxf.reader.parsefile;

import com.aspose.cad.Image;
import com.aspose.cad.ImageOptionsBase;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.PngOptions;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

@Component
public class ParseFileImpl implements ParseFile {

    @Override
    public DxfFile dxfFile(File file) throws FileNotFoundException {

        DxfFile dxfFile = new DxfFile();

        String sign = "_";
        String imgFileTyp = ".png";
        dxfFile.parseName(file.getName(), sign, imgFileTyp);

        Dimension dimension = getDimension(file);

        dxfFile.setWidth(dimension.getWidth());
        dxfFile.setHeight(dimension.getHeight());

        InputStream stream = new FileInputStream(file);

        Image image = Image.load(stream);
        CadRasterizationOptions rasterizationOptions = new CadRasterizationOptions();

        // Set page width & height
        int resolution = 1500;
        int zoom = 1;
        int size = resolution * 4 / 3;
        rasterizationOptions.setPageWidth(size);
        rasterizationOptions.setPageHeight(size);
        rasterizationOptions.setZoom(zoom);

        // Create an instance of PngOptions for the resultant image
        ImageOptionsBase options = new PngOptions();

        // Set rasterization options
        options.setVectorRasterizationOptions(rasterizationOptions);
        options.setRotation(0);

        // Save resultant image
        image.save(dxfFile.getNamePng(), options);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(dxfFile.getNamePng()));

            BufferedImage subImage = img.getSubimage((int) (0.25 * size), (int) (0.25 * size), (int) (0.5 * size),
                    (int) (0.5 * size));

            File outputFile = new File(dxfFile.getNamePng());
            ImageIO.write(subImage, imgFileTyp.substring(1, 4), outputFile);

        } catch (IOException e) {
            System.out.println("Coś poszło nie tak! " + e);
        }
        return dxfFile;
    }

    private Dimension getDimension(File file) throws FileNotFoundException {
        Dimension dimension = new Dimension();
        Scanner scanner = new Scanner(file);

        String typLine = "AcDbLine";
        String typCircle = "AcDbCircle";
        String typEllipse = "AcDbEllipse";
        String typSpline = "AcDbSpline";

        while (scanner.hasNext()) {
            String inputLine = scanner.nextLine();

            if (inputLine.equals(typLine)) {

                ParseObject line = new Line();
                line.parseObject(scanner, dimension);

            } else if (inputLine.equals(typCircle)) {

                ParseObject circle = new Circle();
                circle.parseObject(scanner, dimension);

            } else if (inputLine.equals(typSpline)) {

                ParseObject spline = new Spline();
                spline.parseObject(scanner, dimension);

            } else if (inputLine.equals(typEllipse)) {

            }
        }
        return dimension;
    }
}

