package com.francisnd.natureapplication.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.francisnd.natureapplication.DataHandler;
import com.francisnd.natureapplication.Database;
import com.francisnd.natureapplication.R;
import com.francisnd.natureapplication.Tabbed;
import com.francisnd.natureapplication.ViewWallpaper;
import com.francisnd.natureapplication.WallpaperAdapter;
import com.francisnd.natureapplication.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FavouriteFragment extends Fragment {

    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    WallpaperAdapter wallpaperAdapter;
    List<DataHandler> faves;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getView().findViewById(R.id.favourites);

        swipeRefreshLayout = getView().findViewById(R.id.swipe);

        loadData("First");
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData("Refresh");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title, thumbnail, image;
                title = faves.get(position).getTitle();
                image = faves.get(position).getThumbnail();

                Intent intent = new Intent(getActivity().getApplicationContext(), ViewWallpaper.class);
                intent.putExtra("title",title);
                intent.putExtra("image",image);
                startActivity(intent);
            }
        });
    }

    private void loadData(String type){
        swipeRefreshLayout.setRefreshing(true);
        parseJSON(type);
    }

    private void parseJSON(String type) {
        faves = new Database(getContext()).getFavourites();
        swipeRefreshLayout.setRefreshing(false);
        if(type.equals("Refresh")){
            faves.clear();
            wallpaperAdapter.notifyDataSetChanged();
        }
            wallpaperAdapter = new WallpaperAdapter(getActivity().getApplicationContext(),R.layout.list_items, faves);
            listView.setAdapter(wallpaperAdapter);

    }

}