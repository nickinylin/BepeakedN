package dk.bepeaked.bodybook.Backend.DAO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelDAO {

    public List<String> read(String key) throws IOException {
        List<String> resultSet = new ArrayList<String>();

        File inputWorkbook = new File("Fil");
        if(inputWorkbook.exists()){
            Workbook w;
            try {
                w = Workbook.getWorkbook(inputWorkbook);
                // Get the first sheet
                Sheet sheet = w.getSheet(0);
                // Loop over column and lines
                for (int j = 0; j < sheet.getRows(); j++) {
                    Cell cell = sheet.getCell(0, j);
                    if(cell.getContents().equalsIgnoreCase(key)){
                        for (int i = 0; i < sheet.getColumns(); i++) {
                            Cell cel = sheet.getCell(i, j);
                            resultSet.add(cel.getContents());
                        }
                    }
                    continue;
                }
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            resultSet.add("File not found..!");
        }
        if(resultSet.size()==0){
            resultSet.add("Data not found..!");
        }
        return resultSet;
    }
}
