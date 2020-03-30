package com.kty.first;

import android.net.Uri;

import java.io.Serializable;

public class NewsData implements Serializable {


    private String description;
    private String title;
    private String urlToImage;
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "NewsData{" +
                "description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
