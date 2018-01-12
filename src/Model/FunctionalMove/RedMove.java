package Model.FunctionalMove;

import Model.SpecialPoints;

/**
 * Created by Jedrzej Hodor on 10.01.2018.
 */
public class RedMove extends FunctionalMove {

    @Override
    public Boolean redMove(SpecialPoints specialPoints) {
        specialPoints.setRed(specialPoints.getRed() + 2);
        return true;
    }
}
