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
public class DialogAddPas_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc;
    int planID;
    String pasNew;
    TextView tv;
    EditText et;
    Button btn;
    SharedPreferences prefs;
    Singleton singleton;


    public DialogAddPas_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_add, container, false);

        singleton = Singleton.singleton;
        wc = new WorkoutController();
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        tv = (TextView) view.findViewById(R.id.TV_add_info);
        et = (EditText) view.findViewById(R.id.ET_add_name);
        btn = (Button) view.findViewById(R.id.button_add_ok);

        tv.setText(R.string.input_name_on_the_new_pas);
        et.setText("");
        btn.setOnClickListener(this);

        planID = prefs.getInt("lastUsedPlan", 99999);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btn) {
            pasNew = "" + et.getText();
            if (pasNew.length() == 0 || pasNew.startsWith(" ")) {
                tv.setText(R.string.name_cant_be_empty);
            } else {
                try {
                    wc.addNewPasToPlan(planID, pasNew);
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
