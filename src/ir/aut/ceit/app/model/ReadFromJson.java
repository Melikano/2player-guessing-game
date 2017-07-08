package ir.aut.ceit.app.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by shsh9692 on 7/9/2017.
 */
public class ReadFromJson {
    public String readJSon(String filename) {
        String history = "";
        JSONParser parser = new JSONParser();
        String newLine = System.getProperty("line.separator");//This will retrieve line separator dependent on OS.


        try {

            Object obj = parser.parse(new FileReader(filename));

            JSONObject jsonObject = (JSONObject) obj;
            //System.out.println(jsonObject);

            String ip = (String) jsonObject.get("ip");
            System.out.println(ip);
            history = history + newLine + ip;

            String name = (String) jsonObject.get("name");
            System.out.println(name);
            history = history + newLine + name;

            long id = (Long) jsonObject.get("id");
            System.out.println(id);
            history = history + newLine + id;

            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("messages");
            Iterator<JSONObject> iterator = msg.iterator();
            while (iterator.hasNext()) {
                JSONObject j = iterator.next();
                String sender = (String) j.get("sender");
                System.out.println(sender);
                history = history + newLine + sender;
                String imesg = (String) j.get("mesg");
                System.out.println(imesg);
                history = history + newLine + imesg;
                String time = (String) j.get("time");
                System.out.println(time);
                history = history + newLine + time;
                return history;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}



