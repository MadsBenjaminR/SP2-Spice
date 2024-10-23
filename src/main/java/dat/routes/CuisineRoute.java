package dat.routes;

import dat.controllers.impl.CuisineController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class CuisineRoute {

    private final CuisineController cuisineController = new CuisineController();

    protected EndpointGroup getRoutes() {

        return () -> {
            //Usercontroller skal laves og metoderne skal laves
            post("/", cuisineController::create);
            get("/", cuisineController::read);

        };
    }
}
