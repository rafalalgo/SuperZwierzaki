package Model;

public class Preparation {

    public static void askForPlayersQuant() {
        System.out.println("How many players do play?");
        Supervisor.setPlayersQuant(GetFromHuman.getInt());
    }

    public static Player setPlayer(Integer i) {
        Player player = new Player(i);
        player.setNumber(i);
        Preparation.askForName();
        player.setName(GetFromHuman.getString());
        return player;
    }

    private static void askForName() {
        System.out.println("What is your name?");
    }

    public static void giveCards() {
        for (Integer i = 0; i < Supervisor.getPlayersQuant(); i++) {
            Player player = Supervisor.getPlayers(i);
            Supervisor.draw(5, player);
        }
    }
}
