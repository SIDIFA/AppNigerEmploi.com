package com.openclassrooms.mynavdrawer.Controllers.Activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.openclassrooms.mynavdrawer.Controllers.Model.Annonce;
import com.openclassrooms.mynavdrawer.Controllers.Adapters.AnnonceAdapter;
import com.openclassrooms.mynavdrawer.R;

import java.util.ArrayList;
import java.util.List;

public class ResultatSearch extends AppCompatActivity {

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private List<Annonce> mesAnnonces;
    private RecyclerView recyclerView;
    private AnnonceAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private static final String URL_PRODUCTS = "https://www.cjoint.com/doc/19_03/ICymHPth5WW_mesdonnees.json";
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_search);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.fragment_main_swipe_container_search);
        mesAnnonces = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.ma_vue_search);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        mesAnnonces = (ArrayList<Annonce>) args.getSerializable("ARRAYLIST");

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        adapter = new AnnonceAdapter(this,mesAnnonces);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        this.configureToolbar();

    }

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_resultat);
        //Set the toolbar
        setSupportActionBar(toolbar);
        toolbar.setTitle("Resultat Recherche");
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
