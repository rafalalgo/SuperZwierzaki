package Model;

import java.util.List;

/**
 * Created by Rafal Byczek on 08.01.2018.
 */
public class Waran {
    private static void waranTransposition(Player player1, Player player2) {
        List<Card> tmp = player1.hand;
        player1.hand = player2.hand;
        player2.hand = tmp;

        int tmpQuant = player1.getQuant_of_cards();
        player1.setQuant_of_cards(player2.getQuant_of_cards());
        player2.setQuant_of_cards(tmpQuant);
    }

    public static void waranPermutation(Player master, Player giver, Player receiver) {
        Boolean stop = true;
        while (stop) {
            //pytamy gracza master, czy chce wykonać transpozycje
            if (stop) {
                //pytamy jakich graczy, on nam wpisuje, że player1 i player2.
                Player player1 = null, player2 = null;
                Waran.waranTransposition(player1, player2);
            }
        }
    }
}
