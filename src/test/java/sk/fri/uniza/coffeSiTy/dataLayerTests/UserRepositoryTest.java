package sk.fri.uniza.coffeSiTy.dataLayerTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import sk.fri.uniza.coffeSiTy.entity.Address;
import sk.fri.uniza.coffeSiTy.entity.Article;
import sk.fri.uniza.coffeSiTy.entity.User;
import sk.fri.uniza.coffeSiTy.repository.AddressRepo;
import sk.fri.uniza.coffeSiTy.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepo userRepo;

    @AfterEach
    void zmazData() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "user", "district", "region",
                "city","address");
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void testujVratenieZoznamuUserov() {
        int sizeOfList = userRepo.findAll().size();
        Assertions.assertTrue(sizeOfList > 0);
    }
    @Test
    @Sql("classpath:test-data.sql")
    public void testujVymazeniUseraZdb() {
        long idOfDeletedUser = userRepo.deleteUserById(1L);
        Assertions.assertEquals(0, userRepo.findAll().size());
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void testujVratenieUseraPodlaNicku() {
        User user = userRepo.findUserByNick("filip");
        Assertions.assertNotNull(user);
    }
    @Test
    @Sql("classpath:test-data.sql")
    public void testujVratenieUseraPodlaEmailu() {
        User user = userRepo.findUserByEmail("masaryk@azet.com");
        Assertions.assertNotNull(user);
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void testujUpdateMenaUsera() {
        User user = userRepo.findUserByNick("filip");
        user.setName("adam");
        User updatedUser = userRepo.save(user);
        Assertions.assertEquals( "adam",updatedUser.getName());
    }
    @Test
    @Sql("classpath:test-data.sql")
    public void testujVratenieUseraPodlaMenaObsahujucehoTextSa() {
        List<User> ussersMatchnigKeys = userRepo.findUsersByKey("sa");
        Assertions.assertNotNull(ussersMatchnigKeys);
    }
    @Test
    @Sql("classpath:test-data.sql")
    public void testujVratenieUseraPodlaMenaObsahujucehoTextSamue() {
        List<User> ussersMatchnigKeys = userRepo.findUsersByKey("samue");
        Assertions.assertNotNull(ussersMatchnigKeys);
    }

}
