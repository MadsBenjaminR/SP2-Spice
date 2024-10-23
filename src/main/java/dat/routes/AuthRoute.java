package dat.routes;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.post;

public class AuthRoute {


    private final AuthController authController = new AuthController();

    protected EndpointGroup getRoutes() {

        return () -> {
            //Usercontroller skal laves og metoderne skal laves
            post("/auth/login", authController::login);
            post("/auth/register", authController::register);
            post("/auth/logout", authController::logout);

        };
    }


}
