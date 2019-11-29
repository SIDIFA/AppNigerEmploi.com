package com.openclassrooms.mynavdrawer.Controllers.Fragments;



import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.mynavdrawer.Controllers.Activities.DetailAnnonce;
import com.openclassrooms.mynavdrawer.Controllers.Model.AccesLocal;
import com.openclassrooms.mynavdrawer.Controllers.Model.Annonce;
import com.openclassrooms.mynavdrawer.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UneAnnonce extends Fragment {

    private TextView nomSociete;
    private TextView vue;
    private TextView titre;
    private TextView description;
    private TextView condition;
    private TextView contrat;
    private TextView niveau;
    private TextView ville;
    private TextView delai;
    private TextView formation;
    private TextView experience;
    private TextView langue;
    private TextView contact;
    private TextView couriel;
    private TextView siteweb;
    private TextView telephone;
    private TextView secteur;

    private TextView facebook;
    private TextView google;
    private TextView whatsapp;
    private TextView twitter;

    private ImageView favris;
    private boolean etat = true;

    private static AccesLocal accesLocal;

    private List mesAnnonces;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private String sms;

    public static UneAnnonce newInstance() {
        return (new UneAnnonce());
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_une_annonce, container, false);
        accesLocal = new AccesLocal(getContext());
        mesAnnonces = new ArrayList<String>();
        mesAnnonces = accesLocal.getAllData();
        favris = (ImageView) view.findViewById(R.id.favoris);

        int id  = getActivity().getIntent().getExtras().getInt("id");
        String titre  = getActivity().getIntent().getExtras().getString("titre");
        String nomSociete  = getActivity().getIntent().getExtras().getString("nomSociete");
        String vue  = getActivity().getIntent().getExtras().getString("vue");
        String description  = getActivity().getIntent().getExtras().getString("description");
        String condition  = getActivity().getIntent().getExtras().getString("condition");
        String contrat  = getActivity().getIntent().getExtras().getString("contrat");
        String niveau  = getActivity().getIntent().getExtras().getString("niveau");
        String ville  = getActivity().getIntent().getExtras().getString("lieu");
        String delai  = getActivity().getIntent().getExtras().getString("delai");
        String formation  = getActivity().getIntent().getExtras().getString("formation");
        String experience  = getActivity().getIntent().getExtras().getString("experience");
        String langue  = getActivity().getIntent().getExtras().getString("langue");
        String contact  = getActivity().getIntent().getExtras().getString("contact");
        String couriel  = getActivity().getIntent().getExtras().getString("couriel");
        String siteweb  = getActivity().getIntent().getExtras().getString("siteweb");
        String telephone  = getActivity().getIntent().getExtras().getString("telephone");
        String secteur  = getActivity().getIntent().getExtras().getString("secteur");
        String image_url = getActivity().getIntent().getExtras().getString("imageUrl") ;
        sms = getActivity().getIntent().getExtras().getString("o_resum_sms") ;


        Bundle bundle=new Bundle();
        bundle.putString("secteur", secteur);
        //set Fragmentclass Arguments
        Similaire_annonce fragobj=new Similaire_annonce();
        fragobj.setArguments(bundle);

        this.nomSociete = (TextView)view.findViewById(R.id.aa_organisme);
        this.titre = (TextView)view.findViewById(R.id.aa_titre);
        //this.vue = (TextView)view.findViewById(R.id.aa_v);
        this.description = (TextView) view.findViewById(R.id.aa_description);
        this.condition = (TextView) view.findViewById(R.id.aa_condition);
        this.contrat = (TextView)view.findViewById(R.id.aa_contrat);
        this.niveau = (TextView)view.findViewById(R.id.aa_niveau);
        this.ville = (TextView)view.findViewById(R.id.aa_lieu);
        this.delai = (TextView)view.findViewById(R.id.aa_validateDate);
        this.formation = (TextView)view.findViewById(R.id.aa_formation);
        this.experience = (TextView)view.findViewById(R.id.aa_experience);
        this.langue = (TextView)view.findViewById(R.id.aa_langue);
        this.contact = (TextView)view.findViewById(R.id.aa_contact);
        this.couriel = (TextView)view.findViewById(R.id.aa_couriel);
        this.siteweb = (TextView)view.findViewById(R.id.aa_siteweb);
        this.telephone = (TextView)view.findViewById(R.id.aa_telephone);
        this.secteur = (TextView)view.findViewById(R.id.aa_secteur);
        ImageView imageUrl =(ImageView) view.findViewById(R.id.aa_logo);

        if(mesAnnonces.size() > 0){
            for (int i=0;i<mesAnnonces.size();i++){
                if (String.valueOf(id).equals(String.valueOf(mesAnnonces.get(i)))){
                    favris.setImageResource(R.drawable.ic_favorite_black_36dp);
                    etat = false;
                }
            }
        }
        //this.vue.setText(vue);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.description.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT));
            this.condition.setText(Html.fromHtml(condition, Html.FROM_HTML_MODE_COMPACT));
            this.titre.setText(Html.fromHtml(titre, Html.FROM_HTML_MODE_COMPACT));
            this.contrat.setText(Html.fromHtml(contrat, Html.FROM_HTML_MODE_COMPACT));
            this.niveau.setText(Html.fromHtml(niveau, Html.FROM_HTML_MODE_COMPACT));
            this.formation.setText(Html.fromHtml(formation, Html.FROM_HTML_MODE_COMPACT));
            this.experience.setText(Html.fromHtml(experience, Html.FROM_HTML_MODE_COMPACT));
            this.langue.setText(Html.fromHtml(langue, Html.FROM_HTML_MODE_COMPACT));
            this.secteur.setText(Html.fromHtml(secteur, Html.FROM_HTML_MODE_COMPACT));
            this.nomSociete.setText(Html.fromHtml(nomSociete, Html.FROM_HTML_MODE_COMPACT));
        } else {
            this.description.setText(Html.fromHtml(description));
            this.condition.setText(Html.fromHtml(condition));
            this.titre.setText(Html.fromHtml(titre));
            this.contrat.setText(Html.fromHtml(contrat));
            this.niveau.setText(Html.fromHtml(niveau));
            this.formation.setText(Html.fromHtml(formation));
            this.experience.setText(Html.fromHtml(experience));
            this.langue.setText(Html.fromHtml(langue));
            this.secteur.setText(Html.fromHtml(secteur));
            this.nomSociete.setText(Html.fromHtml(nomSociete));
        }
        this.ville.setText(ville);
        this.delai.setText(delai);
        this.couriel.setText(couriel);
        this.siteweb.setText(siteweb);
        this.telephone.setText(telephone);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.orange1).error(R.drawable.orange1);

        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(imageUrl);

        this.facebook = (TextView)view.findViewById(R.id.facebook);
        this.google = (TextView)view.findViewById(R.id.google);
        this.whatsapp = (TextView)view.findViewById(R.id.whatsapp);
        this.twitter = (TextView)view.findViewById(R.id.twitter);

        this.facebook.setOnClickListener(v -> {
            SharingToSocialMedia("com.facebook.katana");
        });

        this.google.setOnClickListener(v -> {
            SharingToSocialMedia("com.google.android.apps.plus");
        });

        this.whatsapp.setOnClickListener(v -> {
            SharingToSocialMedia("com.whatsapp");
        });

        this.twitter.setOnClickListener(v -> {
            SharingToSocialMedia("com.twitter.android");
        });
        this.favris.setOnClickListener(v -> {
            if (etat){
                showMessage(String.valueOf(id));
                Boolean resultat = accesLocal.insertData(String.valueOf(id));

                if (resultat){
                    favris.setImageResource(R.drawable.ic_favorite_black_36dp);
                    showMessage("Cette est ajoutée dans Mes favories !");
                    etat = false;
                }
                else {
                    showMessage("ERROR "+"NO BD FOUND");
                }
            }
            else {

                Boolean result = accesLocal.deleteData(String.valueOf(id));

                if (result){

                    showMessage("Offre supprimée de Mes favories !");
                    favris.setImageResource(R.drawable.ic_favorite_border_black_36dp);
                    etat = true;
                }
                else {
                    showMessage("ERROR "+"NO BD FOUND");
                }
            }

        });


        return view;
    }

    public void SharingToSocialMedia(String application) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = this.sms;
        String shareSub = "Offre";
        String shareTitle = "Offre NigerEmplois";
        intent.putExtra(android.content.Intent.EXTRA_TITLE, shareTitle);
        intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);

        boolean installed = checkAppInstall(application);
        if (installed) {
            intent.setPackage(application);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(),
                    "Installed application first", Toast.LENGTH_LONG).show();
        }

    }


    private boolean checkAppInstall(String uri) {
        PackageManager pm = getContext().getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    public void showMessage(String chaine){
        Context context = getContext();
        CharSequence text = chaine;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
