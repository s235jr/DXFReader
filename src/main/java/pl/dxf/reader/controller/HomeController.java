package pl.dxf.reader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@SessionAttributes({"dxfFileList", "firstName", "lastName", "description", "createDate"})
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HomeController {

    @Autowired
    ParseFile parseFile;

    @Value("${images.directory}")
    private String directoryForImages;

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
        addFilesToDxfFileList(multipartFileArray, dxfFileList);
        LocalDateTime createDate = LocalDateTime.now();
        model.addAttribute("createDate", createDate);
        model.addAttribute("dxfFileList", dxfFileList);
        return "index";
    }

    @PostMapping("/clearSession")
    public String clearSession(HttpSession session) {
        if (session.getAttribute("dxfFileList") != null) {
            List<DxfFile> dxfFileList = (List<DxfFile>) session.getAttribute("dxfFileList");
            for (DxfFile dxfFile : dxfFileList) {
                File file = new File(directoryForImages + dxfFile.getNamePng());
                file.delete();
            }
            dxfFileList.clear();
            session.setAttribute("dxfFileList", dxfFileList);
        }
        return "redirect:/";
    }

    @PostMapping("/delDxfFileFromList")
    public String removeDxfFileFromSession(HttpSession session, @RequestParam int del) {
        List<DxfFile> dxfFileList = (List<DxfFile>) session.getAttribute("dxfFileList");
        File file = new File(directoryForImages + dxfFileList.get(del).getNamePng());
        file.delete();
        dxfFileList.remove(del);
        session.setAttribute("dxfFileList", dxfFileList);
        return "redirect:/";
    }

    @PostMapping("/addFooter")
    public String addFooter(Model model, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String description) {
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("description", description);
        LocalDateTime createDate = LocalDateTime.now();
        model.addAttribute("createDate", createDate);
        return "redirect:/";
    }

    private void addFilesToDxfFileList(@RequestParam("files") MultipartFile[] multipartFileArray, List<DxfFile> dxfFileList) throws IOException {
        for (MultipartFile multipartFile : multipartFileArray) {
            String regex = ".+\\.(dxf)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(multipartFile.getOriginalFilename());

            if (matcher.matches()) {
                boolean isOnDxfFileList = false;
                for (DxfFile dxfFile : dxfFileList) {
                    if (dxfFile.getOriginalFileName().equals(multipartFile.getOriginalFilename())) {
                        isOnDxfFileList = true;
                    }
                }
                if (isOnDxfFileList == false) {
                    File file = new File(multipartFile.getOriginalFilename());
                    multipartFile.transferTo(file);
                    DxfFile dxfFile = parseFile.dxfFile(file);
                    dxfFileList.add(dxfFile);
                    file.delete();
                }
            }
        }
    }

}
