package pl.dxf.reader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.dxf.reader.entity.User;
import pl.dxf.reader.service.UserServiceImpl;

import javax.validation.Valid;
import javax.validation.Validator;

@Controller
public class UserController {

    private UserServiceImpl userServiceImpl;
    private Validator validator;

    public UserController(UserServiceImpl userServiceImpl, Validator validator) {
        this.userServiceImpl = userServiceImpl;
        this.validator = validator;
    }

    @GetMapping("/register")
    public String addArticle(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping(value = "/register")
    public String saveArticle(@Valid User user, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors() || userServiceImpl.findByEmail(user.getEmail()) != null) {
            return "register";
        } else {
            userServiceImpl.saveUser(user);
        }
        return "redirect:/";
    }
}
