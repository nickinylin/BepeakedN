package dk.bepeaked.bodybook.Fragments.Training;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.SetDTO;
import dk.bepeaked.bodybook.R;
import io.realm.RealmList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChosenExercise_frag extends Fragment implements View.OnClickListener {

    RealmList<SetDTO> exercises = new RealmList<SetDTO>();
    Button fab;
    NumberPicker npWeight1, npWeight2, npReps;
    Button btnOK, btnCancel;
    SharedPreferences prefs;
    ExerciseDTO dto;
    Bundle bundleArgs;
    String argument;

    //skal slettes. til test
    boolean boo = true;


    public ChosenExercise_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chosen_exercise_frag, container, false);


        argument = getArguments().getString("chosenExerciseName");


        getActivity().setTitle(argument);


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

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                // return as Integer
                return ""+((int) value);
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

//        Listen af sæt laves herunder. Den skal blot have en arrayliste af ExerciseDTO'er.

        dto = new ExerciseDTO("Chestpress", "Skub med bryst", "", "", "", exercises);
//        dto.addSet(new SetDTO(29, 10, "12-12-2016", 30));
//        dto.addSet(new SetDTO(2, 10, "12-12-2016", 30));
//        dto.addSet(new SetDTO(20, 10, "12-12-2016", 30));
//        dto.addSet(new SetDTO(20, 10, "12-12-2016", 30));
//        dto.addSet(new SetDTO(20, 10, "12-12-2016", 30));
//        dto.addSet(new SetDTO(20, 10, "12-12-2016", 30));
//        dto.addSet(new SetDTO(20, 10, "12-12-2016", 30));
//        dto.addSet(new SetDTO(20, 10, "12-12-2016", 30));
//        dto.addSet(new SetDTO(20, 10, "12-12-2016", 30));
//        exercises = dto.getSet();


        ListView listView = (ListView) view.findViewById(R.id.listView_exercise);

        ExerciseListAdapter exerciseListAdapter = new ExerciseListAdapter();

        listView.setAdapter(exerciseListAdapter);

        fab = (Button) view.findViewById(R.id.Button_add_new_set);

        fab.setOnClickListener(this);

        setHasOptionsMenu(true);

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
        bundleArgs.putString("chosenExerciseName", argument);
        DialogAddSet_frag dialog = new DialogAddSet_frag();
        dialog.setArguments(bundleArgs);
        dialog.show(getActivity().getFragmentManager(), "empty");

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.exercisemenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.exerciseMenu_help) {
            //TODO hvad der skal ske når man vil have hjælp til øvelse

            Bundle i = new Bundle();
            i.putString("ExerciseHelp", "den valgte øvelse!");

            ExerciseHelp_frag fragment = new ExerciseHelp_frag();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("hej");
            fragment.setArguments(i);
            fragmentTransaction.commit();

            Snackbar.make(getView(), "hrrra", Snackbar.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.exerciseMenu_edit) {
            // TODO Hvad der skal ske for at ændre bundleArgs en øvelse
        }
        return super.onOptionsItemSelected(item);
    }

    public class ExerciseListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return exercises.size();
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
            if (boo) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.exercise_list_element, parent, false);
                boo = false;
            } else if (!boo) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.exercise_list_element_with_date, parent, false);
                boo = true;
            }
            TextView weight = (TextView) convertView.findViewById(R.id.tv_exercise_weight);
            TextView reps = (TextView) convertView.findViewById(R.id.tv_exercise_reps);
            TextView rm = (TextView) convertView.findViewById(R.id.tv_exercise_rm);
            TextView date = (TextView) convertView.findViewById(R.id.tv_exercise_date);

            weight.setText(exercises.get(position).getWeight() + "kg");
            reps.setText(Integer.toString(exercises.get(position).getReps()));
            rm.setText(exercises.get(position).getRm() + "kg");


//            weight.setText(exercises.get(position).getSet().get(0).getWeight() + "kg");
//            reps.setText(Integer.toString(exercises.get(position).getSet().get(0).getReps()));
//            rm.setText(exercises.get(position).getSet().get(0).getRm() + "kg");

            return convertView;
        }
    }
}