package dat.routes;

import dat.config.HibernateConfig;
import dat.daos.SpiceDao;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import dat.dtos.SpiceDTO;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class SpiceRoutesTest {

    private static Javalin app;
    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private static final SpiceDao spiceDao = new SpiceDao(emf);
    private static final String BASE_URL = "http://localhost:7080/api/";

    private static SpiceDto s1, s2, s3;

}
