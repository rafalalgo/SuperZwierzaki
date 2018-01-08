package Model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jędrzej Hodor on 01.01.2018.
 */

public class Supervisor {
    private static List<Player> players;
    private static Hip hip;
    private static SpecialPoints specialPoints;
    private static Situation situation;
    private static Card demandedCard;
    private static int playerWhoHasDemanded;

    private static int players_quant;

    private static int whose_move;

// setting

    public Supervisor() {
        if(hip == null) {
            Supervisor.players = new LinkedList<>();
            Supervisor.hip = new Hip();
            Supervisor.specialPoints = new SpecialPoints(0, 0, 0, 0);
            Supervisor.situation = new Situation();
        }
    }

    public Supervisor(int whose_move, int players_quant, Card demandedCard, int playerWhoHasDemanded) {
        this();
        Supervisor.whose_move = whose_move;
        Supervisor.players_quant = players_quant;
        Supervisor.demandedCard = demandedCard;
        Supervisor.playerWhoHasDemanded = playerWhoHasDemanded;
    }

    public static int getWhoseMove() {
        return whose_move;
    }

    public static int getPlayersQuant() {
        return players_quant;
    }

    public static Player getPlayers(int i) {
        return players.get(i);
    }

    public static void setWhoseMove(int whose_move) {
        Supervisor.whose_move = whose_move;
    }

    public static void setPlayersQuant(int players_quant) {
        Supervisor.players_quant = players_quant;
    }



    private void setPlayers(int players_quant) {
        for (int i = 0; i < players_quant; i++) {
            Player player = Preparation.setPlayer(i);
            players.add(player);
        }
    }

    private void setFirstCard() {
        Card firstCard = hip.get(0);
        this.newCardOnTheHip(firstCard);
        hip.remove(0);
    }

// game



    private void gameBegin() {
        hip.setDeck();
        hip.shuffleDeck();
        this.setFirstCard();
        Preparation.askForPlayersQuant();
        Supervisor.setWhoseMove(0);
        this.setPlayers(Supervisor.players_quant);
        Preparation.giveCards();
    }

    public String game() {
        this.gameBegin();
        Boolean no_winner = true;
        Player winner = null;
        while (no_winner) {
            Human.beginingOfATurn(players.get(whose_move));
            if (!this.playTurn()) {
                Human.error();
            } else {
                if (Supervisor.ifWinner()) {
                    winner = Supervisor.whoWon();
                    no_winner = false;
                }
                this.nextTurn();
            }
        }
        return winner.getName();
    }

    private Boolean playTurn() {
        System.out.println(situation);
        Player player = players.get(whose_move);
        int type = player.whatMove();
        if (specialPoints.checkIfForced()) {
            return this.forcedMove(player);
        } else {
            if (type == 1) {
                return this.ordinaryMove(player);
            } else if (type == 2) {
                return this.multipleMove(player);
            } else if (type == 3) {
                draw(1, player);
                return true;
            }
        }
        return false;
        //W każdym segmencie powinien być return tzn powinno sprawdzać, czy udało się succesful ruch, czy nie
    }

    private void nextTurn() {
        whose_move += 1;
        whose_move = whose_move % players_quant;
    }

    public static Boolean ifWinner() {
        for (int i = 0; i < players_quant; i++) {
            if (0 == (players.get(i)).getQuant_of_cards()) {
                return true;
            }
        }
        return false;
    }

    public static Player whoWon() {
        for (int i = 0; i < players_quant; i++) {
            if (0 == (players.get(i)).getQuant_of_cards()) {
                return players.get(i);
            }
        }
        return players.get(0);
    }

    private static int findCardInTheHip(Card card) {
        for (int i = 0; i < hip.size(); i++) {
            if (hip.get(i) == card) {
                return i;
            }
        }
        Human.error();
        Human.error();
        return 0;
    }

    private static void playerCardsRemover() {
        for (int i = 0; i < players_quant; ) {
            Player player = players.get(i);
            for (int j = 0; j < player.getQuant_of_cards(); j++) {
                int rm = Supervisor.findCardInTheHip(player.getHand(j));
                hip.remove(rm);
            }
        }
    }

    private static void hipWarden() {
        if (hip.isEmpty()) {
            hip.setDeck();
            Supervisor.playerCardsRemover();
        }
    }

    public void newCardOnTheHip(Card card) {
        this.situation.setGivenColour(card.getColour());
        this.situation.setGivenType(card.getType());
    }

// moves

    static void draw(int quantity, Player player) {
        for (int i = 0; i < quantity; i++) {
            Supervisor.hipWarden();
            player.draw(hip.get(0));
            hip.remove(0);
        }
    }

    private Boolean forcedMove(Player player) {
        //zakładamy, że nie zdaży się opcja dwóch rodzajów punktów niezerowych (nie powinna xd)

        int what_move = player.whatForcedMove(this.specialPoints.whatKindOfForced());
        int what_kind = 0;
        if (what_move == 2) {
            what_kind = player.whatKindOfForcedMove();
        }

        if (this.specialPoints.getRed() != 0) {
            if (what_move == 1) {
                draw(this.specialPoints.getRed(), player);
                this.specialPoints.setRed(0);
                return true;
            } else if (what_move == 2) {
                if (what_kind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.Red);
                } else if (what_kind == 2) {
                    return this.multipleDemandedFunction(player, Function.Red);
                }
            } else if (what_move == 3) {
                if (what_kind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.All);
                } else if (what_kind == 2) {
                    return this.multipleDemandedFunction(player, Function.All);
                }
            }
        } else if (this.specialPoints.getOrc() != 0) {
            if (what_move == 1) {
                draw(this.specialPoints.getOrc(), player);
                this.specialPoints.setOrc(0);
                return true;
            } else if (what_move == 2) {
                if (what_kind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.Orc);
                } else if (what_kind == 2) {
                    return this.multipleDemandedFunction(player, Function.Orc);
                }
            }
        } else if (this.specialPoints.getGreen() != 0) {
            if (what_move == 1) {
                // Funkcja tracenia kolejek.
                this.specialPoints.setGreen(0);
                return true;
            } else if (what_move == 2) {
                if (what_kind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.Stp);
                } else if (what_kind == 2) {
                    return this.multipleDemandedFunction(player, Function.Stp);
                }
            }
        } else if (this.specialPoints.getDemand() != 0) {
            if (what_move == 1) {
                draw(1, player);
                return true;
            } else if (what_move == 2) {
                if (what_kind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.Dem);
                } else if (what_kind == 2) {
                    return this.multipleDemandedFunction(player, Function.Dem);
                }
            } else if (what_move == 3) {
                if (what_kind == 1) {
                    return this.ordinaryDemandedType(player, demandedCard.getType());
                } else if (what_kind == 2) {
                    return this.multipleDemandedType(player, demandedCard.getType());
                }
            }

            if (whose_move == playerWhoHasDemanded) {
                this.specialPoints.setDemand(0);
            }
        }
        return false;
    }

    // ordinary

    private Boolean checkIfOrdinaryAllowed(Card card) {
        return (card.getColour() == situation.getGivenColour() || card.getType() == situation.getGivenType()
                || card.getColour() == Colour.ALL || card.getType() == Type.all);
    }

    private Boolean ordinaryMove(Player player) {
        Card pl_card = player.ordinaryMove();
        if (this.checkIfOrdinaryAllowed(pl_card)) {
            player.playOneCard(pl_card);
            newCardOnTheHip(pl_card);
            // Wykonanie funkcji.
            return true;
        } else {
            return false;
        }
    }

    private Boolean checkDemandedFunction(Card card, Function function) {
        return (card.getFunction() == function);
    }

    private Boolean ordinaryDemandedFunction(Player player, Function function) {
        Card pl_card = player.ordinaryMove();
        if (this.checkDemandedFunction(pl_card, function)) {
            player.playOneCard(pl_card);
            newCardOnTheHip(pl_card);
            // Wykonanie funkcji.
            return true;
        } else {
            return false;
        }
    }

    private Boolean checkDemandedType(Card card, Type type) {
        return (card.getType() == type);
    }

    private Boolean ordinaryDemandedType(Player player, Type type) {
        Card pl_card = player.ordinaryMove();
        if (this.checkDemandedType(pl_card, type)) {
            player.playOneCard(pl_card);
            newCardOnTheHip(pl_card);
            // Wykonanie funkcji.
            return true;
        } else {
            return false;
        }
    }

    //multiple

    private Boolean multipleMove(Player player) {
        Card pl_card = player.ordinaryMove();
        int how_many;
        if (this.checkIfOrdinaryAllowed(pl_card)) {
            how_many = player.multipleMove(pl_card);
            if (how_many == 2) {
                Card extra_card = player.getThirdCardToThePair(pl_card);
                player.playOneCard(extra_card);
            } else if (how_many == 3) {
                // Play Polak.
            } else if (how_many == 4) {
                // Play Kazuar.
            } else if (how_many == 5) {
                // cośtam
            }
            player.playFewCards(how_many, pl_card);
            newCardOnTheHip(pl_card);
            // Wykonanie funkcji odp ilość razy.
            return true;
        } else {
            return false;
        }
    } // 9 kart, 2 warany

    private Boolean multipleDemandedFunction(Player player, Function function) {
        Card pl_card = player.ordinaryMove();
        int how_many;
        if (this.checkDemandedFunction(pl_card, function)) {
            how_many = player.multipleMove(pl_card);
            if (how_many == 2) {
                Card extra_card = player.getThirdCardToThePair(pl_card);
                player.playOneCard(extra_card);
            } else if (how_many == 3) {
                // Play Polak.
            } else if (how_many == 4) {
                // Play Kazuar.
            } else if (how_many == 5) {
                // cośtam
            }
            player.playFewCards(how_many, pl_card);
            newCardOnTheHip(pl_card);
            // Wykonanie funkcji odp ilość razy
            return true;
        } else {
            return false;
        }
    }

    private Boolean multipleDemandedType(Player player, Type type) {
        Card pl_card = player.ordinaryMove();
        int how_many;
        if (this.checkDemandedType(pl_card, type)) {
            how_many = player.multipleMove(pl_card);
            if (how_many == 2) {
                Card extra_card = player.getThirdCardToThePair(pl_card);
                player.playOneCard(extra_card);
            } else if (how_many == 3) {
                // Play Polak.
            } else if (how_many == 4) {
                // Play Kazuar.
            } else if (how_many == 5) {
                // cośtam
            }
            player.playFewCards(how_many, pl_card);
            newCardOnTheHip(pl_card);
            // Wykonanie funkcji odp ilość razy.
            return true;
        } else {
            return false;
        }
    }


// special

    private void waranTransposition(Player player1, Player player2) {
        List<Card> tmp = player1.hand;
        player1.hand = player2.hand;
        player2.hand = tmp;

        int tmpQuant = player1.getQuant_of_cards();
        player1.setQuant_of_cards(player2.getQuant_of_cards());
        player2.setQuant_of_cards(tmpQuant);
    }

    public void waranPermutation(Player master, Player giver, Player receiver) {
        Boolean stop = true;
        while (stop) {
            //pytamy gracza master, czy chce wykonać transpozycje
            if (stop) {
                //pytamy jakich graczy, on nam wpisuje, że player1 i player2.
                Player player1 = null, player2 = null;
                this.waranTransposition(player1, player2);
            }
        }
    }

    public void giveCards(Player giver, Player receiver, int quant_given) {
        giver.shuffleHand();
        for (int i = 0; i < quant_given; i++) {
            receiver.draw(giver.showACard(0));
            giver.playOneCard(giver.showACard(0));
        }
    }
}