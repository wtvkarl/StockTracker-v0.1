package stocktracker;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;

public class StockQuote extends JPanel{

    private Stock stock;
    private static final int width = 250, height = 200;

    private JLabel[] titleLabels;
    private JLabel[] valueLabels;

    private JLabel stockCurrentPrice;
    private JLabel openPrice;
    private JLabel lastClosePrice;
    private JLabel percentChange;
    private JLabel PERatio;
    private JLabel lowPrice;
    private JLabel highPrice;

    private JLabel SCP; //Stock Current Price
    private JLabel OP;  //Open Price 
    private JLabel LCP; //Last Close Price
    private JLabel PC;  //Percent Change
    private JLabel PER; //P/E Ratio
    private JLabel LOW; //low of the stock
    private JLabel HIGH; //high of the stock

    public StockQuote(){
        initLabels();
        this.setSize(width, height);
        this.setLayout(new GridLayout(titleLabels.length, 2, 10, 10));
        this.setBackground(Color.lightGray);
    }

    public void setStock(Stock stock){
        this.stock = stock;
    }

    public Stock getStock(){
        return stock;
    }

    private void initLabels(){
        SCP = new JLabel("  Current Price: ");
        OP = new JLabel("  Open Price: ");
        LCP = new JLabel("  Last Close Price: ");
        PC = new JLabel("  Percent Change: ");
        PER = new JLabel("  P/E Ratio: ");
        LOW = new JLabel("  Low: ");
        HIGH = new JLabel("  High: ");

        titleLabels = new JLabel[7];
        titleLabels[0] = SCP;
        titleLabels[1] = OP;
        titleLabels[2] = LCP;
        titleLabels[3] = PC;
        titleLabels[4] = PER;
        titleLabels[5] = LOW;
        titleLabels[6] = HIGH;

        for(JLabel jl : titleLabels){
            jl.setHorizontalAlignment(JLabel.LEFT);
            jl.setForeground(Color.darkGray);
        }

        stockCurrentPrice = new JLabel();
        openPrice = new JLabel();
        lastClosePrice = new JLabel();
        percentChange = new JLabel();
        PERatio = new JLabel();
        lowPrice = new JLabel();
        highPrice = new JLabel();

        valueLabels = new JLabel[7];
        valueLabels[0] = stockCurrentPrice;
        valueLabels[1] = openPrice;
        valueLabels[2] = lastClosePrice;
        valueLabels[3] = percentChange;
        valueLabels[4] = PERatio;
        valueLabels[5] = lowPrice;
        valueLabels[6] = highPrice;
 
        for(JLabel jl : valueLabels){
            jl.setHorizontalAlignment(JLabel.CENTER);
            jl.setOpaque(true);
            jl.setBackground(Color.darkGray);
            jl.setForeground(Color.white);
        }

        for(int i = 0; i < valueLabels.length; i++){
            this.add(titleLabels[i]);
            this.add(valueLabels[i]);
        }
    }

    public void updateLabels(){
        if(stock == null){
            for(JLabel jl : valueLabels){
                jl.setText("-");
            }
            return;
        }

        for(int i = 0; i < valueLabels.length; i++){
            if(i < 3 || i > 4){ //values handling money decimals
                valueLabels[i].setText(String.format("$%,.2f", stock.getStockInfo(i)));
            }
            else if(i == 3){ // percent change 
                valueLabels[i].setText(String.format("%s ($%,.2f)",
                    Double.toString(stock.getPercentChange()) + "%", 
                    StockReader.getPriceChange(stock.getSymbol())));

                valueLabels[i].setForeground((stock.getPercentChange() > 0 ? ColorUtils.UP : ColorUtils.DOWN));
            }
            else if(i == 4){
                valueLabels[i].setText(Double.toString(stock.getStockInfo(i)));
                valueLabels[i].setForeground((stock.getPERatio() > 0 ? ColorUtils.UP : ColorUtils.DOWN));
            }
            else{
                valueLabels[i].setText(Double.toString(stock.getStockInfo(i)));
            }
        }
    }

}
