package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controle=Controle.getInstance(this);
        ecouteMenu((ImageButton)findViewById(R.id.btnMenuIMG),CalculActivity.class);
        ecouteMenu((ImageButton)findViewById(R.id.btnMenuHistorique),HistoActivity.class);
    }

    /**
     * Ouvrir l'activity correspondante.
     * @param btn
     * @param classe
     */
    private void ecouteMenu(ImageButton btn,final Class classe){
        btn.setOnClickListener (new ImageButton.OnClickListener() {
           public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
