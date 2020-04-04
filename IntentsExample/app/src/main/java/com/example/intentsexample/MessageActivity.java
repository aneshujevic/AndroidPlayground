package com.example.intentsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent inting = getIntent();

        this.setUpSerializableMessage(inting);
        this.setUpParcelableMessage(inting);

        this.delayedReturnToMainActivity(10000);
    }

    private void setUpSerializableMessage(Intent intent) {
        TravellingMessageSerializable tms = (TravellingMessageSerializable) intent.getSerializableExtra("serializable_message");
        if (tms != null) {
            ((TextView) (findViewById(R.id.fromSerializable))).setText(tms.getFrom());
            ((TextView) (findViewById(R.id.toSerializable))).setText(tms.getTo());
            ((TextView) (findViewById(R.id.dateSerializable))).setText(tms.getDate());
            ((TextView) (findViewById(R.id.textSerializable))).setText(tms.getText());
        }
    }

    private void setUpParcelableMessage(Intent intent) {
        TravellingMessageParcelable tmp = intent.getParcelableExtra("parcelable_message");
        if (tmp != null) {
            ((TextView) (findViewById(R.id.fromParcelable))).setText(tmp.getFrom());
            ((TextView) (findViewById(R.id.toParcelable))).setText(tmp.getTo());
            ((TextView) (findViewById(R.id.dateParcelable))).setText(tmp.getDate());
            ((TextView) (findViewById(R.id.textParcelable))).setText(tmp.getText());
        }
    }

    private void delayedReturnToMainActivity(final int millis) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(millis);
                    setResult(Activity.RESULT_CANCELED);
                    MessageActivity.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void returnToMain(View v) {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
