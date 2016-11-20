package dk.bepeaked.bodybook.Fragments.Diet;


import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dk.bepeaked.bodybook.Activities.MyAdapter;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Diet_frag extends Fragment {

    ExpandableListView expandableListView;

    public Diet_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Kostplan");

        View view = inflater.inflate(R.layout.fragment_diet, container, false);

        expandableListView = (ExpandableListView) view.findViewById(R.id.exp_listview);

        List<String> Headings = new ArrayList<String>();
        List<String> L1 = new ArrayList<String>();
        List<String> L2 = new ArrayList<String>();
        List<String> L3 = new ArrayList<String>();
        HashMap<String,List<String>> childList = new HashMap<String, List<String>>();

        Headings.add("Morgenmad"); Headings.add("Frokost"); Headings.add("Aftensmad"); Headings.add("Snacks");
        L1.add("Gør dig peaked!"); L1.add("Sej morgenmad");
        L2.add("Niiickii"); L2.add("Cool");
        L3.add("LUKAS!"); L3.add("SEJEE"); L3.add("Niiickii"); L3.add("Cool");

        childList.put(Headings.get(0), L1);
        childList.put(Headings.get(1), L2);
        childList.put(Headings.get(2), L3);

        MyAdapter myAdapter = new MyAdapter(getActivity(), Headings, childList);

        expandableListView.setAdapter(myAdapter);

//

        return expandableListView;
    }

// These two methods removes the Action menu in the appbar! 1/2
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    // These two methods removes the Action menu in the appbar! 2/2
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_settings).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }
}