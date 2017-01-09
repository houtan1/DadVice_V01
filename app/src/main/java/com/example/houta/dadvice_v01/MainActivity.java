package com.example.houta.dadvice_v01;

//import android.app.ActionBar;
import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
//import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.firebase.analytics.FirebaseAnalytics;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    // A Native Express ad is placed in every nth position in the RecyclerView.
    public static final int ITEMS_PER_AD = 9;

    // The Native Express ad height.
    private static final int NATIVE_EXPRESS_AD_HEIGHT = 150;

    // The Native Express ad unit ID.
    private static final String AD_UNIT_ID = "ca-app-pub-9036948389286739/2010780002";

    //Number of cards to be loaded in recyclerview at a time
    private static final int ITEMS_PER_LOAD = ITEMS_PER_AD*3;

    // List of Native Express ads and MenuItems that populate the RecyclerView.
    private List<Object> mRecyclerViewItems;

    // Initializing Firebase Analytics for the MainActivity
    private FirebaseAnalytics mFirebaseAnalytics;

    // ref: https://developer.android.com/training/material/lists-cards.html
    private RecyclerView mRecyclerView;

    //private CardAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //Load ad index location for refreshView
    private int adLoadIndex = ITEMS_PER_AD;

    /**
     * Bringing back the menu inflater and the about menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about_menu:
                seeAboutMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Called when the user clicks about menu button */
    public void seeAboutMenu(){
        // log the about menu call event
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "About Button");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Button");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        // Use intents to start AboutActivity
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
/*
    public void buildFacebookShareButton(){
        ShareButton fbShareButton = (ShareButton) findViewById(R.id.share_btn);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(getString(R.string.facebook_content_url)))
                .setContentTitle("DadVice by BurritoCat")
                .setContentDescription(getString(R.string.facebook_content_description))
                .build();
        fbShareButton.setShareContent(content);
    }

    public void addFacebookShareButtons(){
        int i;
        for (i = 1; i <= mRecyclerViewItems.size(); i=i+1){
            buildFacebookShareButton();
        }
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize facebook sdk
       //// FacebookSdk.sdkInitialize(getApplicationContext());
       //// AppEventsLogger.activateApp(this);

        // Obtain the Firebase Analytics instance, onCreate
        // Required adding permissions for ACCESS_NETWORK_STATE and WAKE_LOCK in AndroidManifest.xml
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager for RecyclerView
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //create a new arrayList
        mRecyclerViewItems = new ArrayList<>();

        //given file name and arrayList, reads from file and populates arrayList
        readRandomFromFile("dadViceDB.txt", mRecyclerViewItems, ITEMS_PER_LOAD);

        //addFacebookShareButtons(); we will use this for fully functional share
        ////buildFacebookShareButton();

        //set up and load ads
        if(isNetworkAvailable())
        {
            addNativeExpressAds();
            setUpAndLoadNativeExpressAds();
        }

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.recyclerViewSwipeLayout);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //scrolling down produces positive dy offset

                boolean shouldRefresh = mRecyclerView.canScrollVertically(1);
                if (!shouldRefresh) {
                    swipeRefreshLayout.setRefreshing(true);//start refresh animation

                    //Refresh to load data here.
                    readRandomFromFile("dadViceDB.txt", mRecyclerViewItems, ITEMS_PER_LOAD);
                    //add ads to the next set of dadvices
                    if(isNetworkAvailable()){
                        addNativeExpressAds();
                        setUpAndLoadNativeExpressAds();
                    }
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                }
                swipeRefreshLayout.setRefreshing(false);//end refresh animation
            }
        });

////////////////////////////////////////////////////////////////////////////////////////////////////

        // Specify adapter that supports cardview and adview
        //This is inefficient but more readable code and since computation is cheap it's better to strive for readable
        if(isNetworkAvailable())
        {
            RecyclerView.Adapter adapter = new RecyclerViewAdapter(this, mRecyclerViewItems);
            mRecyclerView.setAdapter(adapter);
        }
        else
        {
            RecyclerView.Adapter adapternointernet = new RecyclerViewAdapterNoInternet(this, mRecyclerViewItems);
            mRecyclerView.setAdapter(adapternointernet);
        }

    }



    /**
     * Adds Native Express ads to the items list.
     */
    private void addNativeExpressAds() {

        // Loop through the items array and place a new Native Express ad in every ith position in
        // the items List.
        int i;
        //Log.e("TEST","i="+(mRecyclerViewItems.size()-ITEMS_PER_LOAD+1));
        for (i = (mRecyclerViewItems.size()-ITEMS_PER_LOAD+1); i <= mRecyclerViewItems.size(); i=i+1){;//i += ITEMS_PER_AD) {
            if(((i % ITEMS_PER_AD) == 0)&&(i>0)) {
                Log.e("ADDED_AD","Ad at index "+i);
                final NativeExpressAdView adView = new NativeExpressAdView(MainActivity.this);
                mRecyclerViewItems.add(i, adView);
            }

        }
    }

    /**
     * Sets up and loads the Native Express ads.
     */
    private void setUpAndLoadNativeExpressAds() {
        // Use a Runnable to ensure that the RecyclerView has been laid out before setting the
        // ad size for the Native Express ad. This allows us to set the Native Express ad's
        // width to match the full width of the RecyclerView.
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                final float density = MainActivity.this.getResources().getDisplayMetrics().density;
                // Set the ad size and ad unit ID for each Native Express ad in the items list.
                int i;
                for (i = adLoadIndex; i <= mRecyclerViewItems.size(); i += ITEMS_PER_AD) {
                //for (int i = ITEMS_PER_AD; i <= mRecyclerViewItems.size(); i += ITEMS_PER_AD) {
                    Log.e("SetAndLoad","i= "+i);
                    final NativeExpressAdView adView =
                            (NativeExpressAdView) mRecyclerViewItems.get(i);
                    AdSize adSize = new AdSize(
                            (int) (mRecyclerView.getWidth() / density),
                            NATIVE_EXPRESS_AD_HEIGHT);
                    adView.setAdSize(adSize);
                    adView.setAdUnitId(AD_UNIT_ID);
                }
                adLoadIndex = i;
                Log.e("ADDED_AD","Saved adLoadIndex as "+adLoadIndex);


                // Load the first Native Express ad in the items list.
                loadNativeExpressAd(ITEMS_PER_AD);
            }
        });
    }

    /**
     * Loads the Native Express ads in the items list.
     */
    private void loadNativeExpressAd(final int index) {

        if (index >= mRecyclerViewItems.size()) {
            return;
        }

        Object item = mRecyclerViewItems.get(index);
        if (!(item instanceof NativeExpressAdView)) {
            throw new ClassCastException("Expected item at index " + index + " to be a Native"
                    + " Express ad.");
        }

        final NativeExpressAdView adView = (NativeExpressAdView) item;

        // Set an AdListener on the NativeExpressAdView to wait for the previous Native Express ad
        // to finish loading before loading the next ad in the items list.
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                // The previous Native Express ad loaded successfully, call this method again to
                // load the next ad in the items list.
                loadNativeExpressAd(index + ITEMS_PER_AD);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // The previous Native Express ad failed to load. Call this method again to load
                // the next ad in the items list.
                Log.e("MainActivity", "The previous Native Express ad failed to load. Attempting to"
                        + " load the next Native Express ad in the items list.");
                loadNativeExpressAd(index + ITEMS_PER_AD);
            }
        });

        // Load the Native Express ad.
        adView.loadAd(new AdRequest.Builder().build());
    }

    public boolean isNetworkAvailable() {
        boolean return_value;
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return_value = activeNetworkInfo != null;
        return return_value;
    }

    /*
    This method reads strings from a file and writes them to an ArrayList
    ref http://stackoverflow.com/questions/24291721/reading-a-text-file-line-by-line-in-android
    */
    private List readRandomFromFile(String fileName, List outputArray,int num_items) {
        BufferedReader reader;
        List intermedArray = new ArrayList<>();
        try {
            final InputStream file = getAssets().open(fileName);
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            //put intermediate file read together
            intermedArray.add(line);
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    intermedArray.add(line);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        //now randomize the read files and only return num_items of them for build
        randomize_List(intermedArray);
        for (int j = 0; j < num_items; j = j + 1){
            outputArray.add(intermedArray.get(j));
        }
        return outputArray;
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

