package fr.arthurdanjou.test;

import fr.arthurdanjou.test.player.Player;
import fr.arthurdanjou.test.player.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(PlayerRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Player("BunSLaPatate", "Admin", 100, true)));
            log.info("Preloading " + repository.save(new Player("BunSLaFramboise", "Joueur", 0, false)));
        };
    }

}
