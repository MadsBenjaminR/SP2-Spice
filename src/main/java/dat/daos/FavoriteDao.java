package dat.daos;

import dat.dtos.CuisineDTO;
import dat.dtos.FavoriteDTO;
import dat.entities.Cuisine;
import dat.entities.Favorite;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * @author laith kaseb
 **/


public class FavoriteDao {

    private static EntityManagerFactory emf;


    public FavoriteDTO read(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            Favorite favorite = em.find(Favorite.class, integer);
            return favorite != null ? new FavoriteDTO(favorite) : null;
        }
    }

    public List<FavoriteDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<FavoriteDTO> query = em.createQuery("SELECT new dat.dtos.FavoriteDTO(r) FROM Favorite r", FavoriteDTO.class);
            return query.getResultList();
        }
    }

    public FavoriteDTO create(FavoriteDTO favoriteDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Favorite favorite = new Favorite(favoriteDTO);
            em.persist(favorite);
            em.getTransaction().commit();
            return new FavoriteDTO(favorite);
        }
    }


    public void delete(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Favorite favorite = em.find(Favorite.class, integer);
            if (favorite != null){
                em.remove(favorite);
            }
            em.getTransaction().commit();
        }
    }

    public FavoriteDTO update(Integer integer, FavoriteDTO favoriteDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Favorite f = em.find(Favorite.class, integer);
            f.setName(favoriteDTO.getName());
            Favorite newCuisine = em.merge(f);
            em.getTransaction().commit();
            return new FavoriteDTO(newCuisine);
        }
    }

}

