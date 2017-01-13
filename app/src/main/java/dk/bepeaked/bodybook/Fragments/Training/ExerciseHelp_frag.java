package dk.bepeaked.bodybook.Fragments.Training;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseHelp_frag extends Fragment {

    int exerciseID;
    WorkoutController wc;
    String exerciseName;
    String exerciseDesc1;
    String exerciseDesc2;

    public ExerciseHelp_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rod = inflater.inflate(R.layout.fragment_exercise_help_frag, container, false);
        wc = new WorkoutController();

        exerciseID = getArguments().getInt("chosenExerciseID", 99999);

        exerciseName = wc.getExercise(exerciseID).getName();
        exerciseDesc1 = wc.getExercise(exerciseID).getDesc1();
        exerciseDesc2= wc.getExercise(exerciseID).getDesc2();

        TextView description = (TextView) rod.findViewById(R.id.ex_descriptiontext);
        description.setText(exerciseName + " \n " +  " \n" + exerciseDesc1 +" \n " +  " \n" + exerciseDesc2);


        ImageView before = (ImageView) rod.findViewById(R.id.ex_imagebefore);
        ImageView after = (ImageView) rod.findViewById(R.id.ex_imageafter);

        before.setImageResource(R.drawable.coming_soon2);
        after.setImageResource(R.drawable.coming_soon2);



        return rod;

    }

}
