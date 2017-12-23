package Model;

import java.util.Objects;

public class Supervisor {
    private Integer whose_move;
    private Integer players_quant; //powinno być ustawione 1 mniej niż w rzeczyiwtsości, zeby te zabawy z modulo działały
    private List<Player> players;
    private Colour given_colour;
    private Class given_class;
    private List<Card> hip;

    public Supervisor( Integer whose_move, Integer players_quant ) {
        this.whose_move = whose_move;
        this.players_quant = players_quant;
    }

    public Integer getWhoseMove() {
        return whose_move;
    }

    public void setWhoseMove( Integer whose_move ) {
        this.whose_move = whose_move;
    }
    
    public Integer getPlayersQuant() {
        return players_quant;
    }

    public void setPlayersQuant( Integer players_quant ) {
        this.players_quant = players_quant;
    }
    
    public void setDeck() {
        //trzeba by pewnie wstawić karty jakoś
    }
    
    public void setPlayers() {
        //a tu graczy
    }
    
    public void shuffleDeck() {
        Collections.shuffle(hip);
    }
    
    public Boolean playTurn( Boolean if_forced, Integer what_points_forcing ) {
        Player player = get.players(this.whose_move);
        Integer type = player.whatMove();
        if(if_forced){
            //tu by trzeba te wszystkie green/red/orc pointsy wtzucić
        }else{
            if( type == 1 ) {
                return this.ordinaryMove( player ); 
            }else if( type == 2 ) {
                //tu ten multiple move
            } else {
                this.draw( 1, player );
                this.nextTurn();
                return 1;
            }
        }
    }
    
    public Boolean checkIfOrdinaryAllowed( Card card ) {
        return (card.colour == given_colour || card.class == given_class);
    }
    
    public void draw( Integer quantity, Player player ){
        for( Integer i = 0; i < quantity; i++ ) {
            player.draw( hip.get(0) );
            hip.remove( 0 );
        }
    }
    
    public void checkIfTheHipEmpty() {
        //jak sama nazwa wskazuje
    }
    
    public void newCardOnTheHip( Card card ) {
        given_class = card.class;
        given_colour = card.colour;
    }
    
    public void nextTurn() {
        this.whose_move += 1;
        this.whose_move = this.whose_move % this.players_quant;
    }
    
    public Boolean ordinaryMove( Player player ) {
        Card pl_card = player.ordinaryMove();
        if( this.checkIfOrdinaryAllowed( pl_card ) {
            player.moveAllowed( pl_card );
            this.newCardOnTheHip( pl_card );
            this.nextTurn();
            return 1;
        }else{
            return 0;
        }
    }
    
    public void gameBegin() {
        this.whose_move = 0;
        for(Integer i = 0; i < players_quant; i++) {
            Player player = get.players(i);
            this.draw( 5, player );
        }
    }
    
    public Integer game() {
        this.gameBegin();
        Boolean no_winner = 1;
        Player winner;
        while( no_winner ) {
            this.playTurn(/*tu sie coś wpisze jak będą funkcje*/);
            //jakaś funkcja, która sprawdza, czy gracz, który grał ma jeszcze karty, jeśli nie to przypisuje go do winnera.
        }
        return winner.number;
    }
}
