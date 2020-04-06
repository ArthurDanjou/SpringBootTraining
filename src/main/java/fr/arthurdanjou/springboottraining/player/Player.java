package fr.arthurdanjou.springboottraining.player;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Player {

    @Id @GeneratedValue
    private UUID uuid;
    private String name;
    private String rank;
    private double coins;
    private boolean staff;

    public Player() {}

    public Player(String name, String rank, double coins, boolean staff) {
        this.name = name;
        this.rank = rank;
        this.coins = coins;
        this.staff = staff;
    }

}
