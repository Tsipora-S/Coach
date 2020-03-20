package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.outils.MesOutils;

public class CalculActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        init();
    }

    //propriétés
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Controle controle;

    /**
     * initialisations des liens avec les objets graphiques
     */
    private void init(){
        txtPoids=(EditText)findViewById(R.id.txtPoids);
        txtTaille=(EditText)findViewById(R.id.txtTaille);
        txtAge=(EditText)findViewById(R.id.txtAge);
        rdHomme=(RadioButton)findViewById(R.id.rdHomme);
        rdFemme=(RadioButton)findViewById(R.id.rdFemme);
        lblIMG=(TextView)findViewById(R.id.lblIMG);
        imgSmiley=(ImageView)findViewById(R.id.imgSmiley);
        this.controle=Controle.getInstance(this);
        ecouteCalcul();
        ecouteRetourMenu();
        recupProfil();
    }

    /**
     * ecoute evenement sur le bouton calcul
     */
    private void ecouteCalcul(){
        final Button button = findViewById(R.id.btnCalc);

        button.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //Toast.makeText(CalculActivity.this,"test",Toast.LENGTH_SHORT).show();
                // Log.d("message","clic ok sur le bouton calcul*************");

                Integer poids=0;
                Integer taille=0;
                Integer age=0;
                Integer sexe=0;
                //récupération des données saisies
                try{
                    poids=Integer.parseInt(txtPoids.getText().toString());
                    taille=Integer.parseInt(txtTaille.getText().toString());
                    age=Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e){}
                if(rdHomme.isChecked()){
                    sexe=1;
                }
                //controle des données saisies
                if(poids==0||taille==0||age==0){
                     Toast.makeText(CalculActivity.this,"Saisie incorrecte",Toast.LENGTH_SHORT).show();
                }else{
                    afficheResult(poids,taille,age,sexe);
                }
            }
        });
    }

    /**affichage d l'IMG, du message et de l'image
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */

     private void afficheResult(Integer poids,Integer taille,Integer age,Integer sexe) {
         //création de profil et récupération des informations
         this.controle.creerProfil(poids, taille, age, sexe,this);
         float img = this.controle.getIMG();
         String message = this.controle.getMessage();
         //affichage
         if (message == "normal") {
             imgSmiley.setImageResource(R.drawable.normal);
             lblIMG.setTextColor(Color.GREEN);
         } else {
             lblIMG.setTextColor(Color.RED);
             if (message == "trop faible") {
                 imgSmiley.setImageResource(R.drawable.maigre);
             } else {
                 imgSmiley.setImageResource(R.drawable.graisse);
             }
         }

         lblIMG.setText(MesOutils.format2Decimal(img) + " : IMG" + message);
     }

    /**
     * Recuperation du profil s'il a été serialisé
     */
    public void recupProfil(){
        if(controle.getPoids()!=null) {
            txtPoids.setText(controle.getPoids().toString());
            txtPoids.setText(controle.getTaille().toString());
            txtPoids.setText(controle.getAge().toString());
            rdFemme.setChecked(true);
            if (controle.getSexe() == 1) {
                rdHomme.setChecked(true);
            }
            //remettre à vide le profil
            controle.setProfil(null);
        }
            //simule le clic sur le bouton calcul
         //   ((Button)findViewById(R.id.btnCalc)).performClick();
     }

    /**
     * Retour au menu
     */
    private void ecouteRetourMenu(){
        final ImageButton ImageButton = ((ImageButton)findViewById(R.id.btnReturDeCalcul));
        ImageButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
     }


