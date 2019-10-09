package com.example.kimberjin.kymusicplayer.bean;

/**
 * Created by ky4910 on 2019/9/30
 */
public class Music {

    private long id;
    private String title;
    private String album;
    private String albumArt;
    private String artist;
    private long duration;
    private String url;
    private String lrcLink;
    private String composer;
    private String data_added;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLrcLink() {
        return lrcLink;
    }

    public void setLrcLink(String lrcLink) {
        this.lrcLink = lrcLink;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getData_added() {
        return data_added;
    }

    public void setData_added(String data_added) {
        this.data_added = data_added;
    }

    public Music(long id, String title, String album, String albumArt, String artist,
                 long duration, String url, String lrcLink, String composer, String data_added) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.albumArt = albumArt;
        this.artist = artist;
        this.duration = duration;
        this.url = url;
        this.lrcLink = lrcLink;
        this.composer = composer;
        this.data_added = data_added;
    }
}
