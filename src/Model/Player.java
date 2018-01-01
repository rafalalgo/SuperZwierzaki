package Model;

import java.util.List;
import java.util.Objects;
import java.util.Collections;

public class Player {
    private Integer number;
    private List<Card> hand;
    private Integer quant_of_cards;
    private Boolean if_folded = false;

//setting

    public Player(Integer number, Integer quant_of_cards, Boolean if_folded) {
        this.number = number;
        this.quant_of_cards = quant_of_cards;
        this.if_folded = if_folded;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    
    public Boolean getIfFolded() {
        return if_folded;
    }

    public void setIfFolded(Boolean if_folded) {
        this.if_folded = if_folded;
    }

    public void getHand() {
        //jakieś wyświetlenie ręki
    }
  
//moves

    public Integer whatMove() {
        Integer what_move = null;
        // Gracz wpisuje/losuje ruch, który chce wykonać.
        // Na tym poziomie trzeba sprawdzać, czy to ma sens.
        // 1 - ordinary
        // 2 - multiple
        // 3 - draw
        return what_move;
    }

    public void draw(Card card) {
        hand.add(card);
        quant_of_cards += 1;
    }

    public Card ordinaryMove() {
        Integer card_from_the_hand = null;
        // gracz wybiera kartę
        return hand.get(card_from_the_hand);
    }

    public void playOneCard(Card card_allowed) {
        hand.remove(card_allowed);
        quant_of_cards -= 1;
    }

    public Integer multipleMove() {
        Integer how_many = null;
        // gracz wybiera liczbę kart
        return how_many;
    }
    
    public void playFewCards(Integer how_many, Card card) {
        for(Integer i = 0; i < how_many; i++){
          hand.remove(card);
          quant_of_cards -= 1;
        }
    }
    
    public Integer whatForcedMove() {
        Integer what_move = null;
        // Gracz wpisuje ruch.
        // 1 - ruch pasywny, tzn dobieranie/stanie.
        // 2 - ruch aktywny, tzn zagranie karty. (w demand zagranie innej rządającej)
        // 3 - inny - dla red wielka 5, dla demand zagranie rządanej karty.
        return what_move;
    }
    
    public Integer whatKindOfForcedMove() {
        Integer what_kind = null;
        // Gracz wpisuje ruch.
        // 1 - ordinary.
        // 2 - multiple.
        return what_kind;
    }

    public Integer whatDuelMove() {
        Integer what_move = null;
        // Gracz wpisuje/losuje ruch, który chce wykonać.
        // 1 - fold
        // 2 - podbicie
        // 3 - Wielka 5 na Red.
        return what_move;
    }
    
//special

    public void shuffleHand() {
        Collections.shuffle(hand);
    }

    public Card showACard(Integer which) {
        return hand.get(which);
    }

    public Card getThirdCardToThePair() {
        Integer card_from_the_hand = null;
        // gracz wybiera kartę
        return hand.get(card_from_the_hand);
    }
    
    
    
}



