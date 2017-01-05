package dk.bepeaked.bodybook.Fragments.Training;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DAO.WorkoutDAO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutPas_frag extends Fragment implements AdapterView.OnItemClickListener {
    //WorkoutDAO wdao = new WorkoutDAO();

    WorkoutController wc = new WorkoutController();
    //    String[] workoutPases = {"Mandag", "Tirsdag", "Torsdag", "Lørdag", "Søndag" , "Nicki"};
    String nameTrainingplan;
    RealmList<WorkoutPasDTO> realmListString = new RealmList<WorkoutPasDTO>();
    ArrayList<String> workoutPases = new ArrayList<String>();

    public WorkoutPas_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);



//        wc.addPlan("Nicki");
//        wc.addNewPasToPlan("Nicki", "Hobo");
//        wc.addNewPasToPlan("Nicki", "Tiiiirsdag");

        realmListString = wc.getPasses("Nicki");

        for (int i = 0; i < realmListString.size(); i++) {
            workoutPases.add(realmListString.get(i).getName());
            Log.d("Udskriv", "onCreateView: " + realmListString.get(i).getName());
        }


        /*try {
            wdao.createPlan("SebbyG2", getContext());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        getActivity().setTitle("Træningsplan navn");

//        if (!getArguments().isEmpty()) {
//            nameTrainingplan = getArguments().getString("Trainingplan");
//        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, workoutPases);

        ListView listView = (ListView) view.findViewById(R.id.ListView_id);
        System.out.println("NICKI ID: " + listView.getId());
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        Log.d("Nicki", "BUNDLE NICKI 2: " + nameTrainingplan);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.workoutmenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.workoutMenu_add_pas) {
            Snackbar.make(getView(), "hrrra", Snackbar.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.workoutMenu_edit) {
            // TODO Hvad der skal ske for at ændre i en træningsplan
        } else if (item.getItemId() == R.id.workoutMenu_change_plan) {
            // TODO Hvad der skal ske for at skifte aktuel træningsplan
        } else if (item.getItemId() == R.id.workoutMenu_add_workoutplan) {
            AddPlan_frag fragment = new AddPlan_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.listView_fragment_container, fragment).addToBackStack("hej");
//        fragment.setArguments(i);
            fragmentTransaction.commit();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getActivity(), nameTrainingplan ,Toast.LENGTH_LONG).show();

//        Bundle i = new Bundle();
//        i.putString("Træningspas", workoutPases[position]);

        Exercise_frag fragment = new Exercise_frag();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
//        fragment.setArguments(i);
        fragmentTransaction.commit();

    }

}



