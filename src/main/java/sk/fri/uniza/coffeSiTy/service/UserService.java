package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.dto.UserDto;
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

    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setNick(userDto.getNick());
        user.setLastName(userDto.getLastName());
        // encrypt the password pozitim spring security
        //bcrypt algoritmus vracia vzdy string o dlzke 60 znakov
        user.setBirthdate(userDto.getBirthdate());
        user.setPass(passwordEncoder.encode(userDto.getPass()));

        Role role = roleRepo.findByName("ROLE_USER");
      //  Role role2 = roleRepo.findByName("ROLE_ADMIN");

//        if(role == null){
//            role = checkRoleExist();
//        }
        List<Role> roles = List.of(role);
       // user.getRoles().add(role);
        //musi byt pouzity setter na pridanie novej role uzivatelovi
        user.setRoles(roles);
        userRepo.save(user);
    }
}
