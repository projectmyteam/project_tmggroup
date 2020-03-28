package com.otc.landmark.web.dto;

import java.io.Serializable;

public class JsonFileUpload implements Serializable {

    private String path;

    public JsonFileUpload(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
