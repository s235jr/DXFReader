package pl.dxf.reader.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.dxf.reader.Utils;
import pl.dxf.reader.entity.Token;
import pl.dxf.reader.entity.User;
import pl.dxf.reader.repository.TokenRepository;
import pl.dxf.reader.service.UserServiceImpl;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.Validator;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static pl.dxf.reader.entity.Token.Action.ACTIVATEACC;
import static pl.dxf.reader.entity.Token.Action.RESETPASS;

@Controller
@Transactional
public class UserController {

    @Value("${host}")
    public String host;

    private UserServiceImpl userService;
    private Validator validator;
    private TokenRepository tokenRepository;
    private Utils utils;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, Validator validator, TokenRepository tokenRepository, Utils utils) {
        this.userService = userServiceImpl;
        this.validator = validator;
        this.tokenRepository = tokenRepository;
        this.utils = utils;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerUser(@Valid User user, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors() || userService.findByEmail(user.getEmail()) != null) {
            return "register";
        } else {

            user.setEnabled(0);
            userService.saveUser(user);
            Token token = new Token();
            token.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            token.setUser(user);
            token.setAction(ACTIVATEACC);
            token.setValue(RandomStringUtils.random(50, true, true));
            tokenRepository.save(token); //todo powtarzający się token
            String body = "Aby aktywować swoje konto kliknij w link: " + host + token.getValue();
            String title = "Aktywacja konta w aplikacji DxfReader";
            utils.sendEmail(token, title, body);
        }
        return "redirect:/";
    }

    @GetMapping("/forgotpassword")
    public String forgotPassword(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "forgotPassword";
    }

    @PostMapping("/forgotpassword")
    public String sendTokenToResetPassword(String email) {

        User user = userService.findByEmail(email);

        if (user == null) {
            System.out.println("ddddddddddddddddd");
            return "forgotPassword";
        } else {

            Token token = new Token();
            token.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            token.setUser(user);
            token.setAction(RESETPASS);
            token.setValue(RandomStringUtils.random(50, true, true));
            tokenRepository.save(token); //todo powtarzający się token
            String body = "Aby zresetować swoje hasło kliknij w link: " + host + token.getValue();
            String title = "Prośba o zresetowanie hasła";
            utils.sendEmail(token, title, body);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/token/{token}")
    public String activateAcc(@PathVariable String token) {

        Token foundToken = tokenRepository.findByValue(token);

        Timestamp createdToken = foundToken.getCreatedDate();
        LocalDateTime createdTokenLDT = createdToken.toLocalDateTime();
        Timestamp actualTime = new Timestamp(System.currentTimeMillis());
        LocalDateTime actualTimeLDT = actualTime.toLocalDateTime();

        if (ChronoUnit.HOURS.between(createdTokenLDT, actualTimeLDT) < 24 && foundToken.getAction() == ACTIVATEACC) {
            foundToken.getUser().setEnabled(1);
            tokenRepository.delete(foundToken);
        } else if (ChronoUnit.HOURS.between(createdTokenLDT, actualTimeLDT) < 1 && foundToken.getAction() == RESETPASS) {
            String randomPass = RandomStringUtils.random(16, true, true);
            foundToken.getUser().setPassword(randomPass);
            userService.saveUser(foundToken.getUser());
            tokenRepository.delete(foundToken);
            String body = "Twoje nowe hasło to: " + randomPass;
            String title = "Twoje nowe hasło";
            utils.sendEmail(foundToken, title, body);
            return "redirect:/"; //todo z atrybutami
        } else {
            System.out.println("Token nie istnieje lub jest nieważny.");
        }
        return "redirect:/";
    }
}

