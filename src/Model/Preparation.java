package Model;

/**
 * Created by Jędrzej Hodor on 05.01.2018.
 */

public class Preparation {
    private Supervisor supervisor;

    public Preparation(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public void askForPlayersQuant() {
        System.out.println("How many players do play?");
        supervisor.setPlayersQuant(GetFromHuman.getInt());
    }

    public Player setPlayer(int i) {
        Player player = new Player(0, 0, false, "");
        player.setNumber(i);
        this.askForName();
        player.setName(GetFromHuman.getString());
        return player;
    }

    private void askForName() {
        System.out.println("What is your name?");
    }

    public void giveCards() {
        for (int i = 0; i < supervisor.getPlayersQuant(); i++) {
            Player player = supervisor.getPlayers(i);
            supervisor.draw(5, player);
        }
    }

}

