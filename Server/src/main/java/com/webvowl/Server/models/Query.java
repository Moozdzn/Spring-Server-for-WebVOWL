package com.webvowl.Server.models;

public class Query {

    private String query;
    private String owlFileName;

    public Query(String query,String owlFileName){
        this.query = query;
        this.owlFileName = owlFileName;
    }
    public Query(){}

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOwlFileName() {
        return this.owlFileName;
    }

    public void setOwlFileName(String owlFileName) {
        this.owlFileName = owlFileName;
    }
}
