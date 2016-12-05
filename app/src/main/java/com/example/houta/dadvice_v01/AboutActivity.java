package com.example.houta.dadvice_v01;

import android.app.Activity;
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
//import android.view.ViewGroup;
//import android.widget.TextView;

public class AboutActivity extends Activity {

    /**
     * Inflates the back menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.back_menu:
                seeBackMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Called when the user clicks back menu button */
    public void seeBackMenu(){
        // Indicated that we're done with this activity and can return to main activity
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

}
