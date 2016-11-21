package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sebho on 14-11-2016.
 */

public class DishDTO {
    //Type: 0 = Morgenmad, 1 = Frokost, 2 = Aftensmad, 3 = Snack.
    private int type;
    private String name;
    private String imagePath;
    private ArrayList<Ingredient> ingredients;

    public DishDTO(int type, String name, String imagePath, ArrayList<String[]> ingredients) {
        this.type = type;
        this.name = name;
        this.imagePath = imagePath;
        for (int i = 0; i < ingredients.size(); i++){
            String[] ingredient = ingredients.get(i);
            String ingName = ingredient[0];
            String[] stats = ingredient[1].split(",");
            this.ingredients.add(new Ingredient(ingName, Integer.parseInt(stats[0]), Integer.parseInt(stats[1]),
                    Integer.parseInt(stats[2]), Integer.parseInt(stats[3]), Integer.parseInt(stats[4])));
        }

    }


    // Private inner class
    private class Ingredient {

        private String name;
        private int weight;
        private int protein;
        private int fat;
        private int carbohydrate;
        private int calories;

        public Ingredient(String name, int weight, int protein, int fat, int carbohydrate, int calories) {
            this.name = name;
            this.weight = weight;
            this.protein = protein;
            this.fat = fat;
            this.carbohydrate = carbohydrate;
            this.calories = calories;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getProtein() {
            return protein;
        }

        public void setProtein(int protein) {
            this.protein = protein;
        }

        public int getFat() {
            return fat;
        }

        public void setFat(int fat) {
            this.fat = fat;
        }

        public int getCarbohydrate() {
            return carbohydrate;
        }

        public void setCarbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }
    }

}
