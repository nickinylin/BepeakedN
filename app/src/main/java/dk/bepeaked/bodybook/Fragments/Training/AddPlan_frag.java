package dk.bepeaked.bodybook.Fragments.Training;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlan_frag extends DialogFragment {


    public AddPlan_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_trainingplan, container, false);



        return view;
    }

}
