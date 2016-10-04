package com.example.houta.dadvice_v01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //todo find a good place/create a repo text file for dadvices (should be under res)
    //todo create DadVice class - the class should read from a text file, line by line
    //todo initialize array list for DadVice here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the button
        //Button nextButton = (Button)findViewById(R.id.nextButton);
        //removed a comment I had personal issues with

        //initialize simple dadVice String array here for now
        ArrayList<String> dadVice = new ArrayList<>();
        dadVice.add("DadVice1 \n \n \n \n \n \n \n DadVice2 \n \n \n \n \n \n \n \n \n");
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

        //testing randomizer here
        ArrayList<String> randomized_test_list = randomize_ArrayList(dadVice);

        //now, display the dadVice one by one
        //ref: https://developer.android.com/guide/topics/ui/declaring-layout.html
        //seems like using a list-view with an adapter, limited to 1 item is the way to go
        //no, keep using the list, that way it's a more continuous UI

        //initialize ArrayAdapter
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, randomized_test_list);//dadVice);

        //initialize ListView
        ListView myListView = (ListView)findViewById(R.id.listView);

        //connect myListView with myAdapter
        myListView.setAdapter(myAdapter);
    }

    /*
    This function takes an input arraylist and then randomizes it
    https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
     */
    ArrayList<String> randomize_ArrayList(ArrayList<String> input_array)
    {
        //Input parameters to randomizer
        ArrayList<String> randomized_array = input_array;//used to hold output
        Random randomIntGen = new Random(); //uses java.util.Random to create random index
        int input_array_size = input_array.size();//holds length of input array
        int random;//holds index of random array, changes on each iteration of for loop

        //iterates through all of input_array, assigning them random indexes
        for(int counter = 0; counter < input_array_size; ++counter)
        {
            random = randomIntGen.nextInt(input_array_size);
            randomized_array.set(random, input_array.get(counter));
        }

        return randomized_array;
    }
}

