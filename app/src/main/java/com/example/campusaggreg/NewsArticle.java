package com.example.campusaggreg;

import java.sql.Date;

public class NewsArticle {

    private String title;
    private String description;
    private String source;
    private String URL;
    private int upload;

    public NewsArticle(String title, String description, String source,int upload,String URL) {
        this.title = title;
        this.source = source;
        this.description = description;
        this.upload = upload;
        this.URL= URL;
    }

    public String getURL() {
        return URL;
    }


    public String getTitle() {
        return this.title;
    }
    public String getSource(){
        return this.source;
    }
    public String getDescription()
    {
        return this.description;
    }

    public int getUpload(){return this.upload;}
}
