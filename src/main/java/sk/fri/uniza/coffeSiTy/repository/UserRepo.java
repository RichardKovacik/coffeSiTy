package sk.fri.uniza.coffeSiTy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sk.fri.uniza.coffeSiTy.entity.User;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findUserByNick(String nick);
    User findUserById(Long id);

    @Modifying
    long deleteUserById(Long id);

    /**
     *
     * @param key parameter kotry ocakava meno alebo nejaky string kotry sa nachadza v mene
     * @return zoznam pouzivatlov kotrych meno obsahuje danu znakovu reprezenrtaciu z parametra
     */
    @Query("select u from User u where u.name like %?1%")
    List<User> findUsersByKey(String key);
}
