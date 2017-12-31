package Model;

import java.util.List;
import java.util.Objects;
import java.util.Collections;

public class Player {
    private Integer number;
    private List<Card> hand;
    private Integer quant_of_cards;

//setting

    public Player(Integer number, Integer quant_of_cards) {
        this.number = number;
        this.quant_of_cards = quant_of_cards;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void getHand() {
        //jakieś wyświetlenie ręki
    }
  
//moves

    public Integer whatMove() {
        Integer what_move = null;
        //gracz wpisuje/losuje ruch, który chce wykonać
        return what_move;
    }

    public Card ordinaryMove() {
        Integer card_from_the_hand = null;
        //gracz wybiera kartę
        return hand.get(card_from_the_hand);
    }

    public void moveAllowed(Card card_allowed) {
        hand.remove(card_allowed);
        quant_of_cards -= 1;
    }

    public void multipleMove() {
        //jak chce zagrać więcej kart, tzn kombinację
    }

    public void draw(Card card) {
        hand.add(card);
        quant_of_cards += 1;
    }
    
    public Card showACard(Integer which) {
        return hand.get(which);
    }
    
    public void shuffleHand() {
        Collections.shuffle(hand);
    }
    
}
