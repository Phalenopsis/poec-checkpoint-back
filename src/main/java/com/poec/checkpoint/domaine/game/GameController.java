package com.poec.checkpoint.domaine.game;

import com.poec.checkpoint.domaine.Grid;
import com.poec.checkpoint.security.user_app.UserApp;
import com.poec.checkpoint.security.user_app.UserAppRepository;
import com.poec.checkpoint.security.user_app.UserAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserAppService userService;

    @PostMapping("/new")
    public ResponseEntity<GameLaunchedDTO> add(@RequestBody StartingGameDTO startingGameDTO) {
        UserApp foundUser = userService.getById(startingGameDTO.playerId());
        Game game = new Game(
                startingGameDTO.difficulty(),
                foundUser,
                startingGameDTO.playerColor()
        );
        Game gameSaved = gameService.add(game);

        return new ResponseEntity<>(GameLaunchedDTO.mapFromEntity(gameSaved), HttpStatus.OK);
    }

    @PatchMapping("/play")
    public ResponseEntity<GameLaunchedDTO> play(@RequestBody MoveDTO move) {
        Game gameSaved = gameService.update(move);
        return new ResponseEntity<>(GameLaunchedDTO.mapFromEntity(gameSaved), HttpStatus.OK);
    }
}

