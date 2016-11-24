package dk.bepeaked.bodybook.Fragments.Training;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddSet_frag extends Fragment implements View.OnClickListener {

    NumberPicker np_weight, np_reps;
    Button btn_ok;


    public AddSet_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_set_frag, container, false);


        np_weight = (NumberPicker) view.findViewById(R.id.np_weight);
        np_reps = (NumberPicker) view.findViewById(R.id.np_weight_decimal);
        btn_ok = (Button) view.findViewById(R.id.button_ok_add_set);

        np_weight.setMinValue(0);
        np_weight.setMaxValue(200);
        np_weight.setValue(50);

        np_reps.setMinValue(0);
        np_reps.setMaxValue(20);
        np_reps.setValue(4);

        btn_ok.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        ChosenExercise_frag fragment = new ChosenExercise_frag();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }
}
