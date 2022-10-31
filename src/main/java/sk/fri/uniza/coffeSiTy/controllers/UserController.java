package sk.fri.uniza.coffeSiTy.controllers;

import com.google.gson.Gson;
import com.sun.xml.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.fri.uniza.coffeSiTy.controllerHelper.ControllerHelper;
import sk.fri.uniza.coffeSiTy.dto.UserDto;
import sk.fri.uniza.coffeSiTy.entity.District;
import sk.fri.uniza.coffeSiTy.entity.Region;
import sk.fri.uniza.coffeSiTy.entity.User;
import sk.fri.uniza.coffeSiTy.service.AddressService;
import sk.fri.uniza.coffeSiTy.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/registracia")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("districts",addressService.getAllDistricts());
        model.addAttribute("title", "Registracia pouzivatela");
        return "registration";
    }
    @RequestMapping(value = "/regions", method = RequestMethod.GET)
    public @ResponseBody List<Region> findAllAgencies(
            @RequestParam(value = "districtId", required = true) Long districId) {
        System.out.println("totttok");
      //  District district = addressService.findDistrictByID(districId);
        return addressService.getAllRegionsFromDistrict(districId);
    }

//    @ResponseBody
//    @RequestMapping(value = "loadStatesByCountry/{id}", method = RequestMethod.GET)
//    public String loadStatesByCountry(@PathVariable("id") Long id) {
//        Gson gson = new Gson();
//        return gson.toJson(addressService.getAllRegionsFromDistrict(id));
//    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        //   model.addAttribute("user", new UserDto());
        model.addAttribute("title", "Prihlasenie pouzivatela");
        return "login";
    }

    //todo: optimalozvat danu metodu
    @GetMapping(value = "/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.getListAll();
        model.addAttribute("list", listUsers);
        return "users";
    }

    @PostMapping("/registracia/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = null;
        System.out.println("-----------------");
        System.out.println(userDto.getLastName());

        if (userDto.getEmail() != null && !userDto.getEmail().isEmpty()) {
            existingUser = userService.findUserByEmail(userDto.getEmail());
        }
        //nato spravim nejake metody este, optimalizacia do buducna

        //pokial emailova adresa je prirade uz k inemu userovi
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            logger.error("Email uz pouziva iny uzivatel");
            result.rejectValue("email", null,
                    "Tato emailova adresa uz existuje");
        }

        if (userDto.getNick() != null && !userDto.getNick().isEmpty()) {
            existingUser = userService.findUserByNick(userDto.getNick());
        }

        //taktiez nick musi byt pre kazdeho uzivatela unikatny
        if (existingUser != null && existingUser.getNick() != null && !existingUser.getNick().isEmpty()) {
            logger.error("Nickname uz pouziva iny uzivatel");
            result.rejectValue("nick", null,
                    "Tento nickname pouziva uz iny uzivatel");
        }

        if (existingUser == null && !ControllerHelper.isValidAge(userDto.getBirthdate())) {
            logger.error("Neplatny datum narodenia(msi byt len v rozmedzi 18 az 100 rokov)");
            result.rejectValue("birthdate", null,
                    "Neplatny datum narodenia");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            model.addAttribute("title", "Registracia pouzivatela");
            return "/registration";
        }

        userService.saveUser(userDto);
        logger.info("Novy user zaregistrovany");

        return "redirect:/registracia?success";
    }
}
