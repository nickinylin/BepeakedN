package dk.bepeaked.bodybook.Fragments.Training;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pas_frag extends Fragment implements AdapterView.OnItemClickListener, Runnable{
    //WorkoutDAO wdao = new WorkoutDAO();

    WorkoutController wc = new WorkoutController();
    String planName;
    int planID;
    ArrayList<String> arrayListPasNames = new ArrayList<String>();
    SharedPreferences prefs;
    Bundle bundleArgs;
    SwipeMenuListView listView;
    ArrayAdapter adapter;
    RealmList<WorkoutPasDTO> realmListPas;
    Singleton singleton;


    public Pas_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);



        singleton = Singleton.singleton;
        singleton.listen(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        planID = prefs.getInt("lastUsedPlan", 9999);
        Log.d("LUKAS", "planid: " + planID);
        planName = wc.getSpecificPlan(planID).getName();
        Log.d("LUKAS", "plan: " + planName);

        bundleArgs = new Bundle();

        getActivity().setTitle(planName);


        arrayListPasNames = wc.getPasNamesFromPlan(planID);

        realmListPas = wc.getPasses(planID);


        adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, arrayListPasNames);
        listView = (SwipeMenuListView) view.findViewById(R.id.ListView_id);
        System.out.println("listview id: " + listView.getId());
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                openItem.setWidth(300);
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                openItem.setIcon(R.drawable.ic_edit_white_24dp);
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                deleteItem.setWidth(300);
                deleteItem.setIcon(R.drawable.ic_delete_forever_white_24dp);
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
                        bundleArgs.putInt("planID", planID);
                        bundleArgs.putInt("pasID", realmListPas.get(position).getID());

                        DialogEditPas_frag dialog = new DialogEditPas_frag();
                        dialog.setArguments(bundleArgs);
                        dialog.show(getFragmentManager(), "Empty_pas");

                        break;
                    case 1:
                        // delete
                        bundleArgs.putInt("planID", planID);
                        bundleArgs.putInt("pasID", realmListPas.get(position).getID());

                        DialogDeletePas_frag dialog2 = new DialogDeletePas_frag();
                        dialog2.setArguments(bundleArgs);
                        dialog2.show(getFragmentManager(), "Empty_pas");

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });



        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        singleton.unRegistrer(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.pasmenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.pasMenu_add_pas) {
            bundleArgs = new Bundle();
            bundleArgs.putInt("planID", planID);
            DialogAddPas_frag dialog = new DialogAddPas_frag();
            dialog.setArguments(bundleArgs);
            dialog.show(getActivity().getFragmentManager(), "DialogAddPas_frag");
        } else if (item.getItemId() == R.id.pasMenu_change_plan) {
            Plan_frag planFragment = new Plan_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, planFragment).addToBackStack("hej");
            fragmentTransaction.commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getActivity(), planName ,Toast.LENGTH_LONG).show();

        Bundle bundleArgs = new Bundle();
        bundleArgs.putInt("TræningspasID", realmListPas.get(position).getID());

        Exercise_frag fragment = new Exercise_frag();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
        fragment.setArguments(bundleArgs);
        fragmentTransaction.commit();

    }

    private void adapterReload() {
        arrayListPasNames = wc.getPasNamesFromPlan(planID);
        realmListPas = wc.getPasses(planID);
        adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, arrayListPasNames);
        listView.setAdapter(adapter);
    }

    @Override
    public void run() {
        adapterReload();
        Log.d("Nicki", "pas_frag: Run");
    }
}