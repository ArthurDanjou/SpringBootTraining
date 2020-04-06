package fr.arthurdanjou.springboottraining;

import com.google.gson.Gson;
import fr.arthurdanjou.springboottraining.config.ConfigurationManager;
import fr.arthurdanjou.springboottraining.database.mysql.MySQLConnector;
import fr.arthurdanjou.springboottraining.database.redis.RedisConnector;
import lombok.Getter;
import org.springframework.boot.SpringApplication;

import java.util.logging.Logger;

public class SpringBoot {

    @Getter
    private Logger logger = Logger.getLogger(Main.class.getName());

    private static SpringBoot instance;

    private Gson gson;
    private ConfigurationManager configurationManager;
    private RedisConnector redisConnector;
    private MySQLConnector mySQLConnector;

    public SpringBoot(String... args) {
        instance = this;
        this.gson = new Gson();
        this.configurationManager = new ConfigurationManager(this, this.gson, "/springboot/config.json");
        this.configurationManager.loadConfiguration();

        this.redisConnector = new RedisConnector(this, configurationManager.getConfiguration().getRedisConfig());
        this.mySQLConnector = new MySQLConnector(this, configurationManager.getConfiguration().getMySQLConfig());

        SpringApplication.run(Main.class, args);
    }

}
