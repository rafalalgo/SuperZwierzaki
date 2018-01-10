package Model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jędrzej Hodor on 01.01.2018.
 * Edited by Rafal Byczek on 08.01.2018.
 */

public class Supervisor {
    private List<Player> players;
    private Hip hip;
    private SpecialPoints specialPoints;
    private Situation situation;
    private Preparation preparation;
    private Card demandedCard;
    private int playerWhoHasDemanded;
    private int playersQuant;
    private int whoseMove;

// setting

    public Supervisor() {
        if (hip == null) {
            preparation = new Preparation(this);
            players = new LinkedList<>();
            this.hip = new Hip();
            specialPoints = new SpecialPoints(0, 0, 0, 0);
            this.situation = new Situation();
        }
    }

    public Supervisor(int whoseMove, int playersQuant, Card demandedCard, int playerWhoHasDemanded, Card error) {
        this();
        this.whoseMove = whoseMove;
        this.playersQuant = playersQuant;
        this.demandedCard = demandedCard;
        this.playerWhoHasDemanded = playerWhoHasDemanded;
    }

    public int getWhoseMove() {
        return whoseMove;
    }

    public int getPlayersQuant() {
        return playersQuant;
    }

    public Player getPlayers(int i) {
        return players.get(i);
    }

    public void setWhoseMove(int whoseMove) {
        this.whoseMove = whoseMove;
    }

    public void setPlayersQuant(int playersQuant) {
        this.playersQuant = playersQuant;
    }

    private void setPlayers(int playersQuant) {
        for (int i = 0; i < playersQuant; i++) {
            Player player = preparation.setPlayer(i);
            players.add(player);
        }
    }

    private void setFirstCard() {
        Card firstCard = hip.get(0);
        this.newCardOnTheHip(firstCard);
        hip.remove(0);
    }

    public void setPlayersWioslak(List<Player> newq) {
        players.clear();
        for(int i = 0; i < playersQuant; i++) {
            players.add(newq.get(i));
        }
    }


// game

    private void gameBegin() {
        ErrorCard.setError();
        this.hip.setDeck();
        this.hip.shuffleDeck();
        this.setFirstCard();
        this.preparation.askForPlayersQuant();
        this.setWhoseMove(0);
        this.setPlayers(this.playersQuant);
        this.preparation.giveCards();
    }

    public String game() {
        this.gameBegin();
        Boolean noWinner = true;
        Player winner = null;
        while (noWinner) {
            Human.beginingOfATurn(players.get(whoseMove));
            if (!this.playTurn()) {
                Human.error();
            } else {
                if (this.ifWinner()) {
                    winner = this.whoWon();
                    noWinner = false;
                }
                this.nextTurn();
            }
        }
        return winner.getName();
    }

    private Boolean playTurn() {
        System.out.println(situation);
        Player player = players.get(whoseMove);
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
        whoseMove = (whoseMove + 1) % playersQuant;
    }

    public Boolean ifWinner() {
        return players.contains(0);
    }

    public Player whoWon() {
        for (int i = 0; i < playersQuant; i++) {
            if (0 == (players.get(i)).getQuantOfCards()) {
                return players.get(i);
            }
        }
        return players.get(0);
    }

    private int findCardInTheHip(Card card) {
        int tmp = hip.contains(card);
        if (tmp > 0) {
            return tmp;
        }
        Human.error();
        return 0;
    }

    private void playerCardsRemover() {
        for (int i = 0; i < playersQuant; ) {
            Player player = players.get(i);
            for (int j = 0; j < player.getQuantOfCards(); j++) {
                int rm = this.findCardInTheHip(player.getHand(j));
                hip.remove(rm);
            }
        }
    }

    private void hipWarden() {
        if (hip.isEmpty()) {
            hip.setDeck();
            this.playerCardsRemover();
        }
    }

    public void newCardOnTheHip(Card card) {
        situation.setGivenColour(card.getColour());
        situation.setGivenType(card.getType());
        situation.setCardOnTheTop(card);
    }

// moves

    public void draw(int quantity, Player player) {
        for (int i = 0; i < quantity; i++) {
            this.hipWarden();
            player.draw(hip.get(0));
            hip.remove(0);
        }
    }

    private Boolean forcedMove(Player player) {
        //zakładamy, że nie zdaży się opcja dwóch rodzajów punktów niezerowych (nie powinna xd)

        int whatMove = player.whatForcedMove(specialPoints.whatKindOfForced());
        int whatKind = 0;
        if (whatMove == 2 || whatMove == 3) {
            whatKind = player.whatKindOfForcedMove();
        }

        if (specialPoints.getRed() != 0) {
            if (whatMove == 1) {
                draw(specialPoints.getRed(), player);
                specialPoints.setRed(0);
                return true;
            } else if (whatMove == 2) {
                if (whatKind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.Red);
                } else if (whatKind == 2) {
                    return this.multipleDemandedFunction(player, Function.Red);
                }
            } else if (whatMove == 3) {
                if (whatKind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.All);
                } else if (whatKind == 2) {
                    return this.multipleDemandedFunction(player, Function.All);
                }
            }
        } else if (specialPoints.getOrc() != 0) {
            if (whatMove == 1) {
                draw(specialPoints.getOrc(), player);
                specialPoints.setOrc(0);
                return true;
            } else if (whatMove == 2) {
                if (whatKind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.Orc);
                } else if (whatKind == 2) {
                    return this.multipleDemandedFunction(player, Function.Orc);
                }
            }
        } else if (specialPoints.getGreen() != 0) {
            if (whatMove == 1) {
                // Funkcja tracenia kolejek.
                specialPoints.setGreen(0);
                return true;
            } else if (whatMove == 2) {
                if (whatKind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.Stp);
                } else if (whatKind == 2) {
                    return this.multipleDemandedFunction(player, Function.Stp);
                }
            }
        } else if (specialPoints.getDemand() != 0) {
            if (whatMove == 1) {
                draw(1, player);
                return true;
            } else if (whatMove == 2) {
                if (whatKind == 1) {
                    return this.ordinaryDemandedFunction(player, Function.Dem);
                } else if (whatKind == 2) {
                    return this.multipleDemandedFunction(player, Function.Dem);
                }
            } else if (whatMove == 3) {
                if (whatKind == 1) {
                    return this.ordinaryDemandedType(player, demandedCard.getType());
                } else if (whatKind == 2) {
                    return this.multipleDemandedType(player, demandedCard.getType());
                }
            }

            if (whoseMove == playerWhoHasDemanded) {
                specialPoints.setDemand(0);
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
        Card plCard = player.ordinaryMove();
        if (this.checkIfOrdinaryAllowed(plCard)) {
            player.playOneCard(plCard);
            newCardOnTheHip(plCard);
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
        Card plCard = player.ordinaryMove();
        if (this.checkDemandedFunction(plCard, function)) {
            player.playOneCard(plCard);
            newCardOnTheHip(plCard);
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
        Card plCard = player.ordinaryMove();
        if (this.checkDemandedType(plCard, type)) {
            player.playOneCard(plCard);
            newCardOnTheHip(plCard);
            // Wykonanie funkcji.
            return true;
        } else {
            return false;
        }
    }

    //multiple

    private Boolean helpMove(Player player, Card plCard) {
        Boolean ifSuccesfull = false;
        int howMany = player.multipleMove(plCard);
        if (howMany == 2) {
            Card extraCard = player.getThirdCardToThePair(plCard);
            if(extraCard == ErrorCard.getError()) {
                return false;
            }
            player.playOneCard(extraCard);
        } else if (howMany == 3) {
            // Play Polak.
        } else if (howMany == 4) {
            // Play Kazuar.
        } else if (howMany == 5) {
            // cośtam
        } else if (howMany == 1) {
            return player.tenColours(plCard);
        }
        if(ifSuccesfull && howMany != 1) {
            player.playFewCards(howMany, plCard);
            newCardOnTheHip(plCard);
            // Wykonanie funkcji odp ilość razy.
            return true;
        }
        return false;
    }

    private Boolean multipleMove(Player player) {
        Card plCard = player.ordinaryMove();
        if (this.checkIfOrdinaryAllowed(plCard)) {
            return this.helpMove(player, plCard);
        }
        return false;
    } // 2 warany

    private Boolean multipleDemandedFunction(Player player, Function function) {
        Card plCard = player.ordinaryMove();
        if (this.checkDemandedFunction(plCard, function)) {
            return this.helpMove(player, plCard);
        }
        return false;
    }

    private Boolean multipleDemandedType(Player player, Type type) {
        Card plCard = player.ordinaryMove();
        if (this.checkDemandedType(plCard, type)) {
            return this.helpMove(player, plCard);
        }
        return false;
    }

// special

    public void giveCards(Player giver, Player receiver, int quantGiven) {
        giver.shuffleHand();
        for (int i = 0; i < quantGiven; i++) {
            receiver.draw(giver.showACard(0));
            giver.playOneCard(giver.showACard(0));
        }
    }

    public void giveChoosenCards(Player giver, Player receiver, int quantGiven) {
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers = Human.askWhatCardsFromHand(giver, quantGiven);

        for (int i = 0; i < quantGiven; i++) {
            Integer curr = numbers.get(i);
            receiver.draw(giver.showACard(curr - i));
            giver.playOneCard(giver.showACard(curr - i));
        }
    }
}
