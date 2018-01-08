package Model;

/**
 * Created by Jędrzej Hodor on 05.01.2018.
 */

public class Human {

    private static Boolean check1(Integer input, Integer begin, Integer end) {
        return begin <= input && input <= end;
    }

    private static Integer checking(Integer begin, Integer end) {
        Boolean notYet = true;
        Integer er = 0;
        Integer dec = null;
        while (notYet) {
            dec = GetFromHuman.getInt();
            if(dec == -1) {
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

    private static Boolean checkingThird(Player player, Integer numberInHand, Card card) {
        return player.getHand(numberInHand) == card;
    }

    private static void displayWho(Player player) {
        String name = player.getName();
        System.out.println("You are:");
        System.out.println(name);
    }

    private static void displayHand(Player player) {
        System.out.println("Your cards:");
        for (Integer i = 0; i < player.getQuant_of_cards(); i++) {
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

    public static void beginingOfATurn(Player player) {
        Human.displayWho(player);
        Human.displayHand(player);
    }

    public static Integer askWhatMove() {
        Human.displayRulesNormal();
        Human.displayQuestionAction();
        return Human.checking(1, 3);
    }

    public static Integer askWhatCard(Player player) {
        Human.displayCard();
        return Human.checking(0, player.getQuant_of_cards() - 1);
    }

    public static Integer askHowMany(Player player, Card card) {
        Human.displayMultiple();
        return checking(1, player.checkHowManyExactCardsInHand(card));
    }

    public static Integer askWhatForced(Integer options) {
        Human.displayForced();
        return Human.checking(1, options);
    }

    public static Integer askWhatForcedKind() {
        Human.displayForcedKind();
        return Human.checking(1, 2);
    }

    public static Integer askForThirdCard(Player player, Card card) {
        Human.displayThirdCard();
        Integer choosen = Human.checking(0, player.getQuant_of_cards());
        if (Human.checkingThird(player, choosen, card)) {
            Human.error();
            return 0;
        } else {
            return choosen;
        }
    }

    public static Integer askForDuelMove() {
        Human.displayDuel();
        return checking(1, 3);
    }

    public static Integer askForTenColours(Player player) {
        Human.displayTenColours();
        return checking(0,player.getQuant_of_cards() - 1);
    }

}
