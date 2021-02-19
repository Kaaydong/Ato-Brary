package com.example.to_brary;

import java.util.ArrayList;
import java.util.Date;

public class Image {

    private int picture;

    private boolean lewd, gore;

    private ArrayList<String> artists, copyright, characters, details, meta;

    private Date dateUploaded;
    private int imageID;



    public Image() {

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

    public ArrayList<String> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<String> artists) {
        this.artists = artists;
    }

    public ArrayList<String> getCopyright() {
        return copyright;
    }

    public void setCopyright(ArrayList<String> copyright) {
        this.copyright = copyright;
    }

    public ArrayList<String> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<String> characters) {
        this.characters = characters;
    }

    public ArrayList<String> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<String> details) {
        this.details = details;
    }

    public ArrayList<String> getMeta() {
        return meta;
    }

    public void setMeta(ArrayList<String> meta) {
        this.meta = meta;
    }

    public Date getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(Date dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getOriginalImageLink() {
        return originalImageLink;
    }

    public void setOriginalImageLink(String originalImageLink) {
        this.originalImageLink = originalImageLink;
    }

    public String getOriginalImageFile() {
        return originalImageFile;
    }

    public void setOriginalImageFile(String originalImageFile) {
        this.originalImageFile = originalImageFile;
    }

    public int getImageFileSize() {
        return imageFileSize;
    }

    public void setImageFileSize(int imageFileSize) {
        this.imageFileSize = imageFileSize;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    private String originalImageLink; // Subject to change
    private String originalImageFile; // Subject to change
    private int imageFileSize; // KB
    private String uploader;

}
