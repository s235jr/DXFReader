package pl.dxf.reader.parsefile;

import com.aspose.cad.Image;
import com.aspose.cad.ImageOptionsBase;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.PngOptions;
import pl.dxf.reader.entity.Raport;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
@Entity
@Table(name="elements")
public class DxfFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String originalFileName;
    private String name;
    private String namePng;
    private String thickness;
    private String materialTyp;
    private String amount;
    private double width;
    private double height;
    @ManyToOne
    private Raport raport;

    public void parseName(String fileName, String sign, String imgFileTyp){
        try {
            String[] valueFromName = fileName.split(sign);
            this.originalFileName = fileName;
            this.name = (valueFromName[3].substring(0, valueFromName[3].length() - 4));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            this.namePng = ("0" + this.name + LocalDateTime.now().format(formatter) + imgFileTyp);
            this.thickness = valueFromName[0];
            this.materialTyp = valueFromName[1];
            this.amount = valueFromName[2];
        } catch (Exception e) {
            e.printStackTrace();
            this.name = "Error";
            this.namePng = "Error";
            this.thickness = "Error";
            this.materialTyp = "Error";
            this.amount = "Error";
        }
    }

    public void getDimension(File file) throws FileNotFoundException {
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
        this.height = dimension.getHeight();
        this.width = dimension.getWidth();
    }

    public void createImg(File file, String imgFileTyp) throws FileNotFoundException {
        InputStream stream = new FileInputStream(file);

        Image image = Image.load(stream);
        CadRasterizationOptions rasterizationOptions = new CadRasterizationOptions();

        // Set page width & height
        int resolution = 1000;
        int size = resolution * 4 / 3;
        rasterizationOptions.setPageWidth(size);
        rasterizationOptions.setPageHeight(size);

        // Create an instance of PngOptions for the resultant image
        ImageOptionsBase options = new PngOptions();

        // Set rasterization options
        options.setVectorRasterizationOptions(rasterizationOptions);
        options.setRotation(0);

        // Save resultant image
        image.save(getNamePng(), options);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(getNamePng()));

            BufferedImage subImage = img.getSubimage((int) (0.25 * size), (0), (int) (0.75 * size),
                    (1 * size));

            int firstPixelX = resolution;
            int lastPixelX = 0;
            int firstPixelY = resolution;
            int lastPixelY = 0;

            for (int i = 0; i < subImage.getWidth(); i++) {
                for (int j = 0; j < subImage.getHeight(); j++) {
                    int value = subImage.getRGB(i, j);
                    if (value != -1) {
                        if (i < firstPixelX) {
                            firstPixelX = i;
                        }
                        if (i > lastPixelX) {
                            lastPixelX = i;
                        }
                        if (j < firstPixelY) {
                            firstPixelY = j;
                        }
                        if (j > lastPixelY) {
                            lastPixelY = j;
                        }
                    }
                }
            }

            int frame = 100; //pixel
            int sizeImage = Math.max(lastPixelX - firstPixelX, lastPixelY - firstPixelY) + frame;
            int cornerX = firstPixelX - (sizeImage - (lastPixelX - firstPixelX))/2;
            int cornerY = firstPixelY - (sizeImage - (lastPixelY - firstPixelY))/2;

            BufferedImage finalImage = subImage.getSubimage(cornerX, cornerY, sizeImage, sizeImage);
            File outputFile = new File(getNamePng());
            ImageIO.write(finalImage, imgFileTyp.substring(1, 4), outputFile);

        } catch (IOException e) {
            System.out.println("Coś poszło nie tak! " + e);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamePng() {
        return namePng;
    }

    public void setNamePng(String namePng) {
        this.namePng = namePng;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public String getMaterialTyp() {
        return materialTyp;
    }

    public void setMaterialTyp(String materialTyp) {
        this.materialTyp = materialTyp;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Raport getRaport() {
        return raport;
    }

    public void setRaport(Raport raport) {
        this.raport = raport;
    }

    @Override
    public String toString() {
        return "DxfFile{" +
                "id=" + id +
                ", originalFileName='" + originalFileName + '\'' +
                ", name='" + name + '\'' +
                ", namePng='" + namePng + '\'' +
                ", thickness='" + thickness + '\'' +
                ", materialTyp='" + materialTyp + '\'' +
                ", amount='" + amount + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", raport=" + raport +
                '}';
    }
}
