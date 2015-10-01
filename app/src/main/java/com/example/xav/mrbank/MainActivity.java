package com.example.xav.mrbank;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.lang.Thread;

public class MainActivity extends AppCompatActivity {

    private final int MSG_CONFIRM_RESULT = 0;

    ListView contract;
    TextView tvConnected;
    EditText etResponse;
    JSONArray json;

    // Gère les communications avec le thread de téléchargement
    final private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // L'avancement se situe dans msg.arg1
            Log.d("Handler", "Maj UI ?");
            Log.d("Handler", "msg = " + msg.arg1);

            ArrayList<ListItem> contracts = new ArrayList<>();
            try {
                Log.d("Handler", "Length = " + json.length());
                for (int i = 0; i < json.length(); i++) {
                    //Log.d("Handler", json.getJSONArray(i).toString());
                    Log.d("Handler", json.getJSONObject(i).getString("id"));
                    contracts.add(new ListItem(json.getJSONObject(i)));
                }
            }
            catch (Exception e) {
                Log.d("Handler", "Exception :(");
            }

            ContractAdapter adapter = new ContractAdapter(MainActivity.this, contracts);
            contract.setAdapter(adapter);
            contract.setOnItemClickListener(adapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvConnected = (TextView) findViewById(R.id.tvConnected);
        etResponse = (EditText) findViewById(R.id.etResponse);

        contract = (ListView) findViewById(R.id.list_contract);

        // check if you are connected or not
        if (isConnected()) {
            tvConnected.setBackgroundColor(0xFF00CC00);
            tvConnected.setText("You are connected");
        }
        else {
            tvConnected.setText("You are NOT connected");
        }
        //new HttpAsyncTask().execute("http://192.168.41.115/test-php/test-json.php");

        ////////////////////////////// THREAD //////////////////////////////////////////////////////
        new Thread(new Runnable() {
            public void run() {
                //try {
                    String result = GET("http://192.168.41.115/test-php/test-json.php");
                    try {
                        json = new JSONArray(result);
                        Log.d("RESULT", json.toString());
                    }
                    catch (Exception e) {
                        Log.d("StringToJSON", "ERREUR PARSING");
                        json = new JSONArray();
                    }

                    // Le fichier a été téléchargé
                    if (json.length() > 0) {
                        Message msg = mHandler.obtainMessage(MSG_CONFIRM_RESULT, 1, 0);
                        mHandler.sendMessage(msg);

                        /*
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Download OK !", Toast.LENGTH_SHORT).show();
                            }
                        });
                        */
                    }
                //}
                //catch (Exception e) {
                    // Si le thread est interrompu, on sort de la boucle de cette manière
                    //e.printStackTrace();
                //}
            }
        }).start();
        ////////////////////////////// THREAD //////////////////////////////////////////////////////
    }

    public static String GET(String url) {
        InputStream inputStream = null;
        String result = "";
        try {
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        }
        catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    // convert inputstream to String
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line;
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    // check network connection
    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            try {
                json = new JSONArray(result);
                //etResponse.setText(json.toString());
                Log.d("RESULT", json.toString());
            }
            catch (Exception e) {
                etResponse.setText("Erreur json !!!");
            }
        }
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
