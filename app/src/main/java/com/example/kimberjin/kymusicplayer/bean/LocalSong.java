package com.example.kimberjin.kymusicplayer.bean;

/**
 * Created by ky4910 on 2019/9/30
 */
public class LocalSong {

    private String title;
    private String album;
    private String artist;
    private long duration;
    private String composer;
    private String data_added;

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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public LocalSong() {
    }

    public LocalSong(String title, String album, String artist, long duration, String composer, String data_added) {
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.duration = duration;
        this.composer = composer;
        this.data_added = data_added;
    }
}
