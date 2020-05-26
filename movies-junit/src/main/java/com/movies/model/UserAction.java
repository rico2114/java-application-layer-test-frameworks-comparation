package com.movies.model;

public class UserAction {

    private final String activity;
    private final String response;
    private final String data;

    public UserAction(final String activity, final String response, final String data) {
        this.activity = activity;
        this.response = response;
        this.data = data;
    }

    public String getActivity() {
        return activity;
    }

    public String getResponse() {
        return response;
    }

    public String getData() {
        return data;
    }
}
