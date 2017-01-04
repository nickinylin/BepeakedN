package dk.bepeaked.bodybook.Fragments.Diet;


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
public class Recipe_frag extends Fragment {

    public Recipe_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_frag, container, false);

        TextView name = (TextView) view.findViewById(R.id.textView5);
        ImageView image = (ImageView) view.findViewById(R.id.imageView4);
        TextView description = (TextView) view.findViewById(R.id.textView6);

        name.setText(getArguments().getString("Name"));
        description.setText(getArguments().getString("Description"));

        return view;
    }

}
