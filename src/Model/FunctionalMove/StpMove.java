package Model.FunctionalMove;

import Model.SpecialPoints;

/**
 * Created by Jedrzej Hodor on 12.01.2018.
 */
public class StpMove extends FunctionalMove {

    @Override
    public Boolean stpMove(SpecialPoints specialPoints) {
        specialPoints.setGreen(specialPoints.getGreen() + 1);
        return true;
    }
}
