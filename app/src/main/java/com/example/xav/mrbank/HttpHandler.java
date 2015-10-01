package com.example.xav.mrbank;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by xavier on 01/10/15.
 */
public class HttpHandler extends Handler {

    /*
    ListView contract;
    JSONArray json;

    public void HttpHandler(ListView contract, JSONArray json)
    {
        this.contract = contract;
        this.json = json;
    }

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

        ContractAdapter adapter = new ContractAdapter(MainActivity, contracts);
        contract.setAdapter(adapter);
        contract.setOnItemClickListener(adapter);
    }
    */

}
