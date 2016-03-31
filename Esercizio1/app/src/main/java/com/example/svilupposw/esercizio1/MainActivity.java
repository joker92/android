package com.example.svilupposw.esercizio1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //richiamare i vari elementi
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText email=(EditText)findViewById(R.id.email);
        final EditText password=(EditText)findViewById(R.id.password);
        Button button=(Button)findViewById(R.id.button);

        //controllo dellla mail

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email.getText().toString().length() > 0 && password.getText().toString().length()>0){
                    //qua redirect sulla pagina da mostrare all utente autenticato
                    Intent intent=new Intent(getApplicationContext(),Benvenuto.class);
                    intent.putExtra("email",email.getText().toString());

                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Autenticazione errata",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
