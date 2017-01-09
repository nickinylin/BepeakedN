package dk.bepeaked.bodybook.Fragments.Settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings_frag extends Fragment implements AdapterView.OnItemClickListener {


    public Settings_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);
        getActivity().setTitle("Indstillinger");
        String[] settings = {getActivity().getResources().getString(R.string.measure), getActivity().getResources().getString(R.string.activationcode)};

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, settings);

        ListView listView = (ListView) view.findViewById(R.id.ListView_id);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            MeasureUnit_frag fragment = new MeasureUnit_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
            fragmentTransaction.commit();
        } else if (position == 1) {
            ActivationCode_frag fragment = new ActivationCode_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
            fragmentTransaction.commit();
        }
    }
}
