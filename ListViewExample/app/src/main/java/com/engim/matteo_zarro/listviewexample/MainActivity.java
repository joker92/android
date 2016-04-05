package com.engim.matteo_zarro.listviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyAdapter myAdapter;
    private ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        for(int i = 0; i< 100; i++){
            list.add("Element " + i);
        }
        myAdapter = new MyAdapter(getApplicationContext(), list);
        ListView listView = (ListView) findViewById(R.id.my_list);
        if(listView != null) {
            listView.setAdapter(myAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button add_button = (Button) findViewById(R.id.add_button);
        if(add_button != null)
            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myAdapter.addItem("ciao");
                    //quando ruoto lo schermo si perdono gli oggetti aggiunti!andrebbe gestito opportunamente
                }
            });

    }
}
