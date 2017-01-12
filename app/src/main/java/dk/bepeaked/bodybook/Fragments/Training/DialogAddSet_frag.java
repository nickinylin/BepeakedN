package dk.bepeaked.bodybook.Fragments.Training;


import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.NumberPicker;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionWrongInput;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogAddSet_frag extends DialogFragment {

    NumberPicker npWeight1, npReps;
    Button btnOK, btnCancel;
    SharedPreferences prefs;
    ExerciseDTO dto;
    String exerciseName;
    int exerciseID;
    WorkoutController wc;


    public DialogAddSet_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_add_set_frag, container, false);
//        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyAlertDialogStyle);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        exerciseID = getArguments().getInt("chosenExerciseID", 9999);
        exerciseName = wc.getExercise(exerciseID).getName();



        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        npReps = (NumberPicker) view.findViewById(R.id.NumberPickerReps);
        npWeight1 = (NumberPicker) view.findViewById(R.id.NumberPickerWeight1);
        btnOK = (Button) view.findViewById(R.id.button_dialog_OK);

        //TODO skal sættes til den sidst benyttede, så der skal bruges den der sharedpreferences
        npReps.setMinValue(1);
        npReps.setMaxValue(50);
//        npReps.setValue();
        npWeight1.setMinValue(0);
        if(prefs.getBoolean("measurement", false)){
            npWeight1.setMaxValue(convertKilo(200));
        }else{
            npWeight1.setMaxValue(200);
        }
        npWeight1.setValue(10);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    wc.addSet(exerciseName, npWeight1.getValue(), npReps.getValue());
                } catch (ExceptionWrongInput exceptionWrongInput) {
                    exceptionWrongInput.printStackTrace();
                }
            }
        });
        return view;
    }
    private int convertKilo(int kilo) {
        double d = kilo * 2.2046;
        int pounds = (int) d;
        return pounds;
    }
}