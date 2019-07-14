package com.huang.ijkplayer.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;

import com.huang.ijkplayer.example.player.SimpleVideoListener;
import com.huang.ijkplayer.example.player.VideoPlayer;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private VideoPlayer videoPlayer;
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = findViewById(R.id.surfaceView);
        videoPlayer = new VideoPlayer(this);
        videoPlayer.setDisplayer(surfaceView);
        videoPlayer.setVideoListener(new SimpleVideoListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                videoPlayer.start();
            }
        });
        try {
            videoPlayer.load("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        videoPlayer.stop();
        videoPlayer.release();
        super.onDestroy();
    }
}
