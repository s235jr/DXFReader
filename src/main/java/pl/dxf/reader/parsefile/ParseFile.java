package pl.dxf.reader.parsefile;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

@Component
public interface ParseFile {

    DxfFile dxfFile(File file) throws FileNotFoundException;

}
