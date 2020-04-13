package com.example.drawerexample.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.drawerexample.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class add extends Fragment {

    private TextView rezultat;
    private EditText sabirak1;
    private EditText sabirak2;

    public add() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_add, container, false);
        rezultat = (TextView)v.findViewById(R.id.rezultat);
        sabirak1 = (EditText)v.findViewById(R.id.sabirak1);
        sabirak2 = (EditText)v.findViewById(R.id.sabirak2);

        sabirak1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                azurirajRezultat();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        sabirak2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                azurirajRezultat();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return v;
    }

    private void azurirajRezultat() {
        if (!sabirak1.getText().toString().equals("") && !sabirak2.getText().toString().equals("") ) {
            int sab1 = Integer.parseInt(sabirak1.getText().toString());
            int sab2 = Integer.parseInt(sabirak2.getText().toString());
            int zbir = sab1 + sab2;
            rezultat.setText(Integer.toString(zbir));
        }
    }
}
