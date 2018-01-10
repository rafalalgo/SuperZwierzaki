package Model.FunctionalMove;

import Model.Situation;
import Model.SpecialPoints;

/**
 * Created by Jedrzej Hodor on 10.01.2018.
 */

public class OrkaMove extends FunctionalMove {

    @Override
    public Boolean orkaMove(SpecialPoints specialPoints, Situation situation) {
        specialPoints.setOrc(specialPoints.getOrc() + 3);
        JelenMove chg = new JelenMove();
        chg.jelenMove(situation);
        return true;
    }
}
