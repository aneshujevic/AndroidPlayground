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

/**
 * A simple {@link Fragment} subclass.
 */
public class subtract extends Fragment {

    private TextView rezultat;
    private EditText umanjenik;
    private EditText umanjilac;

    public subtract() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_subtract, container, false);
        rezultat = (TextView)v.findViewById(R.id.rezultat);
        umanjenik = (EditText)v.findViewById(R.id.umanjenik);
        umanjilac = (EditText)v.findViewById(R.id.umanjilac);

        umanjenik.addTextChangedListener(new TextWatcher() {
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

        umanjilac.addTextChangedListener(new TextWatcher() {
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
        if (!umanjenik.getText().toString().equals("") && !umanjilac.getText().toString().equals("") ) {
            int um1 = Integer.parseInt(umanjenik.getText().toString());
            int um2 = Integer.parseInt(umanjilac.getText().toString());
            int raz = um1 - um2;
            rezultat.setText(Integer.toString(raz));
        }
    }
}
