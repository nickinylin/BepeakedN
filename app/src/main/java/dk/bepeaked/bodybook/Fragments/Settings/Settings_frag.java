package dk.bepeaked.bodybook.Fragments.Settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings_frag extends Fragment implements AdapterView.OnItemClickListener {

    SharedPreferences prefs;


    public Settings_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        getActivity().setTitle(getString(R.string.settings));
        String[] settings = {getString(R.string.activationcode), getString(R.string.about_us)};

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, settings);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        ToggleButton measure = (ToggleButton) view.findViewById(R.id.switch1);
        if(prefs.getBoolean("measurement", false)){
            measure.setChecked(true);
        }
        ListView listView = (ListView) view.findViewById(R.id.settings);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        measure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Sæt lbs til
                    prefs.edit().putBoolean("measurement", true).commit();
                }else{
                    //Sæt kg til
                    prefs.edit().putBoolean("measurement", false).commit();
                }
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 1) {
            About_frag fragment = new About_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("stack");
            fragmentTransaction.commit();
        } else if (position == 0) {
            ActivationCode_frag fragment = new ActivationCode_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("stack");
            fragmentTransaction.commit();
        }
    }
}
