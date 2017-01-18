package dk.bepeaked.bodybook.Fragments.Training;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.VolleyImageHelper;
import dk.bepeaked.bodybook.Backend.Singleton;
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
    ExerciseDTO exerciseDTO;
    ImageView imageView1, imageView2;

    public ExerciseHelp_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise_help_frag, container, false);
        wc = new WorkoutController();

        exerciseID = getArguments().getInt("chosenExerciseID", 99999);

        exerciseDTO = wc.getExercise(exerciseID);
        exerciseName = exerciseDTO.getName();
        exerciseDesc1 = exerciseDTO.getDesc1();
        exerciseDesc2 = exerciseDTO.getDesc2();
        final String imagePath1 = exerciseDTO.getImagePath1();
        final String imagePath2 = exerciseDTO.getImagePath2();

        imageView1 = (ImageView) view.findViewById(R.id.exercise_image1);
        imageView2 = (ImageView) view.findViewById(R.id.exercise_image2);


        TextView description = (TextView) view.findViewById(R.id.ex_descriptiontext);
        if (exerciseDesc1 == null) {
        description.setText(exerciseName + getString(R.string.theres_no_descr_for_own_exercises) );
        } else {
        description.setText(exerciseName + " \n " + " \n" + exerciseDesc1 + " \n " + " \n" + exerciseDesc2);
        }



        // Her anvendes baggrundstråde, implementeret med en ekstern pakke Volley.

        //ImageView 1 loades
        ImageRequest imageRequest1 = new ImageRequest(imagePath1,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView1.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.FIT_CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView1.setImageResource(R.drawable.error_advice);
            }

        });
        VolleyImageHelper.getInstance(getActivity()).addToRequestQue(imageRequest1);

        //ImageView 2 loades
        ImageRequest imageRequest2 = new ImageRequest(imagePath2,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView2.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.FIT_CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView2.setImageResource(R.drawable.error_advice);
            }

        });
        VolleyImageHelper.getInstance(getActivity()).addToRequestQue(imageRequest2);

        return view;
    }

}
