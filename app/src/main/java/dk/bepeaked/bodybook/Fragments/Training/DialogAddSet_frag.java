package dk.bepeaked.bodybook.Fragments.Training;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogAddSet_frag extends DialogFragment {

    NumberPicker npWeight1, npWeight2, npReps;
    Button btnOK, btnCancel;
    TextView tvInfo;
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
        exerciseID = getArguments().getInt("chosenExerciseID", 9999);
        Log.d("LUKAS", "exerciseID: " + exerciseID);
        exerciseName = wc.getExercise(exerciseID).getName();



        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        npReps = (NumberPicker) view.findViewById(R.id.NumberPickerReps);
        npWeight1 = (NumberPicker) view.findViewById(R.id.NumberPickerWeight1);
        npWeight2 = (NumberPicker) view.findViewById(R.id.NumberPickerWeight2);
        btnOK = (Button) view.findViewById(R.id.button_dialog_OK);
        tvInfo = (TextView) view.findViewById(R.id.TV_addExerciseDialog_info);


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
        tvInfo.setText("Tilføj sæt");


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    wc.addSet(exerciseName, npWeight1.getValue(), npReps.getValue());
                } catch (ExceptionWrongInput e) {
                    e.printStackTrace();
                    tvInfo.setText(e.getMessage());

                }
            }
        });


        return view;
    }

    private DialogInterface.OnDismissListener onDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }


}
