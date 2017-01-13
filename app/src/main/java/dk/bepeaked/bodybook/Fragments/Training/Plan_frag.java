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
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Plan_frag extends Fragment implements AdapterView.OnItemClickListener {
    //WorkoutDAO wdao = new WorkoutDAO();

    private WorkoutController wc = new WorkoutController();
    private String planName;
    private int planID;
    //    private RealmList<String> arrayListPlanNames = new ArrayList<String>();
    private SharedPreferences prefs;
    private Bundle bundleArgs;
    private SwipeMenuListView listView;
    private FloatingActionButton fab;
    private ArrayAdapter adapter;
    private RealmList<WorkoutDTO> realmListPlans;

    public Plan_frag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_plan, container, false);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        bundleArgs = new Bundle();

        getActivity().setTitle("Alle planer");

        realmListPlans = wc.getPlans();
        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < realmListPlans.size(); i++){
            names.add(realmListPlans.get(i).getName());
        }

        adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, names);

        listView = (SwipeMenuListView) view.findViewById(R.id.ListView_id2);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
//                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
//                        0xCE)));
                // set item width
                openItem.setWidth(300);
                // set item title
//                openItem.setTitle("Rediger");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                openItem.setIcon(R.drawable.ic_edit_white_24dp);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
//                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(300);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_forever_white_24dp);
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
                        bundleArgs.putInt("planID", realmListPlans.get(position).getID());

                        DialogEditPas_frag dialog = new DialogEditPas_frag();
                        dialog.setArguments(bundleArgs);
                        dialog.show(getFragmentManager(), "Empty_pas");

                        break;
                    case 1:
                        // delete
                        bundleArgs.putInt("planID", realmListPlans.get(position).getID());

                        DialogDeletePas_frag dialog2 = new DialogDeletePas_frag();
                        dialog2.setArguments(bundleArgs);
                        dialog2.show(getFragmentManager(), "Empty_pas");

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddPlan_frag dialog2 = new DialogAddPlan_frag();
                dialog2.show(getActivity().getFragmentManager(), "DialogAddPlan_frag");
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.workoutmenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.workoutMenu_add_pas) {

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
//        arrayListPlanNames = wc.getPasNamesFromPlan(planID);
        realmListPlans = wc.getPlans();
        // TODO skriv i rapporten at vi prøvede at bruge "adapter.notifyDataSetChanged(); men at det ikke virkede, derfor opretter vi en ny adapter, som er lidt mindre arbejde, end at loade hele fragmentet igen..
//        adapter = new ArrayAdapter(getActivity(), R.layout.listeelement, R.id.listeelem_overskrift, arrayListPlanNames);
        listView.setAdapter(adapter);
    }

    public class ExerciseListAdapterPlan extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}