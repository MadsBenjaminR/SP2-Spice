package dat.controllers.impl;

import dat.controllers.IController;
import dat.dtos.SpiceDTO;
import dat.entities.Spice;
import dat.security.exceptions.ApiException;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.BiFunction;

/**
*Purpose: 
* @author: Jeppe Koch
*/
public class SpiceController implements IController<SpiceDTO, Integer> {

    private final Logger log = LoggerFactory.getLogger(SpiceController.class);


    @Override
    public void read(Context ctx) {
        try {
            int id = ctx.pathParamAsClass("id", Integer.class).get();

            SpiceDTO spiceDTO = spiceDao.read(id);
            ctx.res().setStatus(200);
            ctx.json(spiceDTO, SpiceDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }

    }

    @Override
    public void readAll(Context ctx) {
        try {
            List<SpiceDTO> spiceDTOS = dao.readAll();
            // response
            ctx.res().setStatus(200);
            ctx.json(spiceDTOS, SpiceDTO.class);
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
            ctx.json(spiceDao.create(spiceDTO));

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
            SpiceDTO spiceDTO = ctx.bodyAsClass(SpiceDTO.class);

            // == querying ==
            Spice spice = spiceDao.getById(id);
            spiceDao.update(spice, new Spice(spiceDTO));

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
            spiceDao.delete(id);
            // response
            ctx.res().setStatus(204);
        } catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }



}
