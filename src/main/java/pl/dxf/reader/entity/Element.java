//package pl.dxf.reader.entity;
//
//import javax.persistence.*;
//@Entity
//@Table(name="elements")
//public class Element {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    private String name;
//    private String namePng;
//    private String thickness;
//    private String materialTyp;
//    private String amount;
//    private double width;
//    private double height;
//    @ManyToOne
//    private Raport raport;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getNamePng() {
//        return namePng;
//    }
//
//    public void setNamePng(String namePng) {
//        this.namePng = namePng;
//    }
//
//    public String getThickness() {
//        return thickness;
//    }
//
//    public void setThickness(String thickness) {
//        this.thickness = thickness;
//    }
//
//    public String getMaterialTyp() {
//        return materialTyp;
//    }
//
//    public void setMaterialTyp(String materialTyp) {
//        this.materialTyp = materialTyp;
//    }
//
//    public String getAmount() {
//        return amount;
//    }
//
//    public void setAmount(String amount) {
//        this.amount = amount;
//    }
//
//    public double getWidth() {
//        return width;
//    }
//
//    public void setWidth(double width) {
//        this.width = width;
//    }
//
//    public double getHeight() {
//        return height;
//    }
//
//    public void setHeight(double height) {
//        this.height = height;
//    }
//
//    public Raport getRaport() {
//        return raport;
//    }
//
//    public void setRaport(Raport raport) {
//        this.raport = raport;
//    }
//
//    @Override
//    public String toString() {
//        return "Element{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", namePng='" + namePng + '\'' +
//                ", thickness='" + thickness + '\'' +
//                ", materialTyp='" + materialTyp + '\'' +
//                ", amount='" + amount + '\'' +
//                ", width=" + width +
//                ", height=" + height +
//                ", raport=" + raport +
//                '}';
//    }
//}
