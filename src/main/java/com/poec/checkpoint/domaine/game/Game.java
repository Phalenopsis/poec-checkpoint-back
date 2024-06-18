package com.poec.checkpoint.domaine.game;

import com.poec.checkpoint.security.user_app.UserApp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String difficulty;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserApp user;

    private String grid;

    private int humanPlayerColor;

    private int isFinish = 0;

    public Game(String difficulty, UserApp user, int humanPlayerColor) {
        this.difficulty = difficulty;
        this.user = user;
        this.humanPlayerColor = humanPlayerColor;
        this.grid = generateBlankGrid();
    }

    private String generateBlankGrid() {
        return new String(new char[42]).replace('\0', '0');
    }
}
