package org.triple_brain.module.model.json;

/*
* Copyright Mozilla Public License 1.1
*/

import com.google.gson.Gson;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.triple_brain.module.model.Image;

import java.util.Set;

public class ImageJson {

    private static Gson gson = new Gson();

    public static JSONArray toJsonArray(Set<Image> images) {
        try {
            return new JSONArray(
                    gson.toJson(
                            images
                    )
            );
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
