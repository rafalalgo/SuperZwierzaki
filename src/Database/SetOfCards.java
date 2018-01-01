package Database;

import java.sql.*;

import Model.Card;
import Model.Colour;
import Model.Function;
import Model.Type;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rafal Byczek on 01.01.2018.
 */

public class SetOfCards implements Database {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:animals.db";
    private Connection conn;
    private Statement stat;

    public SetOfCards() {
        try {
            Class.forName(SetOfCards.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
        System.out.println("Otwarto baze danych.");
        createTables();
    }

    public boolean createTables() {
        String createTableWithAnimals = "CREATE TABLE IF NOT EXISTS animals (nr INTEGER PRIMARY KEY, quantity INTEGER, naame varchar(255), colour varchar(255), tyype varchar(255), function varchar(255))";
        try {
            stat.execute(createTableWithAnimals);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertCard(Integer nr, Integer quantity, String name, String colour, String type, String function) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO animals VALUES(?, ?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, nr);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, colour);
            preparedStatement.setString(5, type);
            preparedStatement.setString(6, function);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu zwierzęcia do bazy.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean clearTable() {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM animals;");
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy usuwaniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void closeConnection() {
        try {
            conn.close();
            System.out.println("Zamknieto baze danych.");
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }

    @Override
    public List<Card> getAllCards() {
        List<Card> cardList = new LinkedList<>();
        try {
            ResultSet resultSet = stat.executeQuery("SELECT * FROM animals;");
            while (resultSet.next()) {
                Integer number = resultSet.getInt("nr");
                Integer quantity = resultSet.getInt("quantity");
                String name = resultSet.getString("naame");

                String colour = resultSet.getString("colour");
                Colour colour2 = this.switchStringIntoColour(colour);

                String type = resultSet.getString("tyype");
                Type type2 = this.switchStringIntoType(type);

                String function = resultSet.getString("function");
                Function function2 = this.switchStringIntoFunction(function);

                Card card = new Card(number, name, colour2, type2, function2);
                cardList.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return cardList;
    }

    @Override
    public List<Card> getCardsWithColour(Colour colour) {
        return null;
    }

    @Override
    public List<Card> getCardsWithClass(Class cl) {
        return null;
    }

    @Override
    public List<Card> getCardsWithFunction(Function function) {
        return null;
    }

    private Colour switchStringIntoColour(String colour) {
        Colour colour2 = null;
        switch (colour) {
            case "LB":
                colour2 = Colour.LB;
                break;
            case "LG":
                colour2 = Colour.LG;
                break;
            case "R":
                colour2 = Colour.R;
                break;
            case "DB":
                colour2 = Colour.DB;
                break;
            case "V":
                colour2 = Colour.V;
                break;
            case "O":
                colour2 = Colour.O;
                break;
            case "DG":
                colour2 = Colour.DG;
                break;
            case "Y":
                colour2 = Colour.Y;
                break;
            case "P":
                colour2 = Colour.P;
                break;
            case "ALL":
                colour2 = Colour.ALL;
                break;
        }
        return colour2;
    }

    private Type switchStringIntoType(String type) {
        Type type2 = null;
        switch (type) {
            case "bir":
                type2 = Type.bir;
                break;
            case "hip":
                type2 = Type.hip;
                break;
            case "ins":
                type2 = Type.ins;
                break;
            case "hed":
                type2 = Type.hed;
                break;
            case "rep":
                type2 = Type.rep;
                break;
            case "fis":
                type2 = Type.fis;
                break;
            case "fro":
                type2 = Type.fro;
                break;
            case "bew":
                type2 = Type.bew;
                break;
            case "mky":
                type2 = Type.mky;
                break;
            case "aml":
                type2 = Type.aml;
                break;
            case "cat":
                type2 = Type.cat;
                break;
            case "pig":
                type2 = Type.pig;
                break;
            case "hor":
                type2 = Type.hor;
                break;
            case "bat":
                type2 = Type.bat;
                break;
            case "see":
                type2 = Type.see;
                break;
            case "rod":
                type2 = Type.rod;
                break;
            case "tre":
                type2 = Type.tre;
                break;
            case "bea":
                type2 = Type.bea;
                break;
            case "sml":
                type2 = Type.sml;
                break;
            case "all":
                type2 = Type.all;
                break;
        }
        return type2;
    }

    private Function switchStringIntoFunction(String function) {
        Function function2 = null;
        switch (function) {
            case "Dzi":
                function2 = Function.Dzi;
                break;
            case "Kaz":
                function2 = Function.Kaz;
                break;
            case "Cyr":
                function2 = Function.Cyr;
                break;
            case "Red":
                function2 = Function.Red;
                break;
            case "Stp":
                function2 = Function.Stp;
                break;
            case "Mch":
                function2 = Function.Mch;
                break;
            case "War":
                function2 = Function.War;
                break;
            case "Oth":
                function2 = Function.Oth;
                break;
            case "Giv":
                function2 = Function.Giv;
                break;
            case "Cch":
                function2 = Function.Cch;
                break;
            case "Orc":
                function2 = Function.Orc;
                break;
            case "Pol":
                function2 = Function.Pol;
                break;
            case "Dem":
                function2 = Function.Dem;
                break;
            case "All":
                function2 = Function.All;
                break;
        }
        return function2;
    }
}
