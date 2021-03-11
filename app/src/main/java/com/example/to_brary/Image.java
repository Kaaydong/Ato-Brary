package com.example.to_brary;

import org.json.JSONObject;

import java.util.Date;

public class Image {

    private int picture;

    private boolean lewd, gore;

    private JSONObject artists, copyright, characters, details, meta;

    private Date created; // (Date Uploaded)
    private int imageId;

    private String imageFile;
    private String originalImageSource;


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

    public JSONObject getArtists() {
        return artists;
    }

    public void setArtists(JSONObject artists) {
        this.artists = artists;
    }

    public JSONObject getCopyright() {
        return copyright;
    }

    public void setCopyright(JSONObject copyright) {
        this.copyright = copyright;
    }

    public JSONObject getCharacters() {
        return characters;
    }

    public void setCharacters(JSONObject characters) {
        this.characters = characters;
    }

    public JSONObject getDetails() {
        return details;
    }

    public void setDetails(JSONObject details) {
        this.details = details;
    }

    public JSONObject getMeta() {
        return meta;
    }

    public void setMeta(JSONObject meta) {
        this.meta = meta;
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
