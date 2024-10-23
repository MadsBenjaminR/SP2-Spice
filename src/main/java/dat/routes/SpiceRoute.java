package dat.routes;

import dat.controllers.impl.SpiceController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class SpiceRoute {

    private final SpiceController spiceController = new SpiceController();

    protected EndpointGroup getRoutes() {

        return () -> {
            post("/spice", spiceController::create);
            get("/", spiceController::readAll);
            get("/spice/{id}", spiceController::read);
            //disse skal udvikles:
            get("/search", spiceController::read);
            get("/filter", spiceController::read);
            get("/recommendations}", spiceController::read);
            //
            put("/spice/{id}", spiceController::update);
            delete("/spice/{id}", spiceController::delete);
        };
    }
}
