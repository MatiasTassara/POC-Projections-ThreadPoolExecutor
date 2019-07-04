package ejemploProjection.projections.controller;

import ejemploProjection.projections.model.Player;
import ejemploProjection.projections.service.PlayerService;
import ejemploProjection.projections.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;
    @Autowired
    TeamService teamService;

    @GetMapping("")
    public List<Player> getAllPlayers(){
        return playerService.getAll();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable("id") Integer id){
        return playerService.getById(id);
    }

    @PostMapping("/{idT}")
    public void addPlayer(@RequestBody Player p, @PathVariable("idT") Integer id){
        p.setTeam(teamService.getById(id));
        playerService.add(p);
    }

    @PutMapping("/{id}")
    public void modifyPlayer(@RequestBody Player p, @PathVariable("id") Integer id){
        p.setId(id);
        playerService.modify(p);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") Integer id){
        playerService.delete(id);
    }
}
