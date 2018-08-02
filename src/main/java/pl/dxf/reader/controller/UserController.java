package pl.dxf.reader.controller;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.dxf.reader.entity.User;
import pl.dxf.reader.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.Validator;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Validator validator;

    @RequestMapping("/addUser")
    public String addArticle(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "userForm";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String saveArticle(@Valid User user, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "userForm";
        }
        if (userRepository.findByEmail(user.getEmail()) !=null){
            return "userForm";
        }

        user.setPasswordCrypt(user.getPassword());
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "loginForm";
    }

    @PostMapping("/login")
    public String checkEmailAndPass(@ModelAttribute User user, HttpSession session) {
        User u = userRepository.findByEmail(user.getEmail());
        if (u == null) {
            return "loginForm";
        }

        String passFromDB = u.getPassword();
        String salt = passFromDB.substring(0, 29);
        String cryptPassFromUser = BCrypt.hashpw(user.getPassword(), salt);

        if (passFromDB.equals(cryptPassFromUser)) {
            session.setAttribute("user", u);
            return "redirect:/";
        } else {
            return "loginForm";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }


}
