package com.example.coach.outils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MesOutils {

    /**
     * Conversion chaine sous format ("EEE XXX dd hh:mm:ss 'GMT' yyyy ") vers date
     * @param uneDate
     * @return
     */
    public static Date convertStringToDate(String uneDate){
        return convertStringToDate(uneDate,"EEE XXX dd hh:mm:ss 'GMT' yyyy");
    }

    /**
     * Conversion du format reçu en paramètre vers date
     * @param uneDate
     * @param formatAttendu
     * @return
     */
    public static Date convertStringToDate(String uneDate, String formatAttendu){
        SimpleDateFormat formatter=new SimpleDateFormat(formatAttendu);
        try{
            Date date=formatter.parse(uneDate);
            return date;
        }catch(ParseException e){
            Log.d("erreur","parse de la date impossible"+e.toString());
            e.printStackTrace();
        }
        return null;
    }

   /**
     * Conversion d'une date en chaine sous la forme yyyy-MM-dd hh:mm:ss
     * @param uneDate
     * @return
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return date.format(uneDate);
    }

    /**
     * Retourne un float au format string avec un chiffre apres la virgule.
     * @param valeur
     * @return
     */
    public static String format2Decimal(float valeur){
        return String.format("%.01f", valeur);
    }
}
