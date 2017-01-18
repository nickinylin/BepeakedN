package dk.bepeaked.bodybook.Activities;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.LoadDataExercise;
import dk.bepeaked.bodybook.Fragments.Diet.Diet_frag;
import dk.bepeaked.bodybook.Fragments.Settings.Settings_frag;
import dk.bepeaked.bodybook.Fragments.Training.Pas_frag;
import dk.bepeaked.bodybook.R;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;


public class Main_act extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    NavigationView navigationView;
    Toolbar toolbar;
    boolean EMULATOR = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
    SharedPreferences prefs;
    WorkoutController wc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
//        if (!EMULATOR) {
            Fabric.with(this, new Crashlytics());
//        }

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        wc = new WorkoutController();

        if (prefs.getBoolean("firstAppRun", true)) {
            Log.d("Nicki", "Første gang appen bliver kørt!");
            String newPlanName = "My plan";
            LoadDataExercise ld = new LoadDataExercise();
            ld.dataCreateAllNeededData(newPlanName);
            prefs.edit().putString("lastUsedPlanName", newPlanName).commit();
            prefs.edit().putInt("lastUsedPlan", 1).commit();
            prefs.edit().putBoolean("firstAppRun", false).commit();
            prefs.edit().putBoolean("measurement", false).commit();
        }


        setContentView(R.layout.activity_main);


        // SetDTO the fragment initially
        if (savedInstanceState==null) { // kun tilføje fragmenter ved en frisk start
        Pas_frag fragment = new Pas_frag();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //Clearing the backstack, so it closes the app and doesn't jump between the different primary topics.
        FragmentManager fm = this.getSupportFragmentManager();
//        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
//            fm.popBackStack();
//        }

        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_trainingplan) {
            Pas_frag fragment = new Pas_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("stack");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_diet) {
            Diet_frag fragment = new Diet_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("stack");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_settings) {
            Settings_frag fragment = new Settings_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("stack");
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
