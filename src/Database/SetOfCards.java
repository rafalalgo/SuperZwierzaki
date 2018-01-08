package Database;

import java.sql.*;

import Model.Card;
import Model.Colour;
import Model.Function;
import Model.Type;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println("Database open.");
        createTables();
    }

    public boolean createTables() {
        String createTableWithAnimals = "CREATE TABLE IF NOT EXISTS animals (nr int PRIMARY KEY, quantity int, naame varchar(255), colour varchar(255), tyype varchar(255), function varchar(255))";
        try {
            stat.execute(createTableWithAnimals);
            this.insertCard(5,5, "Sokół Wędrowny", "LB", "bir", "None");
            this.insertCard(8,5, "Igłosternik Białogardły", "LB", "bir", "None");
            this.insertCard(16,5, "Pingwin Cesarski", "LG", "bir", "None");
            this.insertCard(18,5, "Szlamnik Zwyczajny", "LG", "bir", "None");
            this.insertCard(24,5, "Dzięcioł Czarny", "LG", "bir", "Dzi");
            this.insertCard(33,5, "Kazuar Hełmiasty", "R", "bir", "Kaz");
            this.insertCard(38,5, "Kłowacz Kaktusowy", "DB", "bir", "None");
            this.insertCard(41,5, "Wrona Brodata", "DB", "bir", "None");
            this.insertCard(44,5, "Ścierwnik", "DB", "bir", "None");
            this.insertCard(46,5, "Dzięciur Żołędziowy", "DB", "bir", "Dzi");
            this.insertCard(55,5, "Małpożer", "V", "bir", "None");
            this.insertCard(62,2, "Trzewikodziób", "O", "bir", "None");
            this.insertCard(69,2, "Marabut Afrykański", "O", "bir", "None");
            this.insertCard(73,5, "Czapla Zielona", "DG", "bir", "None");
            this.insertCard(75,5, "Kukiel Wielki", "DG", "bir", "None");
            this.insertCard(77,5, "Paszczak Australijski", "DG", "bir", "None");
            this.insertCard(79,5, "Fregata Wielka", "DG", "bir", "None");
            this.insertCard(84,5, "Płomykówka Zwyczajna", "DG", "bir", "None");
            this.insertCard(87,5, "Modroara Hiacyntowa", "Y", "bir", "None");
            this.insertCard(94,5, "Tłuszczak", "Y", "bir", "None");
            this.insertCard(97,5, "Żuraw Krzykliwy", "P", "bir", "None");
            this.insertCard(100,5, "Pazik Czarnosterny", "P", "bir", "None");
            this.insertCard(102,5, "Paw Niebieski", "P", "bir", "None");
            this.insertCard(103,5, "Gorzyk Czerwonogłowy", "P", "bir", "None");
            this.insertCard(104,5, "Cudowronka Błękitna", "P", "bir", "None");
            this.insertCard(105,5, "Głuszec", "P", "bir", "None");
            this.insertCard(107,5, "Cyraneczka zwyczajna", "P", "bir", "Cyr");
            this.insertCard(34,5, "Hipopotam Nilowy", "R", "hip", "Red");
            this.insertCard(6,5, "Rawka Błazen", "LB", "ins", "None");
            this.insertCard(9,5, "Ważka", "LB", "ins", "None");
            this.insertCard(22,5, "Danaid Wędrowny", "LG", "ins", "Stp");
            this.insertCard(35,5, "Komar", "R", "ins", "Red");
            this.insertCard(59,5, "Pszczoła Miodna Afrykańska", "V", "ins", "Stp");
            this.insertCard(83,5, "Koczownica", "DG", "ins", "Stp");
            this.insertCard(88,5, "Cykada Cystosoma Saundersii", "Y", "ins", "None");
            this.insertCard(90,5, "Świerszcz Arachnoscelis Arachnoides", "Y", "ins", "None");
            this.insertCard(93,5, "Wioślak", "Y", "ins", "Mch");
            this.insertCard(96,5, "Krewetka Pistoletowa", "Y", "ins", "None");
            this.insertCard(63,2, "Tenrekowiec Pręgowany", "O", "hed", "None");
            this.insertCard(1,5, "Mamba Czarna", "LB", "rep", "None");
            this.insertCard(25,5, "Grzechotnik Preriowy", "R", "rep", "Red");
            this.insertCard(30,5, "Efa Piaskowa", "R", "rep", "Red");
            this.insertCard(32,5, "Krokodyl Różańcowy", "R", "rep", "Red");
            this.insertCard(43,5, "Aligator Amerykański", "DB", "rep", "None");
            this.insertCard(53,5, "WaranMove z Komodo", "V", "rep", "War");
            this.insertCard(74,5, "Żmija Gabońska", "DG", "rep", "None");
            this.insertCard(80,5, "Toke", "DG", "rep", "None");
            this.insertCard(82,5, "Żółw Sępi", "DG", "rep", "None");
            this.insertCard(29,5, "Tau", "R", "fis", "Red");
            this.insertCard(21,5, "Żaba Chórzystka", "LG", "fro", "None");
            this.insertCard(27,5, "Żaba Psedophryne Corroboree", "R", "fro", "Red");
            this.insertCard(31,5, "Drzewołaz Karłowaty", "R", "fro", "Red");
            this.insertCard(39,5, "Rzekotka Zielona", "DB", "fro", "None");
            this.insertCard(70,2, "Żółwinka Podziemna", "O", "fro", "None");
            this.insertCard(81,5, "Nosorożnica Nosata", "DG", "fro", "None");
            this.insertCard(91,5, "Żaba Coqui", "Y", "fro", "None");
            this.insertCard(108,5, "Chwytnica Czerwonooka", "P", "fro", "None");
            this.insertCard(42,5, "Wydra Morska", "DB", "sml", "None");
            this.insertCard(48,5, "Bóbr Kanadyjski", "DB", "sml", "None");
            this.insertCard(37,5, "Orangutan Borneański", "DB", "mky", "Oth");
            this.insertCard(47,5, "Szympans", "DB", "mky", "Oth");
            this.insertCard(89,5, "Siamang", "Y", "mky", "None");
            this.insertCard(98,5, "Mandryl", "P", "mky", "None");
            this.insertCard(101,5, "Goryl Nizinny", "P", "mky", "None");
            this.insertCard(67,2, "Pancernik Dziewięciopaskowy", "O", "aml", "None");
            this.insertCard(2,5, "Gepard", "LB", "cat", "Giv");
            this.insertCard(36,5, "Likaon", "R", "cat", "Red");
            this.insertCard(52,5, "Tygrys Bengalski", "V", "cat", "None");
            this.insertCard(56,5, "Wilk Europejski", "V", "cat", "None");
            this.insertCard(58,5, "Hiena Centkowana", "V", "cat", "None");
            this.insertCard(76,5, "Pantera Śnieżna", "DG", "cat", "None");
            this.insertCard(12,5, "Guziec Zwyczajny", "LB", "pig", "None");
            this.insertCard(61,2, "Babirussa", "O", "pig", "None");
            this.insertCard(7,5, "Antylopa Skoczek", "LB", "hor", "Giv");
            this.insertCard(10,5, "Gnu Pręgowane", "LB", "hor", "None");
            this.insertCard(20,5, "Wielbłąd Dwugarbny", "LG", "hor", "None");
            this.insertCard(23,5, "Antylopa Widłoroga", "LG", "hor", "None");
            this.insertCard(99,5, "Jeleń Kanadyjski", "P", "hor", "Cch");
            this.insertCard(11,5, "Ogoniak Duży", "LB", "bat", "None");
            this.insertCard(64,2, "Rurkonos Żółtoplamy", "O", "bat", "None");
            this.insertCard(26,5, "Lampart Morski", "R", "sea", "Red");
            this.insertCard(51,5, "Orka Oceaniczna", "V", "sea", "Orc");
            this.insertCard(68,2, "Narwal Jednozębny", "O", "sea", "None");
            this.insertCard(71,2, "Słoń Morski Południowy", "O", "sea", "None");
            this.insertCard(92,5, "Foka Wedela", "Y", "sea", "None");
            this.insertCard(95,5, "Wieloryb Biskajski", "Y", "sea", "None");
            this.insertCard(3,5, "Zając Szarak", "LB", "rod", "None");
            this.insertCard(40,5, "Szczur Wędrowny", "DB", "rod", "None");
            this.insertCard(45,5, "Pręgowiec", "DB", "rod", "None");
            this.insertCard(50,5, "Diabeł Tasmański", "V", "rod", "None");
            this.insertCard(19,5, "Koala", "LG", "tre", "None");
            this.insertCard(28,5, "Kukang Ciemnolicy", "R", "tre", "Red");
            this.insertCard(65,2, "Karłowaty Leniwiec Trójpalczasty", "O", "tre", "None");
            this.insertCard(66,2, "Nosacz Sundyjski", "O", "tre", "Pol");
            this.insertCard(85,5, "Wyjec Czarny", "Y", "tre", "None");
            this.insertCard(106,5, "Lemu Kata", "P", "tre", "None");
            this.insertCard(49,5, "Niedźwiedź Grizzly", "V", "bea", "None");
            this.insertCard(57,5, "Niedźwiedź Polarny", "V", "bea", "None");
            this.insertCard(14,5, "Ratel Miodożerny", "LG", "sml", "Dem");
            this.insertCard(17,5, "Mangusta Pręgowana", "LG", "sml", "Dem");
            this.insertCard(54,5, "Fossa Madagaskarska", "V", "sml", "None");
            this.insertCard(60,5, "Rosomak", "V", "sml", "None");
            this.insertCard(4,4, "Lew Afrykański", "ALL", "all", "All");
            this.insertCard(13,4, "Bawół Afrykański", "ALL", "all", "All");
            this.insertCard(15,4, "Nosorożec Czarny", "ALL", "all", "All");
            this.insertCard(78,4, "Lampart", "ALL", "all", "All");
            this.insertCard(86,4, "Słoń Afrykański", "ALL", "all", "All");
            this.insertCard(109,5, "Alpaka", "LG", "hor", "None");
            this.insertCard(110,5, "Kangur Szary", "LB", "koo", "None");
            this.insertCard(111,5, "Bielik Zwyczajny", "V", "bir", "None");
            this.insertCard(112,5, "Panda Wielka", "P", "tre", "None");
            this.insertCard(113,5, "Żarłacz Biały", "R", "sea", "Red");
            this.insertCard(114,5, "Lis Rudy", "DG", "sml", "None");
            this.insertCard(115,5, "Koń Polski", "Y", "hor", "None");
            this.insertCard(116,5, "Świnia Domowa", "DB", "pig", "None");
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertCard(int nr, int quantity, String name, String colour, String type, String function) {
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
            System.err.println("Blad przy dodawaniu zwierzęcia do bazy."+nr+name);
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
            System.out.println("Database closed.");
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
                int number = resultSet.getInt("nr");
                int quantity = resultSet.getInt("quantity");
                String name = resultSet.getString("naame");

                String colour = resultSet.getString("colour");
                Colour colour2 = this.switchStringIntoColour(colour);

                String type = resultSet.getString("tyype");
                Type type2 = this.switchStringIntoType(type);

                String function = resultSet.getString("function");
                Function function2 = this.switchStringIntoFunction(function);

                Card card = new Card(number, name, colour2, type2, function2);
                for(int i = 0; i < quantity; i++)
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
        List<Card> listCard = this.getAllCards();
        return listCard.stream().filter(item -> item.getColour() == colour).collect(Collectors.toList());
    }

    @Override
    public List<Card> getCardsWithType(Type type) {
        List<Card> listCard = this.getAllCards();
        return listCard.stream().filter(item -> item.getType() == type).collect(Collectors.toList());
    }

    @Override
    public List<Card> getCardsWithFunction(Function function) {
        List<Card> listCard = this.getAllCards();
        return listCard.stream().filter(item -> item.getFunction() == function).collect(Collectors.toList());
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
            case "sea":
                type2 = Type.sea;
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
            case "koo":
                type2 = Type.koo;
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
            case "None":
                function2 = Function.None;
                break;
        }
        return function2;
    }
}
