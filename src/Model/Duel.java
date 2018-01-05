package Model;

/**
 * Created by Jędrzej Hodor on 05.01.2018.
 */

public class Duel {

    private Integer duel = 0;
    private Card duel_card;

    public Duel(Integer duel, Card duel_card) {
        this.duel = duel;
        this.duel_card = duel_card;
    }

    public void addDuel(Integer how_many) {
        this.duel += how_many;
    }

    private void resetDuel() {
        this.duel = 0;
        for (Integer i = 0; i < Supervisor.getPlayersQuant(); i++) {
            Player player = Supervisor.getPlayers(i);
            player.setIfFolded(false);
        }
    }

    private Boolean checkIfWinner() {
        if (Supervisor.ifWinner()) {
            return true;
        }
        Boolean stop = false;
        for (Integer i = 0; i < Supervisor.getPlayersQuant(); i++) {
            Player player = Supervisor.getPlayers(i);
            if (!player.getIfFolded() && !stop) {
                stop = true;
            } else if (!player.getIfFolded() && stop) {
                return false;
            }
        }
        return true;
    }

    private Player findWinner() {
        if (Supervisor.ifWinner()) {
            return Supervisor.whoWon();
        }
        for (Integer i = 0; i < Supervisor.getPlayersQuant(); i++) {
            Player player = Supervisor.getPlayers(i);
            if (!player.getIfFolded()) {
                return player;
            }
        }
        return null;
    }

    private Boolean duelMove(Player player, Function function) {
        Integer what_move = player.whatDuelMove();

        if (what_move == 1) {
            player.setIfFolded(true);
            return true;
        } else if (what_move == 2) {
            Card card = player.ordinaryMove();
            Function f = card.getFunction();
            if (f == Function.Red) {
                this.duel += 2;
                this.duel_card = card;
                return true;
            } else if (f == function) {
                this.duel += 1;
                this.duel_card = card;
                return true;
            } else {
                return false;
            }
        } else if (what_move == 3) {
            if (duel_card.getFunction() == Function.Red) {
                Card card = player.ordinaryMove();
                Function f = card.getFunction();
                if (f == Function.All || f == Function.Cyr) {
                    this.resetDuel();
                    this.duel_card = card;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public Integer duel(Function function, Player triggering_player) {
        Integer index = triggering_player.getNumber();
        Boolean end = false;
        Player winner = null;
        Boolean if_winner = false;

        while (!end) {
            Player current_player = Supervisor.getPlayers(index);
            this.duelMove(current_player, function);

            if (this.duel == 0) {
                end = true;
            } else if (this.checkIfWinner()) {
                end = true;
                winner = this.findWinner();
                if_winner = true;
            }

            index = (index + 1) % Supervisor.getPlayersQuant();
        }
        Supervisor.getWhoseMove() = (index + 1) % Supervisor.getPlayersQuant();
        this.resetDuel();
        Supervisor.newCardOnTheHip(this.duel_card);

        if (if_winner) {
            return winner.getNumber();
        } else {
            return -1;
        }
    } // Zwraca winnera, jeśli został przerwany to -1

}