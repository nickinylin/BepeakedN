package dk.bepeaked.bodybook.Fragments.Training;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Plan_frag extends Fragment implements AdapterView.OnItemClickListener, Runnable {
    //WorkoutDAO wdao = new WorkoutDAO();

    private WorkoutController wc = new WorkoutController();
    private String planName;
    private int planID;
    private SharedPreferences prefs;
    private Bundle bundleArgs;
    private SwipeMenuListView listView;
    private FloatingActionButton fab;
    private ArrayAdapter adapter;
    private RealmList<WorkoutDTO> realmListPlans;
    private ArrayList<String> arrayListPlanNames;
    Singleton singleton;

    public Plan_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_plan, container, false);
        singleton = Singleton.singleton;
        singleton.listen(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        bundleArgs = new Bundle();

        getActivity().setTitle("Alle planer");

        realmListPlans = wc.getPlans();
        arrayListPlanNames = wc.getPlanNamesToArray();

        adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, arrayListPlanNames);

        listView = (SwipeMenuListView) view.findViewById(R.id.ListView_id2);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "edit" item
                SwipeMenuItem editItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                editItem.setWidth(300);
                editItem.setIcon(R.drawable.ic_edit_white_24dp);
                menu.addMenuItem(editItem);

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
                        // edit
                        bundleArgs.putInt("planID", realmListPlans.get(position).getID());

                        DialogEditPlan_frag dialog = new DialogEditPlan_frag();
                        dialog.setArguments(bundleArgs);
                        dialog.show(getFragmentManager(), "Empty_pas");

                        break;
                    case 1:
                        // delete
                        bundleArgs.putInt("planID", realmListPlans.get(position).getID());

                        DialogDeletePlan_frag dialog2 = new DialogDeletePlan_frag();
                        dialog2.setArguments(bundleArgs);
                        dialog2.show(getFragmentManager(), "Empty_pas");

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
//
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
        inflater.inflate(R.menu.planmenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.planMenu_add_plan) {
            DialogAddPlan_frag dialog = new DialogAddPlan_frag();
            dialog.show(getActivity().getFragmentManager(), "DialogAddPlan_frag");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        prefs.edit().putInt("lastUsedPlan", realmListPlans.get(position).getID()).commit();
        Pas_frag fragment = new Pas_frag();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
        fragmentTransaction.commit();
    }

    private void showDialogAlert() {
        bundleArgs = new Bundle();
        bundleArgs.putInt("planID", planID);
        DialogAddPas_frag dialog = new DialogAddPas_frag();
        dialog.setArguments(bundleArgs);
        dialog.show(getActivity().getFragmentManager(), "Empty_pas");
    }

    private void adapterReload() {
        realmListPlans = wc.getPlans();
        arrayListPlanNames = wc.getPlanNamesToArray();
        adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, arrayListPlanNames);
        listView.setAdapter(adapter);
    }

    @Override
    public void run() {
        adapterReload();
    }
}