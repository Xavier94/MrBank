package com.example.xav.mrbank;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView contract;
    TextView tvConnected;
    EditText etResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvConnected = (TextView) findViewById(R.id.tvConnected);
        etResponse = (EditText) findViewById(R.id.etResponse);

        // check if you are connected or not
        if (isConnected()) {
            tvConnected.setBackgroundColor(0xFF00CC00);
            tvConnected.setText("You are conncted");
        }
        else {
            tvConnected.setText("You are NOT conncted");
        }
        new HttpAsyncTask().execute("http://192.168.41.115/test-php/test-json.php");



        contract = (ListView) findViewById(R.id.list_contract);
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
        for (int i = 0; i < data.length; i++) {
            contracts.add(new ListItem(data[i]));
        }
        ContractAdapter adapter = new ContractAdapter(this, contracts);
        contract.setAdapter(adapter);
        contract.setOnItemClickListener(adapter);
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
        String line = "";
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
                JSONObject json = new JSONObject(result);
                etResponse.setText(json.toString());
            }
            catch (Exception e) {
                etResponse.setText("Vide");
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
