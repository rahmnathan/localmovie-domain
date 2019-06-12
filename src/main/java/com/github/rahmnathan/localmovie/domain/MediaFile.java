package com.github.rahmnathan.localmovie.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.rahmnathan.omdb.data.Media;

import javax.persistence.*;
import java.util.Calendar;

@Entity(name = "media")
public class MediaFile {

    @Id
    private String path;
    private String fileName;
    private long created;
    private int views;
    private Media media;
    @Version
    @JsonIgnore
    private long version;

    private MediaFile(String path, Media media, int views, String fileName) {
        this.path = path;
        this.media = media;
        this.fileName = fileName;
        this.views = views;
    }

    public MediaFile(){
        // Default constructor
    }

    @PrePersist
    public void setTimeStamp(){
        created = Calendar.getInstance().getTimeInMillis();
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }

    public void addView(){
        views++;
    }

    public int getViews() {
        return views;
    }

    public long getCreated() {
        return created;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media){
        this.media = media;
    }

    @Override
    public String toString(){
        if(media != null) {
            return media.getTitle();
        }

        return fileName;
    }

    public static class Builder {
        private String fileName;
        private String path;
        private int views;
        private Media media;

        public static Builder newInstance(){
            return new Builder();
        }

        public Builder setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder setMedia(Media media) {
            this.media = media;
            return this;
        }

        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public Builder setViews(int views) {
            this.views = views;
            return this;
        }

        public MediaFile build(){
            return new MediaFile(path, media, views, fileName);
        }

        public static MediaFile copyWithNewTitle(MediaFile mediaFile, String fileName, String title, String path, Integer number){
            if(mediaFile == null)
                return Builder.newInstance()
                        .setFileName(fileName)
                        .setPath(path)
                        .setMedia(Media.Builder.newInstance().setNumber(number).setTitle(title).build())
                        .build();

            return Builder.newInstance()
                    .setFileName(fileName)
                    .setMedia(Media.Builder.copyWithNewTitle(mediaFile.getMedia(), title, number))
                    .setPath(path)
                    .build();
        }

        public static MediaFile copyWithNoImage(MediaFile mediaFile){
            if(mediaFile == null)
                return Builder.newInstance().build();

            return Builder.newInstance()
                    .setFileName(mediaFile.getFileName())
                    .setMedia(Media.Builder.copyWithNoImage(mediaFile.getMedia()))
                    .setViews(mediaFile.getViews())
                    .setPath(mediaFile.getPath())
                    .build();
        }
    }
}