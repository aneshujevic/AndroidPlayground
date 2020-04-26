package com.example.pictures_json;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String jsonURL = "https://json-trial.000webhostapp.com/cats.json";
    private final int jsoncode = 1;
    private ListView listView;
    ArrayList<CatModel> catModelArrayList;
    private static ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv);
        fetchJSON();
    }

    @SuppressLint("StaticFieldLeak")
    private void fetchJSON() {
        showSimpleProgressDialog(this, "Loading...", "Fetching Json", false);
        new AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void[] params) {
                String response = "";
                HashMap<String, String> map = new HashMap<>();
                try {
                    HttpRequest req = new HttpRequest(jsonURL);
                    response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
                } catch (Exception e) {
                    response = e.getMessage();
                }
                return response;
            }

            protected void onPostExecute(String result) {
                Log.d("newwwss", result);
                onTaskCompleted(result, jsoncode);
            }
        }.execute();
    }

    public void onTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson", response.toString());
        if (serviceCode == jsoncode) {
            if (isSuccess(response)) {
                removeSimpleProgressDialog();
                catModelArrayList = getInfo(response);
                CatAdapter catAdapter = new CatAdapter(this, catModelArrayList);
                listView.setAdapter(catAdapter);

            } else {
                Toast.makeText(MainActivity.this, getErrorCode(response), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<CatModel> getInfo(String response) {
        ArrayList<CatModel> catModelArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {

                JSONArray dataArray = jsonObject.getJSONArray("list-of-cats");

                for (int i = 0; i < dataArray.length(); i++) {

                    CatModel catModel = new CatModel();
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    catModel.setName(dataobj.getString("name"));
                    catModel.setRace(dataobj.getString("race"));
                    catModel.setSound(dataobj.getString("sound"));
                    catModel.setImgURL(dataobj.getString("imgURL"));
                    catModelArrayList.add(catModel);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return catModelArrayList;
    }

    public boolean isSuccess(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.optString("status").equals("true");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getErrorCode(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString("status");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No data";
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (Exception ie) {
            ie.printStackTrace();

        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }
}