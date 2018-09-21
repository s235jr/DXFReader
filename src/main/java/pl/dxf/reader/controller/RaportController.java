package pl.dxf.reader.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dxf.reader.CurrentUser;
import pl.dxf.reader.entity.Raport;
import pl.dxf.reader.entity.Status;
import pl.dxf.reader.entity.User;
import pl.dxf.reader.parsefile.DxfFile;
import pl.dxf.reader.repository.DxfFileRepository;
import pl.dxf.reader.repository.RaportRepository;
import pl.dxf.reader.repository.StatusRepository;
import pl.dxf.reader.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.util.List;

@Controller
@Secured("ROLE_USER")
public class RaportController {

    @Value("${imgdirectory}")
    private String directoryForImages;

    private RaportRepository raportRepository;
    private StatusRepository statusRepository;
    private UserRepository userRepository;
    private DxfFileRepository dxfFileRepository;

    public RaportController(RaportRepository raportRepository, StatusRepository statusRepository,
                            UserRepository userRepository, DxfFileRepository dxfFileRepository) {
        this.raportRepository = raportRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
        this.dxfFileRepository = dxfFileRepository;
    }

    @ModelAttribute("status")
    public List<Status> getStatus() {
        return statusRepository.findAll();
    }

    @ModelAttribute("users")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @PostMapping("/saveRaport")
    public String createRaport(HttpSession session, @RequestParam String description, @AuthenticationPrincipal CurrentUser currentUser) {
        List<DxfFile> dxfFileList = (List<DxfFile>) session.getAttribute("dxfFileList");
        Raport raport = new Raport();
        raport.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        raport.setStatus(statusRepository.getOne(1L));
        raport.setUser(currentUser.getUser());
        raport.setDescription(description);
        raport.setNumberOfDxfFile(dxfFileList.size());
        raportRepository.save(raport);
        Long lastId = raportRepository.getLastId();
        raport = raportRepository.getOne(lastId);
        for (DxfFile dxfFile : dxfFileList) {
            dxfFile.setRaport(raport);
            dxfFileRepository.save(dxfFile);
        }
        dxfFileList.clear();
        session.setAttribute("dxfFileList", dxfFileList);
        return "redirect:/";
    }

    @GetMapping("/showMyRaports")
    public String showMyRaports(HttpSession session, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Raport> raportByUserId = raportRepository.findRaportByUserId(currentUser.getUser().getId());
        session.setAttribute("raports", raportByUserId);
        return "raportsList";
    }

    @GetMapping("/showRaport")
    public String showRaport(@RequestParam long id, HttpSession session) {
        List<DxfFile> dxfFileByRaportId = dxfFileRepository.findDxfFileByRaportId(id);
        session.setAttribute("dxfFileRaport", dxfFileByRaportId);
        Raport raport = raportRepository.getOne(id);
        session.setAttribute("raport", raport);
        return "raports";
    }

    @GetMapping("/editRaport")
    public String editRaport(@RequestParam long idEditRaport, HttpSession session, Model model) {
        List<DxfFile> dxfFileByRaportId = dxfFileRepository.findDxfFileByRaportId(idEditRaport);
        session.setAttribute("dxfFileRaportEdit", dxfFileByRaportId);
        Raport raport = raportRepository.getOne(idEditRaport);
        session.setAttribute("raport", raport);
        model.addAttribute("raport", raport);
        return "raportsEdit";
    }

    @PostMapping("/acceptEdit")
    public String acceptEdit(@ModelAttribute Raport raport) {
        Raport raportDB = raportRepository.getOne(raport.getId());
        raportDB.setDescription(raport.getDescription());
        raportDB.setStatus(raport.getStatus());
        raportDB.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        raportRepository.save(raportDB);
        return "redirect:/showMyRaports";
    }

    @PostMapping("/delRaport")
    public String delRaport(@RequestParam long idDelRaport) {
        List<DxfFile> dxfFileByRaportId = dxfFileRepository.findDxfFileByRaportId(idDelRaport);
        if (dxfFileByRaportId != null || !dxfFileByRaportId.isEmpty()) {
            for (DxfFile dxfFile : dxfFileByRaportId) {
                File file = new File(directoryForImages + dxfFile.getNamePng());
                file.delete();
                dxfFileRepository.delete(dxfFile);
            }
        }
        raportRepository.deleteById(idDelRaport);
        return "redirect:/showMyRaports";
    }

    @PostMapping("/delDxfFileFromRaport")
    public String delDxfFileFromRaport(@RequestParam long del, HttpSession session) {

        DxfFile dxfFile = dxfFileRepository.getOne(del);
        File file = new File(directoryForImages + dxfFile.getNamePng());
        file.delete();
        dxfFileRepository.delete(dxfFile);
        Raport raport = (Raport) session.getAttribute("raport");
        List<DxfFile> dxfFileByRaportId = dxfFileRepository.findDxfFileByRaportId(raport.getId());
        raport.setNumberOfDxfFile(dxfFileByRaportId.size());
        raport.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        raportRepository.save(raport);
        session.setAttribute("raport", raport);
        session.setAttribute("dxfFileRaport", dxfFileByRaportId);
        return "redirect:/editRaport?idEditRaport=" + dxfFile.getRaport().getId();
    }

}
