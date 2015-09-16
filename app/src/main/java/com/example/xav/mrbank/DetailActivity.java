package com.example.xav.mrbank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailActivity extends Activity {

    TextView description;
    TextView price;
    ListView contractor;

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

        contractor = (ListView)findViewById(R.id.list_contractor);
        String[] from = new String[] {"img_src", "name"};
        int[] to = new int[] {R.id.img_contractor, R.id.name_contractor};

        List<HashMap<String, String>> fillMaps = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("img_src", "img_" + i);
            map.put("name", "col_1_item_" + i);
            fillMaps.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.listview_row_contractor, from, to);
        contractor.setAdapter(adapter);
    }
}
