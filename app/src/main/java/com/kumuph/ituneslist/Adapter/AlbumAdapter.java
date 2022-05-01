package com.kumuph.ituneslist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kumuph.ituneslist.DataModel.AlbumDataModel;
import com.kumuph.ituneslist.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    //instantiating AlbumDataModel to List
    private List<AlbumDataModel> albumDataModels = new ArrayList<>();
    private Context context;

    //Initializing drawable images on array
    int[] imagePlaceHolderArray = new int[]{R.drawable.imovies_image_placeholder1, R.drawable.imovies_image_placeholder2,R.drawable.imovies_image_placeholder3,
            R.drawable.imovies_image_placeholder4,R.drawable.imovies_image_placeholder5,R.drawable.imovies_image_placeholder6};

    //Instantiating parameters context and List on to AlbumAdapter
    public AlbumAdapter(Context context, List<AlbumDataModel> albumDataModels){
        this.context = context;
        this.albumDataModels = albumDataModels;
    }

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Setting itunes_layout for AlbumAdapter holder main layout on View
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.album_layout,parent,false);
        return new AlbumAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {

        //Initializing AlbumDataModel to List
        AlbumDataModel albumDataModel = albumDataModels.get(position);

        //setting image Array on placeholder randomly, every placeholder image changes on each row everytime the user scrolls/swipe
        Random random = new Random();
        int randomNumber = random.nextInt(imagePlaceHolderArray.length);

        // this is based on image sizes available on itunes
        // pass this string value to Glide for better image quality
        String artWorkUrl600 = albumDataModel.getArtWorkUrl100();
        artWorkUrl600 = artWorkUrl600.substring(0, artWorkUrl600.length() - 13);
        String convertedArtWorkURL = artWorkUrl600+"600x600bb.jpg";

        //Setting value on every item on the recyclerview position
        //image set to 600x600 resolution
        Glide.with(holder.imageViewAlbum).load(convertedArtWorkURL)
                //.thumbnail(0.5f)
                .apply(new RequestOptions()
                        .error(imagePlaceHolderArray[randomNumber]))
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageViewAlbum);

        holder.textViewAlbumName.setText(albumDataModel.getCollectionName());

        //Visit Artist ITunes Album page on button click
        holder.buttonViewAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = albumDataModel.getCollectionViewUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        //Returns the size of Album List
        return albumDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewAlbum;
        TextView textViewAlbumName;
        Button buttonViewAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Initialization for album_layout Widgets here
            imageViewAlbum = itemView.findViewById(R.id.imageViewAlbum);
            textViewAlbumName = itemView.findViewById(R.id.textViewAlbumName);
            buttonViewAlbum = itemView.findViewById(R.id.buttonViewAlbum);
        }
    }
}
