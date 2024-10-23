package dat.controllers.impl;

import dat.controllers.IController;
import dat.daos.FavoriteDao;
import dat.dtos.FavoriteDTO;
import dat.dtos.SpiceDTO;
import dat.entities.Favorite;
import dat.entities.Spice;
import dat.security.exceptions.ApiException;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class FavoriteController implements IController<FavoriteDTO, Integer> {

    private final Logger log = LoggerFactory.getLogger(FavoriteController.class);
    private FavoriteDao favoriteDao;
    @Override
    public void read(Context ctx) {
        try {
            int id = ctx.pathParamAsClass("id", Integer.class).get();

            FavoriteDTO favoriteDTO = favoriteDao.read(id);
            ctx.res().setStatus(200);
            ctx.json(favoriteDTO, FavoriteDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }

    }

    @Override
    public void readAll(Context ctx) {
        try {
            List<FavoriteDTO> favoriteDTOS = favoriteDao.readAll();
            // response
            ctx.res().setStatus(200);
            ctx.json(favoriteDTOS, FavoriteDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void create(Context ctx) {
        try {

            SpiceDTO spiceDTO = ctx.bodyAsClass(SpiceDTO.class);
            ctx.status(HttpStatus.CREATED);
            ctx.json(favoriteDao.create(spiceDTO));

            // == response ==
            ctx.res().setStatus(201);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void update(Context ctx) {
        try {
            long id = Long.parseLong(ctx.pathParam("id"));
            FavoriteDTO favoriteDTO = ctx.bodyAsClass(FavoriteDTO.class);

            // == querying ==
            int favoriteId = ctx.pathParamAsClass("id", Integer.class).get();
            favoriteDao.update(favoriteId, favoriteDTO);

            // == response ==
            ctx.res().setStatus(200);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void delete(Context ctx) {
        try {
            int id = ctx.pathParamAsClass("id", Integer.class).get();
            // entity
            favoriteDao.delete(id);
            // response
            ctx.res().setStatus(204);
        } catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

}
