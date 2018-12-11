package com.example.hilal.uts;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class MediaActivity extends AppCompatActivity {

    VideoView videoView;
    ListView listView;

    ArrayList<String> listVideo;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        videoView = (VideoView) findViewById(R.id.videoView);
        listView = (ListView) findViewById(R.id.listView);

        listVideo = new ArrayList<>();
        listVideo.add("VIDEO & PHOTOGRAPHY SHOWREEL 2016 - tuukkakiviranta.com");
        listVideo.add("7 Mobile Photography Tips & Tricks!");
        listVideo.add("9 photo composition tips (feat. Steve McCurry)");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listVideo);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private Uri dapatkanMedia(String namaMedia){
                return Uri.parse("android.resource://" + getPackageName() +
                        "/raw/" + namaMedia);
            }

            private final void buatPlayer(Uri videoUri){

                videoView.setVideoURI(videoUri);

                //menambah controller playback
                videoView.setMediaController(new MediaController(MediaActivity.this));

                videoView.requestFocus();
                videoView.start();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoModel videoModel = VideoModel.drama[(int) id];

                Uri videoUri = dapatkanMedia(videoModel.getVideoRawId());
                buatPlayer(videoUri);

            }
        });

    }
}
