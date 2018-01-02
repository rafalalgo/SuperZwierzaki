package Tests;

import Database.SetOfCards;
import Model.Card;
import Model.Colour;
import Model.Function;
import Model.Type;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rafal Byczek on 01.01.2018.
 */

public class SetOfCardsTest {
    public static void main(String[] args) {
        SetOfCards setOfCards = new SetOfCards();

        // są takie wbudowane funkcje jak filtrowanie po colorze, typie, funkcji

        List<Card> list;

        System.out.println("Colour LB:");
        list = setOfCards.getCardsWithColour(Colour.LB);
        for(Card card : list) {
            System.out.println(card);
        }

        System.out.println("Type bat:");
        list = setOfCards.getCardsWithType(Type.bat);
        for(Card card : list) {
            System.out.println(card);
        }

        System.out.println("Function Red:");
        list = setOfCards.getCardsWithFunction(Function.Red);
        for(Card card : list) {
            System.out.println(card);
        }

        // ale można też bardziej wyrafinowanie uzywając tak zwanych wyrażeń lambda
        // to na przykład daje Ci wszystkie zwierzęta z pierwszą literą A w nazwie

        list = setOfCards.getAllCards();
        list = list.stream().filter(item -> item.getName().charAt(0) == 'A').collect(Collectors.toList());
        System.out.println("Zwierzęta na A:");
        for(Card card : list) {
            System.out.println(card);
        }

        setOfCards.clearTable();
        setOfCards.closeConnection();
    }
}
