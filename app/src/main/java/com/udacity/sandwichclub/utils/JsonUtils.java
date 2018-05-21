package com.udacity.sandwichclub.utils;

import android.support.annotation.NonNull;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwichData;

        try {
            JSONObject reader = new JSONObject(json);

            sandwichData = new Sandwich();

            sandwichData.setMainName(reader.getJSONObject(Sandwich.NAME_KEY).getString(Sandwich.MAIN_NAME_KEY));

            JSONArray akas = reader.getJSONObject(Sandwich.NAME_KEY).getJSONArray(Sandwich.ALSO_KNOWN_AS_KEY);
            sandwichData.setAlsoKnownAs(jsonArrayToList(akas));

            sandwichData.setPlaceOfOrigin(reader.getString(Sandwich.PLACE_OF_ORIGIN_KEY));

            sandwichData.setDescription(reader.getString(Sandwich.DESCRIPTION_KEY));

            sandwichData.setImage(reader.getString(Sandwich.IMAGE_KEY));

            JSONArray ingredients = reader.getJSONArray(Sandwich.INGREDIENTS_KEY);
            sandwichData.setIngredients(jsonArrayToList(ingredients));
        }
        catch (Exception ex) {
            sandwichData =  null;
        }

        return sandwichData;
    }

    private static List<String> jsonArrayToList(JSONArray array) {
        List newList = new ArrayList();

        try {
            int length = array.length();

            for (int i = 0; i < length; i++) {
                newList.add(array.get(i).toString());
            }
        }
        catch (Exception e) {
            newList.clear();
        }

        return newList;
    }
}
