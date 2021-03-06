package dk.bepeaked.bodybook.Fragments.Training;


import android.app.DialogFragment;
import android.content.DialogInterface;
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
import android.widget.TextView;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionWrongInput;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogAddSet_frag extends DialogFragment {

    NumberPicker npWeight1, npReps;
    Button btnOK, btnCancel;
    TextView tvInfo, tvReps, tvWeight;
    SharedPreferences prefs;
    String exerciseName;
    int exerciseID;
    WorkoutController wc;
    Singleton singleton;


    public DialogAddSet_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_add_set_frag, container, false);
        singleton = Singleton.singleton;
        wc = new WorkoutController();
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        exerciseID = getArguments().getInt("chosenExerciseID", 9999);
        exerciseName = wc.getExercise(exerciseID).getName();


        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        npReps = (NumberPicker) view.findViewById(R.id.NumberPickerReps);
        npWeight1 = (NumberPicker) view.findViewById(R.id.NumberPickerWeight1);
        btnOK = (Button) view.findViewById(R.id.button_dialog_OK);
        tvInfo = (TextView) view.findViewById(R.id.TV_dialogAddSet_info);
        tvReps = (TextView) view.findViewById(R.id.TV_dialogAddSet_reps);
        tvWeight = (TextView) view.findViewById(R.id.TV_dialogAddSet_weight);


        //TODO skal sættes til den sidst benyttede, så der skal bruges den der sharedpreferences
        npReps.setMinValue(1);
        npReps.setMaxValue(50);
        npReps.setValue(prefs.getInt("1 " + exerciseID, 0));

        npWeight1.setMinValue(0);
        if(prefs.getBoolean("measurement", false)){
            npWeight1.setMaxValue(convertKilo(200));
            npWeight1.setValue(convertKilo(prefs.getInt("2 " + exerciseID, 0)));
        }else{
            npWeight1.setMaxValue(200);
            npWeight1.setValue(prefs.getInt("2 " + exerciseID, 0));
        }
        npWeight1.setValue(prefs.getInt("2 " + exerciseID, 0));
        tvInfo.setText(R.string.add_set_reps_and_weight);


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btnOK) {
                    try {
                        if(prefs.getBoolean("measurement", false)) {
                            wc.addSet(exerciseName, npWeight1.getValue()/2.2046, npReps.getValue());
                        }else{
                            wc.addSet(exerciseName, npWeight1.getValue(), npReps.getValue());
                        }
                        prefs.edit().putInt("1 " + exerciseID, npReps.getValue()).commit();
                        prefs.edit().putInt("2 " + exerciseID, npWeight1.getValue()).commit();
                        dismiss();
                    } catch (ExceptionWrongInput e) {
                        e.printStackTrace();
                        tvInfo.setText(e.getMessage());
                    }
                } else if (v == btnCancel) {
                    dismiss();
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

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        singleton.notifyObservers();
    }

}