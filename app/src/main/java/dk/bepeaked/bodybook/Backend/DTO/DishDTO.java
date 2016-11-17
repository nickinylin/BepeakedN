package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;

/**
 * Created by sebho on 14-11-2016.
 */

public class DishDTO {
    private int ID;
    private String name;
    private ArrayList<Ingredient> ingredients;

    public DishDTO(int ID, String name, ArrayList<Ingredient> ingredients) {
        this.ID = ID;
        this.name = name;
        this.ingredients = ingredients;
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
