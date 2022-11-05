package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.dto.UserDto;
import sk.fri.uniza.coffeSiTy.entity.Address;
import sk.fri.uniza.coffeSiTy.entity.Role;
import sk.fri.uniza.coffeSiTy.entity.User;
import sk.fri.uniza.coffeSiTy.repository.RoleRepo;
import sk.fri.uniza.coffeSiTy.repository.UserRepo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getListAll() {
        return userRepo.findAll();
    }

    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    public User findUserByNick(String nick) {
        return userRepo.findUserByNick(nick);
    }
    public User findUserById(Long id) {
        return userRepo.findUserById(id);
    }

    public void saveUser(UserDto userDto, Address address) {
        //todo: null podmineka !=
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setNick(userDto.getNick());
        user.setLastName(userDto.getLastName());
        // encrypt the password pozitim spring security
        //bcrypt algoritmus vracia vzdy string o dlzke 60 znakov
        user.setBirthdate(userDto.getBirthdate());
        user.setPass(passwordEncoder.encode(userDto.getPass()));

        //deafutne vsetkych davam ako userov
        Role role = roleRepo.findByName("ROLE_USER");
        List<Role> roles = List.of(role);
        //musi byt pouzity setter na upadate novej role uzivatelovi
        user.setRoles(roles);
        //nastavim adresu nove uzivatela
        user.setAddress(address);
        //ulozim do databazy nove uzivatela
        userRepo.save(user);
    }

    public void updateUser(UserDto userDto) {
        if (userDto != null) {
            User user = this.findUserById(userDto.getId());
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setLastName(userDto.getLastName());
            userRepo.save(user);
        }
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
