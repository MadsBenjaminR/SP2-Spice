package dat.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dat.entities.Favorite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavoriteDTO {
    @JsonProperty("id")
    private long id;
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("name")
    private String name;

    @JsonProperty("favorite")
    private Set<FavoriteDTO> spices = new HashSet<>();

    public FavoriteDTO(Favorite favorite) {
        this.id = favorite.getId();
        this.userId = favorite.getUser().getId();
        this.name = favorite.getName();
    }
}
