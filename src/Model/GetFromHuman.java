package Model;

import java.util.Scanner;

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
