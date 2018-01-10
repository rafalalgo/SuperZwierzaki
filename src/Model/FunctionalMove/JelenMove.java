package Model.FunctionalMove;

import Model.Colour;
import Model.Human;
import Model.Situation;

/**
 * Created by Rafal Byczek on 08.01.2018.
 */
public class JelenMove extends FunctionalMove {

    public JelenMove() {
    }

    @Override
    public Boolean jelenMove(Situation situation) {
        Colour colour = Human.askJelen();
        if (colour != Colour.ERROR) {
            situation.setGivenColour(colour);
            return true;
        }
        return false;
    }
}
