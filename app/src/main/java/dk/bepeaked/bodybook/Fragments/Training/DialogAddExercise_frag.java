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
import android.widget.EditText;
import android.widget.TextView;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogAddExercise_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc;
    int planID;
    String exerciseNameNew;
    TextView tv;
    EditText et;
    Button btn;
    SharedPreferences prefs;
    Singleton singleton;


    public DialogAddExercise_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_add, container, false);
        wc = new WorkoutController();
        singleton = Singleton.singleton;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        tv = (TextView) view.findViewById(R.id.TV_add_info);
        et = (EditText) view.findViewById(R.id.ET_add_name);
        btn = (Button) view.findViewById(R.id.button_add_ok);

        tv.setText(R.string.input_name_new_exercise);
        et.setText("");
        btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btn) {
            exerciseNameNew = "" + et.getText();
            if (exerciseNameNew.length() == 0 || exerciseNameNew.startsWith(" ")) {
                tv.setText(R.string.name_cant_be_empty);
            }else {
                try {
                    wc.createNewExercise(exerciseNameNew);
                    dismiss();
                } catch (ExceptionNameAlreadyExist e) {
                    e.printStackTrace();
                    tv.setText(e.getMessage());
                }
            }
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        singleton.notifyObservers();
    }
}
