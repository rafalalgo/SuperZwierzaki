package Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jędrzej Hodor on 01.01.2018.
 */

public class Player {
    public List<Card> hand;
    private int number;
    private int quantOfCards;
    private Boolean ifFolded;
    private String name;

//setting

    public Player(int number, int quantOfCards, Boolean ifFolded, String name) {
        this.number = number;
        this.quantOfCards = quantOfCards;
        this.ifFolded = ifFolded;
        this.name = name;
        this.hand = new LinkedList<>();
    }

    public int getQuantOfCards() {
        return quantOfCards;
    }

    public void setQuantOfCards(int quantOfCards) {
        this.quantOfCards = quantOfCards;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Boolean getIfFolded() {
        return ifFolded;
    }

    public void setIfFolded(Boolean ifFolded) {
        this.ifFolded = ifFolded;
    }

    public Card getHand(int i) {
        return hand.get(i);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


//moves

    public int whatMove() {
        return Human.askWhatMove();
    }

    public void draw(Card card) {
        hand.add(card);
        quantOfCards += 1;
    }

    public Card ordinaryMove() {
        int cardFromTheHand = Human.askWhatCard(this);
        if (cardFromTheHand == -1) {
            return ErrorCard.getError();
        }
        return hand.get(cardFromTheHand);
    }

    public void playOneCard(Card cardAllowed) {
        hand.remove(cardAllowed);
        quantOfCards -= 1;
    }

    public int multipleMove(Card card) {
        return Human.askHowMany(this, card);
    }

    public void playFewCards(int howMany, Card card) {
        for (int i = 0; i < howMany; i++) {
            hand.remove(card);
            quantOfCards -= 1;
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
        int cardFromTheHand = Human.askForThirdCard(this, card);
        if (cardFromTheHand == -1) {
            return ErrorCard.getError();
        }
        return hand.get(cardFromTheHand);
    }

    public int checkHowManyExactCardsInHand(Card card) {
        int howMany = 0;
        for (int i = 0; i < this.quantOfCards; i++) {
            if (this.getHand(i) == card) {
                howMany += 1;
            }
        }
        return howMany;
    }

    public Boolean tenColours(Card card) {
        List<Card> played = new LinkedList<>();
        played.add(card);
        for (int i = 0; i < 9; i++) {
            Integer got = Human.askForTenColours(this);
            if (got == -1) {
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
        for (int i = 0; i < 10; i++) {
            this.playOneCard(played.get(i));
        }
        new Supervisor().newCardOnTheHip(played.get(9));
        return true;
    }

    private Boolean ifColourNotInTheList(List<Card> list, Card card) {
        Colour colour = card.getColour();
        for (int i = 0; i < list.size(); i++) {
            Colour colour2 = (list.get(i)).getColour();
            if (colour == colour2) {
                return false;
            }
        }
        return true;
    }
}



