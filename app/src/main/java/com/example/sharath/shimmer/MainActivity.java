package com.example.sharath.shimmer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Recipe> cartList;
    private RecipeListAdapter recipeListAdapter;

    private ShimmerFrameLayout shimmerFrameLayout;

    //URL to fetch menu json
    //this endpoint takes 2sec before giving the response to add
    //some delay to test the Shimmer effect
    private static final String URL = "https://api.androidhive.info/json/shimmer/menu.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);

        recyclerView = findViewById(R.id.recycler_view);
        cartList = new ArrayList<>();
        recipeListAdapter = new RecipeListAdapter(this, cartList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(recipeListAdapter);

        //making http call and fetching menu json
        fetchRecipes();
    }

    /**
     * method make volley network call and parses json
     */
    private void fetchRecipes() {

    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        shimmerFrameLayout.stopShimmerAnimation();
        super.onPause();
    }

}
