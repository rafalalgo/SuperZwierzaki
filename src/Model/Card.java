package Model;

import java.util.Objects;

/**
 * Created by Rafal Byczek on 18.12.2017.
 */

public class Card {
    private Integer number;
    private Integer quantity;
    private String name;
    private Colour colour;
    private Type type;
    private Function function;

    public Card(Integer number, String name, Colour colour, Type type) {
        this.number = number;
        this.name = name;
        this.colour = colour;
        this.type = type;
    }


    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Card(Integer number, Integer quantity, String name, Colour colour, Type type, Function function) {
        this.number = number;
        this.quantity = quantity;
        this.name = name;
        this.colour = colour;
        this.type = type;
        this.function = function;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(number, card.number) &&
                Objects.equals(quantity, card.quantity) &&
                Objects.equals(name, card.name) &&
                colour == card.colour &&
                type == card.type &&
                function == card.function;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, quantity, name, colour, function);
    }
}
