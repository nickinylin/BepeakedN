package dk.bepeaked.bodybook.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dk.bepeaked.bodybook.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Training_frag extends Fragment implements AdapterView.OnItemClickListener {

    String[] workouts = {"Bepeaked", "Test", "Træningsplan 2"};


    public Training_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_training, container, false);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, workouts);

        ListView listView = new ListView(getActivity());
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        //dette gøres ikke i et fragment?? der returneres det view skal skal laves af aktiviteten
//        getActivity().setContentView(listView);

        return listView;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "HALLO!" ,Toast.LENGTH_SHORT).show();
    }
}
