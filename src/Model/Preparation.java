package Model;

/**
 * Created by Jędrzej Hodor on 05.01.2018.
 */

public class Preparation {

    public static void askForPlayersQuant() {
        System.out.println("How many players do play?");
        Supervisor.setPlayersQuant(GetFromHuman.getInt());
    }

    public static Player setPlayer(int i) {
        Player player = new Player(0, 0, false, "");
        player.setNumber(i);
        Preparation.askForName();
        player.setName(GetFromHuman.getString());
        return player;
    }

    private static void askForName() {
        System.out.println("What is your name?");
    }

    public static void giveCards() {
        for (int i = 0; i < Supervisor.getPlayersQuant(); i++) {
            Player player = Supervisor.getPlayers(i);
            Supervisor.draw(5, player);
        }
    }

}

