package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        final String NAME = "name";
        final String MAIN_NAME = "mainName";
        final String ALSO_KNOWN_AS = "alsoKnownAs";
        final String PLACE_OF_ORIGIN= "placeOfOrigin";
        final String  DESCRIPTION = "description";
        final String IMAGE = "image";
        final String INGREDIENTS = "ingredients";


        String mainName;
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients = new ArrayList<>();
        Sandwich sandwich = null;
        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject sandwichName = sandwichObject.optJSONObject(NAME);
            mainName = sandwichName.optString(MAIN_NAME,"sandwitch_anyThing");
            JSONArray KnownAs = sandwichName.optJSONArray(ALSO_KNOWN_AS);
            for (int i = 0 ; i < KnownAs.length() ; i++){
                alsoKnownAs.add(KnownAs.getString(i));
            }
            placeOfOrigin = sandwichObject.optString(PLACE_OF_ORIGIN,"placeOfOrigin");
            description = sandwichObject.optString(DESCRIPTION,"description");
            image = sandwichObject.optString(IMAGE,"image");
            JSONArray ing = sandwichObject.optJSONArray(INGREDIENTS);
            for (int i = 0 ; i < ing.length() ; i++){
                ingredients.add(ing.getString(i));
            }
            sandwich = new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
