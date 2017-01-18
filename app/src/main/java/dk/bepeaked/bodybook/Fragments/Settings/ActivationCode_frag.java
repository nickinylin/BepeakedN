package dk.bepeaked.bodybook.Fragments.Settings;


import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DAO.DietDAO;
import dk.bepeaked.bodybook.Backend.DAO.PlanDAO;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivationCode_frag extends Fragment implements View.OnClickListener{

    private EditText field;
    private ArrayList<String[]> files = new ArrayList<>();
    private DietDAO dao;
    private PlanDAO pdao = new PlanDAO();
    TextView confirm;
    View view;
    InputMethodManager imm;
    public ActivationCode_frag() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_activation_code_frag, container, false);
        getActivity().setTitle(getString(R.string.activationcode));
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        this.field = (EditText) view.findViewById(R.id.editText2);
        Button button = (Button) view.findViewById(R.id.button_dialog_OK);
        this.confirm = (TextView) view.findViewById(R.id.textView4);

        this.dao = new DietDAO();

        button.setOnClickListener(this);

        listFiles("");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        boolean success = false;
        for(int i = 0; i < files.size(); i++){
            if(field.getText().toString().equals(files.get(i)[0])){
                if(files.get(i)[1].equals("csv")){
                    dao.getDishes(getActivity(), files.get(i)[0] + "." + files.get(i)[1]);
                    pdao.getWorkouts(getActivity(), files.get(i)[0] + "W." + files.get(i)[1]);
                    success = true;
                }
            }
        }
        if(success){
            confirm.setText(R.string.success);
        }else{
            confirm.setText(R.string.could_not_find_any_matches);
        }

        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
    private void listFiles(String dirFrom) {
        Resources res = getResources();
        AssetManager am = res.getAssets();
        String fileList[] = new String[0];
        try {
            fileList = am.list(dirFrom);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileList != null)
        {
            for ( int i = 0;i<fileList.length;i++)
            {
                files.add(fileList[i].split("\\."));
            }
        }
    }
}