package Model;

import java.util.Objects;

public class Player {
    private Integer number;
    private List<Card> hand;

    public Player(Integer number) {
        this.number = number;
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
    
    public Integer whatMove() {
        //gracz wpisuje/losuje ruch, który chce wykonać
        return what_move;
    }
    
    public Card ordinaryMove() {
        //gracz wybiera kartę
        return hand.get(card_from_the_hand);
    }
    
    public void moveAllowed(Card card_allowed){
        hand.remove(card_allowed);
    }
    
    public void multipleMove() {
        //jak chce zagrać więcej kart, tzn kombinację
    }
    
    public void draw(Card card) {
        hand.add(card);
    }

    
}
