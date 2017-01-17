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
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
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
    String stringDateLast, stringDateCurrent;
    Singleton singleton;
    int counter = 0;
    boolean measurement;
    int weightInt;
    int reps;
    int rmInt;
    Date date;
//    ExerciseListAdapter exerciseListAdapter;


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
        date = new Date(0, 0, 0);
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
            Log.d("Nicki", "sets: " +realmList.size());
        } catch (ExceptionExerciseDoesntExist e) {
            e.printStackTrace();
        }

        realmListSets = new RealmList<>();

        for (int i = realmList.size(); i > realmList.size()-10 ; i--) {
            if (i > 0) {
                realmListSets.add(realmList.get(i-1));
            }
        }

       ExerciseListAdapter exerciseListAdapter = new ExerciseListAdapter();
        stringDateLast = dateFormatter.format(date);
        for (int i = 0; i < realmListSets.size(); i++) {
            dateCurrent = realmListSets.get(i).getDate();
            stringDateCurrent = dateFormatter.format(dateCurrent);
//            stringDateLast = dateFormatter.format(dateLast);
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


//
//            Log.d("Nicki", "datecurrent: " + dateCurrent + "\n dateLast: " + stringDateLast);

//
//            Log.d("Nicki", "stringdatecurrent: " + stringDateCurrent + "\n stringdateLast: " + stringDateLast);


            ViewHolder1 holder = null;
            int type = getItemViewType(position);

            System.out.println("getView " + position + " " + convertView + " type = " + type);

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
                holder = (ViewHolder1)convertView.getTag();
            }

            boolean measurement = prefs.getBoolean("measurement", false);
            int weightInt = (int) mData.get(position).getWeight();
            int reps = mData.get(position).getReps();
//            stringDateCurrent = ;
            Log.d("Nicki", "position: " + position);
            int rmInt = (int) Math.round(mData.get(position).getRm());
            if (measurement) {
                weightInt = convertKilo(weightInt);
                rmInt = convertKilo(rmInt);
            }

            switch (type) {
                case TYPE_noDate:
                    holder.textViewWeight.setText(weightInt + "kg");
                    holder.textViewReps.setText(reps + "");
                    holder.textViewRM.setText(rmInt + "kg");
                    break;
                case TYPE_Date:
                    holder.textViewWeight.setText(weightInt + "kg");
                    holder.textViewReps.setText(reps + "");
                    holder.textViewRM.setText(rmInt + "kg");
                    holder.textViewDate.setText(dateFormatter.format(mData.get(position).getDate().toString()));
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

//    private void adapterReload() {
//        try {
//            Date date = new Date(9999, 12, 10);
//            stringDateLast = dateFormatter.format(date);
////            Log.d("LUKAS", "latest date er nu: " + stringDateLast);
//            realmList = wc.getSetsFromExercise(exerciseID);
//            realmListSets.clear();
//            for (int i = realmList.size(); i > realmList.size()-10; i--) {
//                realmListSets.add(realmList.get(i-1));
//            }
//        } catch (ExceptionExerciseDoesntExist e) {
//            e.printStackTrace();
//        }
//        ExerciseListAdapter exerciseListAdapter = new ExerciseListAdapter();
//        listView.setAdapter(exerciseListAdapter);
//    }

    @Override
    public void run() {
        reloadData();
//        adapterReload();
    }
}
