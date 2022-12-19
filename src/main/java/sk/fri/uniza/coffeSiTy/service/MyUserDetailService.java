package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.fri.uniza.coffeSiTy.entity.Role;
import sk.fri.uniza.coffeSiTy.entity.User;
import sk.fri.uniza.coffeSiTy.repository.UserRepo;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        User user = null;

        if (nick != null) {
            user = userRepo.findUserByNick(nick);
        }

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getNick(),
                    user.getPass(),
                    mapRolesToAuthorities(user.getRoles()));
        }else {
            throw new UsernameNotFoundException("Nespravne uzivatelske meno alebo heslo.");
        }
    }
    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
