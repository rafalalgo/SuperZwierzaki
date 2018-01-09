package Model;

import Database.SetOfCards;

import java.util.Collections;
import java.util.List;

/**
 * Created by Rafal Byczek on 08.01.2018.
 */
public class Hip {
    private List<Card> hip;

    public Hip() {}

    public void setDeck() {
        SetOfCards setOfCards = new SetOfCards();
        hip = setOfCards.getAllCards();
        setOfCards.clearTable();
        setOfCards.closeConnection();
    }

    public void shuffleDeck() {
        Collections.shuffle(hip);
    }

    public Card get(int index) {
        return hip.get(index);
    }

    public void remove(int index) {
        hip.remove(index);
    }

    public int size() {
        return hip.size();
    }

    public boolean isEmpty() {
        return hip.isEmpty();
    }

    public int contains(Card card) {
        for (int i = 0; i < hip.size(); i++) {
            if (hip.get(i) == card) {
                return i;
            }
        }
        return -1;
    }
}
