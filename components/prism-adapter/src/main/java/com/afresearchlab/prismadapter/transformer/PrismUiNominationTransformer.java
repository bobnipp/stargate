package com.afresearchlab.prismadapter.transformer;

import com.afresearchlab.prismadapter.model.PrismUiNomination;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PrismUiNominationTransformer {
    private static JsonParser parser = new JsonParser();

    public static List<PrismUiNomination> transform(String prismResponseBody) {
        JsonObject object = (JsonObject) parser.parse(prismResponseBody);
        JsonArray data = object.getAsJsonArray("data");
        return StreamSupport.stream(data.spliterator(), false).map(element -> {
            JsonObject dataObject = element.getAsJsonObject();
            JsonObject nestedDataObject = (JsonObject) dataObject.get("data");
            return PrismUiNomination.builder()
                .key(nestedDataObject.get("recordId").getAsString())
                .name(nestedDataObject.get("recordName").getAsString())
                .build();
        }).collect(Collectors.toList());
    }
}
