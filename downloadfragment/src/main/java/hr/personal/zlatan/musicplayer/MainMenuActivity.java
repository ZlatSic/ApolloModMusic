package hr.personal.zlatan.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hr.personal.zlatan.musicplayer.searchactivity.SearchActivity;
import hr.personal.zlatan.musicplayer.searchactivity.YoutubeSearchFragment;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ((Button)findViewById(R.id.download_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenuActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });
    }


}
