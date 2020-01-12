package com.miftakhularzak.moviecatalogue.utils;

import org.json.JSONArray;
import org.json.JSONException;

public class Utils {
    public static String getListFromJSONArray(JSONArray jsonArray, String key) {
        String list = "";
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                list = list  + jsonArray.getJSONObject(i).getString(key);
                list = (i < jsonArray.length() - 1) ? list  +  ", " : list;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
