package Model;

import java.util.Scanner;

/**
 * Created by Jędrzej Hodor on 05.01.2018.
 */

public class GetFromHuman {

    public static Integer getInt() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static String getString() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
}
