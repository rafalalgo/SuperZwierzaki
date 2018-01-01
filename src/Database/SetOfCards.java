package Database;

import Model.Card;
import Model.Colour;
import Model.Function;

import java.util.List;

/**
 * Created by Rafal Byczek on 01.01.2018.
 */

public class SetOfCards implements Database {

    @Override
    public List<Card> getAllCards() {
        return null;
    }

    @Override
    public List<Card> getCardsWithColour(Colour colour) {
        return null;
    }

    @Override
    public List<Card> getCardsWithClass(Class cl) {
        return null;
    }

    @Override
    public List<Card> getCardsWithFunction(Function function) {
        return null;
    }
}
