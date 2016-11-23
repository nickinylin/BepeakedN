package dk.bepeaked.bodybook.Fragments.Training;


import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
public class ChosenExercise_frag extends Fragment {

    String[] workouts = {"Bepeaked", "Træningsplan 1", "Træningsplan 2", "Min egen træningsplan", "Træææning!", "Fuck det bliver godt!", "jeg vil ikke mere", "Træning 3", "Træning 4", "Træning 5", "Træning 6"};

    ArrayList<ExerciseDTO> exercises = new ArrayList<ExerciseDTO>();


    public ChosenExercise_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chosen_exercise_frag, container, false);

        getActivity().setTitle("Valgte øvelse");
int i = 1;
        exercises.add(new ExerciseDTO(20+i, 10+i, 5+i++));
        exercises.add(new ExerciseDTO(20+i, 10+i, 5+i++));
        exercises.add(new ExerciseDTO(20+i, 10+i, 5+i++));
        exercises.add(new ExerciseDTO(20+i, 10+i, 5+i++));
        exercises.add(new ExerciseDTO(20+i, 10+i, 5+i++));
        exercises.add(new ExerciseDTO(20+i, 10+i, 5+i++));



//        ExerciseListAdapter adapter = new ExerciseListAdapter(getActivity(), R.layout.exercise_list_element, exercises );



//        ListView listView = (ListView) view.findViewById(R.id.listView_exercise);
////        listView.setOnItemClickListener(this);
//        listView.setAdapter(adapter);

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


        return view;
    }

//    public class ExerciseListAdapter extends ArrayAdapter<ExerciseDTO> {
//
//
//        public ExerciseListAdapter(Context context, int resource, ArrayList<ExerciseDTO> objects) {
//            super(context, resource, objects);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View view = super.getView(position, convertView, parent);
//
//            TextView weight = (TextView) view.findViewById(R.id.tv_exercise_weight);
//            TextView reps = (TextView) view.findViewById(R.id.tv_exercise_reps);
//            TextView rm = (TextView) view.findViewById(R.id.tv_exercise_rm);
//
//            weight.setText(exercises.get(position).getWeight());
//            reps.setText(exercises.get(position).getReps());
//            rm.setText(exercises.get(position).getRM1());
//
//            return view;
//
//        }
//    }



}
