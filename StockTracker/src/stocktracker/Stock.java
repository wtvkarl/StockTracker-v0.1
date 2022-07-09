package stocktracker;

public class Stock {
    
    private String symbol;
    private double currentPrice;
    private double openPrice;
    private double lastClosePrice;
    private double percentChange;
    private double PERatio;
    private double low;
    private double high;

    private double[] stockInfo;

    public Stock(String sym){

        symbol = sym;
        update();    
        
        stockInfo = new double[7];
        stockInfo[0] = currentPrice;
        stockInfo[1] = openPrice;
        stockInfo[2] = lastClosePrice;
        stockInfo[3] = percentChange;
        stockInfo[4] = PERatio;
        stockInfo[5] = low;
        stockInfo[6] = high;
    }

    public void update(){
        String sym = getSymbol();
        currentPrice = StockReader.getStockPrice(sym);
        openPrice = StockReader.getOpenPrice(sym);
        lastClosePrice = StockReader.getLastClosePrice(sym);
        percentChange = StockReader.getPercentChange(sym);
        PERatio = StockReader.getPERatio(sym);
        low = StockReader.getStockLow(sym);
        high = StockReader.getStockHigh(sym);    
    }

    public String getSymbol(){
        return symbol;
    }

    public double getCurrentPrice(){
        return currentPrice;
    }

    public double getOpenPrice(){
        return openPrice;
    }

    public double getLastClosePrice(){
        return lastClosePrice;
    }

    public double getPercentChange(){
        return percentChange;
    }

    public double getPERatio(){
        return PERatio;
    }

    public double getLow(){
        return low;
    }

    public double getHigh(){
        return high;
    }

    public double getStockInfo(int index){
        return stockInfo[index];
    }

    @Override
    public String toString(){
        return String.format(
            "Symbol: %s\nCurrent Price: %,.2f\nOpen: %,.2f\nClose: %,.2f\nPercentChange: %,.2f", 
            getSymbol(), getCurrentPrice(), getOpenPrice(), getLastClosePrice(), getPercentChange());
    }

}
