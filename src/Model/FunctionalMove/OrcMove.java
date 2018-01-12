package Model.FunctionalMove;

import Model.Situation;
import Model.SpecialPoints;

/**
 * Created by Jedrzej Hodor on 10.01.2018.
 */

public class OrcMove extends FunctionalMove {

    @Override
    public Boolean orcMove(SpecialPoints specialPoints, Situation situation) {
        specialPoints.setOrc(specialPoints.getOrc() + 3);
        CchMove chg = new CchMove();
        chg.cchMove(situation);
        return true;
    }
}
