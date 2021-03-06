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
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogEditPlan_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc;
    Button btnOK, btnCancel;
    TextView tv;
    EditText et;
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
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        wc = new WorkoutController();

        btnOK = (Button) view.findViewById(R.id.button_dialog_delete_OK);
        btnCancel = (Button) view.findViewById(R.id.button_dialog_delete_Cancel);
        tv = (TextView) view.findViewById(R.id.TV_dialog_edit_info);
        et = (EditText) view.findViewById(R.id.ET_dialog_edit);

        tv.setText(R.string.write_new_name_for_plan);


        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnOK) {
            String newPlanName = "" + et.getText();
            if (newPlanName.length() == 0 || newPlanName.startsWith(" ")) {
                tv.setText(R.string.name_cant_be_empty);
            } else {
                try {
                    wc.updatePlanName(planID, newPlanName);
                    dismiss();
                } catch (ExceptionPasDoesntExist e) {
                    e.printStackTrace();
                    tv.setText(R.string.mistake_happened);
                } catch (ExceptionNameAlreadyExist e) {
                    e.printStackTrace();
                    tv.setText(R.string.name_already_exist);
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

