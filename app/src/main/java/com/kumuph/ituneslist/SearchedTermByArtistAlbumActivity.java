package com.kumuph.ituneslist;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kumuph.ituneslist.Adapter.AlbumAdapter;
import com.kumuph.ituneslist.Adapter.ArtistAdapter;
import com.kumuph.ituneslist.DataModel.AlbumDataModel;
import com.kumuph.ituneslist.DataModel.ArtistDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchedTermByArtistAlbumActivity extends AppCompatActivity {

    // Application Bar
    private Toolbar toolbar;
    private TextView textViewsearchedTerm;

    // Instantiating List for ArtistDataModel, Recyclerview, Adapter...
    private RecyclerView recyclerViewArtist;
    private RecyclerView.Adapter artistAdapter;
    private List<ArtistDataModel> artistDataModels;

    // Instantiating List for AlbumDataModel, Recyclerview, Adapter...
    private RecyclerView recyclerViewAlbum;
    private RecyclerView.Adapter albumAdapter;
    private List<AlbumDataModel> albumDataModels;
    private LinearLayoutManager albumLinearLayoutManager;

    SharedPreferences sharedPreferences;
    //Key value pair storing date in Sharedpreferences
    private static String SHARED_PREF_DATE = "datepreferences";
    private static String KEY_DATE_RECENTLY_VISITED = "date";

    // Lazyloader
    LazyLoader lazyLoader;
    private TextView textViewNoArtist;
    private TextView textViewNoAlbum;
    private LinearLayout linearLayoutNoInternet;
    private LinearLayout linearLayoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_term_by_artist_album);

        // Toolbar Initialization...
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textViewsearchedTerm = findViewById(R.id.textViewsearchedTerm);
        //Passing string value to textviewsearchTerm
        textViewsearchedTerm.setText(MainActivity.searchTermValue);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Setting drawable icon on back button navigation into toolbar
        toolbar.setNavigationIcon(ContextCompat.getDrawable(SearchedTermByArtistAlbumActivity.this, R.drawable.ic_back_arrow_activity));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchedTermByArtistAlbumActivity.super.onBackPressed();
            }
        });

        //Initializing Widgets for loading functionalities and Recylerview item count
        //initializing loading widget
        lazyLoader = findViewById(R.id.lazyloader);
        lazyLoader.setAnimDuration(500);
        lazyLoader.setFirstDelayDuration(100);
        lazyLoader.setSecondDelayDuration(200);
        lazyLoader.setInterpolator(new LinearInterpolator());

        textViewNoArtist = findViewById(R.id.textViewNoArtist);
        textViewNoAlbum = findViewById(R.id.textViewNoAlbum);
        linearLayoutMain = findViewById(R.id.linearLayoutMain);
        linearLayoutNoInternet = findViewById(R.id.linearLayoutNoInternet);

        //Initializing Recyclerview, List, Model and LayoutManager for ITunes Artist Search API
        recyclerViewArtist = findViewById(R.id.recyclerViewArtistList);
        recyclerViewArtist.setHasFixedSize(true);
        recyclerViewArtist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        artistDataModels = new ArrayList<>();

        //Initializing Recyclerview, List, Model and LayoutManager for ITunes Artist Search API
        recyclerViewAlbum = findViewById(R.id.recyclerViewAlbumList);
        recyclerViewAlbum.setHasFixedSize(true);
        albumLinearLayoutManager = new GridLayoutManager(this, 2);
        albumAdapter = new AlbumAdapter(SearchedTermByArtistAlbumActivity.this, albumDataModels);
        recyclerViewAlbum.setLayoutManager(albumLinearLayoutManager);
        albumDataModels = new ArrayList<>();

        //Checking network connection if online or offline, if network is active it will fetch from server, if not fetch from Local/Room Database
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting() && artistDataModels != null) {
            fetchAllArtist();
            fetchAllAlbum();
        } else {

                linearLayoutNoInternet.setVisibility(View.VISIBLE);
                linearLayoutMain.setVisibility(View.GONE);
                lazyLoader.setVisibility(View.GONE);
        }

    }

    private void fetchAllArtist(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //pass the searchTermValue to the URL search API to return value
        String url="https://itunes.apple.com/search?term="+ MainActivity.searchTermValue +"&entity=allArtist&attribute=allArtistTerm&country=ph&limit=10";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    //dismiss dialog here
                try {
                    lazyLoader.setVisibility(View.GONE);
                    linearLayoutMain.setVisibility(View.VISIBLE);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        ArtistDataModel artistDataModel = new ArtistDataModel(
                                object.optString("artistName"),
                                object.getString("artistType"),
                                object.getString("artistLinkUrl")


                        );
                        artistDataModels.add(artistDataModel);
                    }
                    artistAdapter = new ArtistAdapter(SearchedTermByArtistAlbumActivity.this, artistDataModels);
                    recyclerViewArtist.setAdapter(artistAdapter);

                    // Get item count for Artist adapter, if count equals 0 then set textViewNoAlbum visible
                    if(artistAdapter.getItemCount() == 0){
                        textViewNoArtist.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: " + jsonException.toString(), Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
        };
        requestQueue.add(stringRequest);
    }

    private void fetchAllAlbum(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Pass the searchTermValue to the URL search API to return value
        String url="https://itunes.apple.com/search?term="+ MainActivity.searchTermValue +"&entity=album&attribute=albumTerm&country=ph&limit=20";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //dismiss dialog here
                try {
                    lazyLoader.setVisibility(View.GONE);
                    linearLayoutMain.setVisibility(View.VISIBLE);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        AlbumDataModel albumDataModel = new AlbumDataModel(
                                object.optString("artworkUrl100"),
                                object.getString("collectionName"),
                                object.getString("collectionViewUrl")


                        );
                        albumDataModels.add(albumDataModel);
                    }
                    albumAdapter = new AlbumAdapter(SearchedTermByArtistAlbumActivity.this, albumDataModels);
                    recyclerViewAlbum.setAdapter(albumAdapter);

                    // Get item count for Album adapter, if count equals 0 then set textViewNoAlbum visible
                    if(albumAdapter.getItemCount() == 0){
                        textViewNoAlbum.setVisibility(View.VISIBLE);
                    }


                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: " + jsonException.toString(), Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
        };
        requestQueue.add(stringRequest);
    }
/*
    //TODO: Using Activity Lifecycle Callbacks on saving Date
        This is where we save the last screen, if the user presses the home button or off the screen, the user either
        wishes to resume or terminate the app, if the app is resumed, it will logged the date, if the app is terminated (onPause>onStop),
        particularly swiped up or close button on Home Menu, the date will be logged.
   */

    @Override
    protected void onStop(){
        super.onStop();
        //Toast.makeText(MainActivity.this, "On Stop", Toast.LENGTH_SHORT).show();

        sharedPreferences = getSharedPreferences(SHARED_PREF_DATE, MODE_PRIVATE);
        //getting current date to be saved on sharedpref in method onDestroy
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_DATE_RECENTLY_VISITED, currentDateTimeString);
        editor.apply();
    }
}