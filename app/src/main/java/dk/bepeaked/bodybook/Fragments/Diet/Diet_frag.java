package dk.bepeaked.bodybook.Fragments.Diet;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dk.bepeaked.bodybook.Backend.DAO.DietDAO;
import dk.bepeaked.bodybook.Backend.DTO.DishDTO;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Diet_frag extends Fragment{

    ExpandableListView expandableListView;
    DietDAO ddao;
    ArrayList<DishDTO> dishList;

    public Diet_frag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet, container, false);

        getActivity().setTitle("kostplan");


        expandableListView = (ExpandableListView)  view.findViewById(R.id.expandA_listview);

        //expandableListView.setChildDivider();
        List<String> Headings = new ArrayList<String>();
        List<String> name1 = new ArrayList<String>();
        List<String> name2 = new ArrayList<String>();
        List<String> name3 = new ArrayList<String>();
        List<String> name4 = new ArrayList<String>();
        List<String> shortDesk1 = new ArrayList<String>();
        List<String> shortDesk2 = new ArrayList<String>();
        List<String> shortDesk3 = new ArrayList<String>();
        List<String> shortDesk4 = new ArrayList<String>();
        HashMap<String, List<String>> nameList = new HashMap<String, List<String>>();
        HashMap<String, List<String>> deskList = new HashMap<String, List<String>>();


        Headings.add("Morgenmad");
        Headings.add("Frokost");
        Headings.add("Aftensmad");
        Headings.add("Snacks");

        dishList = new ArrayList<DishDTO>();
        ddao = (DietDAO) getArguments().getSerializable("DietDAO");
        dishList = ddao.getDTOS();

        for (int i = 0; i < dishList.size(); i++){
            DishDTO dish = dishList.get(i);
            if (dish.getType() == 0){
                name1.add(dish.getName());
                shortDesk1.add(dish.getDeskShort());
            } else if (dish.getType() == 1){
                name2.add(dish.getName());
                shortDesk2.add(dish.getDeskShort());
            } else if (dish.getType() == 2){
                name3.add(dish.getName());
                shortDesk3.add(dish.getDeskShort());
            } else if (dish.getType() == 3){
                name4.add(dish.getName());
                shortDesk4.add(dish.getDeskShort());
            }
        }

        nameList.put(Headings.get(0), name1);
        deskList.put(Headings.get(0), shortDesk1);
        nameList.put(Headings.get(1), name2);
        deskList.put(Headings.get(1), shortDesk2);
        nameList.put(Headings.get(2), name3);
        deskList.put(Headings.get(2), shortDesk3);
        nameList.put(Headings.get(3), name4);
        deskList.put(Headings.get(3), shortDesk4);

        final MyAdapter myAdapter = new MyAdapter(getActivity(), Headings, nameList, deskList);

        expandableListView.setAdapter(myAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id) {

                Object child = myAdapter.getChild(groupPosition, childPosition);
                Object desc = myAdapter.getDesk(groupPosition, childPosition);

                Bundle bundle = new Bundle();

                for(int i = 0; i < dishList.size(); i++){
                    if(dishList.get(i).getName().equals((child.toString()))){
                        bundle.putSerializable("Dish", dishList.get(i));
                    }
                }

                Recipe_frag fragment = new Recipe_frag();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
                fragment.setArguments(bundle);
                fragmentTransaction.commit();
                return true;
            }
        });


        return view;
    }

//    // These two methods removes the Action menu in the appbar! 1/2
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    // These two methods removes the Action menu in the appbar! 2/2
//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.action_settings).setVisible(false);
//        super.onPrepareOptionsMenu(menu);
//    }

    /**
     * Created by Nicki on 18/11/16.
     */

    public static class MyAdapter extends BaseExpandableListAdapter {

        private List<String> header_titles;
        private HashMap<String, List<String>> child_titles;
        private HashMap<String, List<String>> description;
        private Context ctx;

        public MyAdapter(Context ctx, List<String> header_titles, HashMap<String,List<String>> child_titles, HashMap<String,List<String>> deskription) {
            this.ctx = ctx;
            this.child_titles = child_titles;
            this.header_titles = header_titles;
            this.description = deskription;
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
        public Object getDesk(int groupPosition, int childPosition){
            return description.get(header_titles.get(groupPosition)).get(childPosition);
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
//            textView.setTypeface(null, Typeface.BOLD);
            textView.setText(title);


            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            String title = (String) this.getChild(groupPosition, childPosition);
            String desk = (String) this.getDesk(groupPosition, childPosition);

            if(convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.child_layout, null);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.child_item);
            TextView deskView = (TextView) convertView.findViewById(R.id.child_desc);
            textView.setText(title);
            deskView.setText(desk);

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}