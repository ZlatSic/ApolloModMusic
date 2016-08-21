package hr.personal.zlatan.youtubesearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hr.personal.zlatan.youtubesearch.model.Item;
import hr.personal.zlatan.youtubesearch.model.ListResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zlatan on 8/13/16.
 */
public class YoutubeSearch {

    private static YoutubeSearch instance;
    public static YoutubeSearch getInstance(){
        if(instance == null) {
            instance = new YoutubeSearch();
        }
        return instance;
    }


    private YouTubeInterface service;
    private YoutubeSearch(){
        Retrofit r = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = r.create(YouTubeInterface.class);
    }

    public Call<ListResponse> searchVideos(String query){
        return service.searchVideos(query);
    }

    public static void main(String[] args){
        System.out.println(getInstance().searchVideos("eminem"));
    }
}
