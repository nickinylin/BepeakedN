package dk.bepeaked.bodybook.Fragments;


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

import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Diet_frag extends Fragment {


    public Diet_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        View v = inflater.inflate(R.layout.fragment_diet, null);

        getActivity().setTitle("Kostplan");

//        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.listViewBepeaked);
//        elv.setAdapter(elv);
//        return v;
//
//
//        // Inflate the layout for this fragment
//        ExpandableListAdapter mAdapter = (ExpandableListAdapter) findViewById(R.id.listViewBepeaked);
//        setListAdapter(mAdapter);
//        registerForContextMenu(getExpandableListView());

        return inflater.inflate(R.layout.fragment_diet, container, false);
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

//    public class ListViewBepeaked extends ExpandableListActivity {
//
//        public TextView lavTextView() {
//            TextView textView = new TextView(getActivity());
//
//            // Layout parameters for the ExpandableListView
//            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
//
//            textView.setLayoutParams(lp);
//            // Center the text vertically
//            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
//            // Set the text starting position
//            textView.setPadding(36, 0, 0, 0);
//            return textView;
//        }
//
//        private String[] groups = {"Morgenmad", "Frokost", "Aftensmad", "Snack"};
//        private String[][] children = {
//                {"Arnold", "Barry", "Chuck", "David"},
//                {"Ace", "Bandit", "Cha-Cha", "Deuce"},
//                {"Fluffy", "Snuggles"},
//                {"Goldy", "Bubbles"}
//        };
//
//        public class MyExpandableListAdapter extends BaseExpandableListAdapter {
//
//
//            @Override
//            public int getGroupCount() {
//                return groups.length;
//            }
//
//            @Override
//            public int getChildrenCount(int groupPosition) {
//                return children[groupPosition].length;
//            }
//
//            @Override
//            public Object getGroup(int groupPosition) {
//                return groups[groupPosition];
//            }
//
//            @Override
//            public Object getChild(int groupPosition, int childPosition) {
//                return children[groupPosition][childPosition];
//            }
//
//            @Override
//            public long getGroupId(int groupPosition) {
//                return groupPosition;
//            }
//
//            @Override
//            public long getChildId(int groupPosition, int childPosition) {
//                return childPosition;
//            }
//
//            @Override
//            public boolean hasStableIds() {
//
//                // return true
//                return false;
//            }
//
//            @Override
//            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//                return null;
//            }
//
//            @Override
//            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//                TextView textView = lavTextView();
//                textView.setText(getChild(groupPosition, childPosition).toString());
//                return textView;
//            }
//
//            @Override
//            public boolean isChildSelectable(int groupPosition, int childPosition) {
//                return true;
//            }
//        }
//    }
}