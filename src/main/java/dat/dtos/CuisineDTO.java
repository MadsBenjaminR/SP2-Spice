package dat.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dat.entities.Cuisine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonProperty("flavour_profile")
    private String flavourProfile;

    public CuisineDTO(Cuisine cuisine) {
        this.id = cuisine.getId();
        this.name = cuisine.getName();
        this.description = cuisine.getDescription();
        this.flavourProfile = cuisine.getFlavourProfile();
    }


}
