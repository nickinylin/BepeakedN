package dk.bepeaked.bodybook.Fragments.Training;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Exercise_frag extends Fragment implements AdapterView.OnItemClickListener {


    String namePas, namePlan;
    RealmList<ExerciseDTO> realmExercise2 = new RealmList<ExerciseDTO>();
    ArrayList<String> arrayListExerciseNames = new ArrayList<String>();
    Bundle bundleArgs;
    ArrayAdapter adapter;
    private SwipeMenuListView listView;
    View view;
    WorkoutController wc = new WorkoutController();
    SharedPreferences prefs;



    public Exercise_frag() {
        // Required empty public constructor
    }

    //@Override
    public void onSearch()//Vi skal få det brugeren har indtastet.
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.listview, container, false);
        view = inflater.inflate(R.layout.listview, container, false);

        namePas = getArguments().getString("TræningspasNavn", "Empty");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        namePlan = prefs.getString("lastUsedPlan", "empty");


        getActivity().setTitle(namePas);

        arrayListExerciseNames = wc.getExercisesFromPasToArray(namePlan, namePas);

        adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, arrayListExerciseNames);

        listView = (SwipeMenuListView) view.findViewById(R.id.ListView_id);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(300);
                // set a icon
//                deleteItem.setIcon(R.drawable.);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        bundleArgs.putString("planName", namePlan);
                        bundleArgs.putString("pasName", arrayListExerciseNames.get(position));

                        DialogEditPas_frag dialog = new DialogEditPas_frag();
                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                adapterReload();
                            }
                        });
                        dialog.setArguments(bundleArgs);
                        dialog.show(getFragmentManager(), "Empty_pas");

                        break;
                    case 1:
                        // delete
                        bundleArgs.putString("planName", namePlan);
                        bundleArgs.putString("pasName", arrayListExerciseNames.get(position));

                        DialogDeletePas_frag dialog2 = new DialogDeletePas_frag();
                        dialog2.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                adapterReload();
                            }
                        });
                        dialog2.setArguments(bundleArgs);
                        dialog2.show(getFragmentManager(), "Empty_pas");

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


        setHasOptionsMenu(true);


        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.pasmenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.pasMenu_add_exercise) {

            Bundle bundleArgs = new Bundle();
            bundleArgs.putString("TræningspasNavn", namePas);

            AddExercise_frag fragment = new AddExercise_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("AddExercise_frag");
            fragment.setArguments(bundleArgs);
            fragmentTransaction.commit();

        } else if (item.getItemId() == R.id.pasMenu_edit) {
            // TODO Hvad der skal ske for at ændre bundleArgs en træningsplan
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getActivity(), position ,Toast.LENGTH_LONG).show();


        Bundle bundleArgs = new Bundle();
        bundleArgs.putString("chosenExerciseName", arrayListExerciseNames.get(position));


        TabLayoutExercise_frag fragment = new TabLayoutExercise_frag();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("traeningsOevelser");
        fragment.setArguments(bundleArgs);
        fragmentTransaction.commit();

    }

    private void adapterReload() {
        arrayListExerciseNames = wc.getExercisesFromPasToArray(namePlan, namePas);
        // TODO skriv i rapporten at vi prøvede at bruge "adapter.notifyDataSetChanged(); men at det ikke virkede, derfor opretter vi en ny adapter, som er lidt mindre arbejde, end at loade hele fragmentet igen..
        adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, arrayListExerciseNames);
        listView.setAdapter(adapter);
    }
}
