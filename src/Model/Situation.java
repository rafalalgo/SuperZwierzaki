package Model;

/**
 * Created by Rafal Byczek on 08.01.2018.
 */
public class Situation {
    private Colour givenColour;
    private Type givenType;
    private Card cardOnTheTop;

    public Situation() {
    }

    public Situation(Colour givenColour, Type givenType, Card cardOnTheTop) {
        this.givenColour = givenColour;
        this.givenType = givenType;
        this.cardOnTheTop = cardOnTheTop;
    }

    public Colour getGivenColour() {
        return givenColour;
    }

    public void setGivenColour(Colour givenColour) {
        this.givenColour = givenColour;
    }

    public Type getGivenType() {
        return givenType;
    }

    public void setGivenType(Type givenType) {
        this.givenType = givenType;
    }

    public Card getCardOnTheTop() {
        return this.cardOnTheTop;
    }

    public void setCardOnTheTop(Card card) {
        this.cardOnTheTop = card;
    }

    @Override
    public String toString() {
        return "Given colour: " + givenColour + "\n" + "Given type: " + givenType; // mozna dodac wypusanie karty
    }
}
