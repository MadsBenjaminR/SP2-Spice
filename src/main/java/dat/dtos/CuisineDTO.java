package dat.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dat.entities.Cuisine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CuisineDTO {
    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("flavor_profile")
    private String flavorProfile;
    @JsonProperty("spices")
    private Set<SpiceDTO> spices = new HashSet<>();

    public CuisineDTO(Cuisine cuisine){
        this.id = cuisine.getId();
        this.name = cuisine.getName();
        this.description = cuisine.getDescription();
        this.flavorProfile = cuisine.getFlavorProfile();

        this.spices = cuisine.getSpiceSet().stream().map(SpiceDTO::new).collect(Collectors.toSet());
    }
}
