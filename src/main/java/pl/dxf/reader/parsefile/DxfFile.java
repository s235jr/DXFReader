package pl.dxf.reader.parsefile;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class DxfFile {

    private String name;
    private String namePng;
    private String thickness;
    private String materialTyp;
    private String amount;
    private double width;
    private double height;

    public void parseName(String fileName, String sign, String imgFileTyp){
        try {
            String[] valueFromName = fileName.split(sign);
            this.name = (valueFromName[3].substring(0, valueFromName[3].length() - 4));
            this.namePng = ("0" + this.name + LocalDateTime.now() + imgFileTyp);
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


    public String getMaterialTyp() {
        return materialTyp;
    }

    public void setMaterialTyp(String materialTyp) {
        this.materialTyp = materialTyp;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
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

    @Override
    public String toString() {
        return "DxfFile{" +
                "name='" + name + '\'' +
                ", namePng='" + namePng + '\'' +
                ", thickness='" + thickness + '\'' +
                ", materialTyp='" + materialTyp + '\'' +
                ", amount='" + amount + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
