package com.example.intentsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    TravellingMessageParcelable TMP;
    TravellingMessageSerializable TMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TMP = new TravellingMessageParcelable();

        TMP.setFrom("FROM: main activity");
        TMP.setTo("TO: all the others activities");
        TMP.setDate("DATE: it is always now");
        TMP.setText("TEXT: Hello, world! /** Parcelable message **/");

        TMS = new TravellingMessageSerializable();

        TMS.setFrom("FROM:MAIN ACTIVITY");
        TMS.setTo("TO: OTHER ACTIVITIES");
        TMS.setDate("DATE: FAVORITE DATE");
        TMS.setText("TEXT: Hello, world! /** Serializable message **/");
    }

    public void showResultNewActivity(View v) {
        Intent newIntent = new Intent(MainActivity.this, ResultActivity.class);

        float firstNumber = Float.parseFloat(((EditText)findViewById(R.id.firstEditText)).getText().toString());
        float secondNumber = Float.parseFloat(((EditText)findViewById(R.id.secondEditText)).getText().toString());

        newIntent.putExtra("firstNumber", firstNumber);
        newIntent.putExtra("secondNumber", secondNumber);

        newIntent.putExtra("parcelable_message", TMP);
        newIntent.putExtra("serializable_message", TMS);

        MainActivity.this.startActivityForResult(newIntent, 0);
    }
}
