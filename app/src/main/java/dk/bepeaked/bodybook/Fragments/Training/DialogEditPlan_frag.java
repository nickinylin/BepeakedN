package dk.bepeaked.bodybook.Fragments.Training;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogEditPlan_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc = new WorkoutController();
    Button btnOK, btnCancel;
    TextView tv;
    EditText et;
    Bundle argumens;
    String pasName;
    int planID;
    Singleton singleton;


    public DialogEditPlan_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dialog_edit_frag, container, false);
        singleton = Singleton.singleton;
        planID = getArguments().getInt("planID", 9999);
//        planName = getArguments().getString("planName", "Empty");

        btnOK = (Button) view.findViewById(R.id.button_dialog_delete_OK);
        btnCancel = (Button) view.findViewById(R.id.button_dialog_delete_Cancel);
        tv = (TextView) view.findViewById(R.id.TV_dialog_edit_info);
        et = (EditText) view.findViewById(R.id.ET_dialog_edit);

        tv.setText("Skriv det nye navn på planen");


        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnOK) {
            String newPlanName = "" + et.getText();

            try {
                wc.updatePlanName(planID, newPlanName);
            } catch (ExceptionPasDoesntExist e) {
                e.printStackTrace();
                tv.setText("Der skete en fejl");
            } catch (ExceptionNameAlreadyExist e) {
                e.printStackTrace();
                tv.setText("Navnet eksisterer allerede");
            }
            dismiss();

        } else if (v == btnCancel) {
            dismiss();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        singleton.notifyObservers();
    }
}

