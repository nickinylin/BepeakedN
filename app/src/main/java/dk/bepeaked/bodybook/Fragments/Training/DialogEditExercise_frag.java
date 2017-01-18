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
import android.widget.EditText;
import android.widget.TextView;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionCantDelete;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogEditExercise_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc;
    Button btnOK, btnCancel;
    TextView tv;
    EditText et;
    int exerciseID;
    Singleton singleton;


    public DialogEditExercise_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_edit_frag, container, false);
        singleton = Singleton.singleton;
        wc = new WorkoutController();
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        exerciseID = getArguments().getInt("exerciseID", 9999);

        btnOK = (Button) view.findViewById(R.id.button_dialog_delete_OK);
        btnCancel = (Button) view.findViewById(R.id.button_dialog_delete_Cancel);
        tv = (TextView) view.findViewById(R.id.TV_dialog_edit_info);
        et = (EditText) view.findViewById(R.id.ET_dialog_edit);

        tv.setText(R.string.write_name_new_exercise);


        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnOK) {
            String newExerciseName = "" + et.getText();
            if (newExerciseName.length() == 0 || newExerciseName.startsWith(" ")) {
                tv.setText(R.string.name_cant_be_empty);
            } else {
                try {
                    wc.updateExercise(exerciseID, newExerciseName);
                    dismiss();
                } catch (ExceptionCantDelete e) {
                    e.printStackTrace();
                    tv.setText(e.getMessage());
                } catch (ExceptionNameAlreadyExist e) {
                    e.printStackTrace();
                    tv.setText(e.getMessage());
                }
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

