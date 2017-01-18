package dk.bepeaked.bodybook.Fragments.Training;


import android.content.Context;
import android.content.SharedPreferences;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddExercise_frag extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, Runnable {


    String pasName;
    int pasID, planID;
    ArrayList<String> traeningsOevelser = new ArrayList<String>();
    SharedPreferences prefs;
    EditText editTextLocal;
    ArrayAdapter adapter;
    View view;
    WorkoutController wc = new WorkoutController();
    Bundle bundleArgs = new Bundle();
    FloatingActionButton fab;
    Singleton singleton;
    private SwipeMenuListView listView;


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
        getActivity().setTitle(getString(R.string.add_to) + pasName);

        traeningsOevelser = wc.getAllExerciseNamesToArray();

        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton_addExercise_done);
        fab.setOnClickListener(this);

        adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, traeningsOevelser);

//This is the imported project SwipeMenuList, that makes it possible to swipe left on an item in the list

        listView = (SwipeMenuListView) view.findViewById(R.id.ListView_id_search);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem editItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                editItem.setWidth(300);
                editItem.setTitleSize(18);
                editItem.setIcon(R.drawable.ic_edit_white_24dp);
                menu.addMenuItem(editItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                deleteItem.setWidth(300);
                deleteItem.setIcon(R.drawable.ic_delete_forever_white_24dp);
                menu.addMenuItem(deleteItem);

                // create "description" item
                SwipeMenuItem descriptionItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                descriptionItem.setWidth(300);
                descriptionItem.setIcon(R.drawable.ic_info_outline_white_24dp);
                menu.addMenuItem(descriptionItem);
            }
        };

// set creator

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                bundleArgs.putInt("exerciseID", wc.getExercise(traeningsOevelser.get(position)).getID());
                switch (index) {
                    case 0:
                        // open
                        DialogEditExercise_frag dialog = new DialogEditExercise_frag();
                        dialog.setArguments(bundleArgs);
                        dialog.show(getFragmentManager(), "DialogEditExercise_frag");

                        break;
                    case 1:
                        // delete
                        DialogDeleteExercisePermanently_frag dialog2 = new DialogDeleteExercisePermanently_frag();
                        dialog2.setArguments(bundleArgs);
                        dialog2.show(getFragmentManager(), "DialogDeleteExercisePermanently_frag");

                        break;
                    case 2:
                        // description
                        DialogDescription_frag dialog3 = new DialogDescription_frag();
                        dialog3.setArguments(bundleArgs);
                        dialog3.show(getFragmentManager(), "DialogDescription_frag");

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        setHasOptionsMenu(true);

        //This is the search function when you add an exercise to a pas
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
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
            DialogAddExercise_frag dialog = new DialogAddExercise_frag();
            dialog.show(getActivity().getFragmentManager(), "DialogAddExercise_frag");
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Bundle bundleArgs = new Bundle();
        bundleArgs.putInt("chosenExerciseID", wc.getExercise(traeningsOevelser.get(position)).getID());
        bundleArgs.putInt("TræningspasID", pasID);
        bundleArgs.putString("exerciseName", wc.getExercise(traeningsOevelser.get(position)).getName());
        DialogAddExerciseGoals_frag dialog = new DialogAddExerciseGoals_frag();
        dialog.setArguments(bundleArgs);
        dialog.show(getActivity().getFragmentManager(), "Empty_pas");
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
            Snackbar.make(getView(), exercise + getString(R.string.is_added_with) + sets + " x " + reps, Snackbar.LENGTH_LONG).show();
        }

        traeningsOevelser = wc.getAllExerciseNamesToArray();
        adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, traeningsOevelser);
        listView.setAdapter(adapter);
    }
}
