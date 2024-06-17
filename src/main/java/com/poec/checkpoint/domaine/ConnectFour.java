package com.poec.checkpoint.domaine;

public class ConnectFour {
    private Grid grid;
    private Player player1;
    private Player player2;

    public ConnectFour() {
        grid = new Grid();
        player1 = new Player();
        player2 = new Player();
    }

    public void play() {
        player1.setColor(1);
        player2.setColor(-1);
        player1.setAdversary(player2);
        player2.setAdversary(player1);
    }
}
