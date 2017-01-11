package dk.bepeaked.bodybook.Fragments.Training;


import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogAddExerciseGoals_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc = new WorkoutController();
    String planName, pasName, exerciseName;
    NumberPicker npReps, npSets;
    TextView tvReps, tvSets, tvInfo;

    Button btn;
    SharedPreferences prefs;


    public DialogAddExerciseGoals_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_add_exercise_goals, container, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        planName = prefs.getString("lastUsedPlan", "Empty_plan");
        pasName = getArguments().getString("TræningspasNavn", "Empty_pas");
        exerciseName = getArguments().getString("chosenExerciseName", "Empty_exercise");


        npReps = (NumberPicker) view.findViewById(R.id.NumberPickerAddExerciseAddReps);
        npSets = (NumberPicker) view.findViewById(R.id.NumberPickerAddExerciseAddSets);
        tvInfo = (TextView) view.findViewById(R.id.TV_addExerciseDialog_info);
        tvReps = (TextView) view.findViewById(R.id.TV_addExerciseDialog_reps);
        tvSets = (TextView) view.findViewById(R.id.TV_addExerciseDialog_sets);
        btn = (Button) view.findViewById(R.id.button_addExercise_dialog_WithRepsSets_OK);

        tvInfo.setText("Vælg antal repetitioner og sæt");
        tvSets.setText("Sets");
        tvReps.setText("Reps");

        npReps.setMinValue(1);
        npReps.setMaxValue(30);
        npSets.setMinValue(1);
        npSets.setMaxValue(10);


        btn.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btn) {

            try {
                Log.d("Nicki", "planName:" + planName + "pasName:" + pasName + "exerciseName:" + exerciseName);
                wc.addExerciseToPas(planName, pasName, exerciseName, npSets.getValue(), npReps.getValue());
                Snackbar.make(getView(), "Den er tilføjet!", Snackbar.LENGTH_LONG).show();
                dismiss();
            } catch (ExceptionPasDoesntExist e) {
                e.printStackTrace();
                tvInfo.setText(e.getMessage());
            }
        }
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
