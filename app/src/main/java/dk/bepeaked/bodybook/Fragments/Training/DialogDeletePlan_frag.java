package dk.bepeaked.bodybook.Fragments.Training;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionCantDelete;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogDeletePlan_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc;
    Button btnOK, btnCancel;
    TextView tv;
    int pasID, planID;
    Singleton singleton;
    private SharedPreferences prefs;


    public DialogDeletePlan_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dialog_delete_frag, container, false);
        singleton = Singleton.singleton;
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        wc = new WorkoutController();
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        pasID = getArguments().getInt("pasID", 9999);
        planID = getArguments().getInt("planID", 9999);

        btnOK = (Button) view.findViewById(R.id.button_dialog_delete_OK);
        btnCancel = (Button) view.findViewById(R.id.button_dialog_delete_Cancel);
        tv = (TextView) view.findViewById(R.id.TV_dialog_delete_pas);

        tv.setText(R.string.sure_you_want_to_delete_plan);

        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnOK) {
            try {
                wc.deletePlan(planID);
                if (prefs.getInt("lastUsedPlan", 9999) == planID ) {
                    prefs.edit().putInt("lastUsedPlan", wc.getPlans().first().getID()).commit();
                }
                dismiss();
            } catch (ExceptionPasDoesntExist e) {
                e.printStackTrace();
                tv.setText(R.string.mistake_happened_on_delete);
            } catch (ExceptionCantDelete e) {
                e.printStackTrace();
                tv.setText(e.getMessage());
                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
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

