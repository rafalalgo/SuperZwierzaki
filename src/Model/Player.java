package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

/**
 * Created by Jędrzej Hodor on 01.01.2018.
 */

public class Player {
    private Integer number;
    private static List<Card> hand;
    private Integer quant_of_cards;
    private Boolean if_folded = false;
    private String name;

//setting

    public Player(Integer number, Integer quant_of_cards, Boolean if_folded, String name) {
        this.number = number;
        this.quant_of_cards = quant_of_cards;
        this.if_folded = if_folded;
        this.name = name;
        List<Card> hand = new LinkedList<>();
        Player.hand = hand;
    }

    public Integer getQuant_of_cards() {
        return quant_of_cards;
    }

    public void setQuant_of_cards(Integer quant_of_cards) {
        this.quant_of_cards = quant_of_cards;
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

    public Card getHand(Integer i) {
        return hand.get(i);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

//moves

    public Integer whatMove() {
        return Human.askWhatMove();
    }

    public void draw(Card card) {
        hand.add(card);
        quant_of_cards += 1;
    }

    public Card ordinaryMove() {
        Integer card_from_the_hand = Human.askWhatCard(this);
        return hand.get(card_from_the_hand);
    }

    public void playOneCard(Card card_allowed) {
        hand.remove(card_allowed);
        quant_of_cards -= 1;
    }

    public Integer multipleMove(Card card) {
        return Human.askHowMany(this, card);
    }

    public void playFewCards(Integer how_many, Card card) {
        for (Integer i = 0; i < how_many; i++) {
            hand.remove(card);
            quant_of_cards -= 1;
        }
    }

    public Integer whatForcedMove(Integer options) {
        return Human.askWhatForced(options);
    }

    public Integer whatKindOfForcedMove() {
        return Human.askWhatForcedKind();
    }

    public Integer whatDuelMove() {
        return Human.askForDuelMove();
    }

//special

    public void shuffleHand() {
        Collections.shuffle(hand);
    }

    public Card showACard(Integer which) {
        return hand.get(which);
    }

    public Card getThirdCardToThePair(Card card) {
        Integer card_from_the_hand = Human.askForThirdCard(this, card);
        return hand.get(card_from_the_hand);
    }

    public Integer checkHowManyExactCardsInHand(Card card) {
        Integer how_many = 0;
        for (Integer i = 0; i < this.quant_of_cards; i++) {
            if (this.getHand(i) == card) {
                how_many += 1;
            }
        }
        return how_many;
    }

    public Boolean tenColours(Card card) {
        List<Card> played = new LinkedList<>();
        played.add(card);
        for(int i = 0; i < 9; i++) {
            Integer got = Human.askForTenColours(this);
            if(got == -1) {
                return false;
            } else {
                Card next = this.getHand(got);
                if (this.ifColourNotInTheList(played, next)) {
                    played.add(next);
                } else {
                    return false;
                }
            }
        }
        for(int i = 0; i < 10; i++) {
            this.playOneCard(played.get(i));
        }
        Supervisor.newCardOnTheHip(played.get(9));
        return true;
    }

    private Boolean ifColourNotInTheList(List<Card> list, Card card) {
        Colour colour = card.getColour();
        for(int i = 0; i < list.size(); i++) {
            Colour colour2 = (list.get(i)).getColour();
            if(colour == colour2) {
                return false;
            }
        }
        return true;
    }

}



