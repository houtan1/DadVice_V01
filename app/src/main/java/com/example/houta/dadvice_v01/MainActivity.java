package com.example.houta.dadvice_v01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create a new arrayList
        ArrayList<String> dadVice = new ArrayList<>();

        //given file name and arrayList, reads from file and populates arrayList
        readFromFile("dadViceDB.txt", dadVice);

        //randomize dadVice
        randomize_ArrayList(dadVice);

        //display dadVice
        displayArrayList(dadVice);
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

    /*
    This method displays an arrayList
    //ref: https://developer.android.com/guide/topics/ui/declaring-layout.html
    //used right click, extract, method to create this method
    */
    private void displayArrayList(ArrayList<String> inputArray) {
        //initialize ArrayAdapter
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, inputArray);

        //initialize ListView
        ListView myListView = (ListView)findViewById(R.id.listView);

        //connect myListView with myAdapter
        myListView.setAdapter(myAdapter);
    }
}
