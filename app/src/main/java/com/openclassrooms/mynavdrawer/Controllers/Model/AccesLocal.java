package com.openclassrooms.mynavdrawer.Controllers.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.openclassrooms.mynavdrawer.Controllers.BD.BDoffres;

import java.util.ArrayList;
import java.util.List;

import static com.openclassrooms.mynavdrawer.Controllers.BD.BDoffres.COL_ID;
import static com.openclassrooms.mynavdrawer.Controllers.BD.BDoffres.COL_NUM;
import static com.openclassrooms.mynavdrawer.Controllers.BD.BDoffres.TABLE_OFFRE;

public class AccesLocal {

    public static final String DATABASE_NAME = "base.db";
    private BDoffres db_offre;
    private Integer versionBase = 1;
    private SQLiteDatabase db;
    private List maListe;

    public AccesLocal(Context context){
        db_offre = new BDoffres(context,DATABASE_NAME,null,versionBase);
    }

    public boolean insertData(String id){
        db = db_offre.getWritableDatabase();
        ContentValues contenu = new ContentValues();
        //contenu.put(COL_ID,annonce.getId());
        contenu.put(COL_NUM,id);
        /*contenu.put(COL_ORGANISME,annonce.getNomSociete());
        contenu.put(COL_TITRE_OFFRE,annonce.getTitre());
        contenu.put(COL_VUE,annonce.getVue());
        contenu.put(COL_DESCRIPTION,annonce.getDescription());
        contenu.put(COL_CONDITION, annonce.getCondition());
        contenu.put(COL_CONTRAT,annonce.getContrat());
        contenu.put(COL_NIVEAU,annonce.getNiveau());
        contenu.put(COL_LIEU,annonce.getVille());
        contenu.put(COL_DELAI,String.valueOf(annonce.getDelai()));
        contenu.put(COL_FORMATION,annonce.getFormation());
        contenu.put(COL_LANGUE,annonce.getLangue());
        contenu.put(COL_EXPERIENCE,annonce.getExperience());
        contenu.put(COL_SECTEUR,annonce.getSecteur());
        contenu.put(COL_CONTACT,annonce.getContrat());
        contenu.put(COL_COURIEL,annonce.getCouriel());
        contenu.put(COL_SITEWEB,annonce.getSiteweb());
        contenu.put(COL_TELEPHONE,annonce.getTelephone());
        contenu.put(COL_SMS,annonce.getO_resum_sms());
        contenu.put(COL_URL_IMAGE,annonce.getImage());*/

        Long resultat = db.insert(TABLE_OFFRE,null,contenu);
        db.close();

        if (resultat == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public List getAllData() {
        db = db_offre.getReadableDatabase();
        Cursor resultat = db.rawQuery("select * from " + TABLE_OFFRE, null);
        maListe = new ArrayList<>();

        while (resultat.moveToNext()) {
            maListe.add(resultat.getString(1));
        }

        resultat.close();
        db.close();
        return maListe;
    }

    /*
    public List<Annonce> getAllData(){
        db = db_offre.getReadableDatabase();
        Cursor resultat = db.rawQuery("select * from "+TABLE_OFFRE,null);
        mesAnnonces = new ArrayList<>();
            while (resultat.moveToNext()){
                Annonce annonce = new Annonce();
                annonce.setId(resultat.getString(1));
                annonce.setNomSociete(resultat.getString(2));
                annonce.setTitre(resultat.getString(3));
                annonce.setVue(resultat.getString(4));
                annonce.setDescription(resultat.getString(5));
                annonce.setCondition(resultat.getString(6));
                annonce.setContrat(resultat.getString(7));
                annonce.setNiveau(resultat.getString(8));
                annonce.setVille(resultat.getString(9));
                annonce.setDelai(resultat.getString(10));
                annonce.setFormation(resultat.getString(11));
                annonce.setLangue(resultat.getString(12));
                annonce.setExperience(resultat.getString(13));
                annonce.setSecteur(resultat.getString(14));
                annonce.setContact(resultat.getString(15));
                annonce.setCouriel(resultat.getString(16));
                annonce.setSiteweb(resultat.getString(17));
                annonce.setTelephone(resultat.getString(18));
                annonce.setO_resum_sms(resultat.getString(19));
                annonce.setImage(resultat.getString(20));

                mesAnnonces.add(annonce);
            }

        return mesAnnonces;
    }*/

    public boolean deleteData(String id) {
        db = db_offre.getWritableDatabase();
        int resultat = db.delete(TABLE_OFFRE, COL_NUM + "=" + id, null);
        db.close();
        if (resultat == 1){
            return true;
        }
        else {
            return false;
        }
    }
}
