package com.poec.checkpoint.domaine.game;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game add(Game game) {
        return gameRepository.save(game);
    }

    public Game getById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException( "entity not found")
                );
    }

    public Game update(Game game) {



        return gameRepository.save(game);
    }
}
