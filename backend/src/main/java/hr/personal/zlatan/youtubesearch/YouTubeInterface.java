package hr.personal.zlatan.youtubesearch;

import hr.personal.zlatan.youtubesearch.model.ListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zlatan on 8/13/16.
 */
public interface YouTubeInterface {
    @GET("/youtube/v3/search?part=snippet&maxResults=10&type=video&key=AIzaSyCPV9ie13ECbm3P6cu6NJD_WL-5fiag31Y")
    Call<ListResponse> searchVideos(@Query("q") String query);
}
