package dat.entities;

import dat.dtos.SpiceDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spice")
public class Spice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spice_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "flavor_profile", nullable = false)
    private String flavorProfile;

    @JoinTable(name = "cuisine_spice", joinColumns = {@JoinColumn(name = "spice_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "cuisine_id", referencedColumnName = "id")})
    @ManyToMany(mappedBy = "spiceSet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Cuisine> cuisineSet; // = new HashSet<>();

    public Spice(String name, String description, String flavorProfile) {
        this.name = name;
        this.description = description;
        this.flavorProfile = flavorProfile;
    }

    public Spice(SpiceDTO spiceDTO){
        this.id = spiceDTO.getId();
        this.name = spiceDTO.getName();
        this.description = spiceDTO.getDescription();
        this.flavorProfile = spiceDTO.getFlavorProfile();
    }

  /*  public void setCuisineSet(Set<Cuisine> cuisines) {
        if (cuisines != null) {
            this.cuisineSet = cuisines;
            for (Cuisine cuisine : cuisines) {
                cuisine.getSpiceSet().add(this);
            }
        }
    }

   */
}
