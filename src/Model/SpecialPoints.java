package Model;

/**
 * Created by Rafal Byczek on 08.01.2018.
 */
public class SpecialPoints {
    private int red = 0;
    private int orc = 0;
    private int green = 0;
    private int demand = 0;

    public SpecialPoints() {
    }

    public SpecialPoints(int red, int orc, int green, int demand) {
        this.red = red;
        this.orc = orc;
        this.green = green;
        this.demand = demand;
    }

    public Boolean checkIfForced() {
        return red != 0 || orc != 0 || green != 0 || demand != 0;
    }

    public int whatKindOfForced() {
        if (red != 0 || demand != 0) {
            return 3;
        } else if (orc != 0 || green != 0) {
            return 2;
        }
        return 0;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getOrc() {
        return orc;
    }

    public void setOrc(int orc) {
        this.orc = orc;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }
}
