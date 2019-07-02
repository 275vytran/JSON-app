package com.vytran.javajson;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchData extends AsyncTask<Void, Void, Void> {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";

    @Override
    //Background thread
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.myjson.com/bins/g967f");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            //read
            String line = "";
            while(line != null) {
                line = bufferedReader.readLine();
                data = data + line; //the whole JSON file data
            }

            //Parse - format data
            try {
                JSONArray jsonArray = new JSONArray(data);
                for (int i=0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    if (jsonObject.get("name").equals(MainActivity.editText.getText().toString())) {
                        singleParsed = "Name: " + jsonObject.get("name") + "\n" +
                                "Password: " + jsonObject.get("password") + "\n" +
                                "Contact: " + jsonObject.get("contact") + "\n" +
                                "Country: " + jsonObject.get("country") + "\n";

                        dataParsed = dataParsed + singleParsed + "\n";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    //UI thread
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.textView.setText(this.dataParsed);
    }
}
