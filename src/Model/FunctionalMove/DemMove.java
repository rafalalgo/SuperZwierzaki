package Model.FunctionalMove;

import Model.*;

/**
 * Created by Jedrzej Hodor on 12.01.2018.
 */

public class DemMove extends FunctionalMove {

    @Override
    public Boolean demMove(Player player, SpecialPoints specialPoints, Supervisor supervisor) {
        Integer dem = Human.askDemandedCard(player);
        if(dem == -1) {
            return false;
        } else {
            //zmiena integera w carte
            Card demCard = null;
            specialPoints.setDemand(1);
            supervisor.setDemandedCard(demCard);
            supervisor.setPlayerWhoHasDemanded(player);
            return true;
        }
    }
}
