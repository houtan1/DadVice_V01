package com.example.houta.dadvice_v01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //todo create DadVice class - the class should read from a text file, line by line
    //todo initialize array list for DadVice here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the button
        //Button nextButton = (Button)findViewById(R.id.nextButton);

        //initialize simple dadVice String array here for now
        ArrayList<String> dadVice = new ArrayList<>();
        dadVice.add("DadVice1 \n \n \n \n \n \n \n DadVice2 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice2 \n \n \n \n \n \n \n DadVice2 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice3 \n \n \n \n \n \n \n DadVice3 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice1 \n \n \n \n \n \n \n DadVice1 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice2 \n \n \n \n \n \n \n DadVice2 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice3 \n \n \n \n \n \n \n DadVice3 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice1 \n \n \n \n \n \n \n DadVice1 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice2 \n \n \n \n \n \n \n DadVice2 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice3 \n \n \n \n \n \n \n DadVice3 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice1 \n \n \n \n \n \n \n DadVice1 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice2 \n \n \n \n \n \n \n DadVice2 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice3 \n \n \n \n \n \n \n DadVice3 \n \n \n \n \n \n \n \n \n");

        //now, display the dadVice one by one
        //ref: https://developer.android.com/guide/topics/ui/declaring-layout.html
        //seems like using a list-view with an adapter, limited to 1 item is the way to go
        //todo implement the ArrayAdapter as a SimpleCursorAdapter and eliminate the button

        //initialize ArrayAdapter
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dadVice);

        //initialize ListView
        ListView myListView = (ListView)findViewById(R.id.listView);
        //todo implement new v17 requirements and make activity_main.xml the default

        //connect myListView with myAdapter
        myListView.setAdapter(myAdapter);
    }
}
