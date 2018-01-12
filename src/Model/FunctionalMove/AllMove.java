package Model.FunctionalMove;

import Model.SpecialPoints;

/**
 * Created by Jedrzej Hodor on 12.01.2018.
 */
public class AllMove extends FunctionalMove {

    @Override
    public Boolean allMove(SpecialPoints specialPoints) {
        specialPoints.setRed(0);
        return true;
    }
}