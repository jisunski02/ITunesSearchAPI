package com.kumuph.ituneslist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kumuph.ituneslist.DataModel.ArtistDataModel;
import com.kumuph.ituneslist.R;

import java.util.ArrayList;
import java.util.List;


public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    //instantiating ArtistDataModel to List
    private List<ArtistDataModel> artistDataModels = new ArrayList<>();
    private Context context;

    private String artistLinkURL = "";

    //Instantiating parameters context and List on to ArtistAdapter
    public ArtistAdapter(Context context, List<ArtistDataModel> artistDataModel){
        this.context = context;
        this.artistDataModels = artistDataModel;
    }

    @NonNull
    @Override
    public ArtistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Setting itunes_layout for ArtistAdapter holder main layout on View
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.artist_layout,parent,false);
        return new ArtistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ViewHolder holder, int position) {

        //Initializing ArtistDataModel to List
        ArtistDataModel artistDataModel = artistDataModels.get(position);

        //Setting value on every item on the recyclerview position
        holder.textViewArtistName.setText(artistDataModel.getArtistName());
        holder.textViewArtistType.setText(artistDataModel.getArtistType());

        artistLinkURL = artistDataModel.getArtistLinkUrl();

        //Visit Artist ITunes Profile page on button click
        holder.buttonVisitArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        //Returns the size of Artist List
        return artistDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewArtistName, textViewArtistType;
        Button buttonVisitArtist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Initialization for artist_layout Widgets here
            textViewArtistName = itemView.findViewById(R.id.textViewArtist);
            textViewArtistType = itemView.findViewById(R.id.textViewArtistType);
            buttonVisitArtist = itemView.findViewById(R.id.buttonVisitArtist);

        }
    }
}
