package com;


import org.codehaus.jackson.annotate.JsonProperty;

public class Data {

    @JsonProperty("User")
    private String user;

    @JsonProperty("Text")
    private String text;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
