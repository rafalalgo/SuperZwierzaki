package Model.FunctionalMove;

import Model.Supervisor;
import Model.Player;

import java.util.LinkedList;

/**
 * Created by Rafal Byczek on 08.01.2018.
 */
public class WioslakMove extends FunctionalMove {
    @Override
    public Boolean wioslakMove(Supervisor supervisor, Player trig) {
        LinkedList<Player> newq = this.switchQueue(supervisor, trig);
        supervisor.setPlayersWioslak(newq);
        return true;
    }

    private LinkedList<Player> switchQueue(Supervisor supervisor, Player trig) {
        LinkedList<Player> queue = new LinkedList<>();
        Integer start = trig.getNumber();
        Integer quantity = supervisor.getPlayersQuant();
        for(int i = 0; i < quantity; i++) {
            queue.addFirst(supervisor.getPlayers((i + start) % quantity));
        }
        supervisor.setWhoseMove(0);
        return queue;
    }
}
