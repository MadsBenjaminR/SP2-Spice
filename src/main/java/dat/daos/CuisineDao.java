package dat.daos;

import dat.dtos.CuisineDTO;
import dat.entities.Cuisine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author laith kaseb
 **/


public class CuisineDao {

    private static Cuisine cuisine;
    private static EntityManagerFactory emf;


    public CuisineDTO read(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            Cuisine cuisine1 = em.find(Cuisine.class, integer);
            return cuisine1 != null ? new CuisineDTO(cuisine1) : null;
        }
    }


    public List<CuisineDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<CuisineDTO> query = em.createQuery("SELECT new dat.dtos.CuisineDTO(r) FROM Cuisine r", CuisineDTO.class);
            return query.getResultList();
        }
    }

    public CuisineDTO create(CuisineDTO cuisineDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Cuisine cuisine = new Cuisine(cuisineDTO);
            em.persist(cuisine);
            em.getTransaction().commit();
            return new CuisineDTO(cuisine);
        }
    }


    public void delete(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Cuisine cuisine = em.find(Cuisine.class, integer);
            if (cuisine != null){
                em.remove(cuisine);
            }
            em.getTransaction().commit();
        }
    }


    public CuisineDTO update(Integer integer, CuisineDTO cuisineDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Cuisine c = em.find(Cuisine.class, integer);
            c.setName(cuisineDTO.getName());
            c.setDescription(cuisineDTO.getDescription());
            Cuisine newCuisine = em.merge(c);
            em.getTransaction().commit();
            return new CuisineDTO(newCuisine);
        }
    }

}
