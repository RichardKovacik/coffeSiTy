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
import sk.fri.uniza.coffeSiTy.dto.AddressDto;
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
        AddressDto addressDto = new AddressDto();
        User user = userService.findUserByNick(auth.getName());
        if (user != null) {
            userDto.setId(user.getId());
            userDto.setEmail(user.getEmail());
            userDto.setName(user.getName());
            userDto.setNick(user.getNick());
            userDto.setLastName(user.getLastName());
            //hack maly :)
            //todo: potrebujem do buducna spravit UserEditDto aby som nemapoval zbytocne vela hodnot
            userDto.setPass("0000");
            userDto.setBirthdate(user.getBirthdate());
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setPsc(user.getAddress().getPsc());
            addressDto.setCityId(user.getAddress().getCity().getId());
            userDto.setAddressDto(addressDto);
        }
        model.addAttribute("user", userDto);
        return "profile";
    }

    @PostMapping("/profil/zmen")
    public String updateProfilDetails(@Valid @ModelAttribute("user") UserDto userDto,
                                      BindingResult result,
                                      Model model) {
        //zistim ci s s touto emailovou adrsou uz nexistuje user
//        System.out.println("pass"+userDto.getPass());
//        System.out.println(userDto.getAddressDto().getPsc());
        User existingUser = userService.findUserByEmail(userDto.getEmail());

//        pokial emailova adresa je prirade uz k inemu userovi
        if (existingUser != null && !existingUser.getNick().equals(userDto.getNick()) && existingUser.getEmail() != null
                && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "Tuto emailovu adresu uz pouziva iny uzivatel");
        }

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            model.addAttribute("user", userDto);
            return "/profile";
        }

        System.out.println("bez chyb");
        userService.updateUser(userDto);
        return "redirect:/profil?success";
    }
}
