package dk.bepeaked.bodybook.Fragments.Training;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddExercise_frag extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, Runnable {


    String pasName, planName;
    int pasID, exerciseID, planID;
    RealmList<ExerciseDTO> realmListExerciseDTO = new RealmList<ExerciseDTO>();
    ArrayList<String> traeningsOevelser = new ArrayList<String>();
    SharedPreferences prefs;
    EditText editTextLocal;
    ArrayAdapter adapter;
    View view;
    WorkoutController wc = new WorkoutController();
    private SwipeMenuListView listView;
    Bundle bundleArgs = new Bundle();
    FloatingActionButton fab;
    Singleton singleton;


    public AddExercise_frag() {
        // Required empty public constructor
    }

    //@Override
    public void onSearch() //Vi skal få det brugeren har indtastet.
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.listviewsearch, container, false);

        singleton = Singleton.singleton;

        singleton.listen(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        pasID = getArguments().getInt("TræningspasID", 99999);
        pasName = wc.getSpecificPas(pasID).getName();
        getActivity().setTitle("Tilføj til " + pasName);

        traeningsOevelser = wc.getAllExerciseNamesToArray();
        realmListExerciseDTO = wc.getAllExercises();

        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton_addExercise_done);
        fab.setOnClickListener(this);


        adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, traeningsOevelser);

        listView = (SwipeMenuListView) view.findViewById(R.id.ListView_id_search);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(300);
                // set item title
                openItem.setTitle("Rediger");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

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
//                        bundleArgs.putString("planName", planName);
//                        bundleArgs.putString("pasName", arrayListPlanNames.get(position));

                        DialogEditPas_frag dialog = new DialogEditPas_frag();

                        dialog.setArguments(bundleArgs);
                        dialog.show(getFragmentManager(), "Empty_pas");

                        break;
                    case 1:
                        // delete
//                        bundleArgs.putString("planName", planName);
//                        bundleArgs.putString("pasName", arrayListPlanNames.get(position));
//TODO her skal der en toast som siger man kan må slette egne øvelser, og nyt dialogFragment skal oprettes.
                        DialogDeletePas_frag dialog2 = new DialogDeletePas_frag();
                        dialog2.setArguments(bundleArgs);
                        dialog2.show(getFragmentManager(), "Empty_pas");

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


        setHasOptionsMenu(true);

        //Lav noget med vores editText/Search input
        editTextLocal = (EditText) view.findViewById(R.id.editText);
        editTextLocal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                traeningsOevelser.clear();

                traeningsOevelser = wc.getAllExerciseNamesToArray();

                searchItem();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    public void searchItem() {

        for (int j = 0; j < traeningsOevelser.size(); j++) {

            String exerciseName = traeningsOevelser.get(j).toLowerCase();
            System.out.println(exerciseName);

            if (!exerciseName.contains(editTextLocal.getText())) {
                traeningsOevelser.remove(j);
                j = j - 1;

            }
        }
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, traeningsOevelser);

        listView = (SwipeMenuListView) view.findViewById(R.id.ListView_id_search);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        singleton.unRegistrer(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.chosenexercisemenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.pasMenu_add_exercise) {
            Snackbar.make(getView(), "Der skal tilføjes en ny øvelse!", Snackbar.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Bundle bundleArgs = new Bundle();
        bundleArgs.putInt("chosenExerciseID", realmListExerciseDTO.get(position).getID());
        bundleArgs.putInt("TræningspasID", pasID);
        bundleArgs.putString("exerciseName", realmListExerciseDTO.get(position).getName());
        DialogAddExerciseGoals_frag dialog = new DialogAddExerciseGoals_frag();
        dialog.setArguments(bundleArgs);
        dialog.show(getActivity().getFragmentManager(), "Empty_pas");

//
//        Exercise_frag fragment = new Exercise_frag();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragment.setArguments(bundleArgs);
//        fragmentTransaction.commit();

    }


    @Override
    public void onClick(View v) {
        if (v == fab) {
            Bundle bundleArgs = new Bundle();
            bundleArgs.putInt("TræningspasID", pasID);

            Exercise_frag fragment = new Exercise_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
            fragment.setArguments(bundleArgs);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void run() {
        if (prefs.getBoolean("exerciseAdded", false)) {
            prefs.edit().putBoolean("exerciseAdded", false).commit();
            String exercise = prefs.getString("addGoalsName", "Empty");
            int reps = prefs.getInt("addGoalsReps", 9999);
            int sets = prefs.getInt("addGoalsSets", 9999);
            Snackbar.make(getView(), exercise + " er tilføjet med " + sets + " x " + reps, Snackbar.LENGTH_LONG).show();
        }
    }
}
