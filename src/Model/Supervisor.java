package Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jędrzej Hodor on 01.01.2018.
 */

public class Supervisor {

    private static List<Card> hip;
    private static List<Player> players;
    private static Integer players_quant; //powinno być ustawione 1 mniej niż w rzeczyiwtsości, zeby te zabawy z modulo działały

    private static Integer whose_move;
    private static Colour given_colour;
    private static Type given_type;
    private static Integer red = 0;
    private static Integer orc = 0;
    private static Integer green = 0;
    private static Integer demand = 0;

    private static Card demanded_card;
    private static Integer player_who_has_demanded;

// setting

    public Supervisor(Integer whose_move, Colour given_colour, Type given_type, Integer players_quant, Integer red, Integer orc, Integer green, Integer demand, Card demanded_card, Integer player_who_has_demanded) {
        Supervisor.whose_move = whose_move;
        Supervisor.given_colour = given_colour;
        Supervisor.given_type = given_type;
        Supervisor.players_quant = players_quant;
        Supervisor.red = red;
        Supervisor.orc = orc;
        Supervisor.green = green;
        Supervisor.demand = demand;
        Supervisor.demanded_card = demanded_card;
        Supervisor.player_who_has_demanded = player_who_has_demanded;
    }

    public static Integer getWhoseMove() {
        return whose_move;
    }

    public static Integer getPlayersQuant() {
        return players_quant;
    }

    public static Player getPlayers(Integer i) {
        return players.get(i);
    }

    private void setWhoseMove(Integer whose_move) {
        Supervisor.whose_move = whose_move;
    }

    public static void setPlayersQuant(Integer players_quant) {
        Supervisor.players_quant = players_quant;
    }

    public void setDeck() {
        //trzeba by pewnie wstawić karty jakoś
    }

    private void setPlayers(Integer players_quant) {
        for (Integer i = 0; i <= players_quant; i++) {
            players.set(i, Preparation.setPlayer(i));
        }
    }

// game

    public void shuffleDeck() {
        Collections.shuffle(hip);
    }

    private void gameBegin() {
        this.shuffleDeck();
        Preparation.askForPlayersQuant();
        this.setWhoseMove(0);
        this.setPlayers(Supervisor.players_quant);
        Preparation.giveCards();
    }

    public String game() {
        this.gameBegin();
        Boolean no_winner = true;
        Player winner = null;
        while (no_winner) {
            Human.beginingOfATurn();
            if (!this.playTurn()) {
                Human.error();
            } else {
                if (this.ifWinner()) {
                    winner = this.whoWon();
                    no_winner = false;
                }
                this.nextTurn();
            }
        }
        return winner.getName();
    }

    private Boolean playTurn() {
        Player player = players.get(whose_move);
        Integer type = player.whatMove();
        if (this.checkIfForced()) {
            return this.forcedMove(player);
        } else {
            if (type == 1) {
                return this.ordinaryMove(player);
            } else if (type == 2) {
                return this.multipleMove(player);
            } else if (type == 3) {
                this.draw(1, player);
                return true;
            }
        }
        return false;
        //W każdym segmencie powinien być return tzn powinno sprawdzać, czy udało się succesful ruch, czy nie
    }

    public void checkIfTheHipEmpty() {
        //jak sama nazwa wskazuje
    }

    public static void newCardOnTheHip(Card card) {
        given_type = card.getType();
        given_colour = card.getColour();
    }

    private void nextTurn() {
        whose_move += 1;
        whose_move = whose_move % players_quant;
    }

    public Boolean ifWinner() {
        for (Integer i = 0; i < players_quant; i++) {
            if (0 == (players.get(i)).getQuant_of_cards()) {
                return true;
            }
        }
        return false;
    }

    public Player whoWon() {
        for (Integer i = 0; i < players_quant; i++) {
            if (0 == (players.get(i)).getQuant_of_cards()) {
                return players.get(i);
            }
        }
    }

// moves

    static void draw(Integer quantity, Player player) {
        for (Integer i = 0; i < quantity; i++) {
            player.draw(hip.get(0));
            hip.remove(0);
        }
    }

    private Boolean checkIfForced() {
        return red != 0 || orc != 0 || green != 0 || demand != 0;
    }

    private Integer whatKindOfForced() {
        if (Supervisor.red != 0 || Supervisor.demand != 0) {
            return 3;
        } else if (Supervisor.orc != 0 || Supervisor.green != 0) {
            return 2;
        }
    }

    private Boolean forcedMove(Player player) {
        //zakładamy, że nie zdaży się opcja dwóch rodzajów punktów niezerowych (nie powinna xd)

        Integer what_move = player.whatForcedMove(this.whatKindOfForced());
        Integer what_kind = null;
        if (what_move == 2) {
            what_kind = player.whatKindOfForcedMove();
        }

        if (red != 0) {
            if (what_move == 1) {
                draw(red, player);
                this.resetRed();
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
        } else if (orc != 0) {
            if (what_move == 1) {
                draw(orc, player);
                this.resetOrc();
                return true;
            } else if (what_move == 2) {
                if (what_kind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.Orc);
                } else if (what_kind == 2) {
                    return this.multipleDemandedFunction(player, Function.Orc);
                }
            }
        } else if (green != 0) {
            if (what_move == 1) {
                // Funkcja tracenia kolejek.
                this.resetGreen();
                return true;
            } else if (what_move == 2) {
                if (what_kind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.Stp);
                } else if (what_kind == 2) {
                    return this.multipleDemandedFunction(player, Function.Stp);
                }
            }
        } else if (demand != 0) {
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
                    return this.ordinaryDemandedType(player, demanded_card.getType());
                } else if (what_kind == 2) {
                    return this.multipleDemandedType(player, demanded_card.getType());
                }
            }

            if (whose_move == player_who_has_demanded) {
                this.resetDemand();
            }
        }
        return false;
    }

    // ordinary

    private Boolean checkIfOrdinaryAllowed(Card card) {
        return (card.getColour() == given_colour || card.getType() == given_type
                || card.getColour() == Colour.ALL || card.getType() == Type.all);
    }

    private Boolean ordinaryMove(Player player) {
        Card pl_card = player.ordinaryMove();
        if (this.checkIfOrdinaryAllowed(pl_card)) {
            player.playOneCard(pl_card);
            this.newCardOnTheHip(pl_card);
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
            this.newCardOnTheHip(pl_card);
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
            this.newCardOnTheHip(pl_card);
            // Wykonanie funkcji.
            return true;
        } else {
            return false;
        }
    }

    //multiple

    private Boolean multipleMove(Player player) {
        Card pl_card = player.ordinaryMove();
        Integer how_many;
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
            this.newCardOnTheHip(pl_card);
            // Wykonanie funkcji odp ilość razy.
            return true;
        } else {
            return false;
        }
    } // 9 kart, 2 warany

    private Boolean multipleDemandedFunction(Player player, Function function) {
        Card pl_card = player.ordinaryMove();
        Integer how_many;
        if (this.checkDemandedFunction(pl_card, function)) {
            player.playOneCard(pl_card);
            how_many = player.multipleMove();
            if (how_many == 2) {
                Card extra_card = player.getThirdCardToThePair();
                player.playOneCard(extra_card);
            } else if (how_many == 3) {
                // Play Polak.
            } else if (how_many == 4) {
                // Play Kazuar.
            } else if (how_many == 5) {
                // cośtam
            }
            player.playFewCards(how_many, pl_card);
            this.newCardOnTheHip(pl_card);
            // Wykonanie funkcji odp ilość razy
            return true;
        } else {
            return false;
        }
    }

    private Boolean multipleDemandedType(Player player, Type type) {
        Card pl_card = player.ordinaryMove();
        Integer how_many;
        if (this.checkDemandedType(pl_card, type)) {
            player.playOneCard(pl_card);
            how_many = player.multipleMove();
            if (how_many == 2) {
                Card extra_card = player.getThirdCardToThePair();
                player.playOneCard(extra_card);
            } else if (how_many == 3) {
                // Play Polak.
            } else if (how_many == 4) {
                // Play Kazuar.
            } else if (how_many == 5) {
                // cośtam
            }
            player.playFewCards(how_many, pl_card);
            this.newCardOnTheHip(pl_card);
            // Wykonanie funkcji odp ilość razy.
            return true;
        } else {
            return false;
        }
    }


// special

    private void waranTransposition(Player player1, Player player2) {
        List<Card> aux = new LinkedList<>();

        for (Integer i = 0; i < player2.getQuant_of_cards(); i++) {
            aux.add(player2.showACard(0));
            player2.playOneCard(player2.showACard(0));
        }
        for (Integer i = 0; i < player1.getQuant_of_cards(); i++) {
            player2.draw(player1.showACard(0));
            player1.playOneCard(player1.showACard(0));
        }
        for (Integer i = 0; i < player2.getQuant_of_cards(); i++) {
            player1.draw(aux.get(i));
        }
    } // poprawic

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

    public void giveCards(Player giver, Player receiver, Integer quant_given) {
        giver.shuffleHand();
        for (Integer i = 0; i < quant_given; i++) {
            receiver.draw(giver.showACard(0));
            giver.playOneCard(giver.showACard(0));
        }
    }

    public void addRed(Integer how_many) {
        red += how_many;
    }

    public void addOrc(Integer how_many) {
        orc += how_many;
    }

    public void addGreen(Integer how_many) {
        green += how_many;
    }

    public void addDemand(Integer how_many) {
        demand += how_many;
    }

    private void resetRed() {
        red = 0;
    }

    private void resetOrc() {
        orc = 0;
    }

    private void resetGreen() {
        green = 0;
    }

    private void resetDemand() {
        demand = 0;
    }

}
