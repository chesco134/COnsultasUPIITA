/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formidesktop.database.acciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Hector Torres
 */
public class Parser {

    private JSONObject json;

    public Parser() {
        json = new JSONObject();
    }

    public Parser(String jsonString) {
        try {
            json = new JSONObject(jsonString);
        } catch (JSONException e) {
            json = null;
            e.printStackTrace();
        }
    }

    public int getAction() {
        int action = -1;
        try {
            action = json.getInt("action");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return action;
    }

    public String getString(String field) {
        String value = "";
        try {
            value = json.getString(field);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public int getInt(String field) {
        int value = -1;
        try {
            value = json.getInt(field);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public boolean getBoolean(String field) {
        boolean value = false;
        try {
            value = json.getBoolean(field);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }
    
    public String[] getStringArray(String field){
        String[] values = null;
        try{
            JSONArray jarr = json.getJSONArray(field);
            values = new String[jarr.length()];
            for(int i=0; i<jarr.length(); i++)
                values[i] = jarr.getString(i);
        }catch(JSONException e){
            e.printStackTrace();
        }
        return values;
    }

    public void addInt(String field, int value) {
        try {
            json.put(field, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addString(String field, String value) {
        try {
            json.put(field, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addBoolean(String field, boolean value) {
        try {
            json.put(field, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getSerializedMessage() {
        return json.toString();
    }
}
