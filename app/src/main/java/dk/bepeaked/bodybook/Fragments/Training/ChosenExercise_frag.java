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

    RealmList<SetDTO> realmList;
    RealmList<SetDTO> realmListSets;
    FloatingActionButton fab;
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
    int counter = 0;
    boolean measurement;
    int weightInt;
    int reps;
    int rmInt;


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
        Date date = new Date(0, 0, 0);
        stringDateLast = dateFormatter.format(date);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        exerciseID = getArguments().getInt("chosenExerciseID", 99999);

        exerciseName = wc.getExercise(exerciseID).getName();
        try {
            realmList = wc.getSetsFromExercise(exerciseID);
        } catch (ExceptionExerciseDoesntExist e) {
            e.printStackTrace();
        }

        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        graph.getGridLabelRenderer().setGridColor(BLACK);
        graph.getGridLabelRenderer().setVerticalAxisTitle("1RM");
        graph.getGridLabelRenderer().setVerticalAxisTitleTextSize(35);
        graph.getGridLabelRenderer().setLabelVerticalWidth(50);
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

        graph.getLegendRenderer().setBackgroundColor(BLACK);

        graph.getViewport().setBorderColor(WHITE);
        graph.getViewport().setDrawBorder(true);
        graph.getViewport().setScalable(false);
        graph.getViewport().setScalableY(false);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(10);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(18+5);

        DataPoint[] points = new DataPoint[realmList.size()];
        for (int i = 0; i < realmList.size(); i++){
            points[i] = new DataPoint(i, realmList.get(i).getRm());
        }

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(points);

        // styling series
        series.setColor(Color.parseColor("#059ea0"));
        series.setAnimated(true);
        series.setDataWidth(0.8);
        graph.addSeries(series);

        graph.getViewport().scrollToEnd();

        try {
            realmList = wc.getSetsFromExercise(exerciseID);
            Log.d("Nicki", "sets: " +realmList.size());
        } catch (ExceptionExerciseDoesntExist e) {
            e.printStackTrace();
        }

        realmListSets = new RealmList<>();
        int j = 0;

        for (int i = realmList.size(); i > realmList.size()-10 ; i--) {
            if (i > 0) {
                realmListSets.add(realmList.get(i-1));
            }
        }

        for (int i = 0; i < realmListSets.size(); i++) {
            Log.d("Nicki", "realmListSets: " + realmListSets.get(i).getId());
        }

        listView = (ListView) view.findViewById(R.id.LW_chosenExercise);
        ExerciseListAdapter exerciseListAdapter = new ExerciseListAdapter();
        listView.setOnItemLongClickListener(this);
        listView.setAdapter(exerciseListAdapter);

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
//        Log.d("Nicki", "onItemLongClick: " + realmListSets.get(position).getId());
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

        private LayoutInflater mInflater;

        public ExerciseListAdapter() {
            mInflater = (LayoutInflater) getActivity().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        }

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
//            Log.d("Nicki", "datecurrent: " + dateCurrent + "\n dateLast: " + stringDateLast);

            String stringDateCurrent = dateFormatter.format(dateCurrent);
//            String stringDateLast = dateFormatter.format(dateLast);
//            Log.d("Nicki", "stringdatecurrent: " + stringDateCurrent + "\n stringdateLast: " + stringDateLast);

            ViewHolder1 holder = null;
            if (convertView == null) {
                if (stringDateCurrent.equals(stringDateLast)) {
                    convertView = mInflater.inflate(R.layout.exercise_list_element, null);
                    holder = new ViewHolder1();
                    holder.textViewWeight = (TextView) convertView.findViewById(R.id.tv_exercise_weight);
                    holder.textViewReps = (TextView) convertView.findViewById(R.id.tv_exercise_reps);
                    holder.textViewRM = (TextView) convertView.findViewById(R.id.tv_exercise_rm);

                } else {
                    convertView = mInflater.inflate(R.layout.exercise_list_element_with_date, null);
                    holder = new ViewHolder1();
                    holder.textViewWeight = (TextView) convertView.findViewById(R.id.tv_exercise_weight);
                    holder.textViewReps = (TextView) convertView.findViewById(R.id.tv_exercise_reps);
                    holder.textViewRM = (TextView) convertView.findViewById(R.id.tv_exercise_rm);
                    holder.textViewDate = (TextView) convertView.findViewById(R.id.tv_exercise_date);

                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder1) convertView.getTag();

            }

            boolean measurement = prefs.getBoolean("measurement", false);
            int weightInt = (int) realmListSets.get(position).getWeight();
            int reps = realmListSets.get(position).getReps();
            Log.d("Nicki", "position: " + position);
            int rmInt = (int) Math.round(realmListSets.get(position).getRm());
            if (measurement) {
                weightInt = convertKilo(weightInt);
                rmInt = convertKilo(rmInt);
            }
            holder.textViewWeight.setText(weightInt + "kg");
            holder.textViewReps.setText(reps + "");
            holder.textViewRM.setText(rmInt + "kg");
            if (!stringDateCurrent.equals(stringDateLast)) {
                stringDateLast = stringDateCurrent;
                Log.d("Nicki", "StringdateCurrent: " +stringDateCurrent);
                holder.textViewDate.setText(stringDateCurrent + "");
            } else {
                stringDateLast = stringDateCurrent;
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

    private void adapterReload() {
        try {
            Date date = new Date(9999, 12, 10);
            stringDateLast = dateFormatter.format(date);
//            Log.d("LUKAS", "latest date er nu: " + stringDateLast);
            realmList = wc.getSetsFromExercise(exerciseID);
            realmListSets.clear();
            for (int i = realmList.size(); i > realmList.size()-10; i--) {
                realmListSets.add(realmList.get(i-1));
            }
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
