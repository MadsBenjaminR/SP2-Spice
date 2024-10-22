package dat.entities;

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
    private Long id;

    private String name;
    private String description;
    private String flavourProfile;

    @JoinTable(name = "cuisine_spice", joinColumns = {@JoinColumn(name = "spice_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "cuisine_id", referencedColumnName = "id")})
    @ManyToMany(mappedBy = "spiceSet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Cuisine> cuisines;

}
