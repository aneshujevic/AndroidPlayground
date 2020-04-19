package com.example.httphandlerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new GetContacts().execute();
    }

    private final class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "JSON data downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();

            String url = "https://json-trial.000webhostapp.com/file.json";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray list_of_ppl = jsonObj.getJSONArray("list-of-people");

                    for (int i = 0; i < list_of_ppl.length(); i++) {
                        JSONObject c = list_of_ppl.getJSONObject(i);
                        String name = c.getString("name");
                        String surname = c.getString("surname");

                        JSONObject sskvp = c.getJSONObject("super-secret-key");
                        String id = sskvp.getString("id");
                        String phrase = sskvp.getString("phrase");

                        HashMap<String, String> contact = new HashMap<>();

                        contact.put("id", id);
                        contact.put("surname", surname);
                        contact.put("name", name);
                        contact.put("phrase", phrase);

                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "JSON Parsing error " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "JSON PArsing error " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Couldn't get json from server. Check LogCat for possible errors!", Toast.LENGTH_LONG).show();
                    }
                });

            }
            Log.e(TAG, contactList.toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, contactList, R.layout.list_item,
                    new String[]{"name", "surname", "phrase",}, new int[]{R.id.name, R.id.surname, R.id.phrase});
            lv.setAdapter(adapter);
        }
    }
}
