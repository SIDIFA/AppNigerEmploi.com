package com.openclassrooms.mynavdrawer.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.glide.slider.library.SliderLayout;
import com.openclassrooms.mynavdrawer.Controllers.Adapters.AnnonceAdapter;
import com.openclassrooms.mynavdrawer.Controllers.Model.Annonce;
import com.openclassrooms.mynavdrawer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UneCategorie extends AppCompatActivity {

    private List<Annonce> mesAnnonces;
    //private List<Offre> offreList;
    private RecyclerView recyclerView;
    private AnnonceAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    TextView gridData;
    ImageView imageView;
    String cat_name = "Catégorie";

    //FOR DATAS
    private static final int CAT_ADMIN = 0;
    private static final int CAT_AGRI = 1;
    private static final int CAT_COM = 2;
    private static final int CAT_COMP = 3;
    private static final int CAT_CONS = 4;
    private static final int CAT_DIR = 5;
    private static final int CAT_DRO = 6;
    private static final int CAT_EAU = 7;
    private static final int CAT_ECO = 8;
    private static final int CAT_EDU = 9;
    private static final int CAT_FOR = 10;
    private static final int CAT_INF = 11;

    String URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICAx2eqkfCA_mesdonnees.json";
    //private static final String URL_OFFRES = "https://www.cjoint.com/doc/19_03/ICAx2eqkfCA_mesdonnees.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

        Intent intent = getIntent();
        int cat_id = intent.getIntExtra("id", 0);
        cat_name = intent.getStringExtra("name");

        mesAnnonces = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //loadProducts();

        switch (cat_id) {
            case CAT_ADMIN:
                URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICBo5RDLFHj_administration.json";
                break;
            case CAT_AGRI:
                URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICBpbNCtbVj_agriculture.json";
                break;
            case CAT_COM:
                URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICBpddXrvqj_communication.json";
                break;
            case CAT_COMP:
                URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICBppcVrQ4j_comptabilite.json";
                break;
            case CAT_CONS:
                URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICBplIh3MPj_consultation.json";
                break;
            case CAT_DIR:
                URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICBpmEIgqvj_direction.json";
                break;
            case CAT_DRO:
                URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICBpnzkM4Lj_droit.json";
                break;
            case CAT_EAU:
                URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICBpqvYGIVj_eau-hygiene.json";
                break;
            case CAT_ECO:
                URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICBprnrlvqj_economie.json";
                break;
            case CAT_EDU:
                URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICBpshkZ6zj_education.json";
                break;
            case CAT_FOR:
                URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICBptkAFaej_formation.json";
                break;
            case CAT_INF:
                URL_ANNONCES = "https://www.cjoint.com/doc/19_03/ICBpuwHdTaj_informatique.json";
                break;
            default:
                break;
        }
        //loadProducts
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ANNONCES,
                response -> {
                    try {
                        //converting the string to json array object
                        JSONArray array = new JSONArray(response);
                        String imagUrl = "https://www.nigeremploi.com/images/logo_annonceurs/ceni.png";
                        mesAnnonces = Annonce.chargement(array, imagUrl);
                        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                        recyclerView.addItemDecoration(itemDecoration);
                        adapter = new AnnonceAdapter(this,mesAnnonces);
                        //OffreAdapter adapter = new OffreAdapter(getContext(), offreList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        //loadProducts();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                });

        Volley.newRequestQueue(this).add(stringRequest);
        /*mesAnnonces = Annonce.chargement(URL_ANNONCES, this);
        //creating adapter object and setting it to recyclerview
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        adapter = new AnnonceAdapter(this,mesAnnonces);
        //OffreAdapter adapter = new OffreAdapter(getContext(), offreList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);*/


        this.configureToolbar();

    }

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_resultat);
        //Set the toolbar
        setSupportActionBar(toolbar);
        toolbar.setTitle(this.cat_name);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
