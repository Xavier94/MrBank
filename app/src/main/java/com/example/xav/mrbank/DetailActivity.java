package com.example.xav.mrbank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class DetailActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // On récupère l'intent qui a lancé cette activité
        //Intent i = getIntent();
        // Puis on récupère l'âge donné dans l'autre activité, ou 0 si cet extra n'est pas dans l'intent
        //int age = i.getIntExtra(MainActivity.AGE, 0);
    }
}
