package dat.entities;

import dat.dtos.FavoriteDTO;
import dat.security.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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
    @Column(name = "favorite_id")
    private Long id;

    @Column(name = "favorite_name")
    private String name;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(
            name = "cuisine_favorite",
            joinColumns = {@JoinColumn(name = "favorite_id", referencedColumnName = "favorite_id")},
            inverseJoinColumns = {@JoinColumn(name = "cuisine_id", referencedColumnName = "cuisine_id")}
    )private Set<Cuisine> cuisines;

    @ManyToMany
    @JoinTable(
            name = "spice_favorite",
            joinColumns = {@JoinColumn(name = "favorite_id", referencedColumnName = "favorite_id")},
            inverseJoinColumns = {@JoinColumn(name = "spice_id", referencedColumnName = "spice_id")})
    private Set<Spice> spices = new HashSet<>();

    public Favorite(FavoriteDTO favoriteDTO) {
        this.id = favoriteDTO.getId();
        this.name = favoriteDTO.getName();
    }
}
