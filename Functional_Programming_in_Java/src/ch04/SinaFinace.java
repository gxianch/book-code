package ch04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;

public class SinaFinace {
	public static BigDecimal getPrice(final String stockId) {
		try {
			final URL url = 
					new URL("http://hq.sinajs.cn/list="+stockId);
			final BufferedReader reader = 
					new BufferedReader(new InputStreamReader(url.openStream()));
			final String data = reader.readLine();
			return new BigDecimal(data.split(",")[3]);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
