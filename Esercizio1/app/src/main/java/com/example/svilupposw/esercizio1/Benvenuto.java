package com.example.svilupposw.esercizio1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Benvenuto extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benvenuto);
        TextView textView = (TextView) findViewById(R.id.VisualizzaEmail);
        Intent intent = getIntent();
        String s = intent.getStringExtra("email");
        if (s != null)
            textView.setText("ciao la tua email e " + s);

    }

}
