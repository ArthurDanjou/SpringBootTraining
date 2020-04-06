package fr.arthurdanjou.springboottraining.player;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PlayerController {

    private PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @PostMapping("/players")
    public Player postPlayer(@RequestBody Player newPlayer) {
        return playerRepository.save(newPlayer);
    }

    @GetMapping("/players/{uuid}")
    public Player getPlayer(@PathVariable UUID uuid) {
        return playerRepository.findById(uuid)
                .orElseThrow(() -> new PlayerNotFoundException(uuid));
    }

    @PutMapping("/players/{uuid}")
    public Player putPlayer(@RequestBody Player newPlayer, @PathVariable UUID uuid) {
        return playerRepository.findById(uuid)
                .map(player -> {
                    player.setName(newPlayer.getName());
                    player.setRank(newPlayer.getRank());
                    player.setCoins(newPlayer.getCoins());
                    player.setStaff(newPlayer.isStaff());
                    return playerRepository.save(player);
                }).orElseGet(() -> {
                    newPlayer.setUuid(uuid);
                    return playerRepository.save(newPlayer);
                });
    }

    @DeleteMapping("/players/{uuid}")
    public void deletePlayer(@PathVariable UUID uuid) {
        playerRepository.deleteById(uuid);
    }
}
