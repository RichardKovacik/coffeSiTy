package sk.fri.uniza.coffeSiTy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import sk.fri.uniza.coffeSiTy.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findUserByNick(String nick);
    User findUserById(Long id);

    @Modifying
    long deleteUserById(Long id);
}
