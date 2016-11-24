package dk.bepeaked.bodybook.Fragments.Settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import dk.bepeaked.bodybook.Fragments.Training.AddSet_frag;
import dk.bepeaked.bodybook.Fragments.Training.WorkoutPas_frag;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings_frag extends Fragment implements AdapterView.OnItemClickListener {


    public Settings_frag() {
        // Required empty public constructor
    }

    String[] settings = {"Måleenhed (kg / pund)", "Aktiveringskode", "Sprog"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);
        getActivity().setTitle("Indstillinger");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, settings);

        ListView listView = (ListView) view.findViewById(R.id.ListView_id);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {

        } else if (position == 1) {

        } else if  (position == 2) {

        }
    }
}
