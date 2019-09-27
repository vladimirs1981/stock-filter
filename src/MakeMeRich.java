
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class MakeMeRich {

	final static String API_KEY = "WA3ZKJTWJFWY6YRS";

	public static final List<String> symbols = Arrays.asList("AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
			"AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");

	public static void main(String[] args) throws IOException {

		String date = "2019-09-25";
		String symbol = "IBM";
		System.out.println(highStockPriceOnCurrentDate(symbol, date));

//		// 1. Print these symbols using a Java 8 for-each and lambdas
//		Stream<String> stockName = symbols.stream();
//		stockName.forEach(System.out::println);
//		// 2. Use the StockUtil class to print the price of Bitcoin
//		System.out.println(StockUtil.getPrice("BTC-USD"));
//		// collectPrices.forEach(System.out::println);
//		// 3. Create a new List of StockInfo that includes the stock price
//
//		List<StockInfo> stockprice = StockUtil.prices.entrySet().stream()
//				.map(s -> new StockInfo(s.getKey(), s.getValue())).collect(Collectors.toList());
//		stockprice.forEach(System.out::println);
//		// 4. Find the highest-priced stock under $500
//		StockInfo highestStockUnder500 = stockprice.stream().filter(StockUtil.isPriceLessThan(500)).reduce(StockUtil::pickHigh)
//				.get();
//		System.out.println(highestStockUnder500+" is highest-priced stock under $500");

	}

	private static String highStockPriceOnCurrentDate(String symbol, String date)
			throws MalformedURLException, IOException {
		String rootURL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED";
		rootURL += "&symbol=" + symbol;
		rootURL += "&apikey=" + API_KEY;

		URL request = new URL(rootURL);
		InputStream openStream = request.openStream();
		String response = IOUtils.toString(openStream);

		JSONObject root = new JSONObject(response);
		JSONObject daily = (JSONObject) root.get("Time Series (Daily)");
		JSONObject date1 = (JSONObject) daily.get(date);
		String highprice = (String) date1.get("2. high");
		String openprice = (String) date1.get("1. open");

		return highprice + " is high price and "+openprice+" is the open price for " + symbol + " stock on " + date+".";
	}

}
