package ir.aut.ceit.app.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by shsh9692 on 7/8/2017.
 */
public class WriteToJSon {
    private JSONObject obj = new JSONObject();
    private String id;
    private String name;
    private JSONArray list;
    private File file;

    public WriteToJSon(String ip1, String id1, String name1) {
        id = id1;
        name = name1;
        obj.put("name", name1);
        obj.put("id", id1);
        obj.put("ip", ip1);
        list = new JSONArray();
        File file = new File(id1 + ".json");
    }

    public void addMes(String name, String messege, String time1) {
        JSONObject mes = new JSONObject();
        mes.put("time", time1);
        mes.put("sender", name);
        mes.put("mesg", messege);
        list.add(mes);
        obj.put("messages", list);
    }

    public void output() {
        try (FileWriter file = new FileWriter(new File("src/" + name + ".json"))) {
            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(obj);

    }
}