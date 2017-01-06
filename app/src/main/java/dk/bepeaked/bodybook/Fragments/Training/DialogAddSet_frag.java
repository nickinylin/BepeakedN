package dk.bepeaked.bodybook.Fragments.Training;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogAddSet_frag extends DialogFragment {

    NumberPicker npWeight1, npWeight2, npReps;
    Button btnOK, btnCancel;
    SharedPreferences prefs;
    ExerciseDTO dto;


    public DialogAddSet_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_add_set_frag, container, false);

//        Bundle args = getArguments();
//        String title = args.getString("title", "");
//        String message = args.getString("message", "");

        npReps = (NumberPicker) view.findViewById(R.id.NumberPickerReps);
        npWeight1 = (NumberPicker) view.findViewById(R.id.NumberPickerWeight1);
        npWeight2 = (NumberPicker) view.findViewById(R.id.NumberPickerWeight2);
        btnOK = (Button) view.findViewById(R.id.button_dialog_OK);

        //TODO skal sættes til den sidst benyttede, så der skal bruges den der sharedpreferences
        npReps.setMinValue(1);
        npReps.setMaxValue(50);
//        npReps.setValue();
        npWeight1.setMinValue(0);
        npWeight1.setMaxValue(200);
        npWeight1.setValue(10);
        npWeight2.setMinValue(0);
        npWeight2.setMaxValue(3);
        npWeight2.setDisplayedValues(new String[]{"0", "25", "50", "75"});


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }


}
