package com.example.svilupposw.ToEat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class LoginMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText mail = (EditText) findViewById(R.id.textMail);
        final EditText pwd = (EditText) findViewById(R.id.textPsw);
        Button buttonRegister = (Button) findViewById(R.id.buttonRegister);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase ref = MyApplication.getMyFirebaseRef();

                ref.authWithPassword(mail.getText().toString(), pwd.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {

                        MyApplication.setMyUid(authData.getUid());
                        MyApplication.setMail(authData.getProviderData().get("email").toString());

                        MyApplication.getUserRefByKey(authData.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                MyApplication.setName((String) dataSnapshot.child("displayName").getValue());
                                MyApplication.setAge(Integer.valueOf((String) dataSnapshot.child("displayAge").getValue()));

                                Intent intent = new Intent(getApplicationContext(), ListLocalActivity.class);
                                String userName = ((String)dataSnapshot.child("displayName").getValue());
                                String age = ((String)dataSnapshot.child("displayAge").getValue());
                                Log.i("eta", "" + Integer.parseInt(age));
                                Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.welcome)+" "+ userName, Toast.LENGTH_LONG).show();

                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {

                        Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.InvalidLogin),
                                Toast.LENGTH_LONG).show();
                    }

                });
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationMainActivity.class);
                startActivity(intent);
            }

        });
    }

}
