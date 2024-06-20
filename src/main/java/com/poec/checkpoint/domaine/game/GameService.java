package com.poec.checkpoint.domaine.game;

import com.poec.checkpoint.domaine.Grid;
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

    public Game update(MoveDTO move) {
        Game game = getById(move.tableId());
        Grid grid = new Grid(game.getGrid());

        game.setIsFinish(grid.move(move.column(), game.getHumanPlayerColor()));
        if(game.getIsFinish() == 0) {
            game.setIsFinish(grid.playIa(game.getDifficulty()));
        }
        String gridInString = grid.toString();
        game.setGrid(gridInString);

        return gameRepository.save(game);
    }
}
