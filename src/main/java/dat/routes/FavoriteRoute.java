package dat.routes;

import dat.controllers.impl.FavoriteController;
import dat.controllers.impl.SpiceController;
import dat.daos.FavoriteDao;
import dat.daos.SpiceDao;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class FavoriteRoute {

    private final FavoriteController favoriteController;

    public FavoriteRoute(EntityManagerFactory emf) {
        favoriteController = new FavoriteController(new FavoriteDao(emf));
    }


    protected EndpointGroup getRoutes() {

        return () -> {
            post("/{userId}/favorites", favoriteController::create);
            get("/{userId}/favorites", favoriteController::read);
            delete("/{userId}/favorites/{spiceId}", favoriteController::delete);
        };
    }
}
