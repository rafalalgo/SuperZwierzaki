package Model;

/**
 * Created by Rafal Byczek on 18.12.2017.
 */

public class Card {
    private Integer number;
    private Integer quantity;
    private String name;
    private Colour colour;
    private Function function;

    public Card(Integer number, String name, Colour colour) {

        this.number = number;
        this.name = name;
        this.colour = colour;
    }

    public Card(Integer number, Integer quantity, String name, Colour colour, Function function) {
        this.number = number;
        this.quantity = quantity;
        this.name = name;
        this.colour = colour;
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
}