package Tests;

import Database.SetOfCards;
import Model.Card;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rafal Byczek on 01.01.2018.
 */

public class SetOfCardsTest {
    public static void main(String[] args) {
        SetOfCards setOfCards = new SetOfCards();

        setOfCards.insertCard(1, 10, "A", "V", "bir", "War");
        List<Card> list = setOfCards.getAllCards();
        for(Card card : list) {
            System.out.println(card);
        }
        setOfCards.clearTable();
        setOfCards.closeConnection();
    }
}
