package stocktracker;

import java.awt.Color;
import java.awt.Graphics2D;

public class CandleStick {
    private double open;
    private double close;
    private double maxWick;
    private double minWick;
    private Color color; 

    public CandleStick(double o, double c, double min, double max){
        open = o;
        close = c;
        minWick = min;
        maxWick = max;
        color = (o < c) ? ColorUtils.UP : ColorUtils.DOWN;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(color);
    }

    public double getOpenPrice(){
        return open;   
    }

    public double getClosePrice(){
        return close;
    }

    public double getMinWick(){
        return minWick;
    }

    public double getMaxWick(){
        return maxWick;
    }

    public Color getColor(){
        return color;
    }


}
