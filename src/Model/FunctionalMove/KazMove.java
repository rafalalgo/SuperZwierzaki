package Model.FunctionalMove;

import Model.Human;
import Model.Player;
import Model.Supervisor;

/**
 * Created by Jedrzej Hodor on 12.01.2018.
 */
public class KazMove extends FunctionalMove {

    @Override
    public Boolean kazMove(Player player, Supervisor supervisor) {
        Integer howMany = (player.getQuantOfCards() + 1) / 2;
        Player receiver = Human.askWhoGiveCardsTo(player, howMany, supervisor);
        supervisor.giveChoosenCards(player, receiver, howMany);
        return true;
    }
}
