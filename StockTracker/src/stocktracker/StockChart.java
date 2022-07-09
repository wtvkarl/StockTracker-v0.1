package stocktracker;

import java.awt.Color;
import java.awt.Graphics2D;

public class StockChart {
    
    //the US stock market is open from 9:30am-4pm (6.5 hours) or 450 mins

    public Stock stock;
    public static int width; //default to 650 pxls
    public static int height; //default to 400 pxls
    public static int xPos;
    public static int yPos;

    public static int chartMax, chartMin;

    private CandleStick[] candles;

    //set xPos 300
    //set yPos 20
    //set width 650
    //set height 500

    public StockChart(int x, int y, int w, int h, Stock s){
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        stock = s;
        initCandles();
    }

    private void initCandles(){
        candles = new CandleStick[5];
    }

    public void draw(Graphics2D g2d){
        g2d.setClip(xPos, yPos, width, height);
        g2d.setColor(Color.darkGray);
        g2d.fillRect(xPos, yPos, width, height);
        g2d.setColor(Color.white);

        int lines = 10;
        int spacing = height / lines;

        for(int i = 0; i < lines; i++){
            g2d.drawLine(xPos, yPos + (i*spacing) + 25, xPos + width, yPos + (i*spacing) + 25);
        }

        g2d.setColor(ColorUtils.UP);
        g2d.fillRect(400, 300, 30, 30);

        resetClip(g2d);
    }

    private void resetClip(Graphics2D g2d){
        g2d.setClip(0,0,App.width, App.height);
    }
	
	public void update() {
        stock.update();
	}

    //getters

    public Stock getStock(){
        return stock;
    }

    public int getXPos(){
        return xPos;
    }

    public int getYPos(){
        return yPos;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public double getChartMin(){
        return chartMin;
    }

    public double getChartMax(){
        return chartMax;
    }

}
