package com.kumuph.ituneslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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
import com.kumuph.Interfaces.OnCardViewClickListener;
import com.kumuph.ituneslist.Adapter.ITunesListAdapter;
import com.kumuph.ituneslist.DataModel.ITunesListDataModel;
import com.kumuph.ituneslist.Room.DatabaseClient;
import com.kumuph.ituneslist.Room.ITunesListRoom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String ITUNES_SEARCHAPI_URL = "https://itunes.apple.com/search?term=star&amp;country=au&amp;media=movie&amp;all";
    //Instantiating List for ITunesListRoom
    List<ITunesListDataModel> iTunesListRoom;
    private RecyclerView recyclerviewITunesList;
    private List<ITunesListDataModel> iTunesListDataModel;
    private ITunesListAdapter iTunesListAdapter;
    private ProgressBar progressBar;
    JSONArray resultValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Widgets for Main Activity
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        recyclerviewITunesList = findViewById(R.id.recyclerviewITunesList);
        iTunesListDataModel = new ArrayList<>();
        iTunesListAdapter = new ITunesListAdapter(this, iTunesListDataModel);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewITunesList.setLayoutManager(mLayoutManager);
        recyclerviewITunesList.setItemAnimator(new DefaultItemAnimator());
        recyclerviewITunesList.setNestedScrollingEnabled(false);
        recyclerviewITunesList.setAdapter(iTunesListAdapter);

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
                            itunes.getWrappertype(), itunes.getKind(),
                            itunes.getCollectionid(), itunes.getTrackid(),
                            itunes.getArtistname(),
                            itunes.getCollectionname(), itunes.getTrackname(),
                            itunes.getCollectioncensoredname(), itunes.getTrackcensoredname(),
                            itunes.getCollectionartistid(), itunes.getCollectionartistviewurl(),
                            itunes.getCollectionviewurl(),
                            itunes.getTrackviewurl(), itunes.getPreviewurl(),
                            itunes.getArtworkurl30(), itunes.getArtworkurl60(),
                            itunes.getArtworkurl100(), itunes.getCollectionprice(),
                            itunes.getTrackprice(), itunes.getTrackrentalprice(),
                            itunes.getCollectionhdprice(), itunes.getTrackhdprice(),
                            itunes.getTrackhdrentalprice(), itunes.getReleasedate(),
                            itunes.getCollectionexplicitness(), itunes.getTrackexplicitness(),
                            itunes.getDisccount(), itunes.getDiscnumber(),
                            itunes.getTrackcount(), itunes.getTracknumber(),
                            itunes.getTracktimemillis(), itunes.getCountry(),
                            itunes.getCurrency(), itunes.getPrimarygenrename(),
                            itunes.getContentadvisoryrating(), itunes.getShortdescription(),
                            itunes.getLongdescription(), itunes.isHasitunesextras()
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


                        iTunesListRoom = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ITunesListDataModel>>() {
                        }.getType());

                        // adding data to cart list
                        iTunesListDataModel.clear();
                        iTunesListDataModel.addAll(iTunesListRoom);


                        // refreshing recycler view
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
                    iTunesList.setWrappertype(iTunesListRoom.get(i).getWrapperType());
                    iTunesList.setKind(iTunesListRoom.get(i).getKind());
                    iTunesList.setCollectionid(iTunesListRoom.get(i).getCollectionId());
                    iTunesList.setTrackid(iTunesListRoom.get(i).getTrackId());
                    iTunesList.setArtistname(iTunesListRoom.get(i).getArtistName());
                    iTunesList.setCollectionname(iTunesListRoom.get(i).getCollectionName());
                    iTunesList.setTrackname(iTunesListRoom.get(i).getTrackName());
                    iTunesList.setCollectioncensoredname(iTunesListRoom.get(i).getCollectionCensoredName());
                    iTunesList.setTrackcensoredname(iTunesListRoom.get(i).getTrackCensoredName());
                    iTunesList.setCollectionartistid(iTunesListRoom.get(i).getCollectionArtistId());
                    iTunesList.setCollectionartistviewurl(iTunesListRoom.get(i).getCollectionArtistViewUrl());
                    iTunesList.setCollectionviewurl(iTunesListRoom.get(i).getCollectionViewUrl());
                    iTunesList.setTrackviewurl(iTunesListRoom.get(i).getTrackViewUrl());
                    iTunesList.setPreviewurl(iTunesListRoom.get(i).getPreviewUrl());
                    iTunesList.setArtworkurl30(iTunesListRoom.get(i).getArtWorkUrl30());
                    iTunesList.setArtworkurl60(iTunesListRoom.get(i).getArtWorkUrl60());
                    iTunesList.setArtworkurl100(iTunesListRoom.get(i).getArtWorkUrl100());
                    iTunesList.setCollectionprice(iTunesListRoom.get(i).getCollectionPrice());
                    iTunesList.setTrackprice(iTunesListRoom.get(i).getTrackPrice());
                    iTunesList.setTrackrentalprice(iTunesListRoom.get(i).getTrackRentalPrice()); //
                    iTunesList.setCollectionhdprice(iTunesListRoom.get(i).getCollectionHdPrice());
                    iTunesList.setTrackhdprice(iTunesListRoom.get(i).getTrackHdPrice());
                    iTunesList.setTrackhdrentalprice(iTunesListRoom.get(i).getTrackHdRentalPrice());
                    iTunesList.setReleasedate(iTunesListRoom.get(i).getReleaseDate());
                    iTunesList.setCollectionexplicitness(iTunesListRoom.get(i).getCollectionExplicitness());
                    iTunesList.setTrackexplicitness(iTunesListRoom.get(i).getTrackExplicitness());
                    iTunesList.setDisccount(iTunesListRoom.get(i).getDiscCount());
                    iTunesList.setDiscnumber(iTunesListRoom.get(i).getDiscNumber());
                    iTunesList.setTrackcount(iTunesListRoom.get(i).getTrackCount());
                    iTunesList.setTracknumber(iTunesListRoom.get(i).getTrackNumber());
                    iTunesList.setTracktimemillis(iTunesListRoom.get(i).getTrackTimeMillis());
                    iTunesList.setCountry(iTunesListRoom.get(i).getCountry());
                    iTunesList.setCurrency(iTunesListRoom.get(i).getCurrency());
                    iTunesList.setPrimarygenrename(iTunesListRoom.get(i).getPrimaryGenreName());
                    iTunesList.setContentadvisoryrating(iTunesListRoom.get(i).getContentAdvisoryRating());
                    iTunesList.setShortdescription(iTunesListRoom.get(i).getShortDescription());
                    iTunesList.setLongdescription(iTunesListRoom.get(i).getLongDescription());
                    iTunesList.setHasitunesextras(iTunesListRoom.get(i).isHasITunesExtras());

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

}