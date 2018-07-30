package pl.dxf.reader.parsefile;

import org.springframework.stereotype.Component;

import java.util.Scanner;
public interface ParseObject {

    Scanner parseObject(Scanner scanner, Dimension dimension);

}
