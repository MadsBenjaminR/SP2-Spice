package dat.entities;

import dat.dtos.FavoriteDTO;
import dat.security.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
   // @JoinColumn(name = "cuisine_id")
    @JoinTable(name = "cuisine_favorite", joinColumns = {@JoinColumn(name = "cuisine_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "favorite_id", referencedColumnName = "id")})

    private Set<Cuisine> cuisines;

    public Favorite(FavoriteDTO favoriteDTO) {
        this.id = favoriteDTO.getId();
        this.name = favoriteDTO.getName();
    }
}
