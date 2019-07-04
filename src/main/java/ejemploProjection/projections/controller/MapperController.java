package ejemploProjection.projections.controller;

import ejemploProjection.projections.model.Team;
import ejemploProjection.projections.model.TeamAndPlayersDTO;
import ejemploProjection.projections.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/modelMapper")
public class MapperController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("")
    public List<TeamAndPlayersDTO> getAllDTOs(){
        ModelMapper modelMapper = new ModelMapper();

        return teamRepository.findAll().stream()
                .map(team -> modelMapper.map(team, TeamAndPlayersDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/other")
    public List<TeamAndPlayersDTO> getDTOs(){
        ModelMapper modelmapper = new ModelMapper();
        List<Team> teams = teamRepository.findAll();
        List<TeamAndPlayersDTO> dtoList = new ArrayList<>();
        for(Team t: teams) {
            TeamAndPlayersDTO teamPlayerDTO = modelmapper.map(t, TeamAndPlayersDTO.class);
            dtoList.add(teamPlayerDTO);
        }
        return dtoList;
    }

    @RequestMapping("/header")
    public ResponseEntity<String> getBaseUrl(@RequestHeader HttpHeaders header) {
        return ResponseEntity.ok(String.valueOf(header));
    }

    @GetMapping("/user-agent")
    public String getHeadersInfo(@RequestHeader(value="User-Agent") String request) {
        return request;
    }

    @GetMapping("/agent")
    public ResponseEntity<String> userAgent(@RequestHeader("User-Agent") String agent) {
        return new ResponseEntity<>(agent, HttpStatus.OK);
    }


}
