package com.example.houta.dadvice_v01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //created a DB text file for dadVices, implemented assets folder
    //todo create DadVice class - the class should read from a text file, line by line
    //did not create it as a class, perhaps I should
    //initialized array list for DadVice

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //try using BufferedReader here
        //ref http://stackoverflow.com/questions/24291721/reading-a-text-file-line-by-line-in-android
        //works!
        //move string array up here and push these lines to it
        ArrayList<String> dadVice = new ArrayList<>();

        //todo move BufferReader as a function, outside of onCreate
        readFromFile(dadVice);

        BufferedReader reader;
        try {
            final InputStream file = getAssets().open("dadViceDB.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            /*
            String line = reader.readLine();
            while (line != null) {
                Log.d("reading: ", line);
                line = reader.readLine();
            }
            */
            String line = reader.readLine();
            dadVice.add(line);
            while (line != null){
                line = reader.readLine();
                if (line != null) {
                    dadVice.add(line);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        //initialize the button
        //Button nextButton = (Button)findViewById(R.id.nextButton);
        //removed a comment I had personal issues with

        //initialize simple dadVice String array here for now
        /*
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


        dadVice.add("DadVice3 \n \n \n \n \n \n \n DadVice4 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice5 \n \n \n \n \n \n \n DadVice6 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice7 \n \n \n \n \n \n \n DadVice8 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice9 \n \n \n \n \n \n \n DadVice10 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice11 \n \n \n \n \n \n \n DadVice12 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice13 \n \n \n \n \n \n \n DadVice14 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice15 \n \n \n \n \n \n \n DadVice16 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice17 \n \n \n \n \n \n \n DadVice18 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice19 \n \n \n \n \n \n \n DadVice20 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice21 \n \n \n \n \n \n \n DadVice22 \n \n \n \n \n \n \n \n \n");
        dadVice.add("DadVice23 \n \n \n \n \n \n \n DadVice24 \n \n \n \n \n \n \n \n \n");

        */

        //Send dadVice through randomize_ArrayList function
        ArrayList<String> randomizedDadVice = randomize_ArrayList(dadVice);


        //now, display the dadVice one by one
        //ref: https://developer.android.com/guide/topics/ui/declaring-layout.html
        //seems like using a list-view with an adapter, limited to 1 item is the way to go
        //no, keep using the list, that way it's a more continuous UI

        //initialize ArrayAdapter
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, randomizedDadVice);

        //initialize ListView
        ListView myListView = (ListView)findViewById(R.id.listView);

        //connect myListView with myAdapter
        myListView.setAdapter(myAdapter);
    }
    /*
    This function reads strings from a file and writes them to  
     */

    /*
    This function takes an input arrayList and then randomizes it
    */
    ArrayList<String> randomize_ArrayList(ArrayList<String> input_array)
    {
        long seed = System.nanoTime();
        Collections.shuffle(input_array, new Random(seed));
        return input_array;
    }
}
