package dat.daos;

import dat.dtos.CuisineDTO;
import dat.dtos.FavoriteDTO;
import dat.dtos.SpiceDTO;
import dat.entities.Cuisine;
import dat.entities.Favorite;
import dat.entities.Spice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * @author laith kaseb
 **/


public class SpiceDao {

    private static EntityManagerFactory emf;


    public SpiceDTO read(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            Spice spice = em.find(Spice.class, integer);
            return spice != null ? new SpiceDTO(spice) : null;
        }
    }

    public List<SpiceDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<SpiceDTO> query = em.createQuery("SELECT new dat.dtos.SpiceDTO(r) FROM Spice r", SpiceDTO.class);
            return query.getResultList();
        }
    }

    public SpiceDTO create(SpiceDTO spiceDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Spice spice = new Spice(spiceDTO);
            em.persist(spice);
            em.getTransaction().commit();
            return new SpiceDTO(spice);
        }
    }


    public void delete(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Spice spice = em.find(Spice.class, integer);
            if (spice != null){
                em.remove(spice);
            }
            em.getTransaction().commit();
        }
    }

    public SpiceDTO update(Integer integer, SpiceDTO spiceDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Spice c = em.find(Spice.class, integer);
            c.setName(spiceDTO.getName());
            Spice newspice = em.merge(c);
            em.getTransaction().commit();
            return new SpiceDTO(newspice);
        }
    }
}
