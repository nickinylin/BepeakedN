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
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogEditPas_frag extends DialogFragment implements View.OnClickListener {

    WorkoutController wc = new WorkoutController();
    Button btnOK, btnCancel;
    TextView tv;
    EditText et;
    int pasID;
    Singleton singleton;


    public DialogEditPas_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dialog_edit_frag, container, false);
        singleton = Singleton.singleton;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        pasID = getArguments().getInt("pasID", 9999);

        btnOK = (Button) view.findViewById(R.id.button_dialog_delete_OK);
        btnCancel = (Button) view.findViewById(R.id.button_dialog_delete_Cancel);
        tv = (TextView) view.findViewById(R.id.TV_dialog_edit_info);
        et = (EditText) view.findViewById(R.id.ET_dialog_edit);

        tv.setText(R.string.write_new_name_for_pas);


        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnOK) {
            String newPasName = "" + et.getText();
            if (newPasName.length() == 0 || newPasName.startsWith(" ")) {
                tv.setText(R.string.name_cant_be_empty);
            } else {
                try {
                    wc.updatePasName(pasID, newPasName);
                    dismiss();
                } catch (ExceptionNameAlreadyExist exceptionNameAlreadyExist) {
                    exceptionNameAlreadyExist.printStackTrace();
                    tv.setText(R.string.mistake_happened);
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

