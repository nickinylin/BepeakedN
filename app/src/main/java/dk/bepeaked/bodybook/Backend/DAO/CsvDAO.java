package dk.bepeaked.bodybook.Backend.DAO;

import android.content.Context;
import android.content.res.AssetManager;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CsvDAO{

    public final ArrayList<String[]> readCsv(Context context, String file) {
        ArrayList<String[]> questionList = new ArrayList<String[]>();
        AssetManager assetManager = context.getAssets();

        try {
            InputStream csvStream = assetManager.open(file);
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader csvReader = new CSVReader(csvStreamReader);
            String[] line;

            csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                questionList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionList;
    }
}