package dat.routes;

import dat.controllers.impl.FavoriteController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class FavoriteRoute {

    private final FavoriteController favoriteController = new FavoriteController();

    protected EndpointGroup getRoutes() {

        return () -> {
            post("/{userId}/favorites", favoriteController::create);
            get("/{userId}/favorites", favoriteController::read);
            delete("/{userId}/favorites/{spiceId}", favoriteController::delete);
        };
    }
}
