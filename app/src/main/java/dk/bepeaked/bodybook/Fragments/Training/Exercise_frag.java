package dk.bepeaked.bodybook.Fragments.Training;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.LoadDataExercise;
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Exercise_frag extends Fragment implements AdapterView.OnItemClickListener{


    String nameWorkoutPas;
//    RealmList<String> realmExercise = new RealmList<String>();
    RealmList<ExerciseDTO> realmExercise2 = new RealmList<ExerciseDTO>();
//    String[] exercises = {"Benchpress", "Squat", "Deadlift", "Pullups", "Rows"};
    ArrayList<String> hej = new ArrayList<String>();
    LoadDataExercise ld = new LoadDataExercise();


    public Exercise_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listview, container, false);

        realmExercise2 = ld.dataCreateAllExercises();

        for (int i = 0; i < realmExercise2.size(); i++) {
            hej.add(realmExercise2.get(i).getName());
            Log.d("Nicki", "I =" + i);
        }

        getActivity().setTitle("Træningspas navn");
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, hej);

        ListView listView = (ListView) view.findViewById(R.id.ListView_id);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        Log.d("workoutpass", "BUNDLE NICKI 3: " + nameWorkoutPas);

        setHasOptionsMenu(true);

//        if (!getArguments().isEmpty()) {
//            nameWorkoutPas = getArguments().getString("Træningspas");
//        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.pasmenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.pasMenu_add_exercise) {
            Snackbar.make(getView(), "Der skal tilføjes en ny øvelse!", Snackbar.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.pasMenu_edit) {
            // TODO Hvad der skal ske for at ændre bundleArgs en træningsplan
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getActivity(), position ,Toast.LENGTH_LONG).show();


        Bundle bundleArgs = new Bundle();
        bundleArgs.putString("chosenExerciseName", hej.get(position));


        TabLayoutExercise_frag fragment = new TabLayoutExercise_frag ();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
        fragment.setArguments(bundleArgs);
        fragmentTransaction.commit();

    }
}
