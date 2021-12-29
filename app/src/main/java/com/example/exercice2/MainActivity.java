package com.example.exercice2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercice2.resutat.Resultat;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
//    Declarationd des varabiales qu'on a besoin.
    ListView listItems;
    Spinner sp;
    TextView note , moyenne;
    List<Resultat> lr = new ArrayList<Resultat>();
    Integer nombreTotaleDuModules = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Finding Views elements by ID
        listItems = findViewById(R.id.listID);
        sp = findViewById(R.id.spinner2);
        note = findViewById(R.id.noteid);
        moyenne = findViewById(R.id.moyenneid);

    }


    public void getSpinnerValue(View v){
//        declaraion des variables.
        Boolean itemAlreadyExists;
        String module = sp.getSelectedItem().toString();
        String n = note.getText().toString();
        Float notef = Float.parseFloat(n);
        Resultat r = new Resultat(module , notef);


        if( lr.size() != 0) {
//      si la list des resulat n'est pas vide on verifie que le module deja exist ou pas.
            itemAlreadyExists = checkIfItemAlreadyExists(lr , module);
            if( !itemAlreadyExists){
//      si le module n'exist pas on l'ajoute au liste des resultats.
                lr.add(r);
//      et on update la ListView
                updateListView(lr);
            }else{
//      si le module deja exist dans la liste des resultat on Toast un alert.
                Toast toast = Toast.makeText(MainActivity.this , "Module Deja Existe !" ,Toast.LENGTH_SHORT );
                toast.show();
            }
        }else{
//      sinon on insere le premier element sans verification d'existance
            lr.add(r);
            updateListView(lr);
        }
    }

//  Cette fonction Simplememt verifie si un (variable String module) exist dans une (list des class resultat).
    private Boolean checkIfItemAlreadyExists(List<Resultat> lr, String module) {
        for (int i = 0; i < lr.size() ; i++){
            if( lr.get(i).toString().contains(module) ){
                return true;
            }
        }
        return false;
    }

//  fonction responsable sur la mise a jour du ListView
    private void updateListView(List<Resultat> lr) {
        ArrayList arrayMemory = new ArrayList();
        float somme = 0;

        for (int i = 0; i < lr.size() ; i++){
            arrayMemory.add(
                    lr.get(i).getModule() + " " + lr.get(i).getNote().toString()
            );
            somme += lr.get(i).getNote();
        }
        ArrayAdapter ad = new ArrayAdapter(this , android.R.layout.simple_list_item_1 ,arrayMemory );
        listItems.setAdapter(ad);

//      si tous les modules sont present dans la list des resultat => On calcule la moyen et on les affiche.
        if (lr.size() == nombreTotaleDuModules){
            float moy = somme/nombreTotaleDuModules;
            moyenne.setText(Float.toString(moy));
        }

    }


}