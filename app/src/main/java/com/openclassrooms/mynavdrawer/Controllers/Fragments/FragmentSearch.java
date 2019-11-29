package com.openclassrooms.mynavdrawer.Controllers.Fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.openclassrooms.mynavdrawer.Controllers.Activities.ResultatSearch;
import com.openclassrooms.mynavdrawer.Controllers.Model.Annonce;
import com.openclassrooms.mynavdrawer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearch extends Fragment {
    private Button rechercher;
    private EditText motcle;
    private Spinner spinnerSecteur,spinnerContrat, spinnerLieu;
    private String mot,secteur,contrat,lieu;
    private List<Annonce> mesAnnonces, results;
    private static final String URL_PRODUCTS = "https://www.cjoint.com/doc/19_03/ICymHPth5WW_mesdonnees.json";


    public static FragmentSearch newInstance() {
        return (new FragmentSearch());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_search, container, false);

        motcle = (EditText)view.findViewById(R.id.mot_cle);
        rechercher = (Button)view.findViewById(R.id.envoyer);
        spinnerSecteur = (Spinner)view.findViewById(R.id.spinneSecteur);
        spinnerContrat = (Spinner)view.findViewById(R.id.spinnerContrat);
        spinnerLieu = (Spinner)view.findViewById(R.id.spinnerLieu);
        mesAnnonces = new ArrayList<>();
        //progressDialog.setMessage("Recherche...");
        mesAnnonces = FragmentAnnonces.listAnnonces();

        rechercher.setOnClickListener(v -> {

            mot = motcle.getText().toString();
            secteur = spinnerSecteur.getSelectedItem().toString();
            contrat = spinnerContrat.getSelectedItem().toString();
            lieu = spinnerLieu.getSelectedItem().toString();

            results = new ArrayList<>();

            //test("c'est bon" + mot + " " + secteur + " "+ contrat + " " + lieu );
            //loadAnnonces
            //while ()
            //test("c'est bon" + mot + " " + mesAnnonces.size() + " "+ contrat + " " + lieu );
            if (!mot.isEmpty()){
                //test(getString(mesAnnonces.size()));
                for(int i=0; i<mesAnnonces.size(); i++) {
                    Annonce uneAnnonce = mesAnnonces.get(i);
                    String titreAnnonce = (Html.fromHtml(uneAnnonce.getTitre()).toString()).toLowerCase();
                    if(titreAnnonce.contains(mot.toLowerCase())){
                        results.add(uneAnnonce);
                        //test("ok");
                    }
                    else if(!secteur.contains("Tous") || !lieu.contains("Tous") || !contrat.contains("Tous")){
                        String secteurA = Html.fromHtml(uneAnnonce.getSecteur()).toString().toLowerCase();
                        String lieuA = Html.fromHtml(uneAnnonce.getVille()).toString().toLowerCase();
                        String contratA = Html.fromHtml(uneAnnonce.getContrat()).toString().toLowerCase();
                        if(!lieu.contains("Tous") && !contrat.contains("Tous")){
                            if(secteurA.contains(secteur.toLowerCase()) || (lieuA.contains(lieu.toLowerCase()) && contratA.contains(contrat.toLowerCase())))      {
                                results.add(uneAnnonce);
                            }
                        }
                        else {
                            if(secteurA.contains(secteur.toLowerCase()) || (lieuA.contains(lieu.toLowerCase()) || contratA.contains(contrat.toLowerCase()))) results.add(uneAnnonce);
                        }
                    }
                }
                if(results.size() > 0){
                    //progressDialog.dismiss();
                    //test("Reponse :" + results.size() + "elements trouves");
                    Intent i = new Intent(getContext(), ResultatSearch.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST",(Serializable)results);
                    i.putExtra("BUNDLE",args);
                    startActivity(i);
                }
                else{
                    // progressDialog.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Recherche")
                            .setMessage("Aucun résultat trouver avec le mot clé " + mot + "!!!")
                            .setPositiveButton("OK", (dialog, which) -> {

                            })
                            .setCancelable(false)
                            .create()
                            .show();
                }
            }
            else if(secteur.contains("Tous") && lieu.contains("Tous") && contrat.contains("Tous")){
                test("Un champ au minimum doit etre renseigné !");
            }
            else {
                for(int i=0; i<mesAnnonces.size(); i++) {
                    Annonce uneAnnonce = mesAnnonces.get(i);
                    String secteurA = Html.fromHtml(uneAnnonce.getSecteur()).toString().toLowerCase();
                    String lieuA = Html.fromHtml(uneAnnonce.getVille()).toString().toLowerCase();
                    String contratA = Html.fromHtml(uneAnnonce.getContrat()).toString().toLowerCase();
                    if(!lieu.contains("Tous") && !contrat.contains("Tous")){
                        if(secteurA.contains(secteur.toLowerCase()) || (lieuA.contains(lieu.toLowerCase()) && contratA.contains(contrat.toLowerCase())))      {
                            results.add(uneAnnonce);
                        }
                    }
                    else {
                        if(secteurA.contains(secteur.toLowerCase()) || (lieuA.contains(lieu.toLowerCase()) || contratA.contains(contrat.toLowerCase()))) results.add(uneAnnonce);
                    }
                }
                if(results.size() > 0){
                    //progressDialog.dismiss();
                    //test("Reponse :" + results.size() + "elements trouves");
                    Intent i = new Intent(getContext(), ResultatSearch.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST",(Serializable)results);
                    i.putExtra("BUNDLE",args);
                    startActivity(i);
                }
                else{
                    // progressDialog.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Recherche")
                            .setMessage("Aucun résultat trouver avec le mot clé " + mot + "!!!")
                            .setPositiveButton("OK", (dialog, which) -> {

                            })
                            .setCancelable(false)
                            .create()
                            .show();
                }
            }
        });


        return view;
    }

    public void test(String chaine){
        Context context = getContext();
        CharSequence text = chaine;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
