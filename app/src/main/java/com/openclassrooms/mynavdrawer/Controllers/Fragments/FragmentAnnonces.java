package com.openclassrooms.mynavdrawer.Controllers.Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.Indicators.PagerIndicator;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.openclassrooms.mynavdrawer.Controllers.Model.Annonce;
import com.openclassrooms.mynavdrawer.Controllers.Adapters.AnnonceAdapter;
import com.openclassrooms.mynavdrawer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FragmentAnnonces extends Fragment{

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private static ArrayList<Annonce> mesAnnonces;
    //private List<Offre> offreList;
    private RecyclerView recyclerView;
    private AnnonceAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    //On appelle l'API
    private static final String URL_OFFRES = "https://www.cjoint.com/doc/19_03/ICAx2eqkfCA_mesdonnees.json";


    private SliderLayout mDemoSlider;
    private PagerIndicator indicator;

    SwipeRefreshLayout swipeRefreshLayout;


    public static FragmentAnnonces newInstance() {
        return (new FragmentAnnonces());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_annonces, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.fragment_main_swipe_container);
        mesAnnonces = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.ma_vue);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        indicator = view.findViewById(R.id.custom_indicator);
        //loadAnonces
        //mesAnnonces = Annonce.chargement(URL_OFFRES, getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_OFFRES,
                response -> {
                    try {
                        //converting the string to json array object
                        JSONArray array = new JSONArray(response);
                        String imagUrl = "https://www.nigeremploi.com/images/logo_annonceurs/ceni.png";
                        for (int i=0; i<array.length(); i++){
                            //getting product object from json array
                            JSONObject offre = array.getJSONObject(i);
                            //showMessage(offre.getString("id"));
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

                        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                        recyclerView.addItemDecoration(itemDecoration);
                        adapter = new AnnonceAdapter(getContext(),mesAnnonces);
                        //OffreAdapter adapter = new OffreAdapter(getContext(), offreList);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        //loadProducts();

                        mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);

                        getSlide();
                        runLayoutAnimation(recyclerView);
                        configureSwipeRefreshLayout();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                });

        Volley.newRequestQueue(getContext()).add(stringRequest);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    //test("erreur");
                    adapter.getFilter().filter(newText);
                    return false;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    adapter.getFilter().filter(query);
                    return false;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

    public void getSlide(){

        HashMap<String, String> url_maps = new HashMap<String, String>();
        ArrayList<String> listUrl = new ArrayList<String>();
        ArrayList<String> listName = new ArrayList<String>();

        listUrl.add("https://www.cjoint.com/doc/19_03/ICFtvmtmCcc_nigerAO.png");
        listName.add("Niger Emploi\nPlus de 10 ans au service de demandeurs et de récruteurs d'emplois au Niger...");

        listUrl.add("https://www.cjoint.com/doc/19_03/ICFtwQUkv7c_Nigerhosting.png");
        listName.add("Avec Central Test, testez nos solutions e-testing RH pour une évaluation objective...");

        listUrl.add("https://www.cjoint.com/doc/19_03/ICFtxlelEyc_niger-tic.png");
        listName.add("Depuis son lancement en novembre 2011, NigerHosting.com a séduit des centaines...");

        //listUrl.add("https://upload.wikimedia.org/wikipedia/commons/d/db/Android_robot_2014.svg");
        //listName.add("SVG - Android");

        for (int i = 0; i < listUrl.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(listName.get(i))
                    .image(listUrl.get(i))
                    .setBitmapTransformation(new CenterCrop());
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", listName.get(i));
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setCustomIndicator(indicator);
        mDemoSlider.setDuration(4000);
    }

    public void test(String chaine){
        Context context = getContext();
        CharSequence text = chaine;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
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
    public static ArrayList<Annonce> listAnnonces(){
        //mesAnnonces = Annonce.chargement(URL_OFFRES, getContext());
        return mesAnnonces;
    }
}