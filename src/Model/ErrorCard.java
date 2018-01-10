package Model;

/**
 * Created by Jedrzej Hodor on 08.01.2018.
 */

public class ErrorCard {
    private static Card error;

    public ErrorCard(Card error) {
        this.error = error;
    }

    public static void setError() {
        (ErrorCard.error).setType(Type.error);
        (ErrorCard.error).setColour(Colour.ERROR);
        (ErrorCard.error).setFunction(Function.ERROR);
    }

    public static Card getError() {
        return ErrorCard.error;
    }

}
