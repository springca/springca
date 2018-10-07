package com.wxmlabs.springca.core;

import java.util.HashMap;
import java.util.Map;

public class IssuerManager {
    private final Map<String, Issuer> issuerMap = new HashMap<>();

    public Issuer getIssuer(String name) {
        return issuerMap.get(name);
    }

    public void register(String name, Issuer issuer) {
        issuerMap.put(name, issuer);
    }
}
