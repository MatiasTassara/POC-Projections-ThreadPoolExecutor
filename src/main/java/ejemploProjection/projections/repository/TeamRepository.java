package ejemploProjection.projections.repository;

import ejemploProjection.projections.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {

    String QUERY_ALL = "select t.name as name, count(p.id) as quantity " +
            "from team t left join player p on t.id = team_id " +
            "group by name";

    String QUERY_ONE = "select t.name as name, count(p.id) as quantity " +
            "from team t left join player p on t.id = team_id " +
            "where t.id = ?1 " +
            "group by name";

    @Query(value = QUERY_ALL, nativeQuery = true)
    List<TeamPlayerQuantity> getAllTeamsAndQuantity();

    @Query(value = QUERY_ONE, nativeQuery = true)
    TeamPlayerQuantity getTeamAndQuantity(Integer id);
}
