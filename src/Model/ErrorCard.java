package Model;

import com.sun.xml.internal.stream.events.EndElementEvent;

/**
 * Created by Jedrzej Hodor on 08.01.2018.
 */

public class ErrorCard {
    private static Card error;

    public ErrorCard(Card error) {
        this.error = error;
    }

    public static void setError() {
        if (ErrorCard.error == null) {
            ErrorCard.error = new Card(1, "", Colour.ALL, Type.all);
        }
        (ErrorCard.error).setType(Type.error);
        (ErrorCard.error).setColour(Colour.ERROR);
        (ErrorCard.error).setFunction(Function.ERROR);
    }

    public static Card getError() {
        return ErrorCard.error;
    }

}
