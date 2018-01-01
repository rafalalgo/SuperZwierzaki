package Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jędrzej Hodor on 01.01.2018.
 */

public class Supervisor {

    private List<Card> hip;
    private List<Player> players;
    private Integer players_quant; //powinno być ustawione 1 mniej niż w rzeczyiwtsości, zeby te zabawy z modulo działały

    private Integer whose_move;
    private Colour given_colour;
    private Type given_type;
    private Integer red = 0;
    private Integer orc = 0;
    private Integer green = 0;
    private Integer demand = 0;
    private Integer duel = 0;

    private Card demanded_card;
    private Integer player_who_has_demanded;
    private Card duel_card;

// setting

    public Supervisor(Integer whose_move, Colour given_colour, Type given_type, Integer players_quant, Integer red, Integer orc, Integer green, Integer demand, Card demanded_card, Integer player_who_has_demanded, Integer duel, Card duel_card) {
        this.whose_move = whose_move;
        this.given_colour = given_colour;
        this.given_type = given_type;
        this.players_quant = players_quant;
        this.red = red;
        this.orc = orc;
        this.green = green;
        this.demand = demand;
        this.demanded_card = demanded_card;
        this.player_who_has_demanded = player_who_has_demanded;
        this.duel = duel;
        this.duel_card = duel_card;
    }

    public Integer getWhoseMove() {
        return whose_move;
    }

    public void setWhoseMove(Integer whose_move) {
        this.whose_move = whose_move;
    }

    public Integer getPlayersQuant() {
        return players_quant;
    }

    public void setPlayersQuant(Integer players_quant) {
        this.players_quant = players_quant;
    }

    public void setDeck() {
        //trzeba by pewnie wstawić karty jakoś
    }

    public void setPlayers() {
        //a tu graczy
    }

// game

    public void shuffleDeck() {
        Collections.shuffle(hip);
    }

    public void gameBegin() {
        this.whose_move = 0;
        for (Integer i = 0; i < players_quant; i++) {
            Player player = players.get(i);
            this.draw(5, player);
        }
    }

    public Integer game() {
        this.gameBegin();
        Boolean no_winner = true;
        Player winner = null;
        while (no_winner) {
            if (!this.playTurn()) {
                this.nextTurn();
                //Jakiś komunikat, że źle i spróbuj jeszcze raz. Myślę, że należy zakładać znajomość zasad. 
                //Komunikaty czemu źle to można dopisać kiedyś ewentualnie.
            }
            //Funkcja, która sprawdza, czy gracz, który grał ma jeszcze karty, jeśli nie to przypisuje go do winnera.
        }
        return winner.getNumber();
    }

    public Boolean playTurn() {
        Player player = players.get(this.whose_move);
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

    public void newCardOnTheHip(Card card) {
        this.given_type = card.getType();
        this.given_colour = card.getColour();
    }

    public void nextTurn() {
        this.whose_move += 1;
        this.whose_move = this.whose_move % this.players_quant;
    }

// moves

    public void draw(Integer quantity, Player player) {
        for (Integer i = 0; i < quantity; i++) {
            player.draw(hip.get(0));
            hip.remove(0);
        }
    }

    public Boolean checkIfForced() {
        if (this.red == 0 && this.orc == 0 && this.green == 0 && this.demand == 0) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean forcedMove(Player player) {
        //zakładamy, że nie zdaży się opcja dwóch rodzajów punktów niezerowych (nie powinna xd)

        Integer what_move = player.whatForcedMove();
        Integer what_kind;
        if (what_move == 2) {
            what_kind = player.whatKindOfForcedMove2();
        }

        if (this.red != 0) {
            if (what_move == 1) {
                draw(this.red, player);
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
        } else if (this.orc != 0) {
            if (what_move == 1) {
                draw(this.orc, player);
                this.resetOrc();
                return true;
            } else if (what_move == 2) {
                if (what_kind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.Orc);
                } else if (what_kind == 2) {
                    return this.multipleDemandedFunction(player, Function.Orc);
                }
            }
        } else if (this.green != 0) {
            if (what_move == 1) {
                // Funkcja tracenia kolejek.
                this.resetGreen();
                return true;
            } else if (what_move == 2) {
                if (what_kind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.Green);
                } else if (what_kind == 2) {
                    return this.multipleDemandedFunction(player, Function.Green);
                }
            }
        } else if (this.demand != 0) {
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
    }

    public Boolean duelMove(Player player, Function function) {
        Integer what_move = player.whatDuelMove();

        if (what_move == 1) {
            player.setIfFolded(true);
            return true;
        } else if (what_move == 2) {
            Card card = player.ordinaryMove();
            Function f = card.getFunction();
            if (f == Function.Red) {
                this.duel += 2;
                this.duel_card = card;
                return true;
            } else if (f == function) {
                this.duel += 1;
                this.duel_card = card;
                return true;
            } else {
                return false;
            }
        } else if (what_move == 3) {
            if (duel_card.getFunction() == Function.Red) {
                Card card = player.ordinaryMove();
                Function f = card.getFunction();
                if (f == Function.All || f == Function.Cyr) {
                    this.resetDuel();
                    this.duel_card = card;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    // ordinary

    public Boolean checkIfOrdinaryAllowed(Card card) {
        return (card.getColour() == given_colour || card.getType() == given_type
                || card.getColour() == Colour.ALL || card.getType() == Type.all);
    }

    public Boolean ordinaryMove(Player player) {
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

    public Boolean checkDemandedFunction(Card card, Function function) {
        return (card.getFunction() == function);
    }

    public Boolean ordinaryDemandedFunction(Player player, Function function) {
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

    public Boolean checkDemandedType(Card card, Type type) {
        return (card.getType() == type);
    }

    public Boolean ordinaryDemandedType(Player player, Type type) {
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

    public Boolean multipleMove(Player player) {
        Card pl_card = player.ordinaryMove();
        Integer how_many;
        if (this.checkIfOrdinaryAllowed(pl_card)) {
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

    public Boolean multipleDemandedFunction(Player player, Function function) {
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

    public Boolean multipleDemandedType(Player player, Type type) {
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

    public void waranTransposition(Player player1, Player player2) {
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

    public void giveCards(Player giver, Player receiver, Integer quant_given) {
        giver.shuffleHand();
        for (Integer i = 0; i < quant_given; i++) {
            receiver.draw(giver.showACard(0));
            giver.moveAllowed(giver.showACard(0));
        }
    }

    public void addRed(Integer how_many) {
        this.red += how_many;
    }

    public void addOrc(Integer how_many) {
        this.orc += how_many;
    }

    public void addGreen(Integer how_many) {
        this.green += how_many;
    }

    public void addDemand(Integer how_many) {
        this.demand += how_many;
    }

    public void resetRed() {
        this.red = 0;
    }

    public void resetOrc() {
        this.orc = 0;
    }

    public void resetGreen() {
        this.green = 0;
    }

    public void resetDemand() {
        this.demand = 0;
    }

    // duel

    public void addDuel(Integer how_many) {
        this.duel += how_many;
    }

    public void resetDuel() {
        this.duel = 0;
        for (Integer i = 0; i < players_quant; i++) {
            Player player = players.get(i);
            player.setIfFolded(false);
        }
    }

    public Boolean checkIfWinner() {
        Boolean stop = false;
        for (Integer i = 0; i < players_quant; i++) {
            Player player = players.get(i);
            if (!player.getIfFolded() && !stop) {
                stop = true;
            } else if (!player.getIfFolded() && stop) {
                return false;
            }
        }
        return true;
    }

    public Player findWinner() {
        for (Integer i = 0; i < players_quant; i++) {
            Player player = players.get(i);
            if (!player.getIfFolded()) {
                return player;
            }
        }
        return null;
    }

    public Integer duel(Function function, Player triggering_player) {
        Integer index = triggering_player.getNumber();
        Boolean end = false;
        Player winner = null;
        Boolean if_winner = false;

        while (!end) {
            Player current_player = players.get(index);
            this.duelMove(current_player, function);

            if (this.duel == 0) {
                end = true;
            } else if (this.checkIfWinner()) {
                end = true;
                winner = this.findWinner();
                if_winner = true;
            }

            index = (index + 1) % players_quant;
        }
        this.whose_move = (index + 1) % players_quant;
        this.resetDuel();
        this.newCardOnTheHip(this.duel_card);

        if (if_winner) {
            return winner.getNumber();
        } else {
            return -1;
        }
    } // Zwraca winnera, jeśli został przerwany to -1

}
