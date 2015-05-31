package tProps;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 31/05/2015.
 */
public class TwitterSearchTerms {

    private List<String> terms = new ArrayList<>();
    private String termsFile = "search.txt";

    public List<String> getSearchTerms(){

        System.out.println("Finding search terms from search.txt...");
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(termsFile);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line = reader.readLine();

            while(!StringUtils.isEmpty(line)){
                System.out.println("Will add term: " + line);
                terms.add(line);

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return terms;
    }

}
