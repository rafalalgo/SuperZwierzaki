package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

/**
 * Created by Jędrzej Hodor on 01.01.2018.
 */

public class Player {
    private int number;
    public List<Card> hand;
    private int quant_of_cards;
    private Boolean if_folded;
    private String name;

//setting

    public Player(int number, int quant_of_cards, Boolean if_folded, String name) {
        this.number = number;
        this.quant_of_cards = quant_of_cards;
        this.if_folded = if_folded;
        this.name = name;
        this.hand = new LinkedList<>();
    }

    public int getQuant_of_cards() {
        return quant_of_cards;
    }

    public void setQuant_of_cards(int quant_of_cards) {
        this.quant_of_cards = quant_of_cards;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Boolean getIfFolded() {
        return if_folded;
    }

    public void setIfFolded(Boolean if_folded) {
        this.if_folded = if_folded;
    }

    public Card getHand(int i) {
        return hand.get(i);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

//moves

    public int whatMove() {
        return Human.askWhatMove();
    }

    public void draw(Card card) {
        hand.add(card);
        quant_of_cards += 1;
    }

    public Card ordinaryMove() {
        int card_from_the_hand = Human.askWhatCard(this);
        return hand.get(card_from_the_hand);
    }

    public void playOneCard(Card card_allowed) {
        hand.remove(card_allowed);
        quant_of_cards -= 1;
    }

    public int multipleMove(Card card) {
        return Human.askHowMany(this, card);
    }

    public void playFewCards(int how_many, Card card) {
        for (int i = 0; i < how_many; i++) {
            hand.remove(card);
            quant_of_cards -= 1;
        }
    }

    public int whatForcedMove(int options) {
        return Human.askWhatForced(options);
    }

    public int whatKindOfForcedMove() {
        return Human.askWhatForcedKind();
    }

    public int whatDuelMove() {
        return Human.askForDuelMove();
    }

//special

    public void shuffleHand() {
        Collections.shuffle(hand);
    }

    public Card showACard(int which) {
        return hand.get(which);
    }

    public Card getThirdCardToThePair(Card card) {
        int card_from_the_hand = Human.askForThirdCard(this, card);
        return hand.get(card_from_the_hand);
    }

    public int checkHowManyExactCardsInHand(Card card) {
        int how_many = 0;
        for (int i = 0; i < this.quant_of_cards; i++) {
            if (this.getHand(i) == card) {
                how_many += 1;
            }
        }
        return how_many;
    }

}



