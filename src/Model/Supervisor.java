package Model;

import java.util.Collections;
import java.util.List;

public class Supervisor {
    private Integer whose_move;
    private Integer players_quant; //powinno być ustawione 1 mniej niż w rzeczyiwtsości, zeby te zabawy z modulo działały
    private List<Player> players;
    private Colour given_colour;
    private Model.Class given_class;
    private List<Card> hip;

//setting

    public Supervisor(Integer whose_move, Integer players_quant) {
        this.whose_move = whose_move;
        this.players_quant = players_quant;
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
    
//game

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
            this.playTurn(/*tu sie coś wpisze jak będą funkcje*/);
            //jakaś funkcja, która sprawdza, czy gracz, który grał ma jeszcze karty, jeśli nie to przypisuje go do winnera.
        }
        return winner.getNumber();
    }

    public Boolean playTurn(Boolean if_forced, Integer what_points_forcing) {
        Player player = players.get(this.whose_move);
        Integer type = player.whatMove();
        if (if_forced) {
            //tu by trzeba te wszystkie green/red/orc pointsy wrzucić
        } else {
            if (type == 1) {
                return this.ordinaryMove(player);
            } else if (type == 2) {
                //tu ten multiple move
            } else {
                this.draw(1, player);
                this.nextTurn();
                return true;
            }
        }
        return true;
    }

    public void draw(Integer quantity, Player player) {
        for (Integer i = 0; i < quantity; i++) {
            player.draw(hip.get(0));
            hip.remove(0);
        }
    }

    public void checkIfTheHipEmpty() {
        //jak sama nazwa wskazuje
    }

    public void newCardOnTheHip(Card card) {
        given_class = card.getCls();
        given_colour = card.getColour();
    }

    public void nextTurn() {
        this.whose_move += 1;
        this.whose_move = this.whose_move % this.players_quant;
    }

    public Boolean ordinaryMove(Player player) {
        Card pl_card = player.ordinaryMove();
        if (this.checkIfOrdinaryAllowed(pl_card)) {
            player.moveAllowed(pl_card);
            this.newCardOnTheHip(pl_card);
            this.nextTurn();
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean checkIfOrdinaryAllowed(Card card) {
        return (card.getColour() == given_colour || card.getCls() == given_class);
    }

//special
    
    public void waranTransposition(Player player1, Player player2) {
        private List<Card> aux;
        
        for(Integer i = 0; i < player2.quant_of_cards; i++){
            aux.add(player2.showACard(0));
            player2.moveAllowed(player2.showACard(0));
        }
        for(Integer i = 0; i < player1.quant_of_cards; i++){
            player2.draw(player1.showACard(0));
            player1.moveAllowed(player1.showACard(0));
        }
        for(Integer i = 0; i < player2.quant_of_cards; i++){
            player1.draw(aux.get(i));
        }
    }
    
    public void waranPermutation(Player master, Player giver, Player receiver) {
        Boolean stop = true;
        while(stop){
            //pytamy gracza master, czy chce wykonać transpozycje
            if(stop){
                //pytamy jakich graczy, on nam wpisuje, że player1 i player2.
                this.waranTransposition(player1, player2);
            }
        }
    }
    
    public void giveCards(Player giver, Player, receiver, Integer quant_given) {
        giver.shuffleHand();
        for(Integer i = 0; i < quant_given; i++){
            receiver.draw(giver.showACard(0));
            giver.moveAllowed(giver.showACard(0));
        }
    }
    
}

