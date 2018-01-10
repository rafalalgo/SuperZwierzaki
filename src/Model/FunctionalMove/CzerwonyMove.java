package Model.FunctionalMove;

import Model.SpecialPoints;

/**
 * Created by Jedrzej Hodor on 10.01.2018.
 */
public class CzerwonyMove extends FunctionalMove {

    @Override
    public Boolean czerwonyMove(SpecialPoints specialPoints) {
        specialPoints.setRed(specialPoints.getRed() + 2);
        return true;
    }
}
