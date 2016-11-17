package dk.bepeaked.bodybook.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class WorkoutPas_frag extends Fragment implements AdapterView.OnItemClickListener {

    String[] workoutPases = {"Mandag", "Tirsdag", "Torsdag", "Lørdag", "Søndag"};
    String nameTrainingplan;

    public WorkoutPas_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        getActivity().setTitle("Træningspas");

        nameTrainingplan =  getArguments().getString("Trainingplan");


        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, workoutPases);

        ListView listView = new ListView(getActivity());
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        Log.d("Nicki", "BUNDLE NICKI 2: " + nameTrainingplan);


        return listView;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), nameTrainingplan ,Toast.LENGTH_LONG).show();

//        Bundle i = new Bundle();
//        i.putString("Trainingplan", workouts[position]);
//
//        Training_frag fragment = new Training_frag();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
//        fragment.setArguments(i);
//        fragmentTransaction.commit();

    }

}
