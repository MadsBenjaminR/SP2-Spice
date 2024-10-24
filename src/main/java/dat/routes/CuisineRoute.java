package dat.routes;

import dat.controllers.impl.CuisineController;
import dat.controllers.impl.SpiceController;
import dat.daos.CuisineDao;
import dat.daos.SpiceDao;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class CuisineRoute {

    private final CuisineController cuisineController;

    public CuisineRoute(EntityManagerFactory emf) {
        cuisineController = new CuisineController(new CuisineDao(emf));
    }


    protected EndpointGroup getRoutes() {

        return () -> {
            //Usercontroller skal laves og metoderne skal laves
            post("/", cuisineController::create);
            get("/", cuisineController::read);

        };
    }
}
