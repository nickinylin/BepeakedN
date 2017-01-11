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
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogEditPas_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc = new WorkoutController();
    Button btnOK, btnCancel;
    TextView tv;
    EditText et;
    Bundle argumens;
    String pasName, currentPlan;


    public DialogEditPas_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dialog_edit_pas_frag, container, false);

        pasName = getArguments().getString("pasName", "Empty");
        currentPlan = getArguments().getString("planName", "Empty");

        btnOK = (Button) view.findViewById(R.id.button_dialog_delete_pas_OK);
        btnCancel = (Button) view.findViewById(R.id.button_dialog_delete_pas_Cancel);
        tv = (TextView) view.findViewById(R.id.TV_dialog_edit_pas_info);
        et = (EditText) view.findViewById(R.id.ET_dialog_edit_pas);

        tv.setText("Skriv det nye navn på passet");


        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnOK) {
            try {
                String newPasName = "" + et.getText();
                wc.updatePasName(currentPlan, pasName, newPasName);
            } catch (ExceptionPasDoesntExist e) {
                e.printStackTrace();
                tv.setText("Der skete en fejl!");
            }
            dismiss();
        } else if (v == btnCancel) {
            dismiss();
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

