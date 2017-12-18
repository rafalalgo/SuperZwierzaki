package Model;

import java.util.List;

/**
 * Created by Rafal Byczek on 18.12.2017.
 */

public interface Database {
    public List<Card> getAllCards();

    public List<Card> getCardsWithColour(Colour colour);

    public List<Card> getCardsWithClass(Class cl);

    public List<Card> getCardsWithFunction(Function function);
}
