package com.andrew.apolloMod.ui.fragments.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.andrew.apolloMod.R;
import com.andrew.apolloMod.ui.adapters.YoutubeSearchResultElementAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.personal.zlatan.youtubesearch.YoutubeSearch;
import hr.personal.zlatan.youtubesearch.model.ListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YoutubeSearchFragment extends Fragment{

    @BindView(R.id.search_query) EditText searchQuery;
    @BindView(R.id.search_results) ListView searchResults;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_youtube_search, container);

        ButterKnife.bind(this, v);

        return v;
    }

    @OnClick(R.id.search_button) void search(){
        String query = searchQuery.getText().toString();
        YoutubeSearch.getInstance().searchVideos(query).enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                BaseAdapter adapter = new YoutubeSearchResultElementAdapter(getContext(), response.body().getItems());
                searchResults.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Toast toast = Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
