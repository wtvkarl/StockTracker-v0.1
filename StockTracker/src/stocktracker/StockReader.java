package stocktracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class StockReader {

	public static Stock getStock(final String sym){
		return new Stock(sym);
	}

	public static double getStockPrice(final String sym){

		try {
			URL url = new URL("https://stocktwits.com/symbol/" + sym.toUpperCase());
			URLConnection urlC = url.openConnection();
			InputStreamReader inStream = new InputStreamReader(urlC.getInputStream());
			BufferedReader reader = new BufferedReader(inStream);
			
			String line = reader.readLine();
			
			String price = "not found.";
			
			while(line != null) {
				if(line.contains("\"identifier\"")) {
					
					int target = line.indexOf("\"last\"");
					int stockDecimal = line.indexOf(".", target);
					int start = stockDecimal;
					
					while(line.charAt(start) != '\"') {
						start--;
					}
					price = line.substring(start + 2, stockDecimal + 15);
					break;
				}
				line = reader.readLine();
			}
			
            price = filterText(price);

			//closing the connections of both the input/output stream prevents crashing and lag
			inStream.close();
			reader.close();

			return Double.parseDouble(price);
			
		}
		catch(IOException e) {
			Screen.showBadURL();
		}
		
		return 0.0;
	}

	public static double getPriceChange(final String sym){
		try {
			URL url = new URL("https://stocktwits.com/symbol/" + sym.toUpperCase());
			URLConnection urlC = url.openConnection();
			InputStreamReader inStream = new InputStreamReader(urlC.getInputStream());
			BufferedReader reader = new BufferedReader(inStream);
			
			String line = reader.readLine();
			
			String openPrice = "not found.";
			
			while(line != null) {
				if(line.contains("\"identifier\"")) {
					
					int target = line.indexOf("\"change\"");
					int stockDecimal = line.indexOf(".", target);
					int start = stockDecimal;
					
					while(line.charAt(start) != '\"') {
						start--;
					}
					openPrice = line.substring(start + 2, stockDecimal + 15);
					break;
				}
				line = reader.readLine();
			}
			
            openPrice = filterText(openPrice);

			inStream.close();
			reader.close();

			return Double.parseDouble(openPrice);
			
		}
		catch(IOException e) {
			Screen.showBadURL();
		}
		
		return 0.0;
	}

	public static double getOpenPrice(final String sym){
		try {
			URL url = new URL("https://stocktwits.com/symbol/" + sym.toUpperCase());
			URLConnection urlC = url.openConnection();
			InputStreamReader inStream = new InputStreamReader(urlC.getInputStream());
			BufferedReader reader = new BufferedReader(inStream);
			
			String line = reader.readLine();
			
			String openPrice = "not found.";
			
			while(line != null) {
				if(line.contains("\"identifier\"")) {
					
					int target = line.indexOf("\"open\"");
					int stockDecimal = line.indexOf(".", target);
					int start = stockDecimal;
					
					while(line.charAt(start) != '\"') {
						start--;
					}
					openPrice = line.substring(start + 2, stockDecimal + 15);
					break;
				}
				line = reader.readLine();
			}
			
            openPrice = filterText(openPrice);

			inStream.close();
			reader.close();

			return Double.parseDouble(openPrice);
			
		}
		catch(IOException e) {
			Screen.showBadURL();
		}
		
		return 0.0;
	
	}

	public static double getLastClosePrice(final String sym){
		try {
			URL url = new URL("https://stocktwits.com/symbol/" + sym.toUpperCase());
			URLConnection urlC = url.openConnection();
			InputStreamReader inStream = new InputStreamReader(urlC.getInputStream());
			BufferedReader reader = new BufferedReader(inStream);
			
			String line = reader.readLine();
			
			String lastClosePrice = "not found.";
			
			while(line != null) {
				if(line.contains("\"identifier\"")) {
					
					int target = line.indexOf("\"previousClose\"");
					int stockDecimal = line.indexOf(".", target);
					int start = stockDecimal;
					
					while(line.charAt(start) != '\"') {
						start--;
					}
					lastClosePrice = line.substring(start + 2, stockDecimal + 15);
					break;
				}
				line = reader.readLine();
			}
			
            lastClosePrice = filterText(lastClosePrice);

			inStream.close();
			reader.close();

			return Double.parseDouble(lastClosePrice);
			
		}
		catch(IOException e) {
			Screen.showBadURL();
		}
		
		return 0.0;
	
	}

	public static double getPercentChange(final String sym){
		try {
			URL url = new URL("https://stocktwits.com/symbol/" + sym.toUpperCase());
			URLConnection urlC = url.openConnection();
			InputStreamReader inStream = new InputStreamReader(urlC.getInputStream());
			BufferedReader reader = new BufferedReader(inStream);
			
			String line = reader.readLine();
			
			String percentChange = "not found.";
			
			while(line != null) {
				if(line.contains("\"identifier\"")) {
					
					int target = line.indexOf("\"percentChange\"");
					int stockDecimal = line.indexOf(".", target);
					int start = stockDecimal;
					
					while(line.charAt(start) != '\"') {
						start--;
					}
					percentChange = line.substring(start + 2, stockDecimal + 4);
					break;
				}
				line = reader.readLine();
			}
			
            percentChange = filterText(percentChange);

			inStream.close();
			reader.close();

			return Double.parseDouble(percentChange);
			
		}
		catch(IOException e) {
			Screen.showBadURL();
		}
		
		return 0.0;
	}

	public static double getPERatio(final String sym){
		try {
			URL url = new URL("https://stocktwits.com/symbol/" + sym.toUpperCase());
			URLConnection urlC = url.openConnection();
			InputStreamReader inStream = new InputStreamReader(urlC.getInputStream());
			BufferedReader reader = new BufferedReader(inStream);
			
			String line = reader.readLine();
			
			String ratio = "not found.";
			
			while(line != null) {
				if(line.contains("\"fundamentals\"")) {
					
					int target = line.indexOf("\"peRatio\"");
					int stockDecimal = line.indexOf(".", target);
					int start = stockDecimal;
					
					while(line.charAt(start) != '\"') {
						start--;
					}
					ratio = line.substring(start + 1, stockDecimal + 15);
					break;
				}
				line = reader.readLine();
			}
			
            ratio = filterText(ratio);

			inStream.close();
			reader.close();

			return Double.parseDouble(ratio);
			
		}
		catch(IOException e) {
			Screen.showBadURL();
		}
		
		return 0.0;
	}

	public static double getStockLow(final String sym){
		try {
			URL url = new URL("https://stocktwits.com/symbol/" + sym.toUpperCase());
			URLConnection urlC = url.openConnection();
			InputStreamReader inStream = new InputStreamReader(urlC.getInputStream());
			BufferedReader reader = new BufferedReader(inStream);
			
			String line = reader.readLine();
			
			String low = "not found.";
			
			while(line != null) {
				if(line.contains("\"identifier\"")) {
					
					int target = line.indexOf("\"low\"");
					int stockDecimal = line.indexOf(".", target);
					int start = stockDecimal;
					
					while(line.charAt(start) != '\"') {
						start--;
					}
					low = line.substring(start + 1, stockDecimal + 15);
					break;
				}
				line = reader.readLine();
			}
			
            low = filterText(low);

			inStream.close();
			reader.close();

			return Double.parseDouble(low);
			
		}
		catch(IOException e) {
			Screen.showBadURL();
		}
		
		return 0.0;
	}

	public static double getStockHigh(final String sym){
		try {
			URL url = new URL("https://stocktwits.com/symbol/" + sym.toUpperCase());
			URLConnection urlC = url.openConnection();
			InputStreamReader inStream = new InputStreamReader(urlC.getInputStream());
			BufferedReader reader = new BufferedReader(inStream);
			
			String line = reader.readLine();
			
			String high = "not found.";
			
			while(line != null) {
				if(line.contains("\"identifier\"")) {
					
					int target = line.indexOf("\"high\"");
					int stockDecimal = line.indexOf(".", target);
					int start = stockDecimal;
					
					while(line.charAt(start) != '\"') {
						start--;
					}
					high = line.substring(start + 1, stockDecimal + 15);
					break;
				}
				line = reader.readLine();
			}
			
            high = filterText(high);

			inStream.close();
			reader.close();

			return Double.parseDouble(high);
			
		}
		catch(IOException e) {
			Screen.showBadURL();
		}
		
		return 0.0;
	}

	private static String filterText(String price){
        String str = "";
		//only grabs characters that are either a number or a decimal point
        for(char c : price.toCharArray()){
            if(Character.isDigit(c) || c == '.' || c == '-'){
                str += c;
            }
        }

		// if the decimal is not in .## format (less than 2 digits after decimal point)
        if(str.substring(str.indexOf(".")).length() < 3){
            str += '0';
        }
		// if the decimal is not in the .## format (more than 2 digits after decimal point)
		else if(str.substring(str.indexOf(".")).length() > 3){
			str = str.substring(0, str.indexOf(".") + 3);
		}

		//removes any extra decimal points by cutting off the string at the first extra one.
		if(str.substring(str.length()-1).equals(".")){
			str = str.substring(0, str.length()-2);
		}

        return str;
    }

}
