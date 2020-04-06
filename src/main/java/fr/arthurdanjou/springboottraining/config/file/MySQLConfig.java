package fr.arthurdanjou.springboottraining.config.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class MySQLConfig {

    private String host;
    private int port;
    private String user;
    private String password;
    private String database;

}
