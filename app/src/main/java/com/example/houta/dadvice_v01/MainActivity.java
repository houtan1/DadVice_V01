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
    //private RecyclerView.Adapter mAdapter;
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

        //to test with
        String[] myDataset = {
                "string 1",
                "string 2",
                "string 3"
        };

        //test
        ArrayList<String> dadVice2 = new ArrayList<>();
        readFromFile("dadViceDB.txt", dadVice2);
        randomize_ArrayList(dadVice2);

        // specify an adapter (create it in a new class file)
        //mAdapter = new MyAdapter(dadVice2);
        //mRecyclerView.setAdapter(mAdapter);
        mAdapter = new CardAdapter(dadVice2);
        mRecyclerView.setAdapter(mAdapter);

        //RecyclerView and LinearLayoutManager
        //ref: https://www.binpress.com/tutorial/android-l-recyclerview-and-cardview-tutorial/156
        //maybe refactor as it's own class?
        /*
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        */
        //todo follow guidance of
        // http://icetea09.com/blog/2014/12/19/android-cardview-and-recyclerview-in-material-design/
        // and http://stackoverflow.com/questions/31377100/how-to-use-recyclerview-and-cardview
        // also http://www.101apps.co.za/index.php/articles/android-s-recyclerview-and-cardview-widgets.html
        // and finally https://www.binpress.com/tutorial/android-l-recyclerview-and-cardview-tutorial/156
        // android dev https://developer.android.com/samples/CardView/src/com.example.android.cardview/CardViewFragment.html
        // official https://developer.android.com/training/material/lists-cards.html

        // to implement a working RecyclerView first
        // then connect it to use the card_view.xml


        //create a new arrayList
        ArrayList<String> dadVice = new ArrayList<>();

        //given file name and arrayList, reads from file and populates arrayList
        readFromFile("dadViceDB.txt", dadVice);

        //randomize dadVice
        randomize_ArrayList(dadVice);

        //display dadVice
        //displayArrayList(dadVice);
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
    This is the old way. (pre-material design)
    The new ListView is called RecyclerView and it has performance improvements
    But we're interested in implementing our array as cards!
    So we'll be using CardView
    https://developer.android.com/reference/android/support/v7/widget/CardView.html
    https://developer.android.com/training/material/lists-cards.html
    */
    /*
    This method displays an arrayList
    //ref: https://developer.android.com/guide/topics/ui/declaring-layout.html
    //used right click, extract, method to create this method
    */
/*
    private void displayArrayList(ArrayList<String> inputArray) {
        //initialize ArrayAdapter
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, inputArray);

        //initialize ListView
        ListView myListView = (ListView)findViewById(R.id.listView);

        //connect myListView with myAdapter
        myListView.setAdapter(myAdapter);
    }
*/
}
