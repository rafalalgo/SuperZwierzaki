package Model.FunctionalMove;

import Model.Player;
import Model.Supervisor;

/**
 * Created by Jedrzej Hodor on 12.01.2018.
 */
public class PolMove extends FunctionalMove {

    @Override
    public Boolean polMove(Player player, Supervisor supervisor) {
        Integer howMany = (player.getQuantOfCards() + 1) / 2;
        supervisor.removeChoosenCards(player, howMany);
        return true;
    }
}
