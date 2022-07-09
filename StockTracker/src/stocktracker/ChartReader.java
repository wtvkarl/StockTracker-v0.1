package stocktracker;

import java.net.URL;
import java.net.URLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.FileWriter;

public class ChartReader {
    
	private static CandleStick[] last5Sticks = new CandleStick[5];

	private static String[][] candleData;

	//this grabs the price data of the last 5 days of the stock.
    public static void getCandleHTMLData(String sym){
        try {
			candleData = new String[5][4];
			URL url = new URL("https://www.historicalstockprice.com/?symbol=" + sym.toLowerCase());
			URLConnection urlC = url.openConnection();
			InputStreamReader inStream = new InputStreamReader(urlC.getInputStream());
			BufferedReader reader = new BufferedReader(inStream);
			
			String line = reader.readLine();

            FileWriter writer = new FileWriter("res/test.txt", true);
            
			while(line != null) {
				String str = line; 
				if(str.contains("for the last five trading days,")){
					for(int i = 0; i < 1; i++){
						int open = str.indexOf("opened at $");
						int high = str.indexOf("high as $");
						int low = str.indexOf("low as $");
						int close = str.indexOf("closed at $");

						System.out.println(String.format("%d %d %d %d", open, high, low, close));

						double openPrice = Double.parseDouble(str.substring(open + 11, open + 17));
						double highPrice = Double.parseDouble(str.substring(high + 9, high + 15));
						double lowPrice = Double.parseDouble(str.substring(low + 8, low + 14));
						double closePrice = Double.parseDouble(str.substring(close + 11, close + 17));

						CandleStick stick = new CandleStick(openPrice, closePrice, lowPrice, highPrice);
						last5Sticks[i] = stick;

						Double.parseDouble((str.substring(low + 8, str.indexOf(", and c"))));
						Double.parseDouble(str.substring(high + 9, str.indexOf(" and")));
						Double.parseDouble(str.substring(open + 11, str.indexOf(", tr")));
						Double.parseDouble(str.substring(close + 11, str.indexOf(".  T")));
					}
				}
				line = reader.readLine();
			}
			
			inStream.close();
			reader.close();
            writer.close();
			
		}
		catch(IOException e) {
			Screen.showBadURL();
		}
		
    }

	public static CandleStick[] getCandleSticks(){
		return last5Sticks;
	}

}
