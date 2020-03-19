package com.example.ex11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    TextView firstTw;
    Button firstBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstTw = (TextView)findViewById(R.id.thirdTextView);
        firstTw.setText("Ne podesavajmo tekstove ovako, losa je to praksa");

        firstBtn = (Button)findViewById(R.id.firstButton);
        firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeText(getApplicationContext(), getString(R.string.message), Toast.LENGTH_LONG).show();
            }
        });

    }
}
