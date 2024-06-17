package com.poec.checkpoint.domaine.game;

import com.poec.checkpoint.security.user_app.UserApp;
import com.poec.checkpoint.security.user_app.UserAppRepository;
import com.poec.checkpoint.security.user_app.UserAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        //generate grid
        return new ResponseEntity<>(GameLaunchedDTO.mapFromEntity(gameSaved), HttpStatus.OK);
    }
}
/*
 @PostMapping("/add/{userId}")
    public ResponseEntity<GameTableDTO> add(@RequestBody GameTable gameTable, @PathVariable("userId") Long userId) {
        UserApp foundUser = userAppService.getById(userId);
        gameTable.setUser(foundUser);
        GameTable tableCreated = service.add(gameTable);
        GameTableDTO dto = GameTableDTO.mapFromEntity(tableCreated);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
 */
