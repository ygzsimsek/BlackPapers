package com.blackwater.blackpapers.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.blackwater.blackpapers.Activities.CategoryActivity;
import com.blackwater.blackpapers.Common.Common;
import com.blackwater.blackpapers.Interface.itemClickListener;
import com.blackwater.blackpapers.Activities.ListWallpaper;
import com.blackwater.blackpapers.Model.CategoryItem;
import com.blackwater.blackpapers.Model.WallpaperItem;
import com.blackwater.blackpapers.R;
import com.blackwater.blackpapers.Activities.RecentWallpapersActivity;
import com.blackwater.blackpapers.ViewHolder.CategoryViewHolder;
import com.blackwater.blackpapers.ViewHolder.ListWallpaperViewHolder;
import com.blackwater.blackpapers.Activities.ViewWallpaper;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
    private static HomeFragment INSTANCE=null;
    public static HomeFragment getInstance() {
        if(INSTANCE == null)
            INSTANCE = new HomeFragment();
        return INSTANCE;
    }
    FirebaseDatabase database,databaseWP;
    DatabaseReference categoryBackground,categoryBackgroundWP;
    //to list categories
    FirebaseRecyclerOptions<CategoryItem> options;
    FirebaseRecyclerAdapter<CategoryItem,CategoryViewHolder> adapter;
    //list wallpapers
    FirebaseRecyclerOptions<WallpaperItem> optionsWP;
    FirebaseRecyclerAdapter<WallpaperItem,ListWallpaperViewHolder> adapterWP;

    RecyclerView recyclerCategories,recyclerWallpapers;
    ImageButton btnAllCategories, btnAllCollections;

    public HomeFragment() {
        //categories TODO:SPLIT THEM INTO CLASSES
        database = FirebaseDatabase.getInstance();
        categoryBackground = database.getReference(Common.STR_CATEGORY_BACKGROUND);

        //set limit
        Query query = categoryBackground.orderByChild(Common.STR_CATEGORY_BACKGROUND)
                .limitToFirst(6);

        options = new FirebaseRecyclerOptions.Builder<CategoryItem>()
                .setQuery(query,CategoryItem.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<CategoryItem, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position, @NonNull final CategoryItem model) {
                Picasso.get()
                        .load(model.getImageLink())
                        .into(holder.background_image, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception ex) {
                                Picasso.get()
                                        .load(model.getImageLink())
                                        .error(R.drawable.ic_terrain_black_24dp)
                                        .into(holder.background_image, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError(Exception ex) {
                                                Log.e("ERROR","Couldn't get fetch image");
                                            }
                                        });
                            }
                        });

                holder.category_name.setText(model.getName());

                holder.setItemClickListener((view, position1) -> {
                    Common.CATEGORY_ID_SELECTED = adapter.getRef(position1).getKey();
                    Common.CATEGORY_SELECTED = model.getName();
                    Intent intent = new Intent(getActivity(),ListWallpaper.class);
                    startActivity(intent);
                });

            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_category_item,parent,false);
                return new CategoryViewHolder(itemView);
            }
        };

        //wallpapers
        //init FB
        databaseWP = FirebaseDatabase.getInstance();
        categoryBackgroundWP = databaseWP.getReference(Common.STR_WALLPAPER);

        //Trending limit
        Query queryWP = categoryBackgroundWP.orderByChild("viewCount")
                .limitToLast(26);

        optionsWP = new FirebaseRecyclerOptions.Builder<WallpaperItem>()
                .setQuery(queryWP,WallpaperItem.class)
                .build();

        adapterWP = new FirebaseRecyclerAdapter<WallpaperItem, ListWallpaperViewHolder>(optionsWP) {
            @Override
            protected void onBindViewHolder(@NonNull final ListWallpaperViewHolder holder, int position, @NonNull final WallpaperItem model) {
                Picasso.get()
                        .load(model.getImageLink())
                        .into(holder.wallpaper, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception ex) {
                                Picasso.get()
                                        .load(model.getImageLink())
                                        .error(R.drawable.ic_terrain_black_24dp)
                                        .into(holder.wallpaper, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError(Exception ex) {
                                                Log.e("ERROR_BP","Couldn't set fetch image");
                                            }
                                        });
                            }
                        });

                holder.setItemClickListener(new itemClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        Intent intent1 = new Intent(getActivity(),ViewWallpaper.class);
                        Common.select_background = model;
                        Common.select_background_key = adapterWP.getRef(position).getKey();
                        startActivity(intent1);
                    }
                });
            }

            @Override
            public ListWallpaperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_wallpaper_item_small,parent,false);
                int height = parent.getMeasuredHeight()/2;
                itemView.setMinimumHeight(height);
                return new ListWallpaperViewHolder(itemView);
            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnAllCategories = (ImageButton)view.findViewById(R.id.btn_all_categories);
        btnAllCollections = (ImageButton)view.findViewById(R.id.btn_all_popular_collections);
        //slider
        //oh shit it was here
        //lol i just deleted the library TODO: Add slider library and define here
        //categories
        btnAllCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent categoryIntent = new Intent(getActivity(),CategoryActivity.class);
                startActivity(categoryIntent);
            }
        });
        recyclerCategories = (RecyclerView)view.findViewById(R.id.recycler_categories);
        recyclerCategories.setHasFixedSize(false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerCategories.setLayoutManager(gridLayoutManager);

        setCategory();
        //wallpaper stuff
        btnAllCollections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wpIntent = new Intent(getActivity(),RecentWallpapersActivity.class);
                startActivity(wpIntent);
            }
        });
        recyclerWallpapers = (RecyclerView)view.findViewById(R.id.recycler_popular_collections);
        recyclerWallpapers.setHasFixedSize(true);
        recyclerWallpapers.scrollToPosition(RecyclerView.SCROLLBAR_POSITION_DEFAULT);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setStackFromEnd(true);
        recyclerWallpapers.setLayoutManager(linearLayoutManager);

        loadTrendingList();
        return view;
    }

    private void setCategory() {
        adapter.startListening();
        recyclerCategories.setAdapter(adapter);
    }

    private void loadTrendingList() {
        adapterWP.startListening();
        recyclerWallpapers.setAdapter(adapterWP);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
        if(adapterWP!=null)
            adapterWP.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(adapter!=null)
            adapter.stopListening();
        if(adapterWP!=null)
            adapterWP.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
        if(adapterWP!=null)
            adapterWP.startListening();
    }
}
