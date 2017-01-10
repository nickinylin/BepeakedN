package dk.bepeaked.bodybook.Fragments.Training;


import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogAddPas_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc = new WorkoutController();
    String planCurrent;
    String pasNew;
    TextView tv;
    EditText et;
    Button btn;
    SharedPreferences prefs;


    public DialogAddPas_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_pas, container, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        tv = (TextView) view.findViewById(R.id.TV_addPlan_info);
        et = (EditText) view.findViewById(R.id.ET_addPlan_name);
        btn = (Button) view.findViewById(R.id.button_add_plan_ok);

        tv.setText("Indtast navn på træningsplan");
        et.setText("");
        btn.setOnClickListener(this);

        planCurrent = prefs.getString("lastUsedPlan", "Luuukassddd");

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btn) {
            pasNew = "" + et.getText();
            try {
                Log.d("LUKAS", "planCurrent: " + planCurrent +" pasNew: "+pasNew);
                wc.addNewPasToPlan(planCurrent, pasNew);
                dismiss();
            } catch (ExceptionNameAlreadyExist e) {
                e.printStackTrace();
                Log.d("LUKAS", "exception: name already e");
                tv.setText(e.getMessage());
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
