package com.example.sih;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.sih.init.introduction;

import java.util.ArrayList;
import java.util.List;

import me.riddhimanadib.library.BottomBarHolderActivity;
import me.riddhimanadib.library.NavigationPage;

public class MainActivity extends BottomBarHolderActivity implements mapsFragment.OnFragmentInteractionListener, searchFragment.OnFragmentInteractionListener, disasterInfoFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity

            startActivity(new Intent(MainActivity.this, introduction.class));
            Toast.makeText(MainActivity.this, "First Run", Toast.LENGTH_LONG)
                    .show();
        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();
        NavigationPage page1 = new NavigationPage("Home", ContextCompat.getDrawable(this, R.drawable.ic_home_black_24dp), disasterInfoFragment.newInstance());
        NavigationPage page2 = new NavigationPage("Search", ContextCompat.getDrawable(this, R.drawable.ic_search_black_24dp), searchFragment.newInstance());
        NavigationPage page3 = new NavigationPage("Shelter ", ContextCompat.getDrawable(this, R.drawable.ic_map_black_24dp), mapsFragment.newInstance());
        NavigationPage page4 = new NavigationPage("Profile", ContextCompat.getDrawable(this, R.drawable.ic_account_circle_black_24dp), searchFragment.newInstance());
       // NavigationPage page5 = new NavigationPage("Test", ContextCompat.getDrawable(this, R.drawable.ic_search_black_24dp), searchFragment.newInstance());

        // add them in a list
        List<NavigationPage> navigationPages = new ArrayList<>();
        navigationPages.add(page1);
        navigationPages.add(page2);
        navigationPages.add(page3);
        navigationPages.add(page4);

        // pass them to super method
        super.setupBottomBarHolderActivity(navigationPages);

    }
}
