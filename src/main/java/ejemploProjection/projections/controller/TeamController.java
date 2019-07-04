package ejemploProjection.projections.controller;

import ejemploProjection.projections.model.Team;
import ejemploProjection.projections.model.TeamAndMoneyDTO;
import ejemploProjection.projections.repository.TeamPlayerQuantity;
import ejemploProjection.projections.service.AsyncService;
import ejemploProjection.projections.service.PlayerService;
import ejemploProjection.projections.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;
    @Autowired
    PlayerService playerService;
    @Autowired
    AsyncService asyncService;

    @GetMapping("")
    public List<Team> getAllTeams(){
        return teamService.getAll();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable("id") Integer id){
        return teamService.getById(id);
    }

    @PostMapping("")
    public void addTeam(@RequestBody Team t){
        teamService.add(t);
    }

    @PutMapping("/{id}")
    public void modifyTeam(@RequestBody Team t, @PathVariable("id") Integer id){
        t.setId(id);
        teamService.add(t);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Integer id){
        teamService.delete(id);
    }

    //PROJECTIONS (INTERFACE)

    @GetMapping("/projection")
    public List<TeamPlayerQuantity> getAllTeamsAndQuantity(){
        return teamService.getAllInterfaceProjections();
    }

    @GetMapping("/projection/{id}")
    public TeamPlayerQuantity getOneTeamAndQuantity(@PathVariable("id") Integer id){
        return teamService.getOneInterfaceProjection(id);
    }

    //DTO PROJECTIONS

    @GetMapping("/dto")
    public List<TeamAndMoneyDTO> getAllTeamAndMoneyDTOs(){
        return teamService.getAllDTOs();
    }

    @GetMapping("/dto/{id}")
    public TeamAndMoneyDTO getOneTeamAndMoneyDTO(@PathVariable("id") Integer id){
        return teamService.getDTO(id);
    }


    //Completablefuture...

    @GetMapping("/async")
    public ResponseEntity<?> getAsync() {
        CompletableFuture<Integer> resultMethodOne = asyncService.totalTeamOne();
        CompletableFuture<Integer> resultMethodTwo = asyncService.totalTeamTwo();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultMethodOne.join() + resultMethodTwo.join());
    }


}
