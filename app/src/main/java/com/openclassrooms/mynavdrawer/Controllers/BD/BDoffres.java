package com.openclassrooms.mynavdrawer.Controllers.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDoffres extends SQLiteOpenHelper {

    // Table de la base de données
    public static final String TABLE_OFFRE = "offre";
    public static final String COL_ID = "_id";
    public static final String COL_NUM = "num_o";
    /*public static final String COL_ORGANISME = "organisme_o";
    public static final String COL_TITRE_OFFRE = "titre_offre";
    public static final String COL_VUE = "nbr_vue_o";
    public static final String COL_DESCRIPTION = "description_o";
    public static final String COL_CONDITION = "condition_o";
    public static final String COL_CONTRAT = "contrat_o";
    public static final String COL_NIVEAU = "niveau_o";
    public static final String COL_LIEU = "lieu_o";
    public static final String COL_DELAI = "validite_o";
    public static final String COL_FORMATION = "formation_o";
    public static final String COL_LANGUE = "langue_o";
    public static final String COL_EXPERIENCE = "experience_o";
    public static final String COL_SECTEUR = "secteur_o";
    public static final String COL_CONTACT = "contact_o";
    public static final String COL_COURIEL = "couriel_o";
    public static final String COL_SITEWEB = "siteweb_o";
    public static final String COL_TELEPHONE = "phone_o";
    public static final String COL_SMS = "sms_o";
    public static final String COL_URL_IMAGE = "url_o";*/

    // Instruction SQL de création de la base de données
    private static final String DATABASE_CREATE = "create table "
            + TABLE_OFFRE
            + "("
            + COL_ID + " integer primary key autoincrement, "
            + COL_NUM + " text not null"
            /*+ COL_ORGANISME + " text, "
            + COL_TITRE_OFFRE + " text,"
            + COL_VUE + "text,"
            + COL_DESCRIPTION + " text,"
            + COL_CONDITION + " text,"
            + COL_CONTRAT + "text,"
            + COL_NIVEAU + "text,"
            + COL_LIEU + "text,"
            + COL_DELAI + "text,"
            + COL_FORMATION + "text,"
            + COL_LANGUE + "text,"
            + COL_EXPERIENCE + "text,"
            + COL_SECTEUR + "text,"
            + COL_CONTACT + "text,"
            + COL_COURIEL + "text,"
            + COL_SITEWEB + "text,"
            + COL_TELEPHONE + "text,"
            + COL_SMS + "text,"
            + COL_URL_IMAGE + "text"*/
            + ");";

    public BDoffres(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_OFFRE);
        //onCreate(db);
    }
}
