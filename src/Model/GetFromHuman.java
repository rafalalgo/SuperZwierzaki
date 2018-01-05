package Model;

import java.util.Scanner;

public class GetFromHuman {

    private Integer getInt() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private String getString() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
}
