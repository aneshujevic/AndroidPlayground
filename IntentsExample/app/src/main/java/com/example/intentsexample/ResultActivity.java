package com.example.intentsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    Intent inting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        inting = getIntent();

        String resultString = " " + makeResult();

        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText(resultString);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK)
                finish();
        }
    }

    public void showHiddenMessages(View v) {
        Intent messagesIntent = new Intent(ResultActivity.this, MessageActivity.class);
        messagesIntent.putExtra("parcelable_message", inting.getParcelableExtra("parcelable_message"));
        messagesIntent.putExtra("serializable_message", inting.getSerializableExtra("serializable_message"));

        ResultActivity.this.startActivityForResult(messagesIntent, 0);
    }

    private float makeResult() {
        Intent gotIntent = getIntent();

        if (gotIntent != null) {
            float firstNumber = gotIntent.getFloatExtra("firstNumber", 0);
            float secondNumber = gotIntent.getFloatExtra("secondNumber", 0);
            return firstNumber + secondNumber;
        }

        throw new IllegalArgumentException("Intent is null");
    }

    public void delayedReturnToMainActivity(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    ResultActivity.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
