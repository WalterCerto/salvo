package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

import static java.util.stream.Collectors.toList;


@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany( mappedBy="game", fetch=FetchType.EAGER)
    private List<GamePlayer> gamePlayers = new ArrayList<>();

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Set<Score> scores = new HashSet<>();

    private Date creationDate;

    public Game(){ }

    public Game(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        gamePlayers.add(gamePlayer);
    }
    public void setGamePlayers(List<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }
    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public List<Player> getPlayers() {
        return gamePlayers
                .stream()
                .map(sub -> sub.getPlayer())
                .collect(toList());
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gamePlayers=" + gamePlayers +
                ", creationDate=" + creationDate +
                '}';
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Score> getScores() {
        return scores;
    }
    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }
}
