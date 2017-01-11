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
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogDeletePas_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc = new WorkoutController();
    Button btnOK, btnCancel;
    TextView tv;
    Bundle argumens;
    String pasToDelete, currentPlan;


    public DialogDeletePas_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dialog_delete_pas_frag, container, false);

        pasToDelete = getArguments().getString("pasToDelete", "Empty");
        currentPlan = getArguments().getString("planCurrent", "Empty");

        btnOK = (Button) view.findViewById(R.id.button_dialog_delete_pas_OK);
        btnCancel = (Button) view.findViewById(R.id.button_dialog_delete_pas_Cancel);
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
                wc.deletePas(currentPlan, pasToDelete);
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

