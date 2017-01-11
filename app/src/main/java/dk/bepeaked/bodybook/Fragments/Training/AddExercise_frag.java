package dk.bepeaked.bodybook.Fragments.Training;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddExercise_frag extends Fragment implements AdapterView.OnItemClickListener{


    String namePas, namePlan;
    RealmList<ExerciseDTO> realmExercise2 = new RealmList<ExerciseDTO>();
    ArrayList<String> traeningsOevelser = new ArrayList<String>();
    SharedPreferences prefs;
    EditText editTextLocal;
    View view;
    WorkoutController wc = new WorkoutController();
    private SwipeMenuListView listView;



    public AddExercise_frag() {
        // Required empty public constructor
    }

    //@Override
    public void onSearch()//Vi skal få det brugeren har indtastet.
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.listview, container, false);
        view = inflater.inflate(R.layout.listviewsearch, container, false);

        namePas = getArguments().getString("TræningspasNavn", "Empty");

        getActivity().setTitle("Tilføj til "+ namePas);

        traeningsOevelser = wc.getAllExerciseNamesToArray();

        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, traeningsOevelser);

        listView = (SwipeMenuListView) view.findViewById(R.id.ListView_id_search);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        Log.d("workoutpass", "BUNDLE NICKI 3: " + namePas);

        setHasOptionsMenu(true);

        //Lav noget med vores editText/Search input
        editTextLocal = (EditText) view.findViewById(R.id.editText);
        editTextLocal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                traeningsOevelser.clear();

                traeningsOevelser = wc.getAllExerciseNamesToArray();

                searchItem();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    public void searchItem(){

        for (int j = 0 ; j < traeningsOevelser.size(); j++) {

            String exerciseName = traeningsOevelser.get(j).toLowerCase();
            System.out.println(exerciseName);

            if(!exerciseName.contains(editTextLocal.getText())){
                traeningsOevelser.remove(j);
                j=j-1;

            }
        }
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, traeningsOevelser);

        listView = (SwipeMenuListView) view.findViewById(R.id.ListView_id_search);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.pasmenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.pasMenu_add_exercise) {
            Snackbar.make(getView(), "Der skal tilføjes en ny øvelse!", Snackbar.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.pasMenu_edit) {
            // TODO Hvad der skal ske for at ændre bundleArgs en træningsplan
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Bundle bundleArgs = new Bundle();
        bundleArgs.putString("chosenExerciseName", traeningsOevelser.get(position));
        Log.d("Nicki", "pasName; " + namePas);
        bundleArgs.putString("TræningspasNavn", namePas);
        DialogAddExerciseGoals_frag dialog = new DialogAddExerciseGoals_frag();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
//                Snackbar.make(getView(), "", Snackbar.LENGTH_LONG).show();

            }
        });
        dialog.setArguments(bundleArgs);
        dialog.show(getActivity().getFragmentManager(), "Empty_pas");

//
//        Exercise_frag fragment = new Exercise_frag();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragment.setArguments(bundleArgs);
//        fragmentTransaction.commit();

    }



}
