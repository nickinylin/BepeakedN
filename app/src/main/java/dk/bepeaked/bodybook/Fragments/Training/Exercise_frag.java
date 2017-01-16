package dk.bepeaked.bodybook.Fragments.Training;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseGoals;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Exercise_frag extends Fragment implements AdapterView.OnItemClickListener, Runnable {


    String pasName, namePlan;
    int pasID, planID;
    RealmList<ExerciseGoals> realmListExercises = new RealmList<ExerciseGoals>();
    ArrayList<String> arrayListExerciseNames = new ArrayList<String>();
    Bundle bundleArgs = new Bundle();
    private SwipeMenuListView listView;
    View view;
    WorkoutController wc = new WorkoutController();
    SharedPreferences prefs;
    Singleton singleton;


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
        singleton = Singleton.singleton;
        singleton.listen(this);
        pasID = getArguments().getInt("TræningspasID", 99999);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        planID = prefs.getInt("lastUsedPlan", 99999);
        pasName = wc.getSpecificPas(pasID).getName();

        getActivity().setTitle(pasName);

        realmListExercises = wc.getSpecificPas(pasID).getExercises();

        listView = (SwipeMenuListView) view.findViewById(R.id.ListView_id);
        ExerciseListAdapterRepsSets exerciseListAdapter = new ExerciseListAdapterRepsSets();
        listView.setOnItemClickListener(this);
        listView.setAdapter(exerciseListAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item width
                deleteItem.setWidth(300);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_forever_white_24dp);
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
                        //delete
                        bundleArgs.putInt("planID", planID);
                        bundleArgs.putInt("pasID", pasID);

                        bundleArgs.putInt("exerciseGoalsID", realmListExercises.get(position).getID());

                        DialogDeleteExerciseFromPas_frag dialog = new DialogDeleteExerciseFromPas_frag();
                        dialog.setArguments(bundleArgs);
                        dialog.show(getFragmentManager(), "Empty_pas");

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

            Bundle bundleArgs = new Bundle();
            bundleArgs.putInt("TræningspasID", pasID);

            AddExercise_frag fragment = new AddExercise_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("AddExercise_frag");
            fragment.setArguments(bundleArgs);
            fragmentTransaction.commit();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Bundle bundleArgs = new Bundle();
        String name = realmListExercises.get(position).getName();
        ExerciseDTO exerciseDTO = wc.getExercise(name);
        bundleArgs.putInt("chosenExerciseID", exerciseDTO.getID());


        TabLayoutExercise_frag fragment = new TabLayoutExercise_frag();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("traeningsOevelser");
        fragment.setArguments(bundleArgs);
        fragmentTransaction.commit();

    }


//    private void adapterReload() {
//        arrayListExerciseNames = wc.getExercisesFromPasToArray(planName, pasName);
//        // TODO skriv i rapporten at vi prøvede at bruge "adapter.notifyDataSetChanged(); men at det ikke virkede, derfor opretter vi en ny adapter, som er lidt mindre arbejde, end at loade hele fragmentet igen..
//        adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, arrayListExerciseNames);
//        listView.setAdapter(adapter);
//    }

    public class ExerciseListAdapterRepsSets extends BaseAdapter {

        @Override
        public int getCount() {
            return realmListExercises.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.exercise_list_element_with_name_reps_sets, parent, false);


            TextView tvExerciseName = (TextView) convertView.findViewById(R.id.TV_exercise_frag_exercisename);
            TextView tvGoals = (TextView) convertView.findViewById(R.id.TV_exercise_frag_reps_sets);

            tvExerciseName.setText(realmListExercises.get(position).getName());
            tvGoals.setText(realmListExercises.get(position).getSet() + " x " + realmListExercises.get(position).getReps());

            return convertView;
        }
    }

    private void adapterReload() {
        realmListExercises = wc.getSpecificPas(pasID).getExercises();
        ExerciseListAdapterRepsSets exerciseListAdapter = new ExerciseListAdapterRepsSets();
        listView.setOnItemClickListener(this);
        listView.setAdapter(exerciseListAdapter);
    }

    @Override
    public void run() {
        Log.d("Nicki", "Exercise Run metode: ");
        adapterReload();
    }

}
