package com.poec.checkpoint.domaine.game;

public record GameLaunchedDTO(
        Long id,
        Long playerId,
        String difficulty,
        String grid,
        int humanPlayerColor
) {
    public static GameLaunchedDTO mapFromEntity(Game game) {
        return new GameLaunchedDTO(
                game.getId(),
                game.getUser().getId(),
                game.getDifficulty(),
                game.getGrid(),
                game.getHumanPlayerColor()
        );
    }
}
