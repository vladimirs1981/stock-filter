
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MakeMeRich {
	public static final List<String> symbols = Arrays.asList("AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
			"AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");

	public static void main(String[] args) {

		// 1. Print these symbols using a Java 8 for-each and lambdas
		Stream<String> stockName = symbols.stream();
		stockName.forEach(System.out::println);
		// 2. Use the StockUtil class to print the price of Bitcoin
		System.out.println(StockUtil.getPrice("BTC-USD"));
		// collectPrices.forEach(System.out::println);
		// 3. Create a new List of StockInfo that includes the stock price

		List<StockInfo> stockprice = StockUtil.prices.entrySet().stream()
				.map(s -> new StockInfo(s.getKey(), s.getValue())).collect(Collectors.toList());
		stockprice.forEach(System.out::println);
		// 4. Find the highest-priced stock under $500
		StockInfo highestStockUnder500 = stockprice.stream().filter(StockUtil.isPriceLessThan(500)).reduce(StockUtil::pickHigh)
				.get();
		System.out.println(highestStockUnder500+" is highest-priced stock under $500");

	}

}
