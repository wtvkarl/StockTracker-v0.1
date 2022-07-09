package stocktracker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.RenderingHints;

public class Screen extends JPanel implements Runnable, ActionListener{
    
    private boolean running;
    private Thread thread;
    private static final int ticksPerSecond = 60;
    private int ticks = 0;

    private static int screenWidth;
    private static int screenHeight;

    private static JTextField textField;
    private static JButton submitButton;
    private static JLabel stockSymbol;

    private StockQuote quote;
    private StockChart chart;

    private JLabel[] chartLineLabels;

    public Screen(int w, int h){
        screenWidth = w;
        screenHeight = h;
        this.setPreferredSize(new Dimension(w, h));
        this.setLayout(null);
        initStockQuote();
        initStockChart();
    }   

    private void initStockQuote(){
        textField = new JTextField("Enter stock symbol", 16);
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setSize(150, 20);
        textField.setLocation(20,20);

        submitButton = new JButton("Search");
        submitButton.setSize(80, 20);
        submitButton.setLocation(180, 20);
        submitButton.setActionCommand("getQuote");
        submitButton.addActionListener(this);
        submitButton.setFocusable(false);

        quote = new StockQuote();
        quote.setLocation(20, 80);

        stockSymbol = new JLabel("  Stock : ");
        stockSymbol.setSize(200,30);
        stockSymbol.setOpaque(true);
        stockSymbol.setBackground(Color.darkGray);
        stockSymbol.setForeground(Color.white);
        stockSymbol.setLocation(20, 45);

        this.add(textField);
        this.add(submitButton);
        this.add(quote);
        this.add(stockSymbol);
    }

    private void initStockChart(){
        chart = new StockChart(300, 20, 650, 500, null);

        chartLineLabels = new JLabel[10];

        for(int i = 0; i < chartLineLabels.length; i++){
            chartLineLabels[i] = new JLabel("-");
            chartLineLabels[i].setOpaque(true);
            chartLineLabels[i].setSize(50,15);
            chartLineLabels[i].setLocation(chart.getXPos() + chart.getWidth(), chart.getYPos() + 17 + 50 * i);
            chartLineLabels[i].setBackground(Color.darkGray);
            chartLineLabels[i].setForeground(Color.white);
            chartLineLabels[i].setHorizontalAlignment(JLabel.CENTER);
            this.add(chartLineLabels[i]);
        }
    }

    public synchronized void startThread(){
        running = true;
        thread = new Thread(this, "main");
        thread.start();
    }

    public synchronized void stopThread(){
        running = false;
        try{
            thread.join();
        }
        catch(InterruptedException e){}
    }

    @Override
    public void run(){
        int targetUpdates = 60;
        double nsPerSecond = 1_000_000_000.0;
        double drawInterval = nsPerSecond/targetUpdates;
        double delta = 0;
        long then = System.nanoTime();
        long now;
        boolean shouldRender = false;
        
        while(running){
            now = System.nanoTime();
            delta += (now - then) / drawInterval;
            then = now;
            
            if(delta >= 1){
                ticks++;
                update();
                delta--;
                shouldRender = true;
            }
            
            if(shouldRender){
                repaint();
                shouldRender = false;
            }
            
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(ColorUtils.BACKGROUND);
        g2d.fillRect(0, 0, screenWidth, screenHeight);

        chart.draw(g2d);
    }

    public void update(){
        if(ticks == ticksPerSecond * 5){
            //gives us real-time quotes every 5 seconds. no need to used timer tasks
            if(quote.getStock() != null && isValidStockSymbol(textField.getText())){
                quote.getStock().update();
                quote.updateLabels();
                updateChartLabels(quote.getStock());
            }
            ticks = 0;
        }
    }

    private void updateChartLabels(Stock stock){
        if(stock != null){
            //we want the max and min to be 5% above and below (respectfully) the current price.
            for(int i = 0; i < chartLineLabels.length; i++){
                //start at 1.05x the stock's current price
                //decrease by 0.01x every label until we get to the last label (0.95x the current price)
                double val = stock.getCurrentPrice() * 1.05;
                val -= stock.getCurrentPrice() * 0.01 * i;
                chartLineLabels[i].setText(String.format("%,.2f", val));
            }
        }
    }

    private boolean isValidStockSymbol(String str){
        for(char ch : str.toCharArray()){
            if(!Character.isLetter(ch)){
                return false;
            }
        }
        return true;
    }

    public static void showBadURL(){
        textField.setText("Could not find stock.");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("getQuote")){
            if(!isValidStockSymbol(textField.getText())){
                textField.setText("Invalid stock symbol.");
                return;
            }

            textField.setText(textField.getText().toUpperCase());
            stockSymbol.setText("  Stock: " + textField.getText());
            quote.setStock(StockReader.getStock(textField.getText()));
            quote.updateLabels();
            updateChartLabels(quote.getStock());

            ChartReader.getCandleHTMLData(quote.getStock().getSymbol());
        }
    }
}
