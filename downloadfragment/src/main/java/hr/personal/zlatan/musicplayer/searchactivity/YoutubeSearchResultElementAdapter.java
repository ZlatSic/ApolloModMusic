package hr.personal.zlatan.musicplayer.searchactivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.personal.zlatan.musicdownloader.ServicePoint;
import hr.personal.zlatan.musicplayer.R;
import hr.personal.zlatan.youtubesearch.model.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zlatan on 8/15/16.
 */
public class YoutubeSearchResultElementAdapter extends BaseAdapter{

    private Context context;
    private List<Item> data;
    private static LayoutInflater inflater= null;

    public YoutubeSearchResultElementAdapter(Context c, List<Item> data){
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        context = c;
        this.data = data;
    }

    @Override
    public int getCount() {
        return (data==null)?0:data.size();
    }

    @Override
    public Object getItem(int i) {
        return (data==null)?null:data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return new YoutubeSearchResultElement(context, inflater
                .inflate(R.layout.video_list_element, null), data.get(i)).getV();
    }

    public static class YoutubeSearchResultElement {

        @BindView(R.id.video_thumbnail)
        ImageView thumbnail;
        @BindView(R.id.video_title)
        TextView videoTitle;
        @BindView(R.id.video_details)
        TextView videoDetails;

        private View v;
        private Item i;

        public YoutubeSearchResultElement(Context context, View v, Item item) {
            this.v = v;
            this.i = item;
            ButterKnife.bind(this, v);

            videoTitle.setText(item.getSnippet().getTitle());
            videoDetails.setText("By "+item.getSnippet().getChannelTitle());
            Glide.with(context).load(item.getSnippet().getThumbnails().getDefault().getUrl()).into(thumbnail);
        }

        @OnClick(R.id.download_video)
        public void downloadVideo(){
            ServicePoint.downloadFileLink(i.getId().getVideoId()).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String link = response.body();

                    Uri uri = Uri.parse(link);

                    DownloadManager.Request r = new DownloadManager.Request(uri);

                    r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, i.getSnippet().getTitle()+".mp3");

                    r.allowScanningByMediaScanner();

                    r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                    DownloadManager dm = (DownloadManager) v.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                    dm.enqueue(r);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                }
            });

        }

        @OnClick(R.id.play_video)
        public void playVideo(){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+i.getId().getVideoId()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getV().getContext().startActivity(intent);
        }

        public View getV() {
            return v;
        }
    }
}
