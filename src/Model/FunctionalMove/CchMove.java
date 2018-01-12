package Model.FunctionalMove;

import Model.Colour;
import Model.Human;
import Model.Situation;

/**
 * Created by Rafal Byczek on 08.01.2018.
 */
public class CchMove extends FunctionalMove {

    public CchMove() {
    }

    @Override
    public Boolean cchMove(Situation situation) {
        Colour colour = Human.askJelen();
        if (colour != Colour.ERROR) {
            situation.setGivenColour(colour);
            return true;
        }
        return false;
    }
}
