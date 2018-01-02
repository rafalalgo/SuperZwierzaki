package Database;

import Model.Card;
import Model.Colour;
import Model.Function;
import Model.Type;

import java.util.List;

/**
 * Created by Rafal Byczek on 18.12.2017.
 */

public interface Database {
    public List<Card> getAllCards();

    public List<Card> getCardsWithColour(Colour colour);

    public List<Card> getCardsWithType(Type type);

    public List<Card> getCardsWithFunction(Function function);
}
