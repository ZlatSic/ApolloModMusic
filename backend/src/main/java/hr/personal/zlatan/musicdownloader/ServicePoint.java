package hr.personal.zlatan.musicdownloader;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

public class ServicePoint {
	public static Call<String> downloadFileLink(String videoId){
		Retrofit r = new Retrofit.Builder()
				.baseUrl(ServiceInterface.ENDPOINT)
				.addConverterFactory(new Factory() {
					@Override
					public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
							Retrofit retrofit) {
						return new StringExtractor();
					}
				})
				.build();
		
		ServiceInterface service = r.create(ServiceInterface.class);
		
		try {
			return service.getDownloadLink("www.youtube.com/watch?v=" + videoId);
			/*return link;/*

			while(true){
				try{
					DownloadManager.Request r = new Down
					break;
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}*/
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}
