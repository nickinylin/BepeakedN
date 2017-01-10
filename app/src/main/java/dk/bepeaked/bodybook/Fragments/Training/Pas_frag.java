package dk.bepeaked.bodybook.Fragments.Training;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
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


import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pas_frag extends Fragment implements AdapterView.OnItemClickListener {
    //WorkoutDAO wdao = new WorkoutDAO();

    WorkoutController wc = new WorkoutController();
    String[] workoutPases = {"Mandag", "Tirsdag", "Torsdag", "Lørdag", "Søndag", "Nicki"};
    String nameTrainingplan;
    RealmList<WorkoutPasDTO> realmListString = new RealmList<WorkoutPasDTO>();
    //ArrayList<String> workoutPases = new ArrayList<String>();
    SharedPreferences prefs;
    Bundle bundleArgs;
    private SwipeMenuListView listView;

    public Pas_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        nameTrainingplan = prefs.getString("lastUsedPlan", "empty");

        getActivity().setTitle(nameTrainingplan);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, wc.getPasNamesFromPlan(nameTrainingplan));

        listView = (SwipeMenuListView) view.findViewById(R.id.ListView_id);

        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(300);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(300);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_action_bepeaked_logo);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Snackbar.make(getView(), "Open!", Snackbar.LENGTH_LONG).show();
                        break;
                    case 1:
                        // delete
                        Snackbar.make(getView(), "delete!", Snackbar.LENGTH_LONG).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });




//        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, wc.getPasNamesFromPlan(nameTrainingplan));
//
//        ListView listView = (ListView) view.findViewById(R.id.ListView_id);
//        System.out.println("NICKI ID: " + listView.getId());
//        listView.setOnItemClickListener(this);
//        listView.setAdapter(adapter);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.workoutmenu, menu);
        Log.d("Nicki", "onCreateOptionsMenu: " + "Her bliver workoutmenut kaldt");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.workoutMenu_add_pas) {
            showDialogAlert();
        } else if (item.getItemId() == R.id.workoutMenu_edit) {
            // TODO Hvad der skal ske for at ændre bundleArgs en træningsplan
        } else if (item.getItemId() == R.id.workoutMenu_change_plan) {
            // TODO Hvad der skal ske for at skifte aktuel træningsplan
        } else if (item.getItemId() == R.id.workoutMenu_add_workoutplan) {
            DialogAddPas_frag dialog = new DialogAddPas_frag();
            dialog.show(getActivity().getFragmentManager(), "empty");

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getActivity(), nameTrainingplan ,Toast.LENGTH_LONG).show();

        Bundle bundleArgs = new Bundle();
        bundleArgs.putString("TræningspasNavn", workoutPases[position]);

        Exercise_frag fragment = new Exercise_frag();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
        fragment.setArguments(bundleArgs);
        fragmentTransaction.commit();

    }

    private void showDialogAlert() {
        bundleArgs = new Bundle();
        bundleArgs.putString("planCurrent", nameTrainingplan);
        DialogAddPas_frag dialog = new DialogAddPas_frag();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Pas_frag fragment = new Pas_frag();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
                fragment.setArguments(bundleArgs);
                fragmentTransaction.commit();
            }
        });
        dialog.setArguments(bundleArgs);
        dialog.show(getActivity().getFragmentManager(), "Empty_pas");
    }

}