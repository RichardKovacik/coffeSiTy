package sk.fri.uniza.coffeSiTy.dataLayerTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import sk.fri.uniza.coffeSiTy.entity.User;
import sk.fri.uniza.coffeSiTy.repository.UserRepo;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRepo userRepo;

    @AfterEach
    void zmazData() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "user", "district", "region",
                "city","address");
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void testujVratenieZoznamuUserov() {
        int sizeOfList = userRepo.findAll().size();
        Assertions.assertEquals(2, sizeOfList);
    }
    @Test
    @Sql("classpath:test-data.sql")
    public void testujVymazeniUseraSId1Zdb() {
        long idOfDeletedUser = userRepo.deleteUserById(1L);
        Assertions.assertEquals(1, idOfDeletedUser);
    }

    @ParameterizedTest
    @ValueSource(strings = {"filip", "lukas3"})
    @Sql("classpath:test-data.sql")
    public void testujVratenieUseraPodlaNicku(String nick) {
        User user = userRepo.findUserByNick(nick);
        Assertions.assertNotNull(user);
    }
    @ParameterizedTest
    @ValueSource(strings = {"masaryk@azet.com", "lukas@azet.com"})
    @Sql("classpath:test-data.sql")
    public void testujVratenieUseraPodlaEmailu(String email) {
        User user = userRepo.findUserByEmail(email);
        Assertions.assertNotNull(user);
    }
    @ParameterizedTest
    @ValueSource(strings = {"samu","sa", "samuel","s","sa","sam"})
    @Sql("classpath:test-data.sql")
    public void testujVratenieZoznamuUserovPodlaMenaObsahujucehoText(String input) {
        List<User> ussersMatchnigKeys = userRepo.findUsersByKey(input);
        Assertions.assertNotNull(ussersMatchnigKeys);
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void testujZmenuMenaUsera() {
        User user = userRepo.findUserByNick("filip");
        user.setName("adam");
        User updatedUser = userRepo.save(user);
        Assertions.assertEquals( "adam",updatedUser.getName());
    }
}
