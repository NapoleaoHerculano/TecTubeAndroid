package com.napoleao.youtubeclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity
            implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;
    private static final String GOOGLE_YOUTUBE_API_KEY = "AIzaSyAC24Tw4U0viHh7tNqVQOvesyEHpGfeanc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById(R.id.viewYouTubePlayer);
        youTubePlayerView.initialize(GOOGLE_YOUTUBE_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Toast.makeText(this, "Sucesso ao iniciar o player!", Toast.LENGTH_SHORT).show();

        if( !wasRestored ){
            youTubePlayer.cueVideo("RIaJHh60hQY");
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Erro ao iniciar o player!", Toast.LENGTH_SHORT).show();
    }
}
