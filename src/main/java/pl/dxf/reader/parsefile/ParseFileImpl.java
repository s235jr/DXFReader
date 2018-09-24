package pl.dxf.reader.parsefile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;

@Component
public class ParseFileImpl implements ParseFile {

    @Override
    public DxfFile dxfFile(File file) throws FileNotFoundException {

        DxfFile dxfFile = new DxfFile();

        String sign = "_";
        String imgFileTyp = ".png";

        dxfFile.parseName(file.getName(), sign, imgFileTyp);
        dxfFile.getDimension(file);
        dxfFile.createImg(file, imgFileTyp);

        return dxfFile;
    }


}

