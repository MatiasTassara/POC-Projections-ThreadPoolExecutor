package ejemploProjection.projections.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamAndPlayersDTO {

    private String teamName;
    private List<Player> players;
}
