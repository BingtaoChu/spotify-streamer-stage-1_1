package com.example.experiment.udacitystageone;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;

/**
 * Created by Bingtao on 5/30/2015.
 */
public class ArtistHitsActivity extends AppCompatActivity {

    private Context ctx = this;
    private String ArtistName = "";
    private String ArtistID = "";
    private ListView trackResults = null;
    private ArrayList<TrackModel> tracks = new ArrayList<TrackModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_hits);
        Initialize();
    }

    private void Initialize() {
        Intent intent = getIntent();
        ArtistName = intent.getStringExtra(getString(R.string.intentMsgArtistName));
        ArtistID = intent.getStringExtra(getString(R.string.intentMsgArtistID));

        trackResults = (ListView)findViewById(R.id.listTracks);

        // Reference: http://stackoverflow.com/questions/14297178/setting-action-bar-title-and-subtitle
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setSubtitle(ArtistName);
        }

        // This code is used to get tracks
        TopTracksForArtistTask trackTask = new TopTracksForArtistTask(this, getString(R.string.topTracksCountryCode));
        trackTask.execute(ArtistID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", tracks);
        super.onSaveInstanceState(outState);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setArtistTracks(Tracks results) {

        int length = results.tracks.size();
        for(int i = 0; i < length; i++)
        {
            Track obj = results.tracks.get(i);
            String songName = obj.name;
            String albumName = null;
            String albumUrl = null;
            if (obj.album != null) {
                albumName = obj.album.name;
            }
            else{
                albumName = "None";
            }

            if (obj.album.images.size() > 0) {
                // Get the smallest one.
                albumUrl = obj.album.images.get(obj.album.images.size() - 1).url;
            }
            else{
                albumUrl = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAOVBMVEXw8PCX2bOR2K/f6+T18fOo3b6s3sHc6uGn3b2k3Lvi7OaU2LG24Mfz8fLt7+6L1qvR59rL5tae27joZ6ITAAAB50lEQVR4nO3cUVYaQRiE0ZERhCFokv0vVndAtacDNfF+C2jqenyc8y+LJEmSJEk/tduOi4Dv637bEuJt/TjststrJDy87LXrkZCwPULC/ggJ+yMk7I+QsD9Cwv4ICfsjJOyPkLA/QsL+CAn7IxwTXpPCYbOemis8Bp0z4rSnZgq/3graHv/UTOFenyqdRUjYP4uQsH8WIWH/LELC/lmEhP2zCAn7ZxES9s8iJOyfRUjYP4uQsH8WIeG33gpuODzhqXnC8xYccfidHUKInnr0lwov12j8n7f7rcfkpSd8bZJ0eAv+/15P4Wc393uK8P4PEg5ESDgeIeFohITjERKORkg4HiHhaISE4xESjkZIOB4h4WiEhOMREo5G+IOE0RWHpMOaHBM4Tfu96zkSbpfkikPU5XgK+jvt986/EuFyS/7uWVv07xBdjQhLgBOb+ZlPZ4SE/RES9kdI2B8hYX+EhP0REvZHSNgfIWF/hIT9ERL2R0j474ZFJQ/NFE5btSzvwQ2Hdd2Cx2YK5636mvURHbN49G2TaatKT664T0PYP4uQsH8WIWH/LELC/lmEhP2zCAn7ZxES9s8iJOyfRUjYP4uQsH8WIWH/LMJB4az7DJ1PhVcjovsMnU8t6dWI/T4lSZIkSdL/2CfxdsD14FbTIQAAAABJRU5ErkJggg==";
            }
            tracks.add(new TrackModel(songName, albumName, albumUrl));

        }
        TrackResultAdapter resultAdapter = new TrackResultAdapter(ctx, tracks);
        trackResults.setAdapter(resultAdapter);
    }
}