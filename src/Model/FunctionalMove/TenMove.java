package Model.FunctionalMove;

import Model.Human;
import Model.Player;
import Model.Supervisor;

/**
 * Created by Jedrzej Hodor on 12.01.2018.
 */
public class TenMove extends FunctionalMove {

    @Override
    public Boolean tenMove(Player player, Supervisor supervisor, Integer oneOrTwo) {
        Integer howMany;
        Integer q = player.getQuantOfCards();
        if(q < 3) {
            howMany = q;
        } else {
            howMany = q - oneOrTwo;
        }
        Player receiver = Human.askWhoGiveCardsTo(player, howMany, supervisor);
        Human.displayHand(player);
        supervisor.giveChoosenCards(player, receiver, howMany);
        return true;
    }
}
