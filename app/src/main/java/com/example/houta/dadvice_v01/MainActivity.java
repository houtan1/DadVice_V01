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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//// TODO: 19/10/16 ADD import of adlistener, adrequest, adsize, nativeexpressAdView 

public class MainActivity extends Activity {
    // A Native Express ad is placed in every nth position in the RecyclerView.
    public static final int ITEMS_PER_AD = 8;

    // The Native Express ad height.
    private static final int NATIVE_EXPRESS_AD_HEIGHT = 150;

    // The Native Express ad unit ID.
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1072772517";

    // List of Native Express ads and MenuItems that populate the RecyclerView.
    private List<Object> mRecyclerViewItems;

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
        ////////////////////////////////////////////////////////////////////////////////
        // Create a list containing menu items and Native Express ads.
        mRecyclerViewItems = new ArrayList<>();
    //    addMenuItems();
 //       addNativeExpressAds();
  //      setUpAndLoadNativeExpressAds();

        // Specify an adapter.
//        RecyclerView.Adapter adapter = new RecyclerViewAdapter(this, mRecyclerViewItems);
      //  mRecyclerView.setAdapter(adapter);
        /////////////////////////////////////////////////////////////////////////////

        //given file name and arrayList, reads from file and populates arrayList
        StringreadFromFile("dadViceDB.txt", dadVice);//BACKUP NO ADS
        readFromFile("dadViceDB.txt", mRecyclerViewItems);//NEW FUNCTION
        //randomize dadVice
        dadVice = Stringrandomize_ArrayList(dadVice);
        //randomize dadVice
        mRecyclerViewItems = randomize_List(mRecyclerViewItems);

        //specify an adapter
        mAdapter = new CardAdapter(dadVice);
        //display cards via RecyclerView and its CardAdapter
        mRecyclerView.setAdapter(mAdapter);
    }

    /*
    This method reads strings from a file and writes them to an ArrayList
    ref http://stackoverflow.com/questions/24291721/reading-a-text-file-line-by-line-in-android
    */
    private List readFromFile(String fileName, List outputArray) {
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
    This method reads strings from a file and writes them to an ArrayList
    ref http://stackoverflow.com/questions/24291721/reading-a-text-file-line-by-line-in-android
    */
    private ArrayList<String> StringreadFromFile(String fileName, ArrayList<String> outputArray) {
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
    private ArrayList<String> Stringrandomize_ArrayList(ArrayList<String> input_array) {
        long seed = System.nanoTime();
        Collections.shuffle(input_array, new Random(seed));
        return input_array;
    }

    /*
This method takes an input arrayList and then randomizes it
*/
    private List randomize_List(List input_array) {
        long seed = System.nanoTime();
        Collections.shuffle(input_array, new Random(seed));
        return input_array;
    }
}
