package com.openclassrooms.mynavdrawer.Controllers.Model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Annonce implements Serializable {


    private String nomSociete;
    private String vue;
    private String titre;
    private String description;
    private String condition;
    private String contrat;
    private String niveau;
    private String ville;
    private String delai;
    private String formation;
    private String experience;
    private String langue;
    private String contact;
    private String couriel;
    private String siteweb;
    private String telephone;
    private String secteur;
    private String o_resum_sms ;
    private int id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;



    public String getO_resum_sms() {
        return o_resum_sms;
    }

    public void setO_resum_sms(String o_resum_sms) {
        this.o_resum_sms = o_resum_sms;
    }



    public Annonce(){

    }

    public Annonce(int id, String nomSociete, String vue, String titre, String description, String condition, String contrat, String niveau, String ville, String delai, String formation, String experience, String langue, String contact, String couriel, String siteweb, String telephone, String secteur, String o_resum_sms, String image) {
        this.id = id;
        this.nomSociete = nomSociete;
        this.vue = vue;
        this.titre = titre;
        this.description = description;
        this.condition = condition;
        this.contrat = contrat;
        this.niveau = niveau;
        this.ville = ville;
        this.delai = delai;
        this.formation = formation;
        this.experience = experience;
        this.langue = langue;
        this.contact = contact;
        this.couriel = couriel;
        this.siteweb = siteweb;
        this.telephone = telephone;
        this.secteur = secteur;
        this.o_resum_sms = o_resum_sms;
        this.image = image;

    }
    public int getId() {   return id;   }

    public void setId(int id) {  this.id = id;     }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCouriel() {
        return couriel;
    }

    public void setCouriel(String couriel) {
        this.couriel = couriel;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getVue() {
        return vue;
    }

    public void setVue(String vue) {
        this.vue = vue;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getDelai() {
        return delai;
    }

    public void setDelai(String delai) {
        this.delai = delai;
    }
    public static ArrayList<Annonce> chargement(String URL_ANNONCES, Context ctx){
        ArrayList<Annonce> listAnnonces = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ANNONCES,
                response -> {
                    try {
                        //converting the string to json array object
                        JSONArray array = new JSONArray(response);
                        String imagUrl = "https://www.nigeremploi.com/images/logo_annonceurs/ceni.png";
                        for (int i=0; i<array.length(); i++){
                            //getting product object from json array
                            JSONObject offre = array.getJSONObject(i);
                            //test("c'est bon");

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
                            //annonces.setSiteweb(offre.getString("NP"));
                            annonces.setSiteweb(offre.getString("url_contact"));
                            annonces.setImage(imagUrl);
                            annonces.setO_resum_sms(offre.getString("o_resum_sms"));

                            listAnnonces.add(annonces);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {

                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(ctx).add(stringRequest);
        //test(getString(mesAnnonces.size()));
        return listAnnonces;
    }
    public static ArrayList<Annonce> chargement(JSONArray array, String imagUrl) throws JSONException {
        ArrayList<Annonce> listAnnonces = new ArrayList<>();
        for (int i=0; i<array.length(); i++){
            //String imagUrl = "https://www.nigeremploi.com/images/logo_annonceurs/ceni.png";
            JSONObject offre = array.getJSONObject(i);

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
            //annonces.setSiteweb(offre.getString("NP"));
            annonces.setSiteweb(offre.getString("url_contact"));
            annonces.setImage(imagUrl);
            annonces.setO_resum_sms(offre.getString("o_resum_sms"));

            listAnnonces.add(annonces);
        }
        return listAnnonces;
    }
}
