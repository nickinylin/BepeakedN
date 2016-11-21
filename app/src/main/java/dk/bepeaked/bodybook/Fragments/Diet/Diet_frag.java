package dk.bepeaked.bodybook.Fragments.Diet;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        View view = inflater.inflate(R.layout.fragment_diet, container, false);

        getActivity().setTitle("Kostplan");


        expandableListView = (ExpandableListView)  view.findViewById(R.id.expandA_listview);

        List<String> Headings = new ArrayList<String>();
        List<String> L1 = new ArrayList<String>();
        List<String> L2 = new ArrayList<String>();
        List<String> L3 = new ArrayList<String>();
        List<String> L4 = new ArrayList<String>();
        HashMap<String, List<String>> childList = new HashMap<String, List<String>>();

        Headings.add("Morgenmad");
        Headings.add("Frokost");
        Headings.add("Aftensmad");
        Headings.add("Snacks");
        L1.add("Gør dig peaked!");
        L1.add("Sej morgenmad");
        L2.add("Niiickii");
        L2.add("Cool");
        L3.add("LUKAS!");
        L3.add("SEJEE");
        L3.add("Niiickii");
        L3.add("Cool");

        childList.put(Headings.get(0), L1);
        childList.put(Headings.get(1), L2);
        childList.put(Headings.get(2), L3);
        childList.put(Headings.get(3), L4);

        MyAdapter myAdapter = new MyAdapter(getActivity(), Headings, childList);

        expandableListView.setAdapter(myAdapter);



        return view;
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

    /**
     * Created by Nicki on 18/11/16.
     */

    public static class MyAdapter extends BaseExpandableListAdapter {

        private List<String> header_titles;
        private HashMap<String, List<String>> child_titles;
        private Context ctx;

        public MyAdapter(Context ctx, List<String> header_titles, HashMap<String,List<String>> child_titles) {
            this.ctx = ctx;
            this.child_titles = child_titles;
            this.header_titles = header_titles;
        }

        @Override
        public int getGroupCount() {
            return header_titles.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return child_titles.get(header_titles.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return header_titles.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child_titles.get(header_titles.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String title =  (String) this.getGroup(groupPosition);
            if (convertView == null){
                LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.parent_layout, null);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.heading_item);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setText(title);


            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            String title = (String) this.getChild(groupPosition, childPosition);

            if(convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.child_layout, null);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.child_item);
            textView.setText(title);

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}