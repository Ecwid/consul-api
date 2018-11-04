package com.ecwid.consul.v1;

import com.ecwid.consul.UrlParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NodeMetaParameters implements UrlParameters {

    private final Map<String, String> nodeMeta;

    public NodeMetaParameters(Map<String, String> nodeMeta) {
        this.nodeMeta = nodeMeta;
    }

    @Override
    public List<String> toUrlParameters() {
        List<String> params = new ArrayList<>();

        if (nodeMeta != null) {
            String key = "node-meta";

            for (Map.Entry<String, String> entry : nodeMeta.entrySet()) {
                String value = entry.getKey() + ":" + entry.getValue();
                params.add(key + "=" + value);
            }
        }

        return params;
    }
}
