package com.example.to_brary;

import org.json.JSONObject;

import java.util.Date;

public class Image {

    private int picture;

    private boolean lewd, gore;

    private String artists;



    private String copyright;
    private String characters;
    private String details;
    private String meta;

    private Date created; // (Date Uploaded)
    private int imageId;

    private String imageFile;
    private String originalImageSource;


    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public boolean isLewd() {
        return lewd;
    }

    public void setLewd(boolean lewd) {
        this.lewd = lewd;
    }

    public boolean isGore() {
        return gore;
    }

    public void setGore(boolean gore) {
        this.gore = gore;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getOriginalImageSource() {
        return originalImageSource;
    }

    public void setOriginalImageSource(String originalImageSource) {
        this.originalImageSource = originalImageSource;
    }
}
