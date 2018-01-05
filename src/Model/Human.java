package Model;

public class Human {

    private Boolean check1(Integer input, Integer begin, Integer end) {
        return begin <= input && input <= end;
    }

    private Integer checking(Integer begin, Integer end) {
        Boolean notYet = true;
        Integer er = 0;
        Integer dec = null;
        while(notYet) {
            dec = GetFromHuman.getInt();
            notYet = !check1(dec, begin, end);
            if(notYet) {
                this.error();
                if(er == 2) {
                    return 0;
                } else {
                    er += 1;
                }
            }
        }
        return dec;
    }

    private Boolean checkingThird(Player player, Integer numberInHand, Card card) {
        return player.getHand(numberInHand) == card;
    }

    private void displayWho(Player player) {
        String name = player.getName();
        System.out.println("You are:");
        System.out.println(name);
    }

    private void displayHand(Player player) {
        System.out.println("Your cards:")
        for(Integer i = 0; i < player.getQuant_of_cards(); i++) {
            System.out.print(i + " ");
            (player.getHand(i)).displayCard();
        }
    }

    public void error() {
        System.out.println("Error, try again");
    }

    private void displayQuestionAction() {
        System.out.println("What are you willing to do?");
    }

    private void displayRulesNormal() {
        System.out.println("1 to play one card");
        System.out.println("2 to play a few cards");
        System.out.println("3 to draw one card");
    }

    private void displayMultiple() {
        System.out.println("How many cards of that type are you willing to play?");
        System.out.println("Choose a number.");
    }

    private void displayCard() {
        System.out.println("What card/s are you willing to play?");
        System.out.println("Choose number of a card you are willing to play.");
        System.out.println("If you want to play a few same cards, choose one of them");
    }

    private void displayForced() {
        System.out.println("You are forced to do some actions!");
        System.out.println("1 to do a passive move (draw/wait)");
        System.out.println("2 to do an active move (play the same functional card)");
        System.out.println("3 to do a special move (block red or play demanded card)");
    }

    private void displayForcedKind() {
        System.out.println("1 to play one card");
        System.out.println("2 to play a few cards");
    }

    private void displayThirdCard() {
        System.out.println("You can add one card, chose one.");
    }

    private void displayDuel() {
        System.out.println("Duel is on!");
        System.out.println("1 to fold");
        System.out.println("2 to play an active card");
        System.out.println("3 to block");
    }



    public void beginingOfATurn(Player player) {
        this.displayWho(player);
        this.displayHand(player);
    }

    public Integer askWhatMove() {
        this.displayRulesNormal();
        this.displayQuestionAction();
        return this.checking(1, 3);
    }

    public Integer askWhatCard(Player player) {
        this.displayCard();
        return this.checking(0, player.getQuant_of_cards() - 1);
    }

    public Integer askHowMany(Player player, Card card) {
        this.displayMultiple();
        return checking(2, player.checkHowManyExactCardsInHand(card));
    }

    public Integer askWhatForced(Integer options) {
        this.displayForced();
        return checking(1, options);
    }

    public Integer askWhatForcedKind() {
        this.displayForcedKind();
        return checking(1,2);
    }

    public Integer askForThirdCard(Player player, Card card) {
        this.displayThirdCard();
        Integer choosen = this.checking(0, player.getQuant_of_cards());
        if(checkingThird(player, choosen, card)) {
            this.error();
            return 0
        } else {
            return choosen;
        }
    }

    public Integer askForDuelMove() {
        this.displayDuel();
        return checking(1,3);
    }

}
