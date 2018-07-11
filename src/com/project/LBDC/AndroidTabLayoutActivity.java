package com.project.LBDC;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class AndroidTabLayoutActivity extends TabActivity {
	
	public String ss1,ss2;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        

        String[] myStrings = getIntent().getStringArrayExtra("strings");
        ss1 = myStrings[0];
        ss2 = myStrings[1];
        
        
        Intent intent = new Intent (AndroidTabLayoutActivity.this, List.class);
        String[] myStrings1 = new String[] {ss1, ss2};
    	intent.putExtra("strings", myStrings);
    	//intent.putExtra("ss3",ss3);
        startActivity(intent);
        
        
        
        
        
        
        
        
        
        
        TabHost tabHost = getTabHost();
        
        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec("Shops");
        photospec.setIndicator("Shops", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent photosIntent = new Intent(this, List.class);
        photospec.setContent(photosIntent);
        
        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec("Songs");
        // setting Title and Icon for the Tab
        songspec.setIndicator("Songs", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent songsIntent = new Intent(this, SongsActivity.class);
        songspec.setContent(songsIntent);
        
        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("Videos");
        videospec.setIndicator("Videos", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent videosIntent = new Intent(this, VideosActivity.class);
        videospec.setContent(videosIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
    }
}