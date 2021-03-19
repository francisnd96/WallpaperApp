package com.francisnd.natureapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.francisnd.natureapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Fragment1 extends Fragment {

    ListView listView;
    GridView gridView, gridView2;
    List<DataHandler> dataHandlerList;
    SwipeRefreshLayout swipeRefreshLayout;
    WallpaperAdapter wallpaperAdapter;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.list_items,container,false);
        return inflater.inflate(R.layout.activity_main,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Toolbar toolbar = getView().findViewById(R.id.toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

//        listView = getView().findViewById(R.id.list);
        System.out.println("*****Step2");

        gridView = getView().findViewById(R.id.grid);
        imageView = getView().findViewById(R.id.fav);

        dataHandlerList = new ArrayList<>();
        swipeRefreshLayout = getView().findViewById(R.id.swipe);

        loadData("First");
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData("Refresh");
            }
        });

//            View child = gridView.getChildAt(3);
//            ImageView itemText = child.findViewById(R.id.fav);
//            itemText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    System.out.println("test");
//                }
//            });



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title, thumbnail, image;
                title = dataHandlerList.get(position).getTitle();
                image = dataHandlerList.get(position).getImage();

                Intent intent = new Intent(getActivity().getApplicationContext(),ViewWallpaper.class);
                intent.putExtra("title",title);
                intent.putExtra("image",image);
                startActivity(intent);

            }
        });

    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }
    private void loadData(String type){
        parseJSON(inputStreamToString(getActivity().getResources().openRawResource(R.raw.trees)),type);
    }

    private void parseJSON(String res, String type) {
        String title, thumbnail, image;
        if(type.equals("Refresh")){
            dataHandlerList.clear();
            wallpaperAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
        try {
            JSONArray jsonArray = new JSONArray(res);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                title = jsonObject.get("title").toString();
                thumbnail = jsonObject.get("thumbnail").toString();
                image = jsonObject.get("image").toString();
                dataHandlerList.add(new DataHandler(title,thumbnail,image));
            }
            Collections.shuffle(dataHandlerList, new Random());

            wallpaperAdapter = new WallpaperAdapter(getActivity().getApplicationContext(),R.layout.list_items, dataHandlerList);
            gridView.setAdapter(wallpaperAdapter);
//            listView.setAdapter(wallpaperAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
