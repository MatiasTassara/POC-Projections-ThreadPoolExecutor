package ejemploProjection.projections.service;

import ejemploProjection.projections.model.Player;
import ejemploProjection.projections.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Autowired
    PlayerRepository playerRepository;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Integer> totalTeamOne() {
        Integer total = 0;
        try {
            Thread.sleep(2000);
            List<Player> players = playerRepository.findAll();
            total = players.stream()
                    .filter( x -> x.getTeam().getId() == 1)
                    .mapToInt(Player::getPrice)
                    .sum();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(total);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Integer> totalTeamTwo() {
        Integer total = 0;
        try {
            Thread.sleep(4000);
            List<Player> players = playerRepository.findAll();
            total = players.stream()
                    .filter( x -> x.getTeam().getId() == 2)
                    .mapToInt(Player::getPrice)
                    .sum();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(total);
    }
}
