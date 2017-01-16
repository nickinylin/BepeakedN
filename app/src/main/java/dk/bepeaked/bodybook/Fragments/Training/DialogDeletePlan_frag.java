package dk.bepeaked.bodybook.Fragments.Training;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogDeletePlan_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc = new WorkoutController();
    Button btnOK, btnCancel;
    TextView tv;
    String planName;
    int pasID, planID;
    Singleton singleton;


    public DialogDeletePlan_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dialog_delete_frag, container, false);
        singleton = Singleton.singleton;
        pasID = getArguments().getInt("pasID", 9999);
        planID = getArguments().getInt("planID", 9999);

        btnOK = (Button) view.findViewById(R.id.button_dialog_delete_OK);
        btnCancel = (Button) view.findViewById(R.id.button_dialog_delete_Cancel);
        tv = (TextView) view.findViewById(R.id.TV_dialog_delete_pas);

        tv.setText("Er du sikker på planen skal slettes?");

        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnOK) {
            try {
                wc.deletePlan(planID);
                dismiss();
            } catch (ExceptionPasDoesntExist e) {
                e.printStackTrace();
                tv.setText("Der opstod en fejl ved sletning");
            }
        } else if (v == btnCancel) {
            dismiss();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        singleton.notifyObservers();
    }
}
