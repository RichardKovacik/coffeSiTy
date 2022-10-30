package sk.fri.uniza.coffeSiTy.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping("/profil")
    public String showProfilDetails(Model model) {
        //zistim usernam aktualne prihlaseneho uzivatela
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = new UserDto();
        User user = userService.findUserByNick(auth.getName());
        if (user != null) {
            userDto.setId(user.getId());
            userDto.setEmail(user.getEmail());
            userDto.setName(user.getName());
            userDto.setNick(user.getNick());
            userDto.setLastName(user.getLastName());
        }
        model.addAttribute("user", userDto);
        return "profile";
    }

    @PostMapping("/profil/zmen")
    public String updateProfilDetails(@Valid @ModelAttribute("user") UserDto userDto,
                                      BindingResult result,
                                      Model model) {
        userService.updateUser(userDto);
        //todo: validacia editu profilu

        return "redirect:/profil?success";
    }
}
