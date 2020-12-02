package com.webvowl.Server.models;

public class Query {

    //private Integer id;
    private String query;

    public Query(){}

    //Integer id,
    public Query( String query){
        //this.id = id;
        this.query = query;
    }

    //public Integer getId() { return id; }

    //public void setId(Integer id) { this.id = id; }

    public String getQuery() {
        return query;
    }

    public void setQuery(String name) {
        this.query = query;
    }
}
