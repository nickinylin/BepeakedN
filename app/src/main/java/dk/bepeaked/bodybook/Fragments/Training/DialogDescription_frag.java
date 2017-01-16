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
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogDescription_frag extends android.support.v4.app.DialogFragment implements View.OnClickListener {

    WorkoutController wc;
    int exerciseID;
    TextView tvTitle, tvDesc1, tvDesc2;
    Button btn;
    SharedPreferences prefs;
    Singleton singleton;
    ExerciseDTO exerciseDTO;


    public DialogDescription_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_description, container, false);

        singleton = Singleton.singleton;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        wc = new WorkoutController();
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        exerciseID = getArguments().getInt("exerciseID", 9999);

        tvTitle = (TextView) view.findViewById(R.id.TV_description_title);
        tvDesc1 = (TextView) view.findViewById(R.id.TV_description_desc1);
        tvDesc2 = (TextView) view.findViewById(R.id.TV_description_desc2);
        btn = (Button) view.findViewById(R.id.button_description_ok);

        exerciseDTO = wc.getExercise(exerciseID);

        if (!(exerciseDTO.getDesc1() == null)) {
            tvTitle.setText(exerciseDTO.getName());
            tvDesc1.setText(exerciseDTO.getDesc1());
            tvDesc2.setText(exerciseDTO.getDesc2());
        } else {
            tvTitle.setText("Der er ingen beskrivelse til øvelser du selv har tilføjet. God dag!");
            tvDesc1.setText("");
            tvDesc2.setText("");
        }


        btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btn) {
           dismiss();
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        singleton.notifyObservers();
    }
}
