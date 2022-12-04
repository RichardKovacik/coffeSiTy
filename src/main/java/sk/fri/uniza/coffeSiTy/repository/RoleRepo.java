package sk.fri.uniza.coffeSiTy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sk.fri.uniza.coffeSiTy.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name like ?1")
    Role findRoleByName(String name);
}
