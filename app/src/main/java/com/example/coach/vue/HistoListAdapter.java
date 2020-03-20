package com.example.coach.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;
import com.example.coach.outils.MesOutils;

import java.util.ArrayList;

public class HistoListAdapter extends BaseAdapter {

    private ArrayList<Profil> lesProfils;
    private LayoutInflater inflater;
    private Controle controle;
    private Context contexte;

    public HistoListAdapter(Context contexte, ArrayList<Profil> lesProfils){
        this.lesProfils=lesProfils;
        this.inflater=LayoutInflater.from(contexte);
        this.controle=Controle.getInstance(null);
        this.contexte=contexte;
    }

    /**
     * Retourne le nombre de lignes.
     * @return
     */
    @Override
    public int getCount() {
        return lesProfils.size();
    }

    /**
     * Retourne l'item de la ligne actuelle.
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return lesProfils.get(i);
    }

    /**
     * Retourne un indice par rapport à la ligne actuelle.
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Retourne la ligne(view) formatée avec gestion des evenements.
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Declaration d'un holder.
        ViewHolder holder;
        //si la ligne n'existe pas encore.
        if(view==null){
            holder=new ViewHolder();
            //la ligne est construite avec un formatage(inflater) relié à layout_list_histo.
            view=inflater.inflate(R.layout.layout_liste_histo,null);
            //chaque propriété du holder est reliée à une prpriété graphique.
            holder.txtListDate=((TextView)view.findViewById(R.id.txtListDate));
            holder.txtListIMG=((TextView)view.findViewById(R.id.txtListIMG));
            holder.btnListSuppr=((ImageButton)view.findViewById(R.id.btnListSuppr));
            //affecter le holder à la vue.
            view.setTag(holder);
        }else{
            //recuperation du holder dans la ligne existante.
            holder=(ViewHolder)view.getTag();
        }
        //valorisation du contenu du holder.
        holder.txtListDate.setText(MesOutils.convertDateToString(lesProfils.get(i).getDateMesure()));
        holder.txtListIMG.setText(MesOutils.format2Decimal(lesProfils.get(i).getImg()));
        holder.btnListSuppr.setTag(i);
        //clic sur la croix pour supprimer le profil enregistré
        holder.btnListSuppr.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position =(int)v.getTag();
                //demande de suppression au controleur
                controle.delProfil(lesProfils.get(position));
                //rafraichir la liste
                notifyDataSetChanged();
            }
        });
        holder.txtListDate.setTag(i);
        //clic sur le reste de la ligne
        holder.txtListDate.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position =(int)v.getTag();
                //demande de l'affichage du profil dans calculActivity
                ((HistoActivity)contexte).afficheProfil(lesProfils.get(position));
            }
        });
        holder.txtListIMG.setTag(i);
        //clic sur le reste de la ligne
        holder.txtListIMG.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position =(int)v.getTag();
                //demande de l'affichage du profil dans calculActivity
                ((HistoActivity)contexte).afficheProfil(lesProfils.get(position));
            }
        });
        return view;
    }

    private class ViewHolder{
        ImageButton btnListSuppr;
        TextView txtListDate;
        TextView txtListIMG;
    }
}
