package Model;

//Pewnie niepotrzebne, ale potrzebuje miec te funkcje statycznie, wiec skopiowalem

/**
 * Created by Jędrzej Hodor on 09.01.2018.
 */

public class Converter {

    public static Colour switchStringIntoColour(String colour) {
        Colour colour2 = Colour.ERROR;
        switch (colour) {
            case "LB":
                colour2 = Colour.LB;
                break;
            case "LG":
                colour2 = Colour.LG;
                break;
            case "R":
                colour2 = Colour.R;
                break;
            case "DB":
                colour2 = Colour.DB;
                break;
            case "V":
                colour2 = Colour.V;
                break;
            case "O":
                colour2 = Colour.O;
                break;
            case "DG":
                colour2 = Colour.DG;
                break;
            case "Y":
                colour2 = Colour.Y;
                break;
            case "P":
                colour2 = Colour.P;
                break;
            case "ALL":
                colour2 = Colour.ALL;
                break;
        }
        return colour2;
    }

    public static Type switchStringIntoType(String type) {
        Type type2 = Type.error;
        switch (type) {
            case "bir":
                type2 = Type.bir;
                break;
            case "hip":
                type2 = Type.hip;
                break;
            case "ins":
                type2 = Type.ins;
                break;
            case "hed":
                type2 = Type.hed;
                break;
            case "rep":
                type2 = Type.rep;
                break;
            case "fis":
                type2 = Type.fis;
                break;
            case "fro":
                type2 = Type.fro;
                break;
            case "bew":
                type2 = Type.bew;
                break;
            case "mky":
                type2 = Type.mky;
                break;
            case "aml":
                type2 = Type.aml;
                break;
            case "cat":
                type2 = Type.cat;
                break;
            case "pig":
                type2 = Type.pig;
                break;
            case "hor":
                type2 = Type.hor;
                break;
            case "bat":
                type2 = Type.bat;
                break;
            case "sea":
                type2 = Type.sea;
                break;
            case "rod":
                type2 = Type.rod;
                break;
            case "tre":
                type2 = Type.tre;
                break;
            case "bea":
                type2 = Type.bea;
                break;
            case "sml":
                type2 = Type.sml;
                break;
            case "all":
                type2 = Type.all;
                break;
            case "koo":
                type2 = Type.koo;
                break;
        }
        return type2;
    }

    public static Function switchStringIntoFunction(String function) {
        Function function2 = Function.ERROR;
        switch (function) {
            case "Dzi":
                function2 = Function.Dzi;
                break;
            case "Kaz":
                function2 = Function.Kaz;
                break;
            case "Cyr":
                function2 = Function.Cyr;
                break;
            case "Red":
                function2 = Function.Red;
                break;
            case "Stp":
                function2 = Function.Stp;
                break;
            case "Mch":
                function2 = Function.Mch;
                break;
            case "War":
                function2 = Function.War;
                break;
            case "Oth":
                function2 = Function.Oth;
                break;
            case "Giv":
                function2 = Function.Giv;
                break;
            case "Cch":
                function2 = Function.Cch;
                break;
            case "Orc":
                function2 = Function.Orc;
                break;
            case "Pol":
                function2 = Function.Pol;
                break;
            case "Dem":
                function2 = Function.Dem;
                break;
            case "All":
                function2 = Function.All;
                break;
            case "None":
                function2 = Function.None;
                break;
        }
        return function2;
    }
}
