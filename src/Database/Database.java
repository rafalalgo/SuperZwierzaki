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
    List<Card> getAllCards();

    List<Card> getCardsWithColour(Colour colour);

    List<Card> getCardsWithType(Type type);

    List<Card> getCardsWithFunction(Function function);
}
