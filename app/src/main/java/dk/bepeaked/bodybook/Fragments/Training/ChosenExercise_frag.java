package dk.bepeaked.bodybook.Fragments.Training;


import android.app.Dialog;
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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.SetDTO;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChosenExercise_frag extends Fragment implements View.OnClickListener {

    ArrayList<ExerciseDTO> exercises = new ArrayList<ExerciseDTO>();
    Button fab;
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

        ExerciseDTO dto = new ExerciseDTO("Chestpress", "Skub med bryst");
        dto.addSet(new SetDTO(20, 10, "12-12-2016", 30));
        exercises.add(dto);

        ListView listView = (ListView) view.findViewById(R.id.listView_exercise);

        ExerciseListAdapter exerciseListAdapter = new ExerciseListAdapter();

        listView.setAdapter(exerciseListAdapter);

        fab = (Button) view.findViewById(R.id.Button_add_new_set);

        fab.setOnClickListener(this);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onClick(View v) {
        showDialogAlert();
    }

    private void showDialogAlert() {

//AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle );

        final Dialog dialog = new Dialog(getActivity(), R.style.MyAlertDialogStyle);
        dialog.setTitle("NumberPicker");
        dialog.setContentView(R.layout.fragment_dialog_add_set_frag);
        npReps = (NumberPicker) dialog.findViewById(R.id.NumberPickerReps);
        npWeight1 = (NumberPicker) dialog.findViewById(R.id.NumberPickerWeight1);
        npWeight2 = (NumberPicker) dialog.findViewById(R.id.NumberPickerWeight2);
        btnOK = (Button) dialog.findViewById(R.id.button_dialog_OK);

        //TODO skal sættes til den sidst benyttede, så der skal bruges den der sharedpreferences
        npReps.setMinValue(1);
        npReps.setMaxValue(50);
//        npReps.setValue();
        npWeight1.setMinValue(0);
        npWeight1.setMaxValue(200);
        npWeight1.setValue(10);
        npWeight2.setMinValue(0);
        npWeight2.setMaxValue(3);
        npWeight2.setDisplayedValues(new String[]{"0", "25", "50", "75"});

        dialog.show();

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
            // TODO Hvad der skal ske for at ændre i en øvelse
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

            weight.setText(exercises.get(position).getSet().get(0).getWeight() + "kg");
            reps.setText(Integer.toString(exercises.get(position).getSet().get(0).getReps()));
            rm.setText(exercises.get(position).getSet().get(0).getRm() + "kg");

            return convertView;
        }
    }
}