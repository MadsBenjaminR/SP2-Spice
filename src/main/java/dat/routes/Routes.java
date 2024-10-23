package dat.routes;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {


    private final SpiceRoute spiceRoute = new SpiceRoute();
    private final AuthRoute authRoute = new AuthRoute();
    private final FavoriteRoute favoriteRoute = new FavoriteRoute();
    private final CuisineRoute cuisineRoute = new CuisineRoute();

    public EndpointGroup getRoutes() {
        return () -> {
            path("/spices", spiceRoute.getRoutes());
            path("/auth", authRoute.getRoutes());
            //fordi vi finder users favorite spices og cuisines
            path("/users", favoriteRoute.getRoutes());
            path("/cousines", cuisineRoute.getRoutes());
        };
    }
}
