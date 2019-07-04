package ejemploProjection.projections.service;

import ejemploProjection.projections.model.Team;
import ejemploProjection.projections.model.TeamAndMoneyDTO;
import ejemploProjection.projections.repository.TeamAndMoneyDTORepository;
import ejemploProjection.projections.repository.TeamPlayerQuantity;
import ejemploProjection.projections.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamAndMoneyDTORepository teamAndMoneyDTORepository;

    public List<Team> getAll(){
        return teamRepository.findAll();
    }

    public Team getById(Integer id){
        Team t = teamRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Team not found"));
        return t;
    }

    public void add(Team t){
        teamRepository.save(t);
    }

    public void modify(Team t){
        teamRepository.save(t);
    }

    public void delete(Integer id){
        teamRepository.deleteById(id);
    }

    //Projections (interface)
    public List<TeamPlayerQuantity> getAllInterfaceProjections(){
        return teamRepository.getAllTeamsAndQuantity();
    }

    public TeamPlayerQuantity getOneInterfaceProjection(Integer id){
        return teamRepository.getTeamAndQuantity(id);
    }

    //DTO Projections

    public List<TeamAndMoneyDTO> getAllDTOs(){
        return teamAndMoneyDTORepository.getAllDTOs();
    }

    public TeamAndMoneyDTO getDTO(Integer id){
        return teamAndMoneyDTORepository.getOneDTO(id);
    }

}
