package com.example.svilupposw.listadellecosedafare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class AddEvento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evento);

        final EditText text = (EditText) findViewById(R.id.lista);
        final Button button=(Button) findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Firebase eventoRef = MyApplication.getMyFirebaseRef().child("evento");
                Firebase newEventoRef = eventoRef.push();
                Evento todo = new Evento(text.getText().toString());
                newEventoRef.setValue(todo);
                finish();
            }
        });
    }
}
