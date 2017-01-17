package dk.bepeaked.bodybook.Fragments.Training;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogDeleteExerciseFromPas_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc = new WorkoutController();
    Button btnOK, btnCancel;
    TextView tv;
    int pasID, planID, exerciseGoalsID;
    Singleton singleton;


    public DialogDeleteExerciseFromPas_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dialog_delete_frag, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        singleton = Singleton.singleton;
        pasID = getArguments().getInt("pasID", 99999);
        planID = getArguments().getInt("planID", 99999);
        exerciseGoalsID = getArguments().getInt("exerciseGoalsID", 99999);


        btnOK = (Button) view.findViewById(R.id.button_dialog_delete_OK);
        btnCancel = (Button) view.findViewById(R.id.button_dialog_delete_Cancel);
        tv = (TextView) view.findViewById(R.id.TV_dialog_delete_pas);

        tv.setText("Er du sikker på passet skal slettes?");


        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnOK) {
            try {
                wc.deleteExerciseFromPas(exerciseGoalsID);
                dismiss();
            } catch (ExceptionPasDoesntExist e) {
                e.printStackTrace();
                tv.setText(e.getMessage());
            }
        } else if (v == btnCancel) {
            dismiss();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        singleton.notifyObservers();
    }

}

