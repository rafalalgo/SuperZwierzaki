package Model.FunctionalMove;

import Model.*;

import java.util.List;

/**
 * Created by Rafal Byczek on 08.01.2018.
 */
public class WaranMove extends FunctionalMove {
    @Override
    public Boolean waranOnlyTransposion(Supervisor supervisor) {
        Integer pl1 = Human.askWaranWho(supervisor);
        Integer pl2 = Human.askWaranWho(supervisor);
        Player player1 = supervisor.getPlayers(pl1);
        Player player2 = supervisor.getPlayers(pl2);
        this.waranTransposition(player1, player2);
        this.displayWaransucc();
        return true;
    }

    @Override
    public Boolean waranPermutation(Player master, Supervisor supervisor) {
        Boolean stop = true;
        while (stop) {
            Pair<Integer, Integer> P = Human.askWaranGivRec(supervisor);
            Integer giv = P.getFirst();
            Integer rec = P.getSecond();
            Player giver = supervisor.getPlayers(giv);
            Player receiver = supervisor.getPlayers(rec);
            Integer given = giver.getQuantOfCards();
            supervisor.giveChoosenCards(giver, receiver, (given + 1) / 2);
            stop = Human.askWaranIf();
            if (stop) {
                Integer plr1 = Human.askWaranWho(supervisor);
                Integer plr2 = Human.askWaranWho(supervisor);
                Player player1 = supervisor.getPlayers(plr1);
                Player player2 = supervisor.getPlayers(plr2);
                this.waranTransposition(player1, player2);
                this.displayWaransucc();
            }
        }
        return true;
    }

    private void waranTransposition(Player player1, Player player2) {
        List<Card> tmp = player1.hand;
        player1.hand = player2.hand;
        player2.hand = tmp;

        int tmpQuant = player1.getQuantOfCards();
        player1.setQuantOfCards(player2.getQuantOfCards());
        player2.setQuantOfCards(tmpQuant);
    }

    private void displayWaransucc() {
        System.out.println("Transposition successful");
    }
}
