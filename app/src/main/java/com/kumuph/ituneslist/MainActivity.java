package com.kumuph.ituneslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kumuph.ituneslist.Adapter.ITunesListAdapter;
import com.kumuph.ituneslist.DataModel.ITunesListDataModel;
import com.kumuph.ituneslist.Room.DatabaseClient;
import com.kumuph.ituneslist.Room.ITunesListRoom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Application Bar
    private Toolbar toolbar;
    private SearchView searchViewAllArtistAlbum;

    //URL from ITunes Search API
    private String ITUNES_SEARCHAPI_URL = "https://itunes.apple.com/search?term=star&country=au&media=movie&all";

    //Instantiating this List from ITunesListDataModel for ITunesListRoom, Recyclerview
    List<ITunesListDataModel> iTunesListRoom;
    private RecyclerView recyclerviewITunesList;
    private List<ITunesListDataModel> iTunesListDataModel;
    private ITunesListAdapter iTunesListAdapter;
    private ProgressBar progressBar;

    SharedPreferences sharedPreferences;
    //Key value pair storing date in Sharedpreferences
    private static String SHARED_PREF_DATE = "datepreferences";
    private static String KEY_DATE_RECENTLY_VISITED = "date";
    private TextView textviewvRecentlyVisited;

    public static String searchTermValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar Initialization...
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchViewAllArtistAlbum = findViewById(R.id.searchViewAllArtistAlbum);

        //Initializing saved date on Sharedpreferences
        sharedPreferences = getSharedPreferences(SHARED_PREF_DATE, MODE_PRIVATE);

        String keyDate = sharedPreferences.getString(KEY_DATE_RECENTLY_VISITED, null);

        //Initializing Widgets for Main Activity
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        textviewvRecentlyVisited = findViewById(R.id.textviewvRecentlyVisited);
        //Passing the key value to Textview
        textviewvRecentlyVisited.setText(keyDate);

        //Initializing Recyclerview, List, Model and LayoutManager
        recyclerviewITunesList = findViewById(R.id.recyclerviewITunesList);
        iTunesListDataModel = new ArrayList<>();
        iTunesListAdapter = new ITunesListAdapter(this, iTunesListDataModel);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewITunesList.setLayoutManager(mLayoutManager);
        recyclerviewITunesList.setItemAnimator(new DefaultItemAnimator());
        recyclerviewITunesList.setNestedScrollingEnabled(false);
        recyclerviewITunesList.setAdapter(iTunesListAdapter);

        //Setting searchview onQuery, passing the user input to the String constant searchTermValue
        searchViewAllArtistAlbum.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchTermValue = query;
                //Using intent here, query will be use to the next Activity (SearchedTermVyArtistAlbumActivity
                Intent intent = new Intent(MainActivity.this, SearchedTermByArtistAlbumActivity.class);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //Checking network connection if online or offline, if network is active it will fetch from server, if not fetch from Local/Room Database
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting() && iTunesListDataModel != null) {
            fetchfromServer();
        } else {


            fetchfromRoom();
        }

    }

    //Call this function if network is offline, this will load all the data from room database
    private void fetchfromRoom() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                List<ITunesListRoom> ituneslist = DatabaseClient.getInstance(MainActivity.this).getAppDatabase().iTunesListDao().getAll();
                iTunesListDataModel.clear();
                for (ITunesListRoom itunes: ituneslist) {
                    ITunesListDataModel iTunesList = new ITunesListDataModel(
                            //fetching Data from Room Database
                            itunes.getTrackname(),
                            itunes.getArtworkurl100(), itunes.getCollectionprice(),
                            itunes.getPrimarygenrename(), itunes.getLongdescription(),
                            itunes.getColectionId(), itunes.getTrackId()
                    );

                    iTunesListDataModel.add(iTunesList);
                }
                // refreshing recycler view
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iTunesListAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        thread.start();
    }
    //This function simply loads the data available from the server, referencing into particular ITunes search API >> (ITUNES_SEARCHAPI_URL)
    private void fetchfromServer(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ITUNES_SEARCHAPI_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                        /*Gson take part the role here wherein it serializer allows to convert
                           a Json string from JsonArray result to its corresponding Java data type. */
                        iTunesListRoom = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ITunesListDataModel>>() {
                        }.getType());

                        iTunesListDataModel.clear();

                        // Adding data to iTUnes list
                        // Passing Values to another List for Room Database
                        iTunesListDataModel.addAll(iTunesListRoom);

                        // Refreshing recycler view
                        iTunesListAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);

                        saveTask();


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

    //Creating task, this is where you insert List values to Room based on parameters for offline usage
    private void saveTask() {


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                for (int i = 0; i < iTunesListRoom.size(); i++) {
                    ITunesListRoom iTunesList= new ITunesListRoom();
                    iTunesList.setTrackname(iTunesListRoom.get(i).getTrackName());
                    iTunesList.setArtworkurl100(iTunesListRoom.get(i).getArtWorkUrl100());
                    iTunesList.setCollectionprice(iTunesListRoom.get(i).getCollectionPrice());
                    iTunesList.setPrimarygenrename(iTunesListRoom.get(i).getPrimaryGenreName());
                    iTunesList.setLongdescription(iTunesListRoom.get(i).getLongDescription());
                    iTunesList.setColectionId(iTunesListRoom.get(i).getCollectionId());
                    iTunesList.setTrackId(iTunesListRoom.get(i).getTrackId());

                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().iTunesListDao().insert(iTunesList);
                }

                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //onPostExecute method synchronize itself again with this main thread and allows to update it after the task.
                //Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask saveTask = new SaveTask();
        saveTask.execute();
    }

    /* onDestroy method determines the saving point where the user activity must be detected.
        By the time the user presses the back button(onBackPressed), the date will be logged as Recently Visited.
        This is the only part where you must implement the saving of date, otherwise you have another activity,
        because destroying the other activities doesn't mean the user will exit the app.
     */
    @Override
    public void onDestroy(){
        super.onDestroy();

        sharedPreferences = getSharedPreferences(SHARED_PREF_DATE, MODE_PRIVATE);
        //getting current date to be saved on sharedpref in method onDestroy
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_DATE_RECENTLY_VISITED, currentDateTimeString);
        editor.apply();

    }

    /*
        This is where we save the last screen, if the user presses the home button(onPause), the user either
        wishes to resume or terminate the app, if the app is resumed, it will logged the date
        by the time the user presses home button, if the app is terminated onPause, (particularly swiped up or
        close button on Home Menu), the logged date would be the last time the user presses the home button.
    */
    @Override
    public void onPause(){
        super.onPause();

        sharedPreferences = getSharedPreferences(SHARED_PREF_DATE, MODE_PRIVATE);
        //getting current date to be saved on sharedpref in method onDestroy
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_DATE_RECENTLY_VISITED, currentDateTimeString);
        editor.apply();
    }

}