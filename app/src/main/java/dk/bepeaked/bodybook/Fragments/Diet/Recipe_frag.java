package dk.bepeaked.bodybook.Fragments.Diet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DTO.DishDTO;
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
        DishDTO dish = (DishDTO) getArguments().getSerializable("Dish");

        TextView name = (TextView) view.findViewById(R.id.textView5);
        ImageView image = (ImageView) view.findViewById(R.id.imageView4);
        TextView ingredients = (TextView) view.findViewById(R.id.ingredients);
        TextView description = (TextView) view.findViewById(R.id.textView6);

        name.setText(dish.getName());
        image.setImageResource(dish.getImagePath());

        ArrayList<String> ingredientNames = new ArrayList<String>();
        String text = "The ingredients for the recipe are as follows:\n\n";
        int protein = 0, fat = 0, carbonhydrate = 0, calories = 0;
        for(int i = 0; i < dish.getIngredients().size(); i++){
            int nr = i+1;
            text = text + nr + ". " + dish.getIngredients().get(i).getName() + " - " + dish.getIngredients().get(i).getWeight() + " grams" + "\n";
            protein = protein + dish.getIngredients().get(i).getProtein();
            fat = fat + dish.getIngredients().get(i).getFat();
            carbonhydrate = carbonhydrate + dish.getIngredients().get(i).getCarbohydrate();
            calories = calories + dish.getIngredients().get(i).getCalories();
        }
        text = text + "\n\n\n\nThis recipe contains the following:\n\n" + protein + " grams of protein.\n" + fat + " grams of fat.\n" + carbonhydrate + " grams of carbonhydrates.\n" + calories + " calories.";
        ingredients.setText(text);
        description.setText(dish.getDeskLong());

        return view;
    }

}
