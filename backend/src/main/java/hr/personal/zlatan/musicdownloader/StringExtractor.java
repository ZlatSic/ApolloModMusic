package hr.personal.zlatan.musicdownloader;

import java.util.Scanner;

import org.jsoup.Jsoup;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class StringExtractor implements Converter<ResponseBody, String> {

	@Override
	public String convert(ResponseBody value){
		Scanner scanner = null;
		try {
			Scanner s1 = new Scanner(value.byteStream());
			scanner = s1.useDelimiter("\\A");
			
			String html = scanner.hasNext() ? scanner.next() : "";
			
			value.byteStream().close();
			s1.close();
			scanner.close();
			System.out.println(html);
			return "http://www.youtubeinmp3.com" + Jsoup.parse(html).getElementById("download").attr("href");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}