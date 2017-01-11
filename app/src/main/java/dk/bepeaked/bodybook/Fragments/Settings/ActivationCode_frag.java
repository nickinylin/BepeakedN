package dk.bepeaked.bodybook.Fragments.Settings;


import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DAO.DietDAO;
import dk.bepeaked.bodybook.Backend.DAO.JsonDAO;
import dk.bepeaked.bodybook.Backend.DAO.PlanDAO;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivationCode_frag extends Fragment implements View.OnClickListener{

    private EditText field;
    private JsonDAO json = new JsonDAO();
    private ArrayList<String[]> files = new ArrayList<>();
    private DietDAO dao;
    private PlanDAO pdao = new PlanDAO();

    public ActivationCode_frag() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activation_code_frag, container, false);

        field = (EditText) view.findViewById(R.id.editText2);
        Button button = (Button) view.findViewById(R.id.button_dialog_OK);

        this.dao = (DietDAO) getArguments().getSerializable("DietDAO");

        button.setOnClickListener(this);

        listFiles("");

        return view;
    }

    @Override
    public void onClick(View v) {
        for(int i = 0; i < files.size(); i++){
            if(field.getText().toString().equals(files.get(i)[0])){
                if(files.get(i)[1].equals("csv")){
                    dao.getDishes(getActivity(), files.get(i)[0] + "." + files.get(i)[1]);
                }
            }
        }
    }
    private void listFiles(String dirFrom) {
        Resources res = getResources(); //if you are in an activity
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