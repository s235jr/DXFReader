package pl.dxf.reader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.dxf.reader.parsefile.DxfFile;
import pl.dxf.reader.parsefile.ParseFile;

import java.io.File;
import java.io.IOException;

@Controller
public class HomeController {

    public final ParseFile parseFile;

    public HomeController(ParseFile parseFile) {
        this.parseFile = parseFile;
    }

    @RequestMapping("/addFile")
    public String readFile() {
        return "inputFileForm";
    }

    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public String readFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        file.transferTo(convFile);
        DxfFile dxfFile = parseFile.dxfFile(convFile);
        //System.out.println(dxfFile.toString());

        model.addAttribute("height", "2314");
        model.addAttribute("dxfFile", dxfFile);
        return "view";
    }

}
