package dk.bepeaked.bodybook.Fragments.Training;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Exercise_frag extends Fragment implements AdapterView.OnItemClickListener{

    String[] exercises = {"Benchpress", "Squat", "Deadlift", "Pullups", "Rows"};
    String nameWorkoutPas;

    public Exercise_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listview, container, false);

        getActivity().setTitle("Træningspas navn");
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, exercises);

        ListView listView = (ListView) view.findViewById(R.id.ListView_id);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        Log.d("workoutpass", "BUNDLE NICKI 3: " + nameWorkoutPas);

        setHasOptionsMenu(true);

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
            // TODO Hvad der skal ske for at ændre i en træningsplan
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), nameWorkoutPas ,Toast.LENGTH_LONG).show();


        Bundle i = new Bundle();
        i.putString("Træningspas", exercises[position]);

        ChosenExercise_frag fragment = new ChosenExercise_frag();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
        fragment.setArguments(i);
        fragmentTransaction.commit();

    }
}
