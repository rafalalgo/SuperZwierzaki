package Model.FunctionalMove;

import Model.Card;
import Model.Player;
import Model.Supervisor;
import Model.Human;
import java.util.List;

/**
 * Created by Rafal Byczek on 08.01.2018.
 */
public class WaranMove extends FunctionalMove{
    @Override
    public void waranTransposition(Player player1, Player player2) {
        List<Card> tmp = player1.hand;
        player1.hand = player2.hand;
        player2.hand = tmp;

        int tmpQuant = player1.getQuant_of_cards();
        player1.setQuant_of_cards(player2.getQuant_of_cards());
        player2.setQuant_of_cards(tmpQuant);
    }

    @Override
    public void waranPermutation(Player master, Player giver, Player receiver, Supervisor supervisor) {
        Boolean stop = true;
        while (stop) {
            Integer given = giver.getQuant_of_cards();
            supervisor.giveCards(giver,receiver,given);
            stop = Human.askWaranIf();
            if (stop) {
                Integer plr1 = Human.askWaranWho(supervisor);
                Integer plr2 = Human.askWaranWho(supervisor);
                Player player1 = supervisor.getPlayers(plr1);
                Player player2 = supervisor.getPlayers(plr2);
                this.waranTransposition(player1, player2);
            }
        }
    }
}
