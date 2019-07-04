package ejemploProjection.projections.service;

import ejemploProjection.projections.model.Player;
import ejemploProjection.projections.model.Team;
import ejemploProjection.projections.repository.PlayerRepository;
import ejemploProjection.projections.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TeamRepository teamRepository;

    public List<Player> getAll(){
        return playerRepository.findAll();
    }

    public Player getById(Integer id){
        Player p = playerRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Player not found."));
        return p;
    }

    public void add(Player p){
        Team t = teamRepository.findById(p.getTeam().getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Team not found"));
        t.getPlayers().add(p);
        playerRepository.save(p);
    }

    public void modify(Player p){
        Team t = teamRepository.findById(p.getTeam().getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "New team not found"));
        playerRepository.save(p);
    }

    public void delete(Integer id){
        playerRepository.deleteById(id);
    }
}
