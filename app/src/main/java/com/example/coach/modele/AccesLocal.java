package com.example.coach.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coach.outils.MesOutils;
import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {
    //propriétés
    private String nomBase="bdCoach.sqlite";
    private Integer versioBase=1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * Constructeur
     * @param contexte
     */
    public AccesLocal(Context contexte){
        accesBD=new MySQLiteOpenHelper(contexte,nomBase,null,versioBase);
    }

    /**
     * Ajout d'un profil ds la bdd
     * @param profil
     */
    public void ajout(Profil profil){
        String dd=MesOutils.convertDateToString(profil.getDateMesure());
        bd=accesBD.getWritableDatabase();
        String req="insert into profil(datemesure,poids,taille,age,sexe) values";
        req +="(\""+dd+"\","+profil.getPoids()+","+profil.getTaille()+","+profil.getAge()+","+profil.getSexe()+")";
        bd.execSQL(req);

    }

    /**
     * Recuperation du dernier profil de la bdd
     * @return
     */
    public Profil recupDernier(){
        bd=accesBD.getReadableDatabase();
        Profil profil=null;
        String req="select * from profil";
        Cursor curseur=bd.rawQuery(req,null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Date date= MesOutils.convertStringToDate(curseur.getString(0));
            Integer poids=curseur.getInt(1);
            Integer taille=curseur.getInt(2);
            Integer age=curseur.getInt(3);
            Integer sexe=curseur.getInt(4);
            profil=new Profil(date,poids,taille,age,sexe);
        }
        curseur.close();
        return profil;
    }
}
