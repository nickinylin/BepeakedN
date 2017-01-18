package dk.bepeaked.bodybook.Fragments.Training;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TreeSet;

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

    RealmList<SetDTO> realmList;
    RealmList<SetDTO> realmListSets = new RealmList<>();
    FloatingActionButton fab;
    SharedPreferences prefs;
    Bundle bundleArgs;
    String exerciseName;
    int exerciseID;
    WorkoutController wc;
    Date dateCurrent;
    SimpleDateFormat dateFormatter;
    String stringDateLast, stringDateCurrent, kgPunds;
    Singleton singleton;
    boolean measurement;
    Date date;
    GraphView graph;
    ArrayList<DataPoint> points;
    BarGraphSeries<DataPoint> series;
    ArrayList<Integer> rms;
    int numberBars = 5;
    ListView listView;


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
        date = new Date(0, 0, 0);
        stringDateLast = dateFormatter.format(date);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        measurement = prefs.getBoolean("measurement", false);
        exerciseID = getArguments().getInt("chosenExerciseID", 99999);

        //This is for making the graph

        exerciseName = wc.getExercise(exerciseID).getName();
        try {
            realmList = wc.getSetsFromExercise(exerciseID);
        } catch (ExceptionExerciseDoesntExist e) {
            e.printStackTrace();
        }
        for (int i = realmList.size(); i > realmList.size() - 10; i--) {
            if (i > 0) {
                realmListSets.add(realmList.get(i - 1));
            }
        }

        graph = (GraphView) view.findViewById(R.id.graph);
        graph.getGridLabelRenderer().setGridColor(BLACK);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getGridLabelRenderer().setVerticalAxisTitle("1RM");
        graph.getGridLabelRenderer().setVerticalAxisTitleTextSize(45);
        graph.getGridLabelRenderer().setLabelVerticalWidth(60);
        graph.getGridLabelRenderer().setVerticalLabelsColor(BLACK);
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(BLACK);
        graph.getGridLabelRenderer().setHorizontalAxisTitle(" ");
        graph.getGridLabelRenderer().setHorizontalAxisTitleTextSize(55);
        graph.getGridLabelRenderer().setHorizontalAxisTitleColor(BLACK);
//        graph.getGridLabelRenderer().setHorizontalLabelsColor(BLACK);
        graph.getGridLabelRenderer().setVerticalLabelsVisible(true);

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                // return as Integer
                return "" + ((int) value);
            }
        });

        graph.getLegendRenderer().setBackgroundColor(BLACK);

        graph.getViewport().setBorderColor(WHITE);
        graph.getViewport().setDrawBorder(true);
        graph.getViewport().setScalable(false);
        graph.getViewport().setScalableY(false);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(20);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        rms = new ArrayList<>();
        if(measurement) {
            for (int i = 0; i < realmList.size(); i++) {
                rms.add((int) convertKilo(realmList.get(i).getRm()));
            }
        }else{
            for (int i = 0; i < realmList.size(); i++) {
                rms.add((int) realmList.get(i).getRm());
            }
        }
        if (rms.size() > 0) {
            graph.getViewport().setMaxY(Collections.max(rms) + 2);
            numberBars = rms.size();
        } else {
            graph.getViewport().setMaxY(5);
        }

        points = new ArrayList<>();
        if(measurement){
            for (int i = 0; i < realmList.size(); i++) {
                points.add(new DataPoint(i, convertKilo(realmList.get(i).getRm())));
            }
        }else{
            for (int i = 0; i < realmList.size(); i++) {
                points.add(new DataPoint(i, realmList.get(i).getRm()));
            }
        }
        points.add(new DataPoint(points.size(), 0));

        DataPoint[] pointsArray = points.toArray(new DataPoint[points.size()]);
        series = new BarGraphSeries<>(pointsArray);

        // styling series
        series.setColor(Color.parseColor("#059ea0"));
        series.setAnimated(true);
        series.setDataWidth(0.8);
        graph.addSeries(series);
        if (numberBars > 20) {
            graph.getViewport().scrollToEnd();
        }

        //This is for making the listView of the sets

        listView = (ListView) view.findViewById(R.id.LW_chosenExercise);
        listView.setOnItemLongClickListener(this);

        reloadData();


        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton_add_set);

        fab.setOnClickListener(this);

        return view;
    }

    private void reloadData() {

        try {
            realmList = wc.getSetsFromExercise(exerciseID);
        } catch (ExceptionExerciseDoesntExist e) {
            e.printStackTrace();
        }
        points.clear();
        if(measurement){
            for (int i = 0; i < realmList.size(); i++) {
                points.add(new DataPoint(i, convertKilo(realmList.get(i).getRm())));
            }
        }else{
            for (int i = 0; i < realmList.size(); i++) {
                points.add(new DataPoint(i, realmList.get(i).getRm()));
            }
        }
        points.add(new DataPoint(points.size(), 0));
        DataPoint[] pointsArray = points.toArray(new DataPoint[points.size()]);
        if (points.size() > 0) {
            if(measurement) {
                for (int i = 0; i < realmList.size(); i++) {
                    rms.add((int) convertKilo(realmList.get(i).getRm()));
                }
            }else{
                for (int i = 0; i < realmList.size(); i++) {
                    rms.add((int) realmList.get(i).getRm());
                }
            }
            graph.getViewport().setMaxY(Collections.max(rms) + 2);
            numberBars = points.size();
        } else {
            graph.getViewport().setMaxY(5);
        }

        series.resetData(pointsArray);

        if (numberBars > 20) {
            graph.getViewport().scrollToEnd();
        }

        realmListSets.clear();
        for (int i = realmList.size(); i > realmList.size() - 30; i--) {
            if (i > 0) {
                realmListSets.add(realmList.get(i - 1));
            }
        }

        ExerciseListAdapter exerciseListAdapter = new ExerciseListAdapter();
        stringDateLast = dateFormatter.format(date);
        for (int i = 0; i < realmListSets.size(); i++) {
            dateCurrent = realmListSets.get(i).getDate();
            stringDateCurrent = dateFormatter.format(dateCurrent);
            if (stringDateCurrent.equals(stringDateLast)) {
                exerciseListAdapter.addItem(realmListSets.get(i));
                stringDateLast = stringDateCurrent;
            } else {
                exerciseListAdapter.addSeparatorItem(realmListSets.get(i));
                stringDateLast = stringDateCurrent;
            }
        }
        listView.setAdapter(exerciseListAdapter);
    }

    private double convertKilo(double kilo) {
        double d = kilo * 2.2046;
        return d;
    }

    @Override
    public void onClick(View v) {
        showDialogAlert();
    }

    private void showDialogAlert() {
        bundleArgs = new Bundle();
        bundleArgs.putInt("chosenExerciseID", exerciseID);
        DialogAddSet_frag dialog = new DialogAddSet_frag();
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
        bundleArgs.putInt("setID", realmListSets.get(position).getId());

        DialogDeleteSet_frag dialog = new DialogDeleteSet_frag();
        dialog.setArguments(bundleArgs);
        dialog.show(getFragmentManager(), "DialogDeleteSet_frag");
        return false;
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    public class ExerciseListAdapter extends BaseAdapter {

        private static final int TYPE_noDate = 0;
        private static final int TYPE_Date = 1;
        private static final int TYPE_MAX_COUNT = TYPE_Date + 1;

        private RealmList<SetDTO> mData = new RealmList<SetDTO>();
        private LayoutInflater mInflater;

        private TreeSet mSeparatorsSet = new TreeSet();

        public ExerciseListAdapter() {
            mInflater = (LayoutInflater) getActivity().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        }

        public void addItem(final SetDTO item) {
            mData.add(item);
            notifyDataSetChanged();
        }

        public void addSeparatorItem(final SetDTO item) {
            mData.add(item);
            // save separator position
            mSeparatorsSet.add(mData.size() - 1);
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return mSeparatorsSet.contains(position) ? TYPE_Date : TYPE_noDate;
        }

        @Override
        public int getViewTypeCount() {
            return TYPE_MAX_COUNT;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public SetDTO getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder1 holder = null;
            int type = getItemViewType(position);
            if (convertView == null) {
                holder = new ViewHolder1();
                switch (type) {
                    case TYPE_noDate:
                        convertView = mInflater.inflate(R.layout.exercise_list_element, null);
                        holder.textViewWeight = (TextView) convertView.findViewById(R.id.tv_exercise_weight);
                        holder.textViewReps = (TextView) convertView.findViewById(R.id.tv_exercise_reps);
                        holder.textViewRM = (TextView) convertView.findViewById(R.id.tv_exercise_rm);
                        break;
                    case TYPE_Date:
                        convertView = mInflater.inflate(R.layout.exercise_list_element_with_date, null);
                        holder.textViewWeight = (TextView) convertView.findViewById(R.id.tv_exercise_weight);
                        holder.textViewReps = (TextView) convertView.findViewById(R.id.tv_exercise_reps);
                        holder.textViewRM = (TextView) convertView.findViewById(R.id.tv_exercise_rm);
                        holder.textViewDate = (TextView) convertView.findViewById(R.id.tv_exercise_date);
                        break;
                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder1) convertView.getTag();
            }

            double weightInt = mData.get(position).getWeight();
            int reps = mData.get(position).getReps();
            stringDateCurrent = dateFormatter.format(mData.get(position).getDate());
            double rmInt = mData.get(position).getRm();
            if (measurement) {
                weightInt = convertKilo(weightInt);
                rmInt = convertKilo(rmInt);
                kgPunds = " lbs";
            } else {
                kgPunds = " kg";
            }

            switch (type) {
                case TYPE_noDate:
                    holder.textViewWeight.setText((int) weightInt + kgPunds);
                    holder.textViewReps.setText(reps + "");
                    holder.textViewRM.setText(Math.round(rmInt) + kgPunds);
                    break;
                case TYPE_Date:
                    holder.textViewWeight.setText((int) weightInt + kgPunds);
                    holder.textViewReps.setText(reps + "");
                    holder.textViewRM.setText(Math.round(rmInt) + kgPunds);
                    holder.textViewDate.setText(stringDateCurrent);
                    break;
            }

            return convertView;
        }
    }

    public static class ViewHolder1 {
        public TextView textViewWeight;
        public TextView textViewReps;
        public TextView textViewRM;
        public TextView textViewDate;
    }


    //This is to reload the listView when a dialogFragment is dismissed.
    @Override
    public void run() {
        reloadData();
    }
}
