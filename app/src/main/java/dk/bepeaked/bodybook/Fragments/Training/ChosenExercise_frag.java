package dk.bepeaked.bodybook.Fragments.Training;


import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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
        Date date = new Date(2015, 10, 12);
        stringDateLast = dateFormatter.format(date);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        exerciseID = getArguments().getInt("chosenExerciseID", 99999);

        exerciseName = wc.getExercise(exerciseID).getName();




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
                new DataPoint(1, 0.25),
                new DataPoint(2, 0.22),
                new DataPoint(3, 0.12),
                new DataPoint(4, 0.79),
                new DataPoint(5, 1.3)
        });
        // styling series
        series.setColor(Color.BLACK);
        series.setThickness(4);
        graph.addSeries(series);

        try {
            realmList = wc.getSetsFromExercise(exerciseID);
        } catch (ExceptionExerciseDoesntExist e) {
            e.printStackTrace();
        }
        realmListSets = new RealmList<>();
        for (int i = realmList.size()-1; i > 0; i--){
            realmListSets.add(realmList.get(i));
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

            Log.d("LUKAS", "datecounter start: " + counter);
            counter++;
            Log.d("LUKAS", "dateCurrent: " + stringDateCurrent);
            Log.d("LUKAS", "dateLatest: " + stringDateLast);

            if (stringDateCurrent.equals(stringDateLast)) {
//                stringDateLast = stringDateCurrent;
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
            int rmInt = (int) Math.round(realmListSets.get(position).getRm());
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
            Date date = new Date(9999, 12, 10);
            stringDateLast = dateFormatter.format(date);
            Log.d("LUKAS", "latest date er nu: " +stringDateLast);
            realmList = wc.getSetsFromExercise(exerciseID);
            realmListSets.clear();
            for (int i = realmList.size()-1; i > 0; i--){
                realmListSets.add(realmList.get(i));
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
