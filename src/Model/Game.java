package Model;

/**
 * Created by Jędrzej Hodor on 06.01.2018.
 */

public class Game {
    public static void main(String[] args) {
        Supervisor supervisor = new Supervisor();
        String winner;
        winner = supervisor.game();
        System.out.println("The winner is: " + winner);
        System.out.println("Congratulation for all participants ;)");
    }
}
