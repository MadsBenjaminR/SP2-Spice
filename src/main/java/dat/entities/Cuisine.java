package dat.entities;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuisine")
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String flavourProfile;

    @JoinTable(name = "cuisine_favorite", joinColumns = {@JoinColumn(name = "cuisine_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "favorite_id", referencedColumnName = "id")})
    @ManyToMany(mappedBy = "cuisines", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Favorite> favoriteSet;

    @JoinTable(name = "cuisine_spice", joinColumns = {@JoinColumn(name = "cuisine_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "spice_id", referencedColumnName = "id")})
    @ManyToMany(mappedBy = "cuisines", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Spice> spiceSet;


}
