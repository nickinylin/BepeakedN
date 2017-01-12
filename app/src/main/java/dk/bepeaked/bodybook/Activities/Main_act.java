package dk.bepeaked.bodybook.Activities;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DAO.DietDAO;
import dk.bepeaked.bodybook.Backend.DTO.LoadDataExercise;
import dk.bepeaked.bodybook.Fragments.Diet.Diet_frag;
import dk.bepeaked.bodybook.Fragments.Profile_frag;
import dk.bepeaked.bodybook.Fragments.Settings.Settings_frag;
import dk.bepeaked.bodybook.Fragments.Training.Pas_frag;
import dk.bepeaked.bodybook.R;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmList;


public class Main_act extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    NavigationView navigationView = null;
    Toolbar toolbar = null;
    boolean EMULATOR = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
    SharedPreferences prefs;
    public DietDAO diDAO = new DietDAO();
    WorkoutController wc = new WorkoutController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.getDefaultInstance();
//        if (!EMULATOR) {
            Fabric.with(this, new Crashlytics());
//        }

        prefs = PreferenceManager.getDefaultSharedPreferences(this);


        if (prefs.getBoolean("firstAppRun", true)) {
            Log.d("Nicki", "Første gang appen bliver kørt!");
            String newPlanName = "My plan";
            LoadDataExercise ld = new LoadDataExercise();
            ld.dataCreateAllNeededData(newPlanName);
            prefs.edit().putString("lastUsedPlan", newPlanName).commit();
            prefs.edit().putInt("lastUsedPlan", 1);
            prefs.edit().putBoolean("firstAppRun", false).commit();
        }


        setContentView(R.layout.activity_main);

        //+ File.separator + "/userdata/plans"

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_action_settings, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Profile_frag fragment = new Profile_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("stack");
//            fragmentTransaction.addToBackStack("hej");
            fragmentTransaction.commit();


        } else if (id == R.id.nav_trainingplan) {

            Pas_frag fragment = new Pas_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("stack");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_diet) {
            Bundle bundle = new Bundle();

            bundle.putSerializable("DietDAO", diDAO);

            Diet_frag fragment = new Diet_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("stack");
            fragment.setArguments(bundle);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_settings) {
            Bundle bundle = new Bundle();

            bundle.putSerializable("DietDAO", diDAO);

            Settings_frag fragment = new Settings_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("stack");
            fragment.setArguments(bundle);
            fragmentTransaction.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
