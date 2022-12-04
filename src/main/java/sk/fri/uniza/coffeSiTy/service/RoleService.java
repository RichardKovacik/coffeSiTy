package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.entity.Role;
import sk.fri.uniza.coffeSiTy.repository.RoleRepo;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public Role findRoleByName(String name){
        if (name !=null) {
            roleRepo.findRoleByName(name);
        }
        return null;
    }
}
