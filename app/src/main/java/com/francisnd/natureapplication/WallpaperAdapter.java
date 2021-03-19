package com.francisnd.natureapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.francisnd.natureapplication.R;

import java.util.List;

public class WallpaperAdapter extends ArrayAdapter {
    Context context;
    int resource;
    List<DataHandler> dataHandlers;
    public WallpaperAdapter(Context context, int resource, List<DataHandler> list){
        super(context,resource,list);
        this.context = context;
        this.resource = resource;
        this.dataHandlers = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(resource,null,false);
        DataHandler dataList = dataHandlers.get(position);
        TextView textView = view.findViewById(R.id.title);
        ImageView imageView = view.findViewById(R.id.listImage);
        ImageView fav = view.findViewById(R.id.fav);
        boolean colourChange = false;

        List<DataHandler> list =  new Database(getContext()).getFavourites();

        for (DataHandler object : list) {
            if(object.getTitle().equals(dataList.getTitle())){
                fav.setBackground(getContext().getResources().getDrawable(R.drawable.ic_baseline_favorite_24));
                colourChange = true;
            }
        }


        fav.bringToFront();

        boolean finalColourChange = colourChange;
        fav.setOnClickListener(new View.OnClickListener() {
            boolean colour = finalColourChange;
            @Override
            public void onClick(View v) {

                if(colour == false) {
                    new Database(getContext()).addToFavourites(new DataHandler(dataList.getTitle(),dataList.getThumbnail(),dataList.getThumbnail()));
                    fav.setBackground(getContext().getResources().getDrawable(R.drawable.ic_baseline_favorite_24));
                    colour = true;
                }else{
                    new Database(getContext()).removeFromFavourites(new DataHandler(dataList.getTitle(),dataList.getThumbnail(),dataList.getThumbnail()));
                    fav.setBackground(getContext().getResources().getDrawable(R.drawable.ic_baseline_favorite_26));
                    colour = false;
                }

            }
        });

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(getContext());
        circularProgressDrawable.setStrokeWidth(5);
        circularProgressDrawable.setCenterRadius(30);
        circularProgressDrawable.start();

        textView.setText(dataList.getTitle());
        Glide.with(context).load(dataList.getThumbnail()).centerCrop().error(R.drawable.no_image).placeholder(circularProgressDrawable).into(imageView);

        return view;

    }
}
