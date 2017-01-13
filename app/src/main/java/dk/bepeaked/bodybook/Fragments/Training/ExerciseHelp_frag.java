package dk.bepeaked.bodybook.Fragments.Training;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseHelp_frag extends Fragment {


    public ExerciseHelp_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rod = inflater.inflate(R.layout.fragment_exercise_help_frag, container, false);

        ImageView before = (ImageView) rod.findViewById(R.id.ex_imagebefore);
        ImageView after = (ImageView) rod.findViewById(R.id.ex_imageafter);

        before.setImageResource(R.drawable.coming_soon2);
        after.setImageResource(R.drawable.coming_soon2);

        return rod;

    }

}
