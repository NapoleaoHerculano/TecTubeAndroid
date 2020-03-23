package com.napoleao.youtubeclone.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.napoleao.youtubeclone.R;
import com.napoleao.youtubeclone.helper.YouTubeConfig;

public class PlayerActivity extends YouTubeBaseActivity
            implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;
    private String idVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        youTubePlayerView = findViewById(R.id.viewYouTubePlayer);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            idVideo = bundle.getString("idVideo");
            youTubePlayerView.initialize(YouTubeConfig.GOOGLE_YOUTUBE_API_KEY,this);
        }

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Toast.makeText(this, "Sucesso ao iniciar o player!", Toast.LENGTH_SHORT).show();

        youTubePlayer.setFullscreen(true);
        youTubePlayer.setShowFullscreenButton(false);
        youTubePlayer.loadVideo(idVideo);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Erro ao iniciar o player! " + youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
    }
}
