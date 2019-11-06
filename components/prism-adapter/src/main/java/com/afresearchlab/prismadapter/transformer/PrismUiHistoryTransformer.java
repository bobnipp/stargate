package com.afresearchlab.prismadapter.transformer;

import com.afresearchlab.prismadaptermodels.RecordHistory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PrismUiHistoryTransformer {
    private static JsonParser parser = new JsonParser();

    public static List<RecordHistory> transform(String historyJson) {
        JsonObject object = (JsonObject) parser.parse(historyJson);
        JsonArray data = object.getAsJsonArray("data");

        return StreamSupport.stream(data.spliterator(), false).map(element -> {
            JsonObject dataObject = element.getAsJsonObject();
            return RecordHistory.builder()
                    .username(dataObject.get("modifiedBy").getAsString().split(" ")[0].toLowerCase())
                    .date(dataObject.get("modifiedOn").getAsString())
                    .action(dataObject.get("action").getAsString())
                    .build();
        }).collect(Collectors.toList());
    }
}
