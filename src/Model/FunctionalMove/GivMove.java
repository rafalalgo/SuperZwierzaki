package Model.FunctionalMove;


import Model.*;

/**
 * Created by Jedrzej Hodor on 12.01.2018.
 */

public class GivMove extends FunctionalMove {

    @Override
    public Boolean givMove(Player trig, Card startCard, Supervisor supervisor) {
        Duel duel = new Duel(1, startCard, supervisor);
        Pair<Player, Integer> duelResult = duel.duel(startCard.getFunction(), trig);
        Player winner = duelResult.getFirst();
        Integer duelFinal = duelResult.getSecond();

        if(duelFinal != -1) {
            Integer possible = winner.getQuantOfCards();
            Integer howMany = null;
            if(possible > duelFinal) {
                howMany = duelFinal;
            } else if(possible == 1){
                howMany = 1;
            } else {
                howMany = possible - 1;
            }

            Player receiver = Human.askWhoGiveCardsTo(winner, howMany, supervisor);
            supervisor.giveChoosenCards(winner, receiver, howMany);
        }
        return true;
    }
}
