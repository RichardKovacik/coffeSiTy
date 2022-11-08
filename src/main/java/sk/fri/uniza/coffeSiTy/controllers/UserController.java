package sk.fri.uniza.coffeSiTy.controllers;

import com.google.gson.Gson;
import com.sun.xml.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.fri.uniza.coffeSiTy.controllerHelper.ControllerHelper;
import sk.fri.uniza.coffeSiTy.dto.AddressDto;
import sk.fri.uniza.coffeSiTy.dto.UserDto;
import sk.fri.uniza.coffeSiTy.entity.*;
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
        //najskor su dostupne len kraje(districts)
        model.addAttribute("districts",addressService.getAllDistricts());
        model.addAttribute("title", "Registracia pouzivatela");
        return "registration";
    }

    @Transactional
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             Model model) {
        System.out.println(id);

        userService.deleteUserById(id);


        List<User> listUsers = userService.getListAll();
        model.addAttribute("list", listUsers);


//        if (!((long) id)){
//
//        }
//        if (result.hasErrors()){
//            System.out.println(result.getAllErrors());
//        }



//        try {
//            service.vymazZamestnanca(id);
//            ra.addFlashAttribute("sprava", "Zamestananec bol uspesne odstraneny");
//        } catch (ZamestnanecNotFoundExcption e) {
//            ra.addFlashAttribute("exp", e.getMessage());
//        }
        return "redirect:/users?success";
    }
    @RequestMapping(value = "/regions", method = RequestMethod.GET)
    public @ResponseBody List<Region> getRegionsFromDist(
            @RequestParam(value = "districtId") Long districId) {
        return addressService.getAllRegionsFromDistrict(districId);
    }

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public @ResponseBody List<City> getCitiesFromRegion(
            @RequestParam(value = "regionId")  Long regionId) {
        return addressService.getAllCitiesFromRegion(regionId);
    }

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
        //tu je id mesta
        Long cityId = userDto.getAddressDto().getCityId();
        System.out.println(cityId);

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

//        if (userDto.getAddressDto().getPsc())

        if (result.hasErrors()) {
            model.addAttribute("districts",addressService.getAllDistricts());
            model.addAttribute("user", userDto);
            model.addAttribute("title", "Registracia pouzivatela");
            return "/registration";
        }
        //najskor ulozim adresu so city id
        //todo: null exep
         Address address = addressService.saveAdress(userDto.getAddressDto(), cityId);
        //ulozim si usera s danou adresou
        userService.saveUser(userDto, address);
        logger.info("Novy user zaregistrovany");

        return "redirect:/registracia?success";
    }
}
