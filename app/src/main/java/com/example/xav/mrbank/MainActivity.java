package com.example.xav.mrbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView contract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contract = (ListView) findViewById(R.id.list_contract);

        String[] names = new String[] { "Cristiano Ronaldo", "Lionel Messi", "Pelé" };
        int[] ages = { 30, 27, 74, 54 };
        int[] images = { R.drawable.a01, R.drawable.a02, R.drawable.a03 };

        String[][] data = new String[][] {
                {"0", "120", "0"}, // New / min / no money
                {"1", "10", "52"}, // Current / min / money
                {"2", "0", "69"}, // Win / no time / money
                {"3", "0", "-28"}, // Lost /no time / money
                {"4", "0", "0"}, // Judgement / no time / no money
                {"1", "10", "10"}, // Current / min / money
                {"1", "10", "100"}, // Current / min / money
                {"2", "0", "69"}, // Win / no time / money
                {"4", "0", "0"}, // Judgement / no time /
                {"0", "120", "0"}, // New / min / no money
                {"0", "120", "0"}, // New / min / no money
        };

        ArrayList<ListItem> contracts = new ArrayList<>();

        /*
        for (int i = 0; i < names.length; i++) {
            contracts.add(new ListItem(names[i], ages[i], images[i]));
        }
        */
        for (int i = 0; i < data.length; i++) {
            contracts.add(new ListItem(data[i]));
        }

        ContractAdapter adapter = new ContractAdapter(this, contracts);
        contract.setAdapter(adapter);
        contract.setOnItemClickListener(adapter);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //On récupère une ListView de notre layout en XML, c'est la vue qui représente la liste
        //vue = (ListView) findViewById(R.id.listView);

        /*
         * On entrepose nos données dans un tableau qui contient deux colonnes :
         *  - la première contiendra le nom de l'utilisateur
         *  - la seconde contiendra le numéro de téléphone de l'utilisateur
         */
        String[][] repertoire = new String[][] {
                {"Bill Gates", "06 06 06 06 06"},
                {"Bill Gates", "06 06 06 06 06"},
                {"Niels Bohr", "05 05 05 05 05"},
                {"Niels Bohr", "05 05 05 05 05"},
                {"Alexandre III de Macédoine", "04 04 04 04 04"},
                {"Alexandre III de Macédoine", "04 04 04 04 04"},
                {"Plouf", "+33 2 54 89 65 10"},
                {"Plouf", "+33 2 54 89 65 10"},
                {"Plaf", "+33 2 54 89 65 10"},
                {"Plaf", "+33 2 54 89 65 10"},
                {"Loup", "+33 2 54 89 65 10"},
                {"Abracadabra", "+33 2 54 89 65 10"}
        };

        /*
         * On doit donner à notre adaptateur une liste du type « List<Map<String, ?> » :
         *  - la clé doit forcément être une chaîne de caractères
         *  - en revanche, la valeur peut être n'importe quoi, un objet ou un entier par exemple,
         *  si c'est un objet, on affichera son contenu avec la méthode « toString() »
         *
         * Dans notre cas, la valeur sera une chaîne de caractères, puisque le nom et le numéro de téléphone
         * sont entreposés dans des chaînes de caractères
         */
        //List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();

        //HashMap<String, String> element;
        //Pour chaque personne dans notre répertoire…
        //for (int i = 0; i < repertoire.length; i++) {
            //… on crée un élément pour la liste…
            //element = new HashMap<String, String>();
          /*
           * … on déclare que la clé est « text1 » (j'ai choisi ce mot au hasard, sans sens technique particulier)
           * pour le nom de la personne (première dimension du tableau de valeurs)…
           */
            //element.put("text1", repertoire[i][0]);
          /*
           * … on déclare que la clé est « text2 »
           * pour le numéro de cette personne (seconde dimension du tableau de valeurs)
           */
            //element.put("text2", repertoire[i][1]);
            //liste.add(element);
        //}

        //ListAdapter adapter = new SimpleAdapter(this,
            //Valeurs à insérer
          //  liste,
            /*
             * Layout de chaque élément (là, il s'agit d'un layout par défaut
             * pour avoir deux textes l'un au-dessus de l'autre, c'est pourquoi on
             * n'affiche que le nom et le numéro d'une personne)
             */
            //android.R.layout.simple_list_item_2,
            /*
             * Les clés des informations à afficher pour chaque élément :
             *  - la valeur associée à la clé « text1 » sera la première information
             *  - la valeur associée à la clé « text2 » sera la seconde information
             */
            //new String[]{"text1", "text2"},
            /*
             * Enfin, les layouts à appliquer à chaque widget de notre élément
             * (ce sont des layouts fournis par défaut) :
             *  - la première information appliquera le layout « android.R.id.text1 »
             *  - la seconde information appliquera le layout « android.R.id.text2 »
             */
            //new int[]{android.R.id.text1, android.R.id.text2});

        //Pour finir, on donne à la ListView le SimpleAdapter
        //vue.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
