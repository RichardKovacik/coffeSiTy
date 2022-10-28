package sk.fri.uniza.coffeSiTy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.fri.uniza.coffeSiTy.dto.UserDto;
import sk.fri.uniza.coffeSiTy.entity.User;
import sk.fri.uniza.coffeSiTy.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/registracia")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new UserDto());
        model.addAttribute("title", "Registracia pouzivatela");
        return "registration";
    }
    @GetMapping("/login")
    public String showLoginPage(Model model){
     //   model.addAttribute("user", new UserDto());
        model.addAttribute("title", "Prihlasenie pouzivatela");
        return "login";
    }

    @GetMapping(value = "/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.getListAll();
        model.addAttribute("list", listUsers);
        return "users";
    }

    @PostMapping("/registracia/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = null;

        if (userDto.getEmail() != null && !userDto.getEmail().isEmpty()) {
            existingUser = userService.findUserByEmail(userDto.getEmail());
        }
        //nato spravim nejake metody este, optimalizacia do buducna

        //pokial emailova adresa je prirade uz k inemu userovi
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "Tato emailova adresa uz existuje");
        }

        if (userDto.getNick() != null && !userDto.getNick().isEmpty()) {
            existingUser = userService.findUserByNick(userDto.getNick());
        }

        //taktiez nick musi byt pre kazdeho uzivatela unikatny
        if(existingUser != null && existingUser.getNick() != null && !existingUser.getNick().isEmpty()){
            result.rejectValue("nick", null,
                    "Tento nickname pouziva uz iny uzivatel");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/registration";
        }

        userService.saveUser(userDto);
        return "/registrationSucc";
    }
}
