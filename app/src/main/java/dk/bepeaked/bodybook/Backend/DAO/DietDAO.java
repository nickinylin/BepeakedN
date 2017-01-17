package dk.bepeaked.bodybook.Backend.DAO;

import android.app.Activity;
import android.content.res.Resources;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DTO.DishDTO;
import dk.bepeaked.bodybook.Backend.DTO.Ingredient;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class DietDAO{
    CsvDAO dao = new CsvDAO();
    private ArrayList<DishDTO> savedDTO = new ArrayList<>();
    Realm realm = Realm.getDefaultInstance();
    Singleton singleton = Singleton.singleton;

    public void getDishes(Activity act, String file){
        ArrayList<DishDTO> dtos = new ArrayList<>();
        ArrayList<String[]> data = dao.readCsv(act, file);
        ArrayList<String[]> cleanData = new ArrayList<>();
        ArrayList<ArrayList<String>> cleanData2 = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            cleanData.add(data.get(i));
        }
        for(int i = 0; i < cleanData.size(); i++){
            ArrayList<String> string = new ArrayList<>();
            for(int j = 0; j < cleanData.get(i).length; j++){
                if (cleanData.get(i)[j].equals("") || cleanData.get(i)[j].equals(" ") || cleanData.get(i)[j].equals(null)) {
                }else{
                    string.add(cleanData.get(i)[j]);
                }
            }
            if(string.size() > 0){
                cleanData2.add(string);
            }
        }
        for(int i = 0; i < cleanData2.size(); i++){
            DishDTO dto = new DishDTO();
            int indexStart;
            int indexEnd = 0;
            String[] nameSplit;
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            String name;
            if(cleanData2.get(i).get(0).contains("Morgenmad") || cleanData2.get(i).get(0).contains("Frokost")
                    || cleanData2.get(i).get(0).contains("Aftensmad") || cleanData2.get(i).get(0).contains("Snack")){
                indexStart = i;
                nameSplit = cleanData2.get(i).get(0).split(":");
                name = nameSplit[1];
                for(int j = i; j < cleanData2.size(); j++){
                    if(cleanData2.get(j).get(0).contains("Total")){
                        indexEnd = j;
                        break;
                    }
                }
                if(cleanData2.get(i).get(0).contains("Morgenmad")){
                    dto.setType(0);
                }else if(cleanData2.get(i).get(0).contains("Frokost")){
                    dto.setType(1);
                }else if(cleanData2.get(i).get(0).contains("Aftensmad")){
                    dto.setType(2);
                }else if(cleanData2.get(i).get(0).contains("Snack")){
                    dto.setType(3);
                }

                for(int k = indexStart+2; k < indexEnd; k++){
                    String inName = cleanData2.get(k).get(0);
                    int weight = Integer.parseInt(cleanData2.get(k).get(1));
                    int protein = Integer.parseInt(cleanData2.get(k).get(2));
                    int fat = Integer.parseInt(cleanData2.get(k).get(3));
                    int carbon = Integer.parseInt(cleanData2.get(k).get(4));
                    int cal = Integer.parseInt(cleanData2.get(k).get(5));
                    ingredients.add(new Ingredient(inName, weight, protein, fat, carbon, cal));
                }
                dto.setName(name);
                dto.setIngredients(ingredients);
                dtos.add(dto);
            }
        }
        savedDTO = dtos;
        try {
            pushToRealm();
        } catch (ExceptionNameAlreadyExist exceptionNameAlreadyExist) {
            exceptionNameAlreadyExist.printStackTrace();
        }
    }
    private void pushToRealm()throws ExceptionNameAlreadyExist {
        RealmList<DishDTO> dishes = getDishesFromRealms();
        for(int i = 0; i < savedDTO.size(); i++) {
            for(int j = 0; j < dishes.size(); j++){
                if(dishes.get(j).getName().equals(savedDTO.get(i).getName())){
                    throw new ExceptionNameAlreadyExist(singleton.getString(R.string.plan_by_the_name) + savedDTO.get(i).getName() + singleton.getString(R.string.already_exists));
                }
            }
            realm.beginTransaction();
            DishDTO realmPlan = realm.copyToRealm(savedDTO.get(i));
            realm.commitTransaction();
        }
    }
    public RealmList<DishDTO> getDishesFromRealms()throws IndexOutOfBoundsException{
        RealmResults<DishDTO> resultPlans = realm.where(DishDTO.class).findAll();
        RealmList<DishDTO> dishes = new RealmList<DishDTO>();
        dishes.addAll(resultPlans.subList(0, resultPlans.size()));
        return dishes;
    }
    public DishDTO getDishFromRealms(String name)throws IndexOutOfBoundsException{
        RealmResults<DishDTO> resultPlans = realm.where(DishDTO.class).findAll();
        DishDTO dto = new DishDTO();
        for(int i = 0; i < resultPlans.size(); i++){
            if (resultPlans.get(i).getName().equals(name)){
                dto = resultPlans.get(i);
            }
        }
        return dto;
    }
}