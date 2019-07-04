package ejemploProjection.projections.repository;

import ejemploProjection.projections.model.TeamAndMoneyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamAndMoneyDTORepository extends JpaRepository<TeamAndMoneyDTO,Integer> {

    String QUERY_ALL = "select t.name as name, sum(p.price) as money " +
            "from team t inner join player p on t.id = p.team_id " +
            "group by name";



    String QUERY_ONE = "select t.name as name, sum(p.price) as money " +
            "from team t inner join player p on p.team_id = t.id " +
            "where p.id = :id " +
            "group by name";

    @Query(value = QUERY_ALL,nativeQuery = true)
    List<TeamAndMoneyDTO> getAllDTOs();

    @Query(value = QUERY_ONE, nativeQuery = true)
    TeamAndMoneyDTO getOneDTO(@Param("id") Integer id);
}
