package Model;


/**
 * Created by Jędrzej Hodor on 05.01.2018.
 */

public class Duel {
    private int duel;
    private Card duelCard;
    private Supervisor supervisor;

    public Duel(int duel, Card duelCard, Supervisor supervisor) {
        this.duel = duel;
        this.duelCard = duelCard;
    }

    public void addDuel(int howMany) {
        this.duel += howMany;
    }

    private void resetDuel() {
        this.duel = 0;
        for (int i = 0; i < this.supervisor.getPlayersQuant(); i++) {
            Player player = this.supervisor.getPlayers(i);
            player.setIfFolded(false);
        }
    }

    private Boolean checkIfWinner() {
        if (this.supervisor.ifWinner()) {
            return true;
        }
        Boolean stop = false;
        for (int i = 0; i < this.supervisor.getPlayersQuant(); i++) {
            Player player = this.supervisor.getPlayers(i);
            if (!(player.getIfFolded()) && !stop) {
                stop = true;
            } else if (!(player.getIfFolded()) && stop) {
                return false;
            }
        }
        return true;
    }

    private Player findWinner() {
        if (this.supervisor.ifWinner()) {
            return this.supervisor.whoWon();
        }
        for (int i = 0; i < this.supervisor.getPlayersQuant(); i++) {
            Player player = this.supervisor.getPlayers(i);
            if (!player.getIfFolded()) {
                return player;
            }
        }
        return null;
    }

    private Boolean duelMove(Player player, Function function) {
        int whatMove = player.whatDuelMove();

        if (whatMove == 1) {
            player.setIfFolded(true);
            return true;
        } else if (whatMove == 2) {
            Card card = player.ordinaryMove();
            Function f = card.getFunction();
            if (f == Function.Red) {
                this.duel += 2;
                this.duelCard = card;
                return true;
            } else if (f == function) {
                this.duel += 1;
                this.duelCard = card;
                return true;
            } else {
                return false;
            }
        } else if (whatMove == 3) {
            if (duelCard.getFunction() == Function.Red) {
                Card card = player.ordinaryMove();
                Function f = card.getFunction();
                if (f == Function.All || f == Function.Cyr) {
                    this.resetDuel();
                    this.duelCard = card;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public Pair<Player, Integer> duel(Function function, Player triggeringPlayer) {
        int index = triggeringPlayer.getNumber();
        Boolean end = false;
        Player winner = null;
        Boolean ifWinner = false;
        Integer duelFinal = 0;

        while (!end) {
            Player currentPlayer = supervisor.getPlayers(index);
            this.duelMove(currentPlayer, function);

            if (this.duel == 0) {
                end = true;
            } else if (this.checkIfWinner()) {
                end = true;
                winner = this.findWinner();
                ifWinner = true;
            }

            index = (index + 1) % this.supervisor.getPlayersQuant();
        }
        this.supervisor.setWhoseMove((index + 1) % this.supervisor.getPlayersQuant());
        duelFinal = this.duel;
        this.resetDuel();
        new Supervisor().newCardOnTheHip(this.duelCard);

        if (ifWinner) {
            return new Pair<>(winner,duelFinal) ;
        } else {
            return new Pair<>(null, -1);
        }
    } // Zwraca winnera, jeśli został przerwany to -1

}
