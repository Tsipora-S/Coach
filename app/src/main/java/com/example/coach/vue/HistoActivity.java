package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.Profil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HistoActivity extends AppCompatActivity {

    private Controle controle;
    private static AccesDistant accesDistant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        this.controle=Controle.getInstance(this);
        ecouteRetourMenu();
        creerListe();
    }
    /**
     * Retour au menu
     */
    private void ecouteRetourMenu(){
        final ImageButton ImageButton = ((ImageButton)findViewById(R.id.btnRetourDeHisto));
        ImageButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    /**
     * Creer la liste adapter.
     */
    private void creerListe(){
        accesDistant=new AccesDistant();
        accesDistant.envoi("tous",new JSONArray());
        ArrayList<Profil> lesProfils=controle.getLesProfils();
        Collections.sort(lesProfils,Collections.<Profil>reverseOrder());
        if(lesProfils!=null){
            ListView lstHisto = (ListView)findViewById(R.id.lstHisto);
            HistoListAdapter adapter=new HistoListAdapter(this,lesProfils);
            lstHisto.setAdapter(adapter);
        }
    }

    /**
     * Demande d'afficher le profil dans CalculActivity
     * @param profil
     */
    public void afficheProfil(Profil profil){
        controle.setProfil(profil);
        Intent intent = new Intent(HistoActivity.this, CalculActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
