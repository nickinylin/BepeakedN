package dk.bepeaked.bodybook.Fragments.Settings;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import dk.bepeaked.bodybook.Backend.DAO.JsonDAO;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivationCode_frag extends Fragment implements View.OnClickListener{

    private EditText field;
    private JsonDAO json = new JsonDAO();

    public ActivationCode_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activation_code_frag, container, false);

        field = (EditText) view.findViewById(R.id.editText2);
        Button button = (Button) view.findViewById(R.id.button_dialog_OK);

        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
//        String input = field.getText().toString();
//        if(!(input.matches(""))){
//            Field[] fields=R.raw.class.getFields();
//            for(int i = 0; i < fields.length; i++){
//                if(fields[i].getName().equals(input)){
//                    try {
//                        json.getData(this.getActivity(), input);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
    }
}
