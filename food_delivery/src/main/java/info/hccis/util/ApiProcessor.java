package info.hccis.util;

import info.hccis.util.UtilityRest;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

/**
 *
 * @author bjmaclean
 * @since Nov 4, 2021
 */
public class ApiProcessor {

    public static final String URL = "https://datausa.io/api/data?drilldowns=Nation&measures=Population";
    public static final String URL2 = "https://api.funtranslations.com/translate/pirate.json?text=";

    public static String callApi2(String sentence) {

        
        //https://stackoverflow.com/questions/10786042/java-url-encoding-of-query-string-parameters
        String json;
        String translated="";
        try {
            
            String encoded = URLEncoder.encode(sentence);
            json = UtilityRest.getJsonFromRest(URL2+encoded);
            
            System.out.println("json returned=" + json);
            JSONObject jsonObjectOuter = new JSONObject(json);
            JSONObject jsonObjectContents = jsonObjectOuter.getJSONObject("contents");
            translated = jsonObjectContents.getString("translated");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return translated;

    }

        public static int callApi(String yearEntered) {

        int population=0;
        
        //https://stackoverflow.com/questions/10786042/java-url-encoding-of-query-string-parameters
        String json;
        try {
            json = UtilityRest.getJsonFromRest(URL);
            //System.out.println("json returned=" + json);
            JSONObject jsonObjectOuter = new JSONObject(json);
            JSONArray jsonArray = jsonObjectOuter.getJSONArray("data");

//            JSONArray jsonArraySource = jsonObjectOuter.getJSONArray("source");

            
            
            boolean found = false;

            for (int currentIndex = 0; currentIndex < jsonArray.length(); currentIndex++) {

                JSONObject jsonObjectForAYear = jsonArray.getJSONObject(currentIndex);
                String yearFound = jsonObjectForAYear.getString("Year");
                if (yearFound.equals(yearEntered)) {
                    System.out.println("Found the year");
                     population = jsonObjectForAYear.getInt("Population");
                    found = true;
                    break;
                }

            }
            if (!found) {
                System.out.println("Could not find a population for that year");
                population = -1;
            }
        } catch (IOException ex) {
            Logger.getLogger(ApiProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return population;

    }

}
