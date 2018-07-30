package pl.dxf.reader.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import pl.dxf.reader.parsefile.DxfFile;
import pl.dxf.reader.parsefile.ParseFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@SessionAttributes("dxfFileList")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HomeController {

    public final ParseFile parseFile;

    public HomeController(ParseFile parseFile) {
        this.parseFile = parseFile;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/")
    public String readFile(@RequestParam("files") MultipartFile[] multipartFileArray, Model model, HttpSession session) throws IOException {

        List<DxfFile> dxfFileList = new ArrayList<>();

        if (session.getAttribute("dxfFileList") != null) {
            dxfFileList = (List<DxfFile>) session.getAttribute("dxfFileList");
        }

        for (MultipartFile multipartFile : multipartFileArray) {
            String regex = ".+\\.(dxf)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(multipartFile.getOriginalFilename());

            if (matcher.matches()) {
                File file = new File(multipartFile.getOriginalFilename());
                multipartFile.transferTo(file);
                DxfFile dxfFile = parseFile.dxfFile(file);
                dxfFileList.add(dxfFile);
                file.delete();
            }
        }

        model.addAttribute("dxfFileList", dxfFileList);
        return "index";
    }

}
