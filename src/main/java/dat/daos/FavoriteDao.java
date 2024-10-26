package dat.daos;

import dat.dtos.CuisineDTO;
import dat.dtos.FavoriteDTO;
import dat.dtos.SpiceDTO;
import dat.dtos.UserDTO;
import dat.entities.Cuisine;
import dat.entities.Favorite;
import dat.entities.Spice;
import dat.security.entities.User;
import dat.security.exceptions.ApiException;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Set;

/**
 * @author laith kaseb
 **/


public class FavoriteDao {

    private static EntityManagerFactory emf;

    public FavoriteDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public FavoriteDTO read(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            Favorite favorite = em.find(Favorite.class, id);
            return favorite != null ? new FavoriteDTO(favorite) : null;
        }
    }

    public List<FavoriteDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<FavoriteDTO> query = em.createQuery("SELECT new dat.dtos.FavoriteDTO(r) FROM Favorite r", FavoriteDTO.class);
            return query.getResultList();
        }
    }

    public FavoriteDTO create(FavoriteDTO favoriteDTO, User user) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Favorite favorite = new Favorite(favoriteDTO);

            if(favoriteDTO.getUsername() != null) {
                em.merge(favoriteDTO);
            }else {
                favorite.setUser(user);
                em.persist(favorite);

            }
            em.getTransaction().commit();
            return new FavoriteDTO(favorite);

        }

    }





    public void delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Favorite favorite = em.find(Favorite.class, id);
            if (favorite != null){
                em.remove(favorite);
            }
            em.getTransaction().commit();
        }
    }

    public FavoriteDTO update(Long id, FavoriteDTO favoriteDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Favorite f = em.find(Favorite.class, id);
            f.setName(favoriteDTO.getName());
            Favorite newCuisine = em.merge(f);
            em.getTransaction().commit();
            return new FavoriteDTO(newCuisine);
        }
    }


    public FavoriteDTO createSpiceFavoriteList(List<FavoriteDTO> favoriteDTOList, SpiceDTO spiceDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Find user and spice entities in the database
            Spice spiceEntity = em.find(Spice.class, spiceDTO.getId());

            if (spiceEntity == null) {
                throw new IllegalArgumentException("Spice not found.");
            }
            Favorite favorite = new Favorite();
            if (favoriteDTOList.size() > 0) {
                for (FavoriteDTO favoriteDTO : favoriteDTOList) {
                    favorite.setId(favoriteDTO.getId());
                    favorite.setId(spiceDTO.getId());
                }

            }

            // Persist the favorite entity
            em.persist(favorite);

            // Commit the transaction
            em.getTransaction().commit();

            return new FavoriteDTO(favorite);

        }


        }

    public List<FavoriteDTO> getFavoriteFromUser(User user) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<FavoriteDTO> query = em.createQuery("select f from Favorite f WHERE f.user  = :user", FavoriteDTO.class);
            query.setParameter("user", user);

            return query.getResultList();
        }
    }
}

