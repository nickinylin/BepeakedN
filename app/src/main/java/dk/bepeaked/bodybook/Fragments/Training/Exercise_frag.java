package dk.bepeaked.bodybook.Fragments.Training;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
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
