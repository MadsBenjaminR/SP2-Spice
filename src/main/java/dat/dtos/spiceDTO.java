package dat.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class spiceDTO {
    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("flavor_profile")
    private String flavorProfile;
    @JsonProperty("description")
    private String description;
    
}
