package fr.arthurdanjou.springboottraining.config.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RedisConfig {

    private String host;
    private int port;
    private String password;
}
