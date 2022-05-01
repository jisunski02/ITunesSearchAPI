package com.kumuph.ituneslist.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kumuph.ituneslist.DataModel.ITunesListDataModel;
import com.kumuph.ituneslist.MainActivity;
import com.kumuph.ituneslist.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Instantiating ITunesListAdapter
public class ITunesListAdapter extends RecyclerView.Adapter<ITunesListAdapter.ViewHolder> {

    //instantiating ITunesListDataModel to List
    private List<ITunesListDataModel> iTunesListDataModels = new ArrayList<>();
    //use to identify what context it will use, wether it will be fragment, activity, etc.
    private Context context;
    Dialog dialogDetailedView;

    //Initializing drawable images on array
    int[] imagePlaceHolderArray = new int[]{R.drawable.imovies_image_placeholder1, R.drawable.imovies_image_placeholder2,R.drawable.imovies_image_placeholder3,
            R.drawable.imovies_image_placeholder4,R.drawable.imovies_image_placeholder5,R.drawable.imovies_image_placeholder6};

    //Instantiating parameters context and List on to ITuneListAdapter
    public ITunesListAdapter(Context context, List<ITunesListDataModel> iTunesListDataModels){
        this.context = context;
        this.iTunesListDataModels = iTunesListDataModels;
    }

    @NonNull
    @Override
    public ITunesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Setting itunes_layout for ITunesListAdapter holder main layout on View
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itunes_layout,parent,false);
        return new ITunesListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ITunesListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //Instantiating ItunesListDataModel onBindViewHolder current position

        try {
            //setting image Array on placeholder randomly, every placeholder image changes on each row everytime the user scrolls/swipe
            Random random = new Random();
            int randomNumber = random.nextInt(imagePlaceHolderArray.length);

            //Initializing ITunesListDatamodel to List
            ITunesListDataModel iTunesListDataModel = iTunesListDataModels.get(position);

            //Setting value on every item on the recyclerview position
            Glide.with(holder.imageViewArtWork).load(iTunesListDataModel.getArtWorkUrl100())
                    //.thumbnail(0.5f)
                    .apply(new RequestOptions()
                            .error(imagePlaceHolderArray[randomNumber]))
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageViewArtWork);

            holder.textViewTrackName.setText(iTunesListDataModel.getTrackName());
            holder.textViewPrimaryGenre.setText(iTunesListDataModel.getPrimaryGenreName());
            holder.textViewCollectionPrice.setText("$"+ String.valueOf(iTunesListDataModels.get(position).getCollectionPrice()));

            holder.buttonViewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        dialogDetailedView = new Dialog(context);
                        dialogDetailedView.requestWindowFeature(Window.FEATURE_NO_TITLE);//...........
                        dialogDetailedView.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialogDetailedView.setContentView(R.layout.dialog_viewdetails);
                        dialogDetailedView.setCancelable(true);
                        dialogDetailedView.show();

                        ImageView imageViewDetail = dialogDetailedView.findViewById(R.id.imageViewDetail);
                        TextView trackName = dialogDetailedView.findViewById(R.id.textViewTrackNameDetail);
                        TextView primarygenre = dialogDetailedView.findViewById(R.id.textViewPrimaryGenreDetail);
                        TextView collectionprice = dialogDetailedView.findViewById(R.id.tvCollectionPriceDetail);
                        TextView longdescription = dialogDetailedView.findViewById(R.id.textViewLongDescriptionDetail);

                        Glide.with(imageViewDetail).load(iTunesListDataModel.getArtWorkUrl100())
                                //.thumbnail(0.5f)
                                .apply(new RequestOptions()
                                        .error(imagePlaceHolderArray[randomNumber]))
                                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageViewDetail);

                        trackName.setText(iTunesListDataModel.getTrackName());
                        primarygenre.setText(iTunesListDataModel.getPrimaryGenreName());
                        collectionprice.setText("$" + String.valueOf(iTunesListDataModels.get(position).getCollectionPrice()));
                        if (iTunesListDataModel.getLongDescription().equals(" ")) {
                            longdescription.setText("No Description Available");
                        } else {
                            longdescription.setText(iTunesListDataModel.getLongDescription());
                        }
                    }

                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        //Returns the size of iTunes List
        return iTunesListDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Instantiating itunes_layout widgets here
        ImageView imageViewArtWork;
        TextView textViewTrackName, textViewCollectionPrice, textViewPrimaryGenre;
        Button buttonViewDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Initialization for itunes_layout Widgets here
            imageViewArtWork = itemView.findViewById(R.id.imageViewArtWork);
            textViewTrackName = itemView.findViewById(R.id.textViewTrackName);
            textViewCollectionPrice = itemView.findViewById(R.id.tvCollectionPrice);
            textViewPrimaryGenre = itemView.findViewById(R.id.textViewPrimaryGenre);
            buttonViewDetails = itemView.findViewById(R.id.buttonViewDetails);
        }
    }
}
