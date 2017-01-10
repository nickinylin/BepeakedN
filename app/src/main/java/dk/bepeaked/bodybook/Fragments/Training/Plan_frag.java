package dk.bepeaked.bodybook.Fragments.Training;


import android.content.SharedPreferences;
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
public class Plan_frag extends Fragment implements AdapterView.OnItemClickListener {

    String[] workouts = {"Bepeaked", "Træningsplan 1", "Træningsplan 2", "Min egen træningsplan", "Træææning!", "Fuck det bliver godt!", "jeg vil ikke mere", "Træning 3","Træning 4","Træning 5","Træning 6" };
    SharedPreferences prefs;

    public Plan_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listview, container, false);



        getActivity().setTitle("Træningsplaner");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, workouts);

        ListView listView = (ListView) view.findViewById(R.id.ListView_id);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "hejsa " + position ,Toast.LENGTH_SHORT).show();

        Bundle i = new Bundle();
        i.putString("Træningsplan", workouts[position]);


        Log.d("Nicki", "BUNDLE NICKI 1: " + i.getString("Trainingplan"));

        Pas_frag fragment = new Pas_frag();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
        fragment.setArguments(i);
        fragmentTransaction.commit();

    }
}