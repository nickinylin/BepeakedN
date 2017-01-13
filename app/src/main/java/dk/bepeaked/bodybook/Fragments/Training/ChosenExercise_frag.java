package dk.bepeaked.bodybook.Fragments.Training;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.SetDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionExerciseDoesntExist;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChosenExercise_frag extends Fragment implements View.OnClickListener, View.OnLongClickListener, AdapterView.OnItemLongClickListener, Runnable {

    RealmList<SetDTO> realmListSets;
    FloatingActionButton fab;
    NumberPicker npWeight1, npWeight2, npReps;
    Button btnOK, btnCancel;
    SharedPreferences prefs;
    ExerciseDTO dto;
    Bundle bundleArgs;
    String exerciseName;
    int exerciseID;
    WorkoutController wc;
    Date dateLast, dateCurrent;
    SimpleDateFormat dateFormatter;
    String stringDateLast;
    Singleton singleton;


    //skal slettes. til test
    boolean boo = true;
    private ListView listView;


    public ChosenExercise_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chosen_exercise_frag, container, false);
        singleton = Singleton.singleton;
        singleton.listen(this);
        wc = new WorkoutController();
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        stringDateLast = dateFormatter.format(new Date(2015, 10, 12));
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        exerciseID = getArguments().getInt("chosenExerciseID", 99999);

        exerciseName = wc.getExercise(exerciseID).getName();
        Log.d("Nicki", "CHOSENE - exerciseID" + exerciseID + " NAVN " + exerciseName);

        getActivity().setTitle(exerciseName);


        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        graph.getGridLabelRenderer().setGridColor(BLACK);
        graph.getGridLabelRenderer().setVerticalAxisTitle("1RM");
        graph.getGridLabelRenderer().setVerticalAxisTitleTextSize(35);
        graph.getGridLabelRenderer().setLabelVerticalWidth(20);
        graph.getGridLabelRenderer().setVerticalLabelsColor(BLACK);
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(BLACK);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Dage");
        graph.getGridLabelRenderer().setHorizontalAxisTitleTextSize(35);
        graph.getGridLabelRenderer().setHorizontalAxisTitleColor(BLACK);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(BLACK);
        graph.getGridLabelRenderer().setVerticalLabelsVisible(true);

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                // return as Integer
                return "" + ((int) value);
            }
        });

        graph.getLegendRenderer().setBackgroundColor(WHITE);


        graph.getViewport().setBorderColor(BLACK);
        graph.getViewport().setDrawBorder(true);
        graph.getViewport().setScalable(true);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 0.25),
                new DataPoint(0.3, 0.22),
                new DataPoint(0.4, 0.12),
                new DataPoint(0.6, 0.79),
                new DataPoint(2, 1.3)
        });
        // styling series
        series.setColor(Color.BLACK);
        series.setThickness(4);
        graph.addSeries(series);

        try {
            realmListSets = wc.getSetsFromExercise(exerciseID);
        } catch (ExceptionExerciseDoesntExist e) {
            e.printStackTrace();
        }
//        Collections.reverse(realmListSets);

//        listView = (SwipeMenuListView) view.findViewById(R.id.SwipeListView_chosen_exercise);

        listView = (ListView) view.findViewById(R.id.LW_chosenExercise);
        ExerciseListAdapter exerciseListAdapter = new ExerciseListAdapter();
        listView.setOnItemLongClickListener(this);
        listView.setAdapter(exerciseListAdapter);

//        SwipeMenuCreator creator = new SwipeMenuCreator() {
//
//            @Override
//            public void create(SwipeMenu menu) {
//                // create "delete" item
//                SwipeMenuItem deleteItem = new SwipeMenuItem(
//                        getActivity().getApplicationContext());
//                // set item width
//                deleteItem.setWidth(900);
//                deleteItem.setTitle("SLET");
//                // set a icon
//                deleteItem.setIcon(R.drawable.ic_delete_forever_white_24dp);
//                // add to menu
//                menu.addMenuItem(deleteItem);
//            }
//        };
//
//// set creator
//
//        listView.setMenuCreator(creator);
//
//        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//                switch (index) {
//                    case 0:
//                        //delete
////                        bundleArgs.putString("planName", planName);
////                        bundleArgs.putString("pasName", pasName);
////                        bundleArgs.putString("exerciseName", realmListExercises.get(position).getName());
//
//                        DialogDeleteExerciseFromPas_frag dialog = new DialogDeleteExerciseFromPas_frag();
//                        dialog.setArguments(bundleArgs);
//                        dialog.show(getFragmentManager(), "Empty_pas");
//
//                        break;
//                }
//                // false : close the menu; true : not close the menu
//                return false;
//            }
//        });
//
//        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
//        listView.setCloseInterpolator(new BounceInterpolator());

        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton_add_set);

        fab.setOnClickListener(this);

        return view;
    }

    private int convertKilo(int kilo) {
        double d = kilo * 2.2046;
        int pounds = (int) d;
        return pounds;
    }

    @Override
    public void onClick(View v) {
        showDialogAlert();
    }

    private void showDialogAlert() {
        bundleArgs = new Bundle();
        bundleArgs.putInt("chosenExerciseID", exerciseID);
        DialogAddSet_frag dialog = new DialogAddSet_frag();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                adapterReload();
            }
        });
        dialog.setArguments(bundleArgs);
        dialog.show(getActivity().getFragmentManager(), "empty");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        singleton.unRegistrer(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        bundleArgs = new Bundle();
        Log.d("Nicki", "onItemLongClick: " + realmListSets.get(position).getId());
        bundleArgs.putInt("setID", realmListSets.get(position).getId());

        DialogDeleteSet_frag dialog = new DialogDeleteSet_frag();
        dialog.setArguments(bundleArgs);
        dialog.show(getFragmentManager(), "Empty_pas");
        return false;
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    public class ExerciseListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return realmListSets.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            dateCurrent = realmListSets.get(position).getDate();
            String stringDateCurrent = dateFormatter.format(dateCurrent);
//            String stringDateLast = dateFormatter.format()


            if (stringDateCurrent.equals(stringDateLast)) {
                stringDateLast = stringDateCurrent;
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.exercise_list_element, parent, false);
            } else {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.exercise_list_element_with_date, parent, false);
                TextView tvDate = (TextView) convertView.findViewById(R.id.tv_exercise_date);
                tvDate.setText(stringDateCurrent);
                stringDateLast = stringDateCurrent;
            }
            TextView weight = (TextView) convertView.findViewById(R.id.tv_exercise_weight);
            TextView reps = (TextView) convertView.findViewById(R.id.tv_exercise_reps);
            TextView rm = (TextView) convertView.findViewById(R.id.tv_exercise_rm);
            TextView date = (TextView) convertView.findViewById(R.id.tv_exercise_date);

            boolean measurement = prefs.getBoolean("measurement", false);
            int weightInt = (int) realmListSets.get(position).getWeight();
            int rmInt = (int) realmListSets.get(position).getRm();
            if (measurement) {
                weightInt = convertKilo(weightInt);
                rmInt = convertKilo(rmInt);
            }
            weight.setText(weightInt + "kg");
            reps.setText(Integer.toString(realmListSets.get(position).getReps()));
            rm.setText(rmInt + "kg");


            return convertView;
        }
    }

    private void adapterReload() {
        try {
            realmListSets = wc.getSetsFromExercise(exerciseID);
        } catch (ExceptionExerciseDoesntExist e) {
            e.printStackTrace();
        }
        ExerciseListAdapter exerciseListAdapter = new ExerciseListAdapter();
        listView.setAdapter(exerciseListAdapter);
    }

    @Override
    public void run() {
        adapterReload();
    }
}
