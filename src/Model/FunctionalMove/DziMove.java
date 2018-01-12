package Model.FunctionalMove;

import Model.Pair;
import Model.Player;
import Model.Supervisor;

/**
 * Created by Jedrzej Hodor on 12.01.2018.
 */

public class DziMove extends FunctionalMove {

    @Override
    public Boolean dziMove(Pair<Integer,Player> pair, Supervisor supervisor) {
        Integer cond = pair.getFirst();
        if(cond == 0) {
            Player winner = pair.getSecond();
            supervisor.draw(1, winner);
        }
        // 0 to blokowanie wygranej, a 1 to blokowanie 10colours.
        return true;
    }
}
