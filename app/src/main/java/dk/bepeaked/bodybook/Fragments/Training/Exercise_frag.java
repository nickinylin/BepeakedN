package dk.bepeaked.bodybook.Fragments.Training;


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
import android.widget.ListView;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.LoadDataExercise;
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Exercise_frag extends Fragment implements AdapterView.OnItemClickListener{


    String nameWorkoutPas;
//    RealmList<String> realmExercise = new RealmList<String>();
    RealmList<ExerciseDTO> realmExercise2 = new RealmList<ExerciseDTO>();
//    String[] exercises = {"Benchpress", "Squat", "Deadlift", "Pullups", "Rows"};
    ArrayList<String> traeningsOevelser = new ArrayList<String>();
    LoadDataExercise ld = new LoadDataExercise();
    SharedPreferences prefs;
    EditText editTextLocal;
    View view;


    public Exercise_frag() {
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

        nameWorkoutPas = getArguments().getString("TræningspasNavn", "Empty");

        getActivity().setTitle(nameWorkoutPas);

        realmExercise2 = ld.dataCreateAllExercises();

        for (int i = 0; i < realmExercise2.size(); i++) {
            traeningsOevelser.add(realmExercise2.get(i).getName());
            Log.d("Nicki", "I =" + i);
        }

        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, traeningsOevelser);

        ListView listView = (ListView) view.findViewById(R.id.ListView_id);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        Log.d("workoutpass", "BUNDLE NICKI 3: " + nameWorkoutPas);

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

                for (int i = 0; i < realmExercise2.size(); i++) {
                    traeningsOevelser.add(realmExercise2.get(i).getName());
                    Log.d("Ramyar", "I =" + i);
                }

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

        ListView listView = (ListView) view.findViewById(R.id.ListView_id);
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
//        Toast.makeText(getActivity(), position ,Toast.LENGTH_LONG).show();


        Bundle bundleArgs = new Bundle();
        bundleArgs.putString("chosenExerciseName", traeningsOevelser.get(position));


        TabLayoutExercise_frag fragment = new TabLayoutExercise_frag ();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("traeningsOevelser");
        fragment.setArguments(bundleArgs);
        fragmentTransaction.commit();

    }
}
