package Model;

public class Preparation {

    public void askForPlayersQuant() {
        System.out.println("How many players do play?");
        Supervisor.setPlayersQuant(GetFromHuman.getInt());
    }

    public Player setPlayer(Integer i) {
        Player player = new Player;
        player.setNumber(i);
        this.askForName();
        player.setName(GetFromHuman.getString());
        return Player;
    }

    private void askForName() {
        System.out.println("What is your name?");
    }

    public void giveCards() {
        Supervisor.getWhoseMove(); = 0;
        for (Integer i = 0; i < Supervisor.getPlayersQuant(i); i++) {
            Player player = Supervisor.getPlayers(i);
            Supervisor.draw(5, player);
        }
    }
}
