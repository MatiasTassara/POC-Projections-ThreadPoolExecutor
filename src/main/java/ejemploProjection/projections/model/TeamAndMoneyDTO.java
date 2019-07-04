package ejemploProjection.projections.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamAndMoneyDTO {
    @Id
    private Integer id;
    private String name;
    private Integer money;
}
