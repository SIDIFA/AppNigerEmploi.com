package com.openclassrooms.mynavdrawer.Controllers.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.openclassrooms.mynavdrawer.Controllers.Adapters.AnnonceAdapter;
import com.openclassrooms.mynavdrawer.Controllers.Model.AccesLocal;
import com.openclassrooms.mynavdrawer.Controllers.Model.Annonce;
import com.openclassrooms.mynavdrawer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavoris extends Fragment {

    private List<Annonce> mesAnnonces;
    private RecyclerView recyclerView;
    private AnnonceAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    private static AccesLocal accesLocal;
    private List maListe;
    private static final String URL_PRODUCTS = "https://www.cjoint.com/doc/19_03/ICAx2eqkfCA_mesdonnees.json";


    public static FragmentFavoris newInstance() {
        return (new FragmentFavoris());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_favoris, container, false);
        accesLocal = new AccesLocal(getContext());

        mesAnnonces = new ArrayList<>();
        maListe = new ArrayList<String>();
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.fragment_main_swipe_container_fav);
        recyclerView = (RecyclerView) view.findViewById(R.id.ma_vue_fav);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);

        maListe = accesLocal.getAllData();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                response -> {
                    try {
                        //converting the string to json array object
                        JSONArray array = new JSONArray(response);
                        String imagUrl = "https://www.nigeremploi.com/images/logo_annonceurs/ceni.png";
                        for (int i=0; i<array.length(); i++){
                            //getting product object from json array
                            JSONObject offre = array.getJSONObject(i);
                            //showMessage(offre.getString("id"));
                            for (int j=0;j<maListe.size();j++){
                                if (String.valueOf(maListe.get(j)).equalsIgnoreCase(offre.getString("id"))){
                                    //showMessage("c'est bon");

                                    Annonce annonces = new Annonce();
                                    annonces.setId(offre.getInt("id"));
                                    annonces.setTitre(offre.getString("titre_offre"));
                                    annonces.setNomSociete(offre.getString("organisme"));
                                    annonces.setSecteur(offre.getString("secteur"));
                                    annonces.setVue(offre.getString("vo_nombre"));
                                    annonces.setContrat(offre.getString("type_contra"));
                                    annonces.setDelai(offre.getString("validite"));
                                    annonces.setVille(offre.getString("lieu"));
                                    annonces.setNiveau(offre.getString("diplome"));
                                    annonces.setFormation(offre.getString("formation"));
                                    annonces.setExperience(offre.getString("experience"));
                                    annonces.setDescription(offre.getString("descrip"));
                                    annonces.setCondition(offre.getString("conditions"));
                                    annonces.setCouriel(offre.getString("mail_contact"));
                                    annonces.setContact(offre.getString("url_contact"));
                                    annonces.setTelephone(offre.getString("phone_contact"));
                                    annonces.setLangue(offre.getString("langue"));
                                    annonces.setSiteweb("NP");
                                    annonces.setImage(imagUrl);
                                    annonces.setO_resum_sms(offre.getString("o_resum_sms"));

                                    mesAnnonces.add(annonces);
                                }
                            }

                            if (mesAnnonces.size() != 0){

                                adapter = new AnnonceAdapter(getContext(),mesAnnonces);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setAdapter(adapter);
                                runLayoutAnimation(recyclerView);
                                configureSwipeRefreshLayout();
                            }
                            else {
                                showMessage("Vous n'avez pas encore de favoris");
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                });

        Volley.newRequestQueue(getContext()).add(stringRequest);

        return view;
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(() -> updateAnnonce());
    }

    private void updateAnnonce(){
        swipeRefreshLayout.setRefreshing(false);
        //mesAnnonces.clear();
        //mesAnnonces.addAll(annonces);
        //adapter.notifyDataSetChanged();
        runLayoutAnimation(recyclerView);
    }

    public void showMessage(String chaine){
        Context context = getContext();
        CharSequence text = chaine;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
