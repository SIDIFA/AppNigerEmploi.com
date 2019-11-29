package com.openclassrooms.mynavdrawer.Controllers.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.glide.slider.library.SliderLayout;
import com.openclassrooms.mynavdrawer.Controllers.Model.Annonce;
import com.openclassrooms.mynavdrawer.Controllers.Adapters.AnnonceAdapter;
import com.openclassrooms.mynavdrawer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Similaire_annonce extends Fragment {
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private List<Annonce> mesAnnonces, results;
    private RecyclerView recyclerView;
    private AnnonceAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private String url = "https://www.cjoint.com/doc/19_03/ICxbHnMTuai_offre.json";
    //private String url = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";

    private SliderLayout mDemoSlider;
    SwipeRefreshLayout swipeRefreshLayout;

    public static Similaire_annonce newInstance() {
        return (new Similaire_annonce());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_similaire__annonce, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.fragment_main_swipe_container);
        recyclerView = (RecyclerView) view.findViewById(R.id.ma_vue_similaire);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mesAnnonces = new ArrayList<>();
        results = new ArrayList<>();
        //progressDialog.setMessage("Recherche...");
        mesAnnonces = FragmentAnnonces.listAnnonces();
        String secteur = getActivity().getIntent().getExtras().getString("secteur");
        int id = getActivity().getIntent().getExtras().getInt("id");
        Annonce annonce;
        for (int i=0; i<mesAnnonces.size(); i++){
            annonce = mesAnnonces.get(i);
            if((annonce.getSecteur().contains(secteur) || (secteur.contains(annonce.getSecteur()))) && id != annonce.getId() ){
                results.add(annonce);
            }
        }
        if(results.size() == 0){
            String lieu=getActivity().getIntent().getExtras().getString("lieu");
            String contrat=getActivity().getIntent().getExtras().getString("contrat");
            for (int i=0; i<mesAnnonces.size(); i++){
                annonce = mesAnnonces.get(i);
                if((annonce.getVille().equals(lieu) && annonce.getContrat().equals(contrat)) && id != annonce.getId() ){
                    results.add(annonce);
                }
            }
        }
        if(results.size() == 0){
            test("Pas d'annonces simulaires pour cette annoces" +id);
        }
        //String strtext=getActivity().getIntent().getExtras().getString("contrat");

        //this.test("Nombre de vues: " +strtext);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        adapter = new AnnonceAdapter(getContext(),results);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        runLayoutAnimation(recyclerView);
        configureSwipeRefreshLayout();

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
        adapter.notifyDataSetChanged();
        runLayoutAnimation(recyclerView);
    }
    public void test(String chaine){
        Context context = getContext();
        CharSequence text = chaine;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
