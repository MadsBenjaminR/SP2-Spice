package dat.routes;

import dat.controllers.impl.SpiceController;
import dat.daos.SpiceDao;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class SpiceRoute {

    private  SpiceController spiceController;

    public SpiceRoute(EntityManagerFactory emf) {
        spiceController = new SpiceController(new SpiceDao(emf));
    }

    protected EndpointGroup getRoutes() {

        return () -> {
            post("/spice", spiceController::create);
            get("/", spiceController::readAll);
            get("/spice/{id}", spiceController::read);
            //disse skal udvikles:

            get("/search", spiceController::read);
            get("/filter", spiceController::read);
            get("/recommendations", spiceController::read);

            //
            put("/spice/{name}", spiceController::update);
            delete("/spice/{name}", spiceController::delete);
        };
    }
}
