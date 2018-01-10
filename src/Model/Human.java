package Model;

import java.util.LinkedList;

/**
 * Created by Jędrzej Hodor on 05.01.2018.
 */

public class Human {

// check

    private static Boolean check1(int input, int begin, int end) {
        return begin <= input && input <= end;
    }

    private static int checking(int begin, int end) {
        Boolean notYet = true;
        int er = 0;
        int dec = 0;
        while (notYet) {
            dec = GetFromHuman.getInt();
            if (dec == -1) {
                Human.goBack();
                return -1;
            }
            notYet = !check1(dec, begin, end);
            if (notYet) {
                Human.error();
                if (er == 2) {
                    return -1;
                } else {
                    er += 1;
                }
            }
        }
        return dec;
    }

    private static Boolean checkingThird(Player player, int numberInHand, Card card) {
        return player.getHand(numberInHand) == card;
    }

    private static String checkingJelen() {
        String get = GetFromHuman.getString();
        while (!(get.equals("LB") || get.equals("LG") || get.equals("R") || get.equals("DB") || get.equals("V") ||
                get.equals("O") || get.equals("DG") || get.equals("Y") || get.equals("P"))) {
            get = GetFromHuman.getString();
        }
        return get;
    }

    private static LinkedList<Integer> checkingChooseCards(Integer cardsInHand, Integer quantity) {
        LinkedList<Integer> cards = new LinkedList<>();
        Boolean stop;

        while (true) {
            cards.clear();
            stop = false;
            for (int i = 0; i < quantity && !stop; i++) {
                Integer get = GetFromHuman.getInt();
                if (get == -1) {
                    stop = true;
                    Human.goBack();
                } else if (!check1(get, 0, cardsInHand) || cards.contains(get)) {
                    stop = true;
                    Human.error();
                }
            }
            if (!stop) {
                return cards;
            }
        }
    }

// display

    private static void displayWho(Player player) {
        String name = player.getName();
        System.out.println("You are:");
        System.out.println(name);
    }

    private static void displayHand(Player player) {
        System.out.println("Your cards:");
        for (int i = 0; i < player.getQuantOfCards(); i++) {
            System.out.print(i + " ");
            (player.getHand(i)).displayCard();
        }
    }

    public static void error() {
        System.out.println("Error, try again");
    }

    public static void goBack() {
        System.out.println("You have just returned to the previous screen");
    }

    public static void tryAgain() {
        System.out.println("Try again");
    }

    public static void beginingOfATurn(Player player) {
        Human.displayWho(player);
        Human.displayHand(player);
    }

    private static void displayQuestionAction() {
        System.out.println("What are you willing to do?");
    }

    private static void displayRulesNormal() {
        System.out.println("1 to play one card");
        System.out.println("2 to play a few cards");
        System.out.println("3 to draw one card");
    }

    private static void displayMultiple() {
        System.out.println("How many cards of that type are you willing to play?");
        System.out.println("Choose a number.");
        System.out.println("Choose 1 to play /Ten colours power/");
    }

    private static void displayCard() {
        System.out.println("What card/s are you willing to play?");
        System.out.println("Choose number of a card you are willing to play.");
        System.out.println("If you want to play a few same cards, choose one of them");
    }

    private static void displayForced() {
        System.out.println("You are forced to do some actions!");
        System.out.println("1 to do a passive move (draw/wait)");
        System.out.println("2 to do an active move (play the same functional card)");
        System.out.println("3 to do a special move (block red or play demanded card)");
    }

    private static void displayForcedKind() {
        System.out.println("1 to play one card");
        System.out.println("2 to play a few cards");
    }

    private static void displayThirdCard() {
        System.out.println("You can add one card, chose one.");
    }

    private static void displayDuel() {
        System.out.println("Duel is on!");
        System.out.println("1 to fold");
        System.out.println("2 to play an active card");
        System.out.println("3 to block");
    }

    private static void displayTenColours() {
        System.out.println("Choose another card");
    }

    private static void displayJelen() {
        System.out.println("You can change a given colour.");
        System.out.println("Choose from the following options:");
        System.out.println("LB, LG, R, DB, V, O, DG, Y, P");
    }

    private static void displayWaranIf() {
        System.out.println("Do you want to make a transposition?");
        System.out.println("1 Yes");
        System.out.println("0 No");
    }

    private static void displayWaranWho(Integer q) {
        System.out.println("Choose a player to do a transposition");
        System.out.println("Choose a number from 0 to " + (q - 1));
    }

    private static void displayCardsSituation(Supervisor supervisor) {
        System.out.println("Cards situation:");
        for (int i = 0; i < supervisor.getPlayersQuant(); i++) {
            Player player = supervisor.getPlayers(i);
            System.out.println("Player number " + i + " named " + player.getName()
                    + " has " + player.getQuantOfCards() + " cards.");
        }
    }

    private static void displayWaranGiver(Integer q) {
        System.out.println("Choose player who will give half of his cards to another");
        System.out.println("Choose number from 0 to " + (q - 1));
    }

    private static void displayWaranReceiver(Integer q) {
        System.out.println("Choose player who will receive those cards");
    }

    private static void displayCardsToChoose(Integer quantity) {
        System.out.println("Choose numbers of cards");
        System.out.println("Choose " + quantity + " cards.");
    }

// ask

    public static int askWhatMove() {
        Human.displayRulesNormal();
        Human.displayQuestionAction();
        return Human.checking(1, 3);
    }

    public static int askWhatCard(Player player) {
        Human.displayCard();
        return Human.checking(0, player.getQuantOfCards() - 1);
    }

    public static int askHowMany(Player player, Card card) {
        Human.displayMultiple();
        return checking(1, player.checkHowManyExactCardsInHand(card));
    }

    public static int askWhatForced(int options) {
        Human.displayForced();
        return Human.checking(1, options);
    }

    public static int askWhatForcedKind() {
        Human.displayForcedKind();
        return Human.checking(1, 2);
    }

    public static int askForThirdCard(Player player, Card card) {
        Human.displayThirdCard();
        int choosen = Human.checking(0, player.getQuantOfCards() - 1);
        if (Human.checkingThird(player, choosen, card)) {
            Human.error();
            return -1;
        } else {
            return choosen;
        }
    }

    public static int askForDuelMove() {
        Human.displayDuel();
        return checking(1, 3);
    }

    public static Integer askForTenColours(Player player) {
        Human.displayTenColours();
        return checking(0, player.getQuantOfCards() - 1);
    }

    public static Colour askJelen() {
        Human.displayJelen();
        String col = Human.checkingJelen();
        return Converter.switchStringIntoColour(col);
    }

    public static Boolean askWaranIf() {
        Human.displayWaranIf();
        Integer ifT = GetFromHuman.getInt();
        switch (ifT) {
            case 0:
                return false;
            case 1:
                return true;
        }
        return false;
    }

    public static Integer askWaranWho(Supervisor supervisor) {
        Integer q = supervisor.getPlayersQuant();

        Human.displayCardsSituation(supervisor);
        Human.displayWaranWho(q);
        return checking(0, q);
    }

    public static Pair<Integer, Integer> askWaranGivRec(Supervisor supervisor) {
        Integer q = supervisor.getPlayersQuant();

        Human.displayCardsSituation(supervisor);
        Human.displayWaranGiver(q);
        Integer p1 = checking(0, q);
        Human.displayWaranReceiver(q);
        Integer p2 = checking(0, q);
        return new Pair<>(p1, p2);
    }

    public static LinkedList<Integer> askWhatCardsFromHand(Player player, Integer quantity) {
        Integer cardsInHand = player.getQuantOfCards();

        displayCardsToChoose(quantity);
        if (cardsInHand <= quantity) {
            LinkedList<Integer> cards = new LinkedList<>();
            for (Integer i = 0; i < cardsInHand; i++) {
                cards.add(i);
            }
            return cards;
        } else {
            return Human.checkingChooseCards(cardsInHand, quantity);
        }
    }


}
