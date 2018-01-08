package Model;

/**
 * Created by Rafal Byczek on 08.01.2018.
 */
public class Situation {
    private Colour given_colour;
    private Type given_type;
    private Card cardOnTheTop;

    public Situation() {
    }

    public Situation(Colour given_colour, Type given_type, Card cardOnTheTop) {
        this.given_colour = given_colour;
        this.given_type = given_type;
        this.cardOnTheTop = cardOnTheTop;
    }

    public Colour getGivenColour() {
        return given_colour;
    }

    public void setGivenColour(Colour given_colour) {
        this.given_colour = given_colour;
    }

    public Type getGivenType() {
        return given_type;
    }

    public void setGivenType(Type given_type) {
        this.given_type = given_type;
    }

    public Card getCardOnTheTop() {
        return this.cardOnTheTop;
    }

    public void setCardOnTheTop(Card card) {
        this.cardOnTheTop = card;
    }

    @Override
    public String toString() {
        return "Given colour: " + given_colour + "\n" + "Given type: " + given_type; // mozna dodac wypusanie karty
    }
}
