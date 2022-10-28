package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.dto.UserDto;
import sk.fri.uniza.coffeSiTy.entity.User;
import sk.fri.uniza.coffeSiTy.repository.UserRepo;

import javax.management.relation.Role;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

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
        // encrypt the password using spring security
     //   user.setPassword(passwordEncoder.encode(userDto.getPassword()));

//        Role role = roleRepository.findByName("ROLE_ADMIN");
//        if(role == null){
//            role = checkRoleExist();
//        }
//        user.setRoles(Arrays.asList(role));
        userRepo.save(user);
    }
}
