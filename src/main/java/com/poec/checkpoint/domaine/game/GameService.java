package com.poec.checkpoint.domaine.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game add(Game game) {
        return gameRepository.save(game);
    }
}
