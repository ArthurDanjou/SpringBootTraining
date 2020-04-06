package fr.arthurdanjou.springboottraining.config;

import fr.arthurdanjou.springboottraining.config.file.MySQLConfig;
import fr.arthurdanjou.springboottraining.config.file.RedisConfig;
import lombok.Getter;

@Getter
public class Configuration {

    private int portRun;
    private RedisConfig redisConfig;
    private MySQLConfig mySQLConfig;

    public Configuration() {
        this.portRun = 1010;
        this.redisConfig = new RedisConfig("127.0.0.1", 6379, "password");
        this.mySQLConfig = new MySQLConfig("127.0.0.1", 3306, "arthur", "password");
    }

}
