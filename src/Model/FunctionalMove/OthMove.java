package Model.FunctionalMove;


import Model.*;

/**
 * Created by Jedrzej Hodor on 12.01.2018.
 */

public class OthMove extends FunctionalMove {

    @Override
    public Boolean othMove(Player trig, Card startCard, Supervisor supervisor) {
        Duel duel = new Duel(1, startCard, supervisor);
        Pair<Player, Integer> duelResult = duel.duel(startCard.getFunction(), trig);
        Player winner = duelResult.getFirst();
        Integer duelFinal = duelResult.getSecond();
        Player curr = null;

        if(duelFinal != -1) {
            for(int i = 0; i < supervisor.getPlayersQuant(); i++) {
                if(i != winner.getNumber()) {
                    curr = supervisor.getPlayers(i);
                    supervisor.draw(duelFinal, curr);
                }
            }
        }
        return true;
    }
}
