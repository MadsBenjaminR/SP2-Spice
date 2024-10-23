package dat.daos;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.dtos.CuisineDTO;
import dat.dtos.FavoriteDTO;
import dat.dtos.SpiceDTO;
import dat.dtos.UserDTO;
import dat.entities.Cuisine;
import dat.entities.Favorite;
import dat.entities.Spice;
import dat.security.entities.User;
import io.javalin.Javalin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CuisineDaoTest {
    private static Javalin app;
    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private static final CuisineDao cuisineDao = new CuisineDao(emf);
    private static final String BASE_URL = "http://localhost:7070/api/";

    private static CuisineDTO c1, c2, c3;
    private static FavoriteDTO f1, f2, f3;
    private static SpiceDTO spice1, spice2, spice3;
    private static Set<CuisineDTO> cuisineSet = new HashSet<>();
    private static Set<FavoriteDTO> favoriteSet = new HashSet<>();
    private static Set<SpiceDTO> spiceSet = new HashSet<>();
    private static UserDTO user1;

    @BeforeAll
    static void setupAll(){
        app = ApplicationConfig.startServer(7080);
    }

    @BeforeEach
    void setup(){
        user1 = new UserDTO(1L, "admim", "admin" );

        spice1 = new SpiceDTO(1L, "Spidskommen", "jord", "earth");
        spice2 = new SpiceDTO(2L, "Garam massala", "jord", "earth");
        spice3 = new SpiceDTO(3L, "Oregano", "jord", "earth");

        spiceSet.add(spice1);
        spiceSet.add(spice2);
        spiceSet.add(spice3);

        f1 = new FavoriteDTO(1L, user1.getId(), "favorites", favoriteSet);
        f2 = new FavoriteDTO(2L,user1.getId(), "favorites", favoriteSet);
        f3 = new FavoriteDTO(3L, user1.getId(),"favorites", favoriteSet);

        favoriteSet.add(f1);
        favoriteSet.add(f2);
        favoriteSet.add(f3);


        c1 = new CuisineDTO(1L, "indian", "unique and powerful mix", "Earth", spiceSet);
        c2 = new CuisineDTO(2L, "indian2", "unique and powerful mix", "Earth", spiceSet);
        c3 = new CuisineDTO(3L, "indian3", "unique and powerful mix", "Earth", spiceSet);
        cuisineDao.create(c1);
        cuisineDao.create(c2);
        cuisineDao.create(c3);

    }

    @AfterEach
    void tearDown() {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Cuisine ").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE cuisine_cuisine_id_seq RESTART WITH 1").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDownAll(){
        ApplicationConfig.stopServer(app);
    }


    @Test
    void read() {
        CuisineDTO cuisineDTO = new CuisineDTO(4L, "Thai", "Spicy and flavorful", "Hot", spiceSet);
        cuisineDao.create(cuisineDTO);
        CuisineDTO readCuisine = cuisineDao.read(cuisineDTO.getId());

        assertEquals(cuisineDTO.getId(), readCuisine.getId());


    }

    @Test
    void readAll() {
        int actual = cuisineDao.readAll().size();
        int expected = cuisineSet.size();
        assertEquals(3, actual);
    }

    @Test
    void create() {
        CuisineDTO createdCuisine = cuisineDao.create(c1);
        assertEquals(createdCuisine.getId(), c1.getId());


        assertEquals(4, cuisineSet.size());

    }

    @Test
    void delete() {
        CuisineDTO cuisineToDelete = cuisineDao.read(1L);
        cuisineDao.delete(cuisineToDelete.getId());
        List<CuisineDTO> allCuisines = cuisineDao.readAll();
        assertEquals(2, allCuisines.size());

    }

    @Test
    void update() {
        c1.setName("update");
        cuisineDao.update(c1.getId(), c1);
        CuisineDTO updated = cuisineDao.read(c1.getId());
        assertEquals("update", updated.getName());
    }
}