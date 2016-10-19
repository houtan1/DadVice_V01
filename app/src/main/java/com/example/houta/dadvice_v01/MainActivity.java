package com.example.houta.dadvice_v01;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MainActivity extends Activity {
    // ref: https://developer.android.com/training/material/lists-cards.html
    private RecyclerView mRecyclerView;
    private CardAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager for RecyclerView
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //create a new arrayList
        ArrayList<String> dadVice = new ArrayList<>();

        //given file name and arrayList, reads from file and populates arrayList
        readFromFile("dadViceDB.txt", dadVice);

        //randomize dadVice
        randomize_ArrayList(dadVice);

        //specify an adapter
        mAdapter = new CardAdapter(dadVice);
        //display cards via RecyclerView and its CardAdapter
        mRecyclerView.setAdapter(mAdapter);
    }

    /*
    This method reads strings from a file and writes them to an ArrayList
    ref http://stackoverflow.com/questions/24291721/reading-a-text-file-line-by-line-in-android
    */
    private ArrayList<String> readFromFile(String fileName, ArrayList<String> outputArray) {
        BufferedReader reader;
        try {
            final InputStream file = getAssets().open(fileName);
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            outputArray.add(line);
            while (line != null){
                line = reader.readLine();
                if (line != null) {
                    outputArray.add(line);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return outputArray;
    }

    /*
    This method takes an input arrayList and then randomizes it
    */
    private ArrayList<String> randomize_ArrayList(ArrayList<String> input_array) {
        long seed = System.nanoTime();
        Collections.shuffle(input_array, new Random(seed));
        return input_array;
    }
}
