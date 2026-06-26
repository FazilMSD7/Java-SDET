package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    // DataProvider 1
    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {

        String path = System.getProperty("user.dir")+"//testData//UserTestData.xlsx"; // taking xl file from testData

        ExcelUtility xlutil = new ExcelUtility(path); // creating object for ExcelUtility

        int totalrows = xlutil.getRowCount("UserData");
        int totalcols = xlutil.getCellCount("UserData", 1);

        String apidata[][] = new String[totalrows][totalcols]; // created for two dimensional array

        for (int i = 1; i <= totalrows; i++) {  // read data from excel (skip header row)

            for (int j = 0; j < totalcols; j++) {

                apidata[i - 1][j] = xlutil.getCellData("UserData", i, j);
            }
        }

        return apidata; // returning 2D array
    }
    
    @DataProvider(name="Usernames")
    public String[] getUsernames() throws IOException{
    	String path = System.getProperty("user.dir")+"//testData//UserTestData.xlsx";

        ExcelUtility xlutil = new ExcelUtility(path);
        int rowNum = xlutil.getRowCount("UserData");
        
        String apidata[] = new String[rowNum];
        for(int i=1; i<=rowNum; i++) {
        	apidata[i-1] = xlutil.getCellData("UserData", i, 1);
        }
        return apidata;
    }
}