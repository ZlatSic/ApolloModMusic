package hr.personal.zlatan.musicdownloader;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceInterface {
	public static final String ENDPOINT = "http://www.youtubeinmp3.com/";

	@GET("download/?")
	Call<String> getDownloadLink(@Query("video") String videoURL);
}
