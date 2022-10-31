package sk.fri.uniza.coffeSiTy.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.fri.uniza.coffeSiTy.service.AddressService;

@Controller
public class HomePageController {
    @Autowired
    AddressService addressService;
    @RequestMapping("/")
    public String home() {
//        System.out.println(addressService.getAllRegionsFromDistrict(2L).size());;
        return "index";
    }

}
