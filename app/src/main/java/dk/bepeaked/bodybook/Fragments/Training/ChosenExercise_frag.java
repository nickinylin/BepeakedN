package dk.bepeaked.bodybook.Fragments.Training;


import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChosenExercise_frag extends Fragment implements View.OnClickListener {

    String[] workouts = {"Bepeaked", "Træningsplan 1", "Træningsplan 2", "Min egen træningsplan", "Træææning!", "Fuck det bliver godt!", "jeg vil ikke mere", "Træning 3", "Træning 4", "Træning 5", "Træning 6"};
    ArrayList<ExerciseDTO> exercises = new ArrayList<ExerciseDTO>();
    FloatingActionButton fab;
    NumberPicker npWeight1, npWeight2, npReps;
    Button btnOK, btnCancel;
    SharedPreferences prefs;

    //skal slettes. til test
    boolean boo = true;


    public ChosenExercise_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chosen_exercise_frag, container, false);

        getActivity().setTitle("Valgte øvelse");

        // making the graph

        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 0.25),
                new DataPoint(0.3, 0.22),
                new DataPoint(0.4, 0.12),
                new DataPoint(0.6, 0.79),
                new DataPoint(2, 1.3)
        });
        // styling series
        series.setTitle("Random Curve 1");
        series.setColor(Color.GREEN);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);

// custom paint to make a dotted line
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
        series.setCustomPaint(paint);
        graph.addSeries(series);

//        Listen af sæt laves herunder. Den skal blot have en arrayliste af ExerciseDTO'er.

        int i = 1;
        exercises.add(new ExerciseDTO(20 + i, 10 + i, 5 + i++));
        exercises.add(new ExerciseDTO(20 + i, 10 + i, 5 + i++));
        exercises.add(new ExerciseDTO(20 + i, 10 + i, 5 + i++));
        exercises.add(new ExerciseDTO(20 + i, 10 + i, 5 + i++));
        exercises.add(new ExerciseDTO(20 + i, 10 + i, 5 + i++));
        exercises.add(new ExerciseDTO(20 + i, 10 + i, 5 + i++));
        exercises.add(new ExerciseDTO(20 + i, 10 + i, 5 + i++));
        exercises.add(new ExerciseDTO(20 + i, 10 + i, 5 + i++));
        exercises.add(new ExerciseDTO(20 + i, 10 + i, 5 + i++));
        exercises.add(new ExerciseDTO(20 + i, 10 + i, 5 + i++));
        exercises.add(new ExerciseDTO(20 + i, 10 + i, 5 + i++));
        exercises.add(new ExerciseDTO(20 + i, 10 + i, 5 + i++));

        ListView listView = (ListView) view.findViewById(R.id.listView_exercise);

        ExerciseListAdapter exerciseListAdapter = new ExerciseListAdapter();

        listView.setAdapter(exerciseListAdapter);

        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton_chosen_exercise);

        fab.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        showDialogAlert();

    }

    private void showDialogAlert() {

        final Dialog dialog = new Dialog(getActivity(), R.style.MyAlertDialogStyle);
        dialog.setTitle("NumberPicker");
        dialog.setContentView(R.layout.fragment_dialog_add_set_frag);
        npReps = (NumberPicker) dialog.findViewById(R.id.NumberPickerReps);
        npWeight1 = (NumberPicker) dialog.findViewById(R.id.NumberPickerWeight1);
        npWeight2 = (NumberPicker) dialog.findViewById(R.id.NumberPickerWeight2);
        btnOK = (Button) dialog.findViewById(R.id.button_ok_add_set);

        //TODO skal sættes til den sidst benyttede, så der skal bruges den der preference manager?
        npReps.setMinValue(1);
        npReps.setMaxValue(50);
        npWeight1.setMinValue(0);
        npWeight1.setMaxValue(200);
        npWeight1.setValue(10);
        npWeight2.setMinValue(0);
        npWeight2.setMaxValue(3);
        npWeight2.setDisplayedValues(new String[]{"0", "25", "50", "75"});




        dialog.show();




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

            weight.setText(Integer.toString(exercises.get(position).getWeight()));
            reps.setText(Integer.toString(exercises.get(position).getReps()));
            rm.setText(Integer.toString(exercises.get(position).getRM1()));

            return convertView;
        }
    }
}
