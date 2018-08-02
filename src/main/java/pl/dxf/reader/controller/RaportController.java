package pl.dxf.reader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dxf.reader.entity.Raport;
import pl.dxf.reader.entity.User;
import pl.dxf.reader.parsefile.DxfFile;
import pl.dxf.reader.repository.DxfFileRepository;
import pl.dxf.reader.repository.RaportRepository;
import pl.dxf.reader.repository.StatusRepository;
import pl.dxf.reader.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
public class RaportController {

    @Autowired
    RaportRepository raportRepository;

    @Autowired
    DxfFileRepository dxfFileRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/saveRaport")
    public String createRaport(HttpSession session, @RequestParam String description) {
        Raport raport = new Raport();
        raportRepository.save(raport);
        Long lastId = raportRepository.getLastId();
        raport = raportRepository.findOne(lastId);
        List<DxfFile> dxfFileList = (List<DxfFile>) session.getAttribute("dxfFileList");
        for(DxfFile dxfFile : dxfFileList) {
            dxfFileRepository.save(dxfFile);
        }
        raport.setCreatedDate(new Date(System.currentTimeMillis()));
        raport.setStatus(statusRepository.findOne(1L));
        User user =(User) session.getAttribute("user");
        System.out.println(user);
        raport.setUser(user);
        raport.setDescription(description);
        raportRepository.save(raport);
        dxfFileList.clear();
        session.setAttribute("dxfFileList", dxfFileList);
        return "redirect:/";

    }

}
