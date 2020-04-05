package fr.arthurdanjou.test.player;

import java.util.UUID;

public class PlayerNotFoundException extends RuntimeException {

    PlayerNotFoundException(UUID uuid) {
        super("Could not find player with uuid : " +uuid.toString());
    }

}
