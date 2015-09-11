package com.example.xav.mrbank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {


    TextView description;
    TextView price;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        description = (TextView) findViewById(R.id.detail_description);
        description.setText("C'est l'heure de finir ce code pour passer a un autre :)");

        price = (TextView) findViewById(R.id.detail_price);
        price.setText("28 €");

        // On récupère l'intent qui a lancé cette activité
        //Intent i = getIntent();
        // Puis on récupère l'âge donné dans l'autre activité, ou 0 si cet extra n'est pas dans l'intent
        //int age = i.getIntExtra(MainActivity.AGE, 0);
    }
}
