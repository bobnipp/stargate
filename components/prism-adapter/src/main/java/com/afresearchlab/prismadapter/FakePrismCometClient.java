package com.afresearchlab.prismadapter;

public class FakePrismCometClient implements PrismCometClient {
    @Override
    public String getNomList(String sessionId) {
        return "{\"data\": [ { \"disabled\": false, \"savable\": true, \"leaf\": true, \"editable\": true, \"expanded\": false, \"iconCls\": \"prism-nom-icon\", \"clickable\": true, \"selfLoad\": true, \"data\": { \"recordId\": \"110000000000001PR8AWS\", \"recordName\": \"1000\", \"prismState\": \"A\", \"type\": \"NOM\", \"prismStatus\": \"\" }, \"text\": \"1000(1000)\", \"id\": \"0-110000000000001PR8AWS\" }, { \"disabled\": false, \"savable\": true, \"leaf\": true, \"editable\": true, \"expanded\": false, \"iconCls\": \"prism-nom-icon\", \"clickable\": true, \"selfLoad\": true, \"data\": { \"recordId\": \"000000000000001PR7AWS\", \"recordName\": \"1234\", \"prismState\": \"A\", \"type\": \"NOM\", \"prismStatus\": \"\" }, \"text\": \"1234(1234)\", \"id\": \"0-000000000000001PR7AWS\" } ], \"success\": true }";
    }
}
