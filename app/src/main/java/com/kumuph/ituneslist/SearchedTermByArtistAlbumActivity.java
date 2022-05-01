package com.kumuph.ituneslist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        //pass the searchTermValue to the URL search API to return value
        String url="https://itunes.apple.com/search?term="+ MainActivity.searchTermValue +"&entity=album&attribute=albumTerm&country=ph&limit=20";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //dismiss dialog here
                try {

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
}